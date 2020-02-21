package com.test;

import java.io.IOException;
import java.util.HashSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class ClusterTest {

	public static void main(String[] args) throws Exception {
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
