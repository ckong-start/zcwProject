package com.scw.user.controller;

import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scw.common.bean.AppResponse;
import com.scw.user.bean.UserTest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@Api(tags="swagger文档的测试")
public class TestRedisController {

	//如果项目中已经引入redis的场景启动器+ 配置文件中配置了redishost参数
	//自动装配redis框架提供的： RedisTemplate / StringRedisTemplate[主要使用]	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate; //一般不用
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@GetMapping("/testRedis1")
	public AppResponse<String> testRedis1() {
		Object key1 = redisTemplate.opsForValue().get("key1");
		log.info("获取key1的值是：{}", key1);
		redisTemplate.opsForValue().set("key1", "val1");
		return AppResponse.ok("ok");
	}
	
	@GetMapping("/testRedis2")
	public AppResponse<String> testRedis2() {
		//1.向redis中插入数据
		stringRedisTemplate.opsForValue().set("key2", "haha", 10, TimeUnit.MINUTES);
		//2.获取key2的过期时间
		Long expire = stringRedisTemplate.getExpire("key2", TimeUnit.SECONDS);
		log.warn("key2的过期时间为: {} 秒", expire);
		//3.获取key2的值
		String val2 = stringRedisTemplate.opsForValue().get("key2");
		log.error("key2的值为: {} ", val2);
		return AppResponse.ok("ok");
	}
	
	@ApiOperation(value="跟说hello")
	@ApiImplicitParams(value= {
			@ApiImplicitParam(name="id", value="主键", required=true, defaultValue="1001", dataType="int"),
			@ApiImplicitParam(name="username", value="用户名", required=true, dataType="String")
	})
	@GetMapping("/hello")
	public AppResponse<String> test(Integer id, String username) {
		log.info("输入的id是：", id);
		log.info("输入的username是：", username);
		return AppResponse.ok("hello");
	}
	
	@ApiOperation("获取用户信息")
	@ApiImplicitParams(value= {
			@ApiImplicitParam(name="id", value="主键", required=true, defaultValue="1001", dataType="int")
	})
	@GetMapping("/getUser")
	public AppResponse<UserTest> getUser(Integer id) {
		return AppResponse.ok(new UserTest(id, "zhangsan", "123456", "zs@163.com"));
	}
}
