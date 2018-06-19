package com.jin.jooq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:application.properties" })
@SpringBootApplication
@ImportResource({ "classpath:/spring/applicationContext*.xml" })
public class JooqApplication {

	public static void main(String[] args) {
		SpringApplication.run(JooqApplication.class, args);
	}
}
