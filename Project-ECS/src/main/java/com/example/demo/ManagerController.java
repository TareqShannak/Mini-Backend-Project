package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.ResourceWithContract;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Manager;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.services.ManagerService;
import com.example.demo.services.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("api/v1")
@EnableSwagger2
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private UserService userService;

	@GetMapping("/client_resources")
	@PreAuthorize("hasAuthority('resource_with_contract:read')")
	public List<ResourceWithContract> getEmployeesByManager() {
		Manager manager = managerService.getManagerByEmail(JwtTokenVerifier.username);
		List<User> users = userService.getUsersByManagerId(manager.getId());
		List<ResourceWithContract> resources = new ArrayList<>();
		for (User user : users)
			for (Contract contract : user.getContracts().stream().filter(c -> c.getEndDate().after(new Date()))
					.collect(Collectors.toList()))
				resources.add(new ResourceWithContract(contract.getResource().getEmail(), user.getCompanyName(),
						contract.getPosition(), contract.getResource().getHireDate()));
		return resources;
	}

}
