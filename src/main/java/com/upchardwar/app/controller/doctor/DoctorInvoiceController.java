package com.upchardwar.app.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.doctor.DoctorInvoice;
import com.upchardwar.app.services.doctor.IDoctorInvoiceService;

@RestController
@RequestMapping("/upchardwar/invoice")
@CrossOrigin("*")
public class DoctorInvoiceController {
	
	@Autowired
	private IDoctorInvoiceService  doctorInvoiceService;

    @PostMapping("/create")
    public ResponseEntity<DoctorInvoice> createDoctorInvoice(@RequestBody DoctorInvoice doctorInvoice) {
        DoctorInvoice createdInvoice = doctorInvoiceService.createDoctorInvoice(doctorInvoice);
        return ResponseEntity.ok(createdInvoice);
    }
    
    @GetMapping("/get/doctor/{doctorId}")
    public ResponseEntity<Page<DoctorInvoice>> getAppointmentsByDoctorId(
            @PathVariable Long doctorId, Pageable pageable) {
        Page<DoctorInvoice> doctorinvoice = doctorInvoiceService.getInvoiceByDoctorId(doctorId, pageable);
        return ResponseEntity.ok(doctorinvoice);
    }
	
}
