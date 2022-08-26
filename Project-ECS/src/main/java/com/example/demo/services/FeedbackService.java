package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Feedback;
import com.example.demo.repositories.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public void saveFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	
	public List<Feedback> getAllFeedbacks() {
		return feedbackRepository.findAll();
	}

}
