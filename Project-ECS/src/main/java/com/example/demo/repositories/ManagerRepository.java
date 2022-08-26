package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
	
	Manager findByEmail(String email);
	

}
