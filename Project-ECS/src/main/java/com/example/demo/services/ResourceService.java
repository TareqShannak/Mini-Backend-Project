package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Resource;
import com.example.demo.repositories.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	ResourceRepository resourceRepository;

	public List<Resource> allResources() {
		return resourceRepository.findAll();
	}

	public Resource getResourceById(Integer id) {
		return resourceRepository.findById(id.longValue()).get();
	}
	
	public void deleteResourceById(Long id) {
		resourceRepository.deleteById(id);
	}
	
}
