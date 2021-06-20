package com.pxs.jcode.ibatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.print("start app");
		
		SpringApplication.run(Application.class, args);
		System.out.print("start end app");
	}

}
