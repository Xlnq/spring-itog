package com.example.skillfactory.repository;

import com.example.skillfactory.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

