package com.casestudy.greatOutdoors;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(GreatOutdoorsApplication.class);
	//static Logger logger = Logger.getLogger(GreatOutdoorsApplication.class.getName());
	//static Logger logger = Logger.getLogger(GreatOutdoorsApplication.class);
	public static void main(String[] args) {
		logger.info("***** GreatOutdoorsApplication Start *****");
		SpringApplication.run(GreatOutdoorsApplication.class, args);
		logger.info("***** GreatOutdoorsApplication End *****");

	}

}
