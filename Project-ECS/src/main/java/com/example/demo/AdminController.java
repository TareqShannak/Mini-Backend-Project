package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contract;
import com.example.demo.entities.User;
import com.example.demo.services.ContractService;
import com.example.demo.services.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@CrossOrigin
public class AdminController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('user:read')")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

//	TODO: fix "DELETE statement conflicted"
//	@DeleteMapping("/users/{userId}")
//	@PreAuthorize("hasAuthority('user:write')")
//	public void deleteUser(@PathVariable("userId") Long userId) {
//		for(Contract contract: userService.findUserById(userId).getContracts()) {
//			contractService.deleteContractById(contract.getId());
//		}
//		userService.deleteUserById(userId);		
//	}

}