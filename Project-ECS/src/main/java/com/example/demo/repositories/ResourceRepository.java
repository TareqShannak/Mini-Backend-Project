package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	@Query(value = "SELECT e.email, e.hire_date FROM employee e, contract r, customer c WHERE e.id = r.id AND r.id = c.id AND (r.end_date IS NULL OR r.end_date >= CURDATE()) AND c.email LIKE :username", nativeQuery = true)
	public List<Resource> findByEmail(@Param("username") String username);
	

}
