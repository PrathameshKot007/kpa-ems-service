package com.kpa.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpa.ems.dto.UserSignUpDto;
import com.kpa.ems.service.UserService;

@RestController
@RequestMapping("/ems")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	//API1 - Add User
	@PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserSignUpDto request) {
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully!");
    }
}
