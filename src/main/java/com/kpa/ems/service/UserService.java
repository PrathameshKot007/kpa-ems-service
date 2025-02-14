package com.kpa.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kpa.ems.dto.UserSignUpDto;
import com.kpa.ems.entity.UserEntity;
import com.kpa.ems.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	public void registerUser(UserSignUpDto signUpRequest) {
		UserEntity user = new UserEntity();
		user.setUserName(signUpRequest.getUserName());
		user.setUserEmail(signUpRequest.getUserEmail());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setUserType(signUpRequest.getUserType());
		userRepository.save(user);
	}
}
