package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.pharma.Pharmacy;

public interface PharmaRepository extends JpaRepository<Pharmacy, Long> {

}
