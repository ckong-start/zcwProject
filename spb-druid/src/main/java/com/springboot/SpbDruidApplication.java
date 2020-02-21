package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.springboot.mapper")
@SpringBootApplication
public class SpbDruidApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpbDruidApplication.class, args);
	}

}
