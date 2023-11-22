package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.lab.Lab;

public interface LabRepository extends JpaRepository<Lab, Long> {

}
