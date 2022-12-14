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

	public List<Resource> getAllResources() {
		return resourceRepository.findAll();
	}

	public Resource getResourceById(Integer id) {
		return resourceRepository.findById(id.longValue()).get();
	}
	
	public Resource getResourceByEmail(String email) {
		return resourceRepository.findByEmail(email);
	}
	public void deleteResourceById(Long id) {
		resourceRepository.deleteById(id);
	}
	
	public void saveResource(Resource resource) {
		resourceRepository.save(resource);
	}
	
}
