package com.humanassist.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.humanassist"})
@EnableCaching
public class StartUP {

	public static void main(String[] args) {

		SpringApplication.run(StartUP.class, args);
	}

}
