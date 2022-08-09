package com.example.demo;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Resource;
import com.example.demo.entities.User;
import com.example.demo.repositories.ContractRepository;
import com.example.demo.repositories.ResourceRepository;
import com.example.demo.repositories.UserRepository;

@SpringBootTest
class ProjectEcpApplicationTests {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ContractRepository contractRepo;
	
	@Autowired
	ResourceRepository resourceRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testCreateUser() {
		User user = new User(1L, "yasmeen@gmail.com", "GJYSAGJYAG6UE882", "Nokia", new Date());
		
		user.addResource(new Resource(1L, "tshannk@gmail.com", new Date()));
		user.addResource(new Resource(2L, "anasbarakat@gmail.com", new Date()));
		
		//Insure that emails in the list:
		user.getResources().forEach(r->System.out.println(r.getEmail()));
		
		System.out.println(user);
		userRepo.save(user);
	}
	
	@Test
	void testCreatContract() {
		Contract contract1 = new Contract();
		contract1.setStartDate(new Date());
		contract1.setPosition("Backend");
		contract1.setEndDate(new Date());
		contract1.setUser(userRepo.findById(1L).get());
		contract1.setResource(resourceRepo.findById(1L).get());
		
		
		Contract contract2 = new Contract();
		contract2.setStartDate(new Date());
		contract2.setPosition("Frontend");
		contract2.setEndDate(new Date(2023,4,5));
		contract2.setUser(userRepo.findById(1L).get());
		contract2.setResource(resourceRepo.findById(2L).get());
		
		contractRepo.save(contract1);
		contractRepo.save(contract2);
		
	}
	

}
