package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.patient.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
