package com.giffgaff.springboot.web.springbootfirstwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the mail class to start the application
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.giffgaff.springboot.web")
@EntityScan(basePackages = "com.giffgaff.springboot.web.model")
@EnableJpaRepositories(basePackages = "com.giffgaff.springboot.web.service")
public class SpringBootFirstWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}

}
