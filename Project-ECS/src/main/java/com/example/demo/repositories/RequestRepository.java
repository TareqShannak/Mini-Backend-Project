package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
