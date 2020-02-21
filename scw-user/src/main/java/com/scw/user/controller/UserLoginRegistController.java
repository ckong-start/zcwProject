package com.scw.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mysql.cj.Session;
import com.scw.common.bean.AppResponse;
import com.scw.common.utils.ScwAppUtils;
import com.scw.common.vo.BaseVo;
import com.scw.user.bean.TMember;
import com.scw.user.service.UserService;
import com.scw.user.utils.SmsTemplate;
import com.scw.user.vo.UserRegistVO;
import com.scw.user.vo.UserRespVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "用户登录注册模块")
@RestController
@Slf4j
public class UserLoginRegistController {

	@ApiOperation(value = "用户登录")
	@PostMapping("/user/doLogin")
	public AppResponse<Object> doLogin(String loginacct, String userpswd) {
		// 1.调用业务层查询登录用户对象 UserRegistService提供根据用户账号和密码查询用户返回用户的方法
		TMember member = null;
		try {
			member = userService.doLogin(loginacct, userpswd);
		} catch (Exception e) {
			return AppResponse.fail("10011", e.getMessage());
		}
		if (member == null) {
			// 登录失败
			return AppResponse.fail("10010", "账号或者密码错误");
		}
		// 2.将返回的用户对象转为UserRespVO对象 手动生成token，使用uuid
		// 同上使用copy方法将查询到的用户的属性拷贝到UserRespVO对象中
		UserRespVO userRespVO = new UserRespVO();
		BeanUtils.copyProperties(member, userRespVO);
		//手动生成accessToken
		String uuid = UUID.randomUUID().toString().replace("-", "");
		userRespVO.setAccessToken(uuid);
		// 3.将生成的accessToken 和查询到的用户对象存到redis中：保持登录状态
		// 将token拼接字符串login：++：token作为key，把查询到的member对象转为json存入redis中
		String memberKey = "login:" + uuid + ":token";
		srt.opsForValue().set(memberKey, new Gson().toJson(member), 1, TimeUnit.DAYS);
		
		// 4.响应UserRespVO
		return AppResponse.ok(userRespVO);
	}

	@Autowired
	UserService userService;

	@ApiOperation(value = "用户注册")
	@PostMapping("/user/regist")
	public AppResponse<Object> doRegist(UserRegistVO urvo) {
		// 1.检验验证码是否正确 1.1获取redis储存的验证码 1.2比较请求参数中的验证码和redis中获取的是否一致
		String codeKey = "code:" + urvo.getLoginacct() + ":code";
		String redisCode = srt.opsForValue().get(codeKey);
		String clientCode = urvo.getCode();
		if (StringUtils.isEmpty(redisCode)) {
			// redis中的验证码已经过期了
			return AppResponse.fail("10006", "验证码失效");
		}
		if (!redisCode.equalsIgnoreCase(clientCode)) {
			// 前端提交的验证码错误
			return AppResponse.fail("10007", "验证码错误");
		}
		// 2.调用业务层处理注册的业务 自动装配UserRegistService类，调用注册方法，并tryCatch，有异常就返回注册失败的结果
		try {
			userService.doRegist(urvo);
		} catch (Exception e) {
			return AppResponse.fail("10008", e.getMessage());
		}
		// 3.删除之前的未过期的注册验证码
		srt.delete(codeKey);
		// 4.给注册后的响应
		return AppResponse.ok("注册成功");
	}

	@ApiOperation(value = "重置密码")
	@PostMapping("/user/reset")
	public String reset() {

		return null;
	}

	@Autowired
	SmsTemplate smsTemplate;
	@Autowired
	StringRedisTemplate srt;

	@ApiOperation(value = "发送短信验证码")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phoneNum", value = "手机号码", required = true, defaultValue = "18062978711") })
	@PostMapping("/user/sendsms")
	public AppResponse<Object> sendSms(String phoneNum) {
		log.info("收到的手机号码:{}", phoneNum);
		// 1.1验证手机号码格式
		boolean isMobilePhone = ScwAppUtils.isMobilePhone(phoneNum);
		if (!isMobilePhone) {
			return AppResponse.fail("10001", "手机号码格式错误");
		}
		// 带时效性的不需要持久化的数据 一般都使用redis保存
		// 1.2判断手机号码获取验证码次数是否超过范围24小时内3次
		// 在redis中该手机号码可能保存多条对应的记录，每一种数据都需要设计一个唯一的key
		// 拼接保存手机号码获取验证码次数的唯一的key
		String codeCountKey = "code:" + phoneNum + ":count";
		String codeCount = srt.opsForValue().get(codeCountKey);
		Integer count = 0;
		if (!StringUtils.isEmpty(codeCount)) {
			// 该手机号码不是第一次获取验证码
			count = Integer.parseInt(codeCount);
			if (count >= 3) {
				// 获取验证码次数超过3次
				return AppResponse.fail("10002", "该手机号码今天获取验证码次数不足");
			}
		}
		// count=0代表第一次获取，!=0代表第N次获取验证码
		// 1.3判断手机号码是否有未过期的验证码
		String codeKey = "code:" + phoneNum + ":code";
		// 判断是否还存在未失效的验证码
		boolean flag = srt.hasKey(codeKey);
		if (flag) {
			// 手机号码验证码还未失效
			return AppResponse.fail("10003", "该手机号码获取验证码过于频繁");
		}
		// 1.4生成6为验证码---String code = UUID.randomUUID().toString().replace("-",
		// "").substring(0, 6);
		String code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
		// 1.5调用smsTemplate的方法发送短信
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", phoneNum);
		querys.put("param", "code:" + code);
		querys.put("tpl_id", "TP1711063");
		flag = smsTemplate.sendMessage(querys);
		if (!flag) {
			return AppResponse.fail("10004", "手机获取验证码失败");
		}
		// 1.6如果成功将手机号码和验证码保存在服务器中并设置过期时间 10分钟
		srt.opsForValue().set(codeKey, code, 1, TimeUnit.MINUTES);
		// 1.7更新该手机号码获取验证码的次数 24小时内有效
		// incr在键原有的基础上+1 ， decr在原有基础上自减1
		if (count == 0) {
			// 第一次获取验证码手动保存次数
			srt.opsForValue().set(codeCountKey, "1", 24, TimeUnit.HOURS);
		} else {
			srt.opsForValue().increment(codeCountKey);
		}
		// 1.8给访问者响应结果
		return AppResponse.ok("短信验证码发送成功");
	}

	@ApiOperation(value = "验证短信验证码")
	@PostMapping("/user/valide")
	public String valide() {

		return null;
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session, BaseVo vo) {
		String accessToken = vo.getAccessToken();
		String memberKey = "login:" + accessToken + ":token";
		srt.delete(memberKey);
		session.invalidate();
		return "redirect:/";
	}
}
