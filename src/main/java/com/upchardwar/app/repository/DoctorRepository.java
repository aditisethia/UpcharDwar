package com.upchardwar.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.Speciality;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	public Optional<Doctor> findByDrName(String drName);
}
