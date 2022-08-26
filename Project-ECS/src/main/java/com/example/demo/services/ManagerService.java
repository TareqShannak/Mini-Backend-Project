package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Manager;
import com.example.demo.repositories.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	public List<Manager> getAllManagers(){
		return managerRepository.findAll();
	}
	
	public Manager getManagerByEmail(String email) {
		return managerRepository.findByEmail(email);
		
	}

}
