package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.ResourceWithContract;
import com.example.demo.entities.Contract;
import com.example.demo.entities.Manager;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.services.ContractService;
import com.example.demo.services.ManagerService;
import com.example.demo.services.ResourceService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/v1")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private ContractService contractService;

	@GetMapping("/client_resources")
	@PreAuthorize("hasAuthority('resource_with_contract:read')")
	public List<ResourceWithContract> getEmployeesByManager() {
		Manager manager = managerService.getManagerByEmail(JwtTokenVerifier.username);
		List<User> users = userService.getUsersByManagerId(manager.getId());
		List<ResourceWithContract> resources = new ArrayList<>();
		for (User user : users)
			for (Contract contract : user.getContracts().stream().filter(c -> c.getEndDate().after(new Date()))
					.collect(Collectors.toList()))
				resources.add(new ResourceWithContract(contract.getResource().getId(), contract.getResource().getEmail(), user.getCompanyName(),
						contract.getPosition(), contract.getResource().getHireDate()));
		Collections.sort(resources, ResourceWithContract.idComparator);
		return resources;
	}
	
	@GetMapping("/companies")
	@PreAuthorize("hasAuthority('resource_with_contract:read')")
	public List<String> getMyCompanies() {
		Manager manager = managerService.getManagerByEmail(JwtTokenVerifier.username);
		List<String> companyNames = new ArrayList<>();
		for(User user: userService.getUsersByManagerId(manager.getId()))
			companyNames.add(user.getCompanyName());
		return companyNames;
	}
	
	@PostMapping("/add_resource/{companyName}")
	@PreAuthorize("hasAuthority('resource_with_contract:read')")
	public void addResource(@PathVariable(name = "companyName") String companyName, @RequestBody Resource newResource) {
		User customer = userService.getUserByCompanyName(companyName);
		Contract newContract = newResource.getContracts().iterator().next();
		
		newResource.setContracts(null);
		newResource.addUser(customer);
		resourceService.saveResource(newResource);
		newResource = resourceService.getResourceByEmail(newResource.getEmail());
		
		customer.addResource(newResource);
		
		newContract.setResource(newResource);
		newContract.setUser(customer);
		contractService.saveContract(newContract);
		
	}

}
