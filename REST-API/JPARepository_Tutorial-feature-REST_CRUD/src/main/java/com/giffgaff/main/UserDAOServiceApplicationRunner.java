package com.giffgaff.main;

import com.giffgaff.entity.User;
import com.giffgaff.service.UserDAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDAOServiceApplicationRunner implements ApplicationRunner {
	private static final Logger log = LoggerFactory.getLogger(UserDAOServiceApplicationRunner.class);
	
	@Autowired
	UserDAOService userDaoService;

	/**
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User("Vipul","Admin");
		long insert_id = userDaoService.insert(user);
		log.info("New User created"+user);
	}
}
