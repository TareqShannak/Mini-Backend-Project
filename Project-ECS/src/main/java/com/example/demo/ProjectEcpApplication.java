package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@SpringBootApplication
public class ProjectEcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEcpApplication.class, args);
	}

}
