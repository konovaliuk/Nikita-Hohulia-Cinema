package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}