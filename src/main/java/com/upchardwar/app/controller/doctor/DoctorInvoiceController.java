package com.upchardwar.app.controller.doctor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.dto.DoctorInvoiceDto;
import com.upchardwar.app.services.doctor.IDoctorInvoiceService;

@RestController
@RequestMapping("/upchardwar/invoice")
@CrossOrigin("*")
public class DoctorInvoiceController {
//	
//	@Autowired
//	private IDoctorInvoiceService  doctorInvoiceService;

//	 @PostMapping("/process")
//	    public ResponseEntity< Map<String ,Object> > processDoctorInvoice(@RequestBody DoctorInvoiceDto doctorInvoiceDto) {
//	        
//		 doctorInvoiceService.processInvoice(doctorInvoiceService.convertToDoctorInvoice(doctorInvoiceDto));
//	        return ResponseEntity.ok("Doctor invoice processed successfully.");
//	    }
	
}
