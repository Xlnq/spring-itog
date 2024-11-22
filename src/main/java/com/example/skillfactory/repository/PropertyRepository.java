package com.example.skillfactory.repository;

import com.example.skillfactory.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}

