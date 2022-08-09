package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Resource;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResourceService resourceService;

	@GetMapping(value = {"/", "/login"})
	public String getUser() {
		return "user";
	}
	
	@PostMapping("/register")
	public String createUser() {
		return "Register User!";
	}
	
	@GetMapping("/all/resources")
	public List<Resource> getAllResources(){
		return resourceService.allResources();
	}
	
	@GetMapping("/resources")
	public List<Resource> getMyResources(@RequestParam String email){
		return userService.retrieveResources(email);
	}
}
