package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {

	public void deleteById(Long id);
}
