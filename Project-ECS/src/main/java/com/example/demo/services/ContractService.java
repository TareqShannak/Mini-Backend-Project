package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Contract;
import com.example.demo.repositories.ContractRepository;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;

	public void saveContract(Contract contract) {
		contractRepository.save(contract);
	}
	
	public Contract getContractById(Integer id) {
		return contractRepository.findById(id.longValue()).get();
	}
	
	public void deleteContractById(Long id) {
		contractRepository.deleteById(id);
	}
	
}
