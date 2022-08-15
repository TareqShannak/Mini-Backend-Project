package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Boolean validateLogin(String name, String pass) {
		return name.equalsIgnoreCase("TareqShannak") && pass.equalsIgnoreCase("0000");
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
