package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.pharma.MedicineInvoice;

public interface MedicineInvoiceRepo extends JpaRepository<MedicineInvoice, Long> {

}
