package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableSwagger2
public class ProjectEcpApplication {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public void signupUser(@RequestBody User newUser) {
		userService.addUser(newUser);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectEcpApplication.class, args);
	}
}
