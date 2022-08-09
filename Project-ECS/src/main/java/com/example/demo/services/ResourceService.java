package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Resource;
import com.example.demo.repositories.ContractRepository;
import com.example.demo.repositories.ResourceRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class ResourceService {

	@Autowired
	ResourceRepository resourceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ContractRepository contractRepository;
	
	public List<Resource> allResources(){
		
		List<Resource> result = new ArrayList<Resource>();
		for(Resource resource: resourceRepository.findAll())
			result.add(resource);
		return result;
	}
	

}
