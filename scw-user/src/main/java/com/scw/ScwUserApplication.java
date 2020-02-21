package com.scw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scw.user.utils.SmsTemplate;

@SpringBootApplication
@EnableDiscoveryClient //eureka客户端
@EnableFeignClients //feign远程调用
@EnableCircuitBreaker //熔断保护
@EnableHystrix  //feign对熔断的支持
@MapperScan("com.scw.user.mapper")
public class ScwUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScwUserApplication.class, args);
	}
	
	//向容器中注入SmsTemplate对象  并加载properties配置文件中的属性值设置给该对象
	@ConfigurationProperties(prefix="sms") //SmsTemplate一定要 有set方法
	@Bean
	public SmsTemplate getSmsTemplate() {
		return new SmsTemplate();
	}
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
