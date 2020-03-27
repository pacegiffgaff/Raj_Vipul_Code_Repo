package com.giffgaff.springsecurity.service;

import com.giffgaff.springsecurity.model.User;
import com.giffgaff.springsecurity.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
