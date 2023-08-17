package com.mainproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mainproject")
public class AniplantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AniplantApplication.class, args);
	}

} 
