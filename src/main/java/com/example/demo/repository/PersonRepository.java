package com.example.demo.repository;

import com.example.demo.model.Person;
import com.example.demo.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
