package com.tharbad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TharbadApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TharbadApiApplication.class, args);
		System.out.println("Tharbad API is running...");
	}
}
