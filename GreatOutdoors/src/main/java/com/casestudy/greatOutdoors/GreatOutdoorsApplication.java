package com.casestudy.greatOutdoors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.casestudy.greatOutdoors")
@EntityScan("com.casestudy.greatOutdoors.entity")
@EnableJpaRepositories("com.casestudy.greatOutdoors.dao")
public class GreatOutdoorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreatOutdoorsApplication.class, args);
	}

}
