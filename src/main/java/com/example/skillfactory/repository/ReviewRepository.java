package com.example.skillfactory.repository;

import com.example.skillfactory.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

