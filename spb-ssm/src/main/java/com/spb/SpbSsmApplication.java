package com.spb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.spb.mapper")
@SpringBootApplication
public class SpbSsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpbSsmApplication.class, args);
	}

}
