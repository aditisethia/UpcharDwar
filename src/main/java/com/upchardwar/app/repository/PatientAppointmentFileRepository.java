package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.PatientAppointmentFile;

public interface PatientAppointmentFileRepository extends JpaRepository<PatientAppointmentFile, Long> {

}
