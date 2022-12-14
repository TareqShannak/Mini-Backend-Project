package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	public Optional<User> findById(Long id);
	
	public List<User> findByManagerId(Long managerId);
	
	public User findByCompanyName(String comapnyName);
	

	
}
