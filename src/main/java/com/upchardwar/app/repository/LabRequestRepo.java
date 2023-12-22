package com.upchardwar.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.lab.LabRequests;

public interface LabRequestRepo extends JpaRepository<LabRequests, Long> {

	public List<LabRequests> findByRequestDate(LocalDate requestDate);

}
