package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.DoctorInvoice;

public interface DoctorInvoiceRepository extends JpaRepository<DoctorInvoice, Long> {

}
