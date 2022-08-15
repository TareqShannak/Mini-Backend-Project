package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.repositories.ContractRepository;
import com.example.demo.repositories.ResourceRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class ResourceService {

	@Autowired
	ResourceRepository resourceRepository;

	public List<Resource> allResources() {

		return (List<Resource>) resourceRepository.findAll();
//		List<Resource> result = new ArrayList<Resource>();
//		Iterable<Resource> resources = resourceRepository.findAll();
//		for (Resource resource : resources)
//			result.add(resource);
//		return result;
	}

	public List<Resource> retrieveMyResources(String email) {
		return resourceRepository.findByEmail(email);

	}

}
