package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.services.ContractService;
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

	@GetMapping("/my_resources/{resourceId}")
	@PreAuthorize("hasAuthority('resource:read')")
	public Resource getResource(@PathVariable("resourceId") Integer resourceId) {
		return resourceService.getResourceById(resourceId);
	}

	@GetMapping("/my_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public List<Resource> getMyResources() {
		List<Resource> resources = userService.findUserByEmail(JwtTokenVerifier.username).getResources().stream().collect(Collectors.toList());
		for (Resource resource : resources) {
			resource.setContracts(resource.getContracts().stream()
					.filter(c -> c.getUser().getEmail().equals(JwtTokenVerifier.username)).collect(Collectors.toSet()));
		}
		Collections.sort(resources, Resource.idComparator);
		return resources;
	}

	@GetMapping("/current_resources")
	@PreAuthorize("hasAuthority('resource:read')")
	public List<Resource> getMyCurrentResources() {
		Set<Contract> contracts = userService.findUserByEmail(JwtTokenVerifier.username).getContracts();
		List<Resource> currentResources = new ArrayList<>();
		for (Contract contract : contracts.stream().filter(c -> c.getEndDate().after(new Date()))
				.collect(Collectors.toList()))
			currentResources.add(contract.getResource());
		Collections.sort(currentResources, Resource.idComparator);
		return currentResources;
	}
	
	@GetMapping("/historical_resources")
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
		Collections.sort(currentResources, Resource.idComparator);
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
		System.out.println(contract.getEndDate());

//		DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//
//		Date date = null;
//		try {
//			date = utcFormat.parse(contract.getEndDate().toString());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//		pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
//
//		System.out.println(pstFormat.format(date));
		
		newContract.setEndDate(contract.getEndDate());
		contractService.saveContract(newContract);
	}
	
	@PutMapping("/edit_profile")
	@PreAuthorize("hasAuthority('feedback:write')")
	public void editUser(@RequestBody User user) {
		User newUser = userService.findUserByEmail(JwtTokenVerifier.username);
		newUser.setCompanyName(user.getCompanyName());
		
		userService.saveUser(newUser);	
	}
}
