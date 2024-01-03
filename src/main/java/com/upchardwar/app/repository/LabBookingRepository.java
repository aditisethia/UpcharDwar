package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.lab.Booking;

public interface LabBookingRepository extends JpaRepository<Booking, Long> {

}
