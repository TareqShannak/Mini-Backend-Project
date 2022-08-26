package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Feedback;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.services.FeedbackService;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("api/v1")
@EnableSwagger2
@CrossOrigin
public class FeedbackController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/add_feedback/{resourceId}")
	@PreAuthorize("hasAuthority('feedback:write')")
	public void addFeedback(@PathVariable("resourceId") Integer resourceId, @RequestBody Feedback feedback) {
		Resource resource = resourceService.getResourceById(resourceId);
		resource.addFeedback(feedback);
		feedback.setResource(resource);
		User user = userService.findUserByEmail(JwtTokenVerifier.username);
		user.addFeedback(feedback);
		feedback.setUser(user);
		feedbackService.saveFeedback(feedback);
	}
	
	@GetMapping("/view_feedback/{resourceId}")
	@PreAuthorize("hasAuthority('feedback:read')")
	public List<Feedback> viewFeedbacks(@PathVariable("resourceId") Long resourceId) {
		return feedbackService
				.getAllFeedbacks()
				.stream()
				.filter(f -> f.getResource().getId().equals(resourceId))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/view_my_feedback/{resourceId}")
	@PreAuthorize("hasAuthority('feedback:read')")
	public List<Feedback> viewMyFeedbacks(@PathVariable("resourceId") Long resourceId) {
		
		Long userId = userService.findUserByEmail(JwtTokenVerifier.username).getId(); 
		
		return feedbackService
				.getAllFeedbacks()
				.stream()
				.filter(f -> f.getResource().getId().equals(resourceId) && f.getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}

}
