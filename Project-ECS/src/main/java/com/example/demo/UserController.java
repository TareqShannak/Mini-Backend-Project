package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Resource;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ResourceService resourceService;

	@Autowired
	UserRepository userRepository;

	private static final List<Resource> Resources = Arrays.asList(new Resource(1L, "oQattoush@gmail.com", new Date()),
			new Resource(2L, "mHafez@gmail.com", new Date()), new Resource(3L, "wSayara@gmail.com", new Date()));

	@GetMapping("/resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public List<Resource> getAllResources() {
		
		//return resourceService.allResources();
		
		List<Resource> resources = new ArrayList<>();
		for (Resource resource : Resources)
			for (Contract contract : resource.getContracts()) 
				if (contract.getUser().getEmail().equals(JwtTokenVerifier.username)
						&& contract.getEndDate().after(new Date())) 
					resources.add(resource);
		return Resources;
	}
	
	@GetMapping(path = "/resources/{resourceId}")
	@PreAuthorize("hasAuthority('resource:read')")
	public Resource getResource(@PathVariable("resourceId") Integer resourceId) {
		return Resources.stream().filter(resource -> resourceId.equals(resource.getId().intValue())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Resource " + resourceId + " does not exists"));
	}

	@GetMapping("/my_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public Set<Resource> getMyResources() {
		System.out.println(userRepository.findByEmail("yasmeen@gmail.com"));
		System.out.println("******" + userService.findUserByEmail("yasmeen@gmail.com").getResources());
		return userService.findUserByEmail("yasmeen@gmail.com").getResources();
	}

}
