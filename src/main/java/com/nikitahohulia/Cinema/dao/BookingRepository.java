package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}