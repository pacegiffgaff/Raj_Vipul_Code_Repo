package com.giffgaff.springboot.web.service;

import java.util.List;

import com.giffgaff.springboot.web.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TodoRepository interface to get the list of users
 */
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByUser(String user);

}
