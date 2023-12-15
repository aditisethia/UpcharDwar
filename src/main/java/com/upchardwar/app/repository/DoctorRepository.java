package com.upchardwar.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.Speciality;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	public Optional<Doctor> findByUserName(String drName);
	
	@Query("select d from Doctor d where d.status=:b and d.id=:id")
	public Optional<Doctor> findByIdAndStatus(String b,long id);
	
	 Page<Doctor> findByStatus(String status, Pageable pageable);

	public Optional<Doctor> findByEmail(String email);
	 
	  
}
