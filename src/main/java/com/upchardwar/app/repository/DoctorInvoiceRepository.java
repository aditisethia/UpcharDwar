package com.upchardwar.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.DoctorInvoice;

public interface DoctorInvoiceRepository extends JpaRepository<DoctorInvoice, Long> {
	
	List<DoctorInvoice> findByDoctorId(Long doctorId);
	  
    Page<DoctorInvoice> findByDoctorId(Long doctorId, Pageable pageable);
}
