package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature.Responses;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class SecKillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String luaStr = "local userid=KEYS[1];\r\n" + 
			"local prodid=KEYS[2];\r\n" + 
			"local qtkey='sk:'..prodid..\":qt\";\r\n" + 
			"local usersKey='sk:'..prodid..\":usr\";\r\n" + 
			"local userExists=redis.call(\"sismember\",usersKey,userid);\r\n" + 
			"if tonumber(userExists)==1 then \r\n" + 
			"   return 2;\r\n" + 
			"end\r\n" + 
			"local num= redis.call(\"get\" ,qtkey);\r\n" + 
			"if tonumber(num)<=0 then \r\n" + 
			"   return 0;\r\n" + 
			"else \r\n" + 
			"   redis.call(\"decr\",qtkey);\r\n" + 
			"   redis.call(\"sadd\",usersKey,userid);\r\n" + 
			"end\r\n" + 
			"return 1" ; 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//通过连接池获取连接
		Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
		
		String userid = new Random().nextInt(50000) + "";
		String prodid = request.getParameter("prodid");
/*		//使用LUA脚本解决资源争夺问题：  库存为负数+库存不足+公平竞争问题
		String sha1 = jedis.scriptLoad(luaStr);
		Object evalsha = jedis.evalsha(sha1, 2, userid,prodid);
		//返回的结果会自动被jedis转换为Long类型
		
		Long  result = (Long)evalsha;
		
		//System.out.println(result);
		if(result==1) {
			System.err.println(userid + "秒杀成功");
		}else if(result==2) {
			System.err.println("重复秒杀");
			
		}else if(result==0) {
			System.err.println("库存不足");
			
		}*/
		//拼接redis商品库存的key
		String prodKey = "sk:" + prodid + ":qt";
		//拼接redis商品秒杀用户列表的key
		String usersKey = "sk:" + prodid + ":usr";
		//判断用户是否已经秒杀
		Boolean flag = jedis.sismember(usersKey, userid);
		if (flag) {
			//集合中存在该用户
			response.getWriter().print("请勿重复秒杀");
			System.err.println(userid + "用户重复秒杀");
			jedis.close();
			return;
		}
		//获取商品id在redis中的库存
		//在获取库存之前给库存添加乐观锁
		jedis.watch(prodKey);
		String qtStr = jedis.get(prodKey);
		int count;
		//判断库存是否足够
		if(qtStr == null) {
			//库存还未初始化，秒杀尚未开始
			response.getWriter().print("秒杀还未开始，请稍后");
			System.err.println(userid + "秒杀还未开始");
			jedis.close();
			return;
		}else {
			count = Integer.parseInt(qtStr);
			System.err.println("获取到的库存为：" + count);
		}
		if (count <= 0) {
			response.getWriter().print("库存不足~~~");
			System.err.println(userid + "库存不足");
			return;
		}
		//redis的每个更新命令都是原子性的执行后会导致watch失效，以下的多个redis命令应该组队执行
		Transaction multi = jedis.multi();
		//以上是验证步骤，以下是秒杀请求处理
		//库存-1
		multi.decr(prodKey);
		//将用户id存入到redis秒杀用户列表中
		multi.sadd(usersKey, userid);
		//执行jedis事务，事务执行后，watch会自动取消
		multi.exec();
		System.err.println(userid + "秒杀成功，库存数为：" + (count - 1));
		//根据结果给用户相应
		response.getWriter().print("秒杀成功~~");
	}

}
