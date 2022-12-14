package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	@PreAuthorize("hasAuthority('user:read')")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
}