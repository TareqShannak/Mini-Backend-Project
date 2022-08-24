package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Feedback;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.services.ContractService;
import com.example.demo.services.FeedbackService;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("api/v1")
@EnableSwagger2
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private FeedbackService feedbackService;
	
//	Just Testing..
//	@GetMapping("/resources")
//	@PreAuthorize("hasAuthority('resource:write')")
//	public List<Resource> getAllResources() {
//		return resourceService.allResources();
//	}

	@GetMapping("/my_resources/{resourceId}")
	@PreAuthorize("hasAuthority('resource:read')")
	public Resource getResource(@PathVariable("resourceId") Integer resourceId) {
		return resourceService.getResourceById(resourceId);
	}

	@GetMapping("/my_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public Set<Resource> getMyResources() {
		Set<Resource> resources = userService.findUserByEmail(JwtTokenVerifier.username).getResources();
		for (Resource resource : resources) {
			resource.setContracts(resource.getContracts().stream()
					.filter(c -> c.getUser().getEmail().equals(JwtTokenVerifier.username)).collect(Collectors.toSet()));
		}
		return resources;
	}

	@GetMapping("/my_current_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public List<Resource> getMyCurrentResources() {
		Set<Contract> contracts = userService.findUserByEmail(JwtTokenVerifier.username).getContracts();
		List<Resource> currentResources = new ArrayList<>();
		for (Contract contract : contracts.stream().filter(c -> c.getEndDate().after(new Date()))
				.collect(Collectors.toList()))
			currentResources.add(contract.getResource());
		return currentResources;
	}

	@GetMapping("/my_finished_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public List<Resource> getMyFinishedResources() {
		Set<Contract> contracts = userService.findUserByEmail(JwtTokenVerifier.username).getContracts();
		List<Resource> currentResources = new ArrayList<>();
		for (Contract contract : contracts.stream().filter(c -> c.getEndDate().before(new Date()))
				.collect(Collectors.toList()))
			currentResources.add(contract.getResource());
		for (Resource resource : currentResources) {
			resource.setContracts(resource.getContracts().stream()
					.filter(c -> c.getUser().getEmail().equals(JwtTokenVerifier.username)).collect(Collectors.toSet()));
		}
		return currentResources;
	}

	@GetMapping("/client_info")
	@PreAuthorize("hasAuthority('feedback:write')")
	public User getUserInfo() {
		return userService.findUserByEmail(JwtTokenVerifier.username);
	}

	@PutMapping("/my_contracts/{contractId}")
	@PreAuthorize("hasAuthority('resource:read')")
	public void editContract(@PathVariable("contractId") Integer contractId, @RequestBody Contract contract) {
		Contract newContract = contractService.getContractById(contractId);
		newContract.setPosition(contract.getPosition());
		newContract.setEndDate(contract.getEndDate());
		contractService.saveContract(newContract);
	}
	
	@PostMapping("/add_feedback/{resourceId}")
	@PreAuthorize("hasAuthority('feedback:write')")
	public void addFeedback(@PathVariable("resourceId") Integer resourceId, @RequestBody Feedback feedback) {
		Resource resource = resourceService.getResourceById(resourceId);
		resource.addFeedback(feedback);
		feedback.setResource(resource);
		feedbackService.saveFeedback(feedback);
	}
	
	@GetMapping("/view_feedback/{resourceId}")
	@PreAuthorize("hasAuthority('resource:read')")
	public Set<Feedback> viewFeedbacks(@PathVariable("resourceId") Integer resourceId) {
		Set<Resource> resources = userService.findUserByEmail(JwtTokenVerifier.username).getResources();
		List<Long> resourcesIds = new ArrayList<>();
		for (Resource resource : resources) {
			resourcesIds.add(resource.getId());			
			resource.setContracts(resource.getContracts().stream()
					.filter(c -> c.getUser().getEmail().equals(JwtTokenVerifier.username)).collect(Collectors.toSet()));
		}
		if(resourcesIds.contains(Long.valueOf(resourceId)))		
			return resourceService.getResourceById(resourceId).getFeedbacks();
		
		//Must Return not OK in HTTP Response
		return null;
	}
	
	@PostMapping("/edit_profile")
	@PreAuthorize("hasAuthority('feedback:write')")
	public void editUser(@RequestBody User user) {
		User newUser = userService.findUserByEmail(JwtTokenVerifier.username);
		newUser.setCompanyName(user.getCompanyName());
		
		userService.saveUser(newUser);	
	}
}
