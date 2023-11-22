package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
