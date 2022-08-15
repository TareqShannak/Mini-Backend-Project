package com.example.demo;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResourceService resourceService;


//	For TESTING:
	
//	private static final List<Resource> Resources = Arrays.asList(new Resource(1L, "oQattoush@gmail.com", new Date()),
//			new Resource(2L, "mHafez@gmail.com", new Date()), new Resource(3L, "wSayara@gmail.com", new Date()));

	@PostMapping("/signup")
	public void signupUser(@RequestBody User newUser) {
		userService.addUser(newUser);
	}
	
	@GetMapping("/resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public List<Resource> getAllResources() {
		 return resourceService.allResources();
	}
	
	@GetMapping(path = "/resources/{resourceId}")
	@PreAuthorize("hasAuthority('resource:read')")
	public Resource getResource(@PathVariable("resourceId") Integer resourceId) {
		return resourceService.getResourceById(resourceId);
//		return Resources.stream().filter(resource -> resourceId.equals(resource.getId().intValue())).findFirst()
//				.orElseThrow(() -> new IllegalStateException("Resource " + resourceId + " does not exists"));
	}

	
	//TODO : Generic The Search!
	
	@GetMapping("/my_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public Set<Resource> getMyResources() {
		
		System.out.println(JwtTokenVerifier.username);
		return userService.findUserByEmail(JwtTokenVerifier.username).getResources();
	}
	

}
