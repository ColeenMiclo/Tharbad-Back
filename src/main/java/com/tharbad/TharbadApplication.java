package com.tharbad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TharbadApplication {

	private static final Logger log = LoggerFactory.getLogger(TharbadApplication.class);
	public static void main(String[] args) {
		log.debug("TharbadApplication started");
		SpringApplication.run(TharbadApplication.class, args);
	}

}
