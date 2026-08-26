package com.adityaraj.booking_service.repository;

import com.adityaraj.booking_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
