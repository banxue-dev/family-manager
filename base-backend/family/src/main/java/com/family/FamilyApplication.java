package com.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.family.*.mapper"})
public class FamilyApplication {
	 public static void main(String[] args) {
	        SpringApplication.run(FamilyApplication.class, args);
	    }
}
