package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
