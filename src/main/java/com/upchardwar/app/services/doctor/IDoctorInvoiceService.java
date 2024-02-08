package com.upchardwar.app.services.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.upchardwar.app.entity.doctor.DoctorInvoice;

public interface IDoctorInvoiceService {

	public DoctorInvoice createDoctorInvoice(DoctorInvoice doctorInvoice);

	Page<DoctorInvoice> getInvoiceByDoctorId(Long doctorId, Pageable pageable);
}
