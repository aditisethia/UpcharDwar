package com.upchardwar.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabTest;

public interface LabTestRepo extends JpaRepository<LabTest, Long> {



	Page<LabTest> findByLabId(Long labId, Pageable pageable);

}
