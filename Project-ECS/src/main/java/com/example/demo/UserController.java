package com.example.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/v1/resources")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResourceService resourceService;

//	@GetMapping(value = {"/", "/login"})
//	public String getUser() {
//		return "user";
//	}
//	
//	@PostMapping("/register")
//	public String createUser() {
//		return "Register User!";
//	}
//	
//	@GetMapping("/all/resources")
//	public List<Resource> getAllResources(){
//		return resourceService.allResources();
//	}
//	
//	@GetMapping("/resources")
//	public List<Resource> getMyResources(@RequestParam String email){
//		return userService.retrieveResources(email);
//	}
	
	
	private static final List<Resource> Resources = Arrays.asList(new Resource(1L, "oQattoush@gmail.com", new Date()),
			new Resource(2L, "mHafez@gmail.com", new Date()), new Resource(3L, "wSayara@gmail.com", new Date()));
	
	@GetMapping
	public List<Resource> getAllResources(){
		return Resources;
	}

	@GetMapping(path = "{resourceId}")
	public Resource getResource(@PathVariable("resourceId") Integer resourceId) {
		return Resources.stream().filter(resource -> resourceId.equals(resource.getId().intValue())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Resource " + resourceId + " does not exists"));
	}
}
