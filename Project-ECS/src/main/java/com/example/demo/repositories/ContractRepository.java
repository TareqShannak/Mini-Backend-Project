package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}
