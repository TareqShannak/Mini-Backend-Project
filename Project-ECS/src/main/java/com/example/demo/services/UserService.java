package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Boolean validateLogin(String name, String pass) {
		return name.equalsIgnoreCase("TareqShannak") && pass.equalsIgnoreCase("0000");
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public List<User> getUsersByManagerId(Long managerId) {
		return userRepository.findByManagerId(managerId);
	}
	
	public User getUserByCompanyName(String companyName) {
		return userRepository.findByCompanyName(companyName);
	}
}
