package com.upchardwar.app.services.doctor;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.upchardwar.app.dto.DoctorInvoiceDto;
import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.doctor.DoctorInvoice;
@Service
public interface IDoctorInvoiceService {
	 public Map<String, Object> processInvoice(Appointment appointment);
	 public DoctorInvoice convertToDoctorInvoice(DoctorInvoiceDto doctorInvoiceDto) ;
}
