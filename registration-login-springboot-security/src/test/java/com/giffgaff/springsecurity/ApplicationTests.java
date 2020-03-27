package com.giffgaff.springsecurity;

import com.giffgaff.springsecurity.model.User;
import com.giffgaff.springsecurity.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	public void findAllUsers() {
		List<User> users = userRepository.findAll();
		Assert.assertNotNull(users);
		Assert.assertTrue(!users.isEmpty());
	}

	@Test
	public void createUser_By_providingUser() {
		String email = "john@gmail.com";
		User user = new User("John", email,"pass","paris");
		User savedUser = userRepository.save(user);
		User newUser = userRepository.findByEmail(savedUser.getEmail());
		Assert.assertNotNull(newUser);
		Assert.assertEquals(email, newUser.getEmail());
		Assert.assertEquals("John", newUser.getFullName());
	}

	@Test
	public void test_password_encoder_by_given_password() {
		String password = "testpassword";
		String encodePassword = passwordEncoder.encode(password);
		Assert.assertNotEquals(password, encodePassword);
	}

}
