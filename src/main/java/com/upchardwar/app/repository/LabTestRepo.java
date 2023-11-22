package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.lab.LabTest;

public interface LabTestRepo extends JpaRepository<LabTest, Long> {

}
