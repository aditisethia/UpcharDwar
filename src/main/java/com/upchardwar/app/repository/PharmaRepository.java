package com.upchardwar.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.pharma.Pharmacy;

public interface PharmaRepository extends JpaRepository<Pharmacy, Long> {
	public Optional<Pharmacy> findByEmail(String email);
}
