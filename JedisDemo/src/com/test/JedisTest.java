package com.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class JedisTest {
	Jedis jedis = new Jedis("192.168.146.3", 6379);

	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("192.168.146.3", 6379);
		// 查看服务是否运行，打出pong表示OK
		String ping = jedis.ping();
		System.out.println("connection ===>>> " + ping);
		jedis.close();
	}

	@Test
	public void testKey() throws Exception {

		Set<String> keys = jedis.keys("*");
		for (String string : keys) {
			System.out.println(string);
		}
		System.out.println("jedis.exists===>>> " + jedis.exists("topN"));
		System.out.println("jedis.ttl(topN)===>>> " + jedis.ttl("topN"));
		jedis.close();
	}

	@Test
	public void testString() throws Exception {
		//添加键值对
		jedis.set("k1", "value1");
		System.out.println("v1的值是：" + jedis.get("k1"));
		//同时设置多个键值对
		jedis.mset("k2", "value2", "k3", "value3", "k4", "value4");
		//同时获取多个value
		System.out.println(jedis.mget("k2", "k3", "k4"));
		jedis.close();
	}
	@Test
	public void testList() throws Exception {
		jedis.lpush("lk1", "v1", "v2", "v3", "v4", "v5", "v6", "v7");
		System.out.println("lk1 lpop的值为：" + jedis.lpop("lk1"));
		System.out.println("lk1 rpop的值为：" + jedis.rpop("lk1"));
		List<String> list = jedis.lrange("lk1", 0, -1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("lk1 lrange的第" + i + "个值为：" + list.get(i));
		}
		System.out.println("lk1取出值后的长度：" + jedis.llen("lk1"));
		jedis.close();
	}
	@Test
	public void testSet() throws Exception {
		jedis.sadd("orders", "ts001");
		jedis.sadd("orders", "ts002");
		jedis.sadd("orders", "ts003");
		jedis.sadd("orders", "ts004");
		Set<String> smembers = jedis.smembers("orders");
	     for (Iterator<String> iterator = smembers.iterator(); iterator.hasNext();) {
	         String string = iterator.next();
	         System.out.println(string);
	       }
		jedis.close();
	}
	@Test
	public void testHash() throws Exception {
		jedis.hset("hash1", "username", "lisi");
		System.out.println("hash1的值为：" + jedis.hget("hash1", "username"));//lisi
		Map<String, String> map = new HashMap<String, String>();
	     map.put("telphone","13810169999");
	     map.put("address","atguigu");
	     map.put("email","abc@163.com");
	     jedis.hmset("hash2", map);
	     List<String> result = jedis.hmget("hash2", "email");
	     for (String element : result) {
			System.out.println(element);//abc@163.com
		}
		jedis.close();
	}
	@Test
	public void testZset() throws Exception {
		jedis.zadd("topN", 2500, "红楼梦");
		jedis.zadd("topN", 3000, "西游记");
		jedis.zadd("topN", 3500, "水浒传");
		jedis.zadd("topN", 4000, "三国演义");
		Set<String> result1 = jedis.zrange("topN", 0, -1);
		for (String str : result1) {
			System.out.println(str);
		}
		System.out.println("top前三的排名：");
		Set<String> result2 = jedis.zrevrange("topN", 0, 2);
		for (Iterator<String> iterator = result2.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}
	@Test
	public void testCluster() throws Exception {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.146.3", 6379));
		nodes.add(new HostAndPort("192.168.146.3", 6380));
		nodes.add(new HostAndPort("192.168.146.3", 6381));
		//JedisCluster(HostAndPort node)
		JedisCluster jedisCluster = new JedisCluster(nodes);
		String k1 = jedisCluster.get("k1");
		System.out.println("k1的值为：" + k1);
		Long ttl = jedisCluster.ttl("k1");
		System.out.println("k1的过期时间" + ttl);
		jedisCluster.close();
	}
}
