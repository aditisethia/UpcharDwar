package com.upchardwar.app.services.impl.doctor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.upchardwar.app.dto.DoctorInvoiceDto;
import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.doctor.DoctorInvoice;
import com.upchardwar.app.entity.payload.ApiResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.repository.AppointmentRepository;
import com.upchardwar.app.repository.DoctorInvoiceRepository;
import com.upchardwar.app.repository.DoctorRepository;
import com.upchardwar.app.repository.PatientRepository;
import com.upchardwar.app.services.doctor.IDoctorInvoiceService;

public class DoctorInvoiceServiceImpl implements IDoctorInvoiceService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
	private DoctorInvoiceRepository doctorInvoiceRepository;
	
	@Override
	public Map<String, Object> processInvoice(Appointment appointment) {
		
		Map<String , Object> response =new HashMap<>();
		  float invoiceAmount = calculateInvoiceAmount(appointment);

	        // Create and save the invoice
	        DoctorInvoice invoice = new DoctorInvoice();
	        invoice.setInvoiceGenerateDate(LocalDate.now());
	        invoice.setAmount(invoiceAmount);
	        invoice.setDoctor(appointment.getDoctor());
	        invoice.setPatient(appointment.getPatient());
	        invoice.setAppointment(appointment);

	        // Save the invoice
	        doctorInvoiceRepository.save(invoice);

	        // Update appointment status and link the invoice
	        appointment.setStatus(AppConstant.APPOINTMENT_STATUS_COMPLETED);
	        appointment.setDoctorInvoice(invoice);
 
	        
	        ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.INVOICE_PROCESS,HttpStatus.OK);
		    response.put("response", apiResponse);
	       
	     response.put( "Invoice", appointmentRepository.save(appointment));
	        
		return  response;
	}

	@Override
	public DoctorInvoice convertToDoctorInvoice(DoctorInvoiceDto doctorInvoiceDto) {
		 DoctorInvoice doctorInvoice = new DoctorInvoice();
	        doctorInvoice.setInvoiceGenerateDate(doctorInvoiceDto.getInvoiceGenerateDate());
	        doctorInvoice.setAmount(doctorInvoiceDto.getAmount());
	        doctorInvoice.setInvoiceStatus(doctorInvoiceDto.getInvoiceStatus());
	        doctorInvoice.setPaymentMethod(doctorInvoiceDto.getPaymentMethod());

	        // Set related entities
	        doctorInvoice.setDoctor(doctorRepository.findById(doctorInvoiceDto.getDoctorId()).orElse(null));
	        doctorInvoice.setPatient(patientRepository.findById(doctorInvoiceDto.getPatientId()).orElse(null));
	        doctorInvoice.setAppointment(appointmentRepository.findById(doctorInvoiceDto.getAppointmentId()).orElse(null));

	        return doctorInvoice;
		
	}
	
	
	 private float calculateInvoiceAmount(Appointment appointment) {
	       
	        return appointment.getPaidAmount();
	    }

}
