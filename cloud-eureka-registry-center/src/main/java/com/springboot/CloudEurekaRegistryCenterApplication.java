package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaRegistryCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaRegistryCenterApplication.class, args);
	}

}
