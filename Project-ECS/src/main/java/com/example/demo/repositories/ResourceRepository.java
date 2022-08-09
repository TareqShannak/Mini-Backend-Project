package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	

}
