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
	
	public List<Resource> retrieveResources(String email) {
		User user = userRepository.findByEmail(email).get(0);
		List<Resource> result = new ArrayList<Resource>();
		for (Resource resource : user.getResources())
			result.add(resource);
		return result;
	}
}
