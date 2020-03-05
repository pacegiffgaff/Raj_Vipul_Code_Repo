package com.giffgaff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giffgaff.service.WelcomeService;

@RestController
public class WelcomeController {

	//Auto wiring
	@Autowired
	private WelcomeService service;

	/**
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return service.retrieveWelcomeMessage();
	}
}