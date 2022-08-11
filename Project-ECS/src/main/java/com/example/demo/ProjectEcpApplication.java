package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.UserService;

@SpringBootApplication
public class ProjectEcpApplication {
	
//	@Autowired
//	UserService userService;
//	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(ModelMap model) {
//		return "login";
//	}
//	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String signup(@RequestParam String name, ModelMap model, @RequestParam String password) {
//		
//		
//		Boolean isValid = userService.validateLogin(name, password);
//		if(!isValid) {
//			model.put("ErrorMessage", "Invalid Credentials");
//			return "login";
//		}
//		model.put("name", name);
//		return "welcome";
//	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectEcpApplication.class, args);
	}

}
