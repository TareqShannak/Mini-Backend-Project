package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Request;
import com.example.demo.repositories.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public void saveRequest(Request request) {
		requestRepository.save(request);
	}
	
	public List<Request> getAllRequests(){
		return requestRepository.findAll();
	}
}
