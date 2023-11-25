package com.upchardwar.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.lab.Lab;


public interface LabRepository extends JpaRepository<Lab, Long> {
	public Optional<Lab> findByLabName(String labName);
}
