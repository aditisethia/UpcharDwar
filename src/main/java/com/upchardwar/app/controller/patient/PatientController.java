package com.upchardwar.app.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;
import com.upchardwar.app.services.IPatientService;

@RestController
@RequestMapping("/upchardwar/patient")
public class PatientController {
    
	@Autowired
	private IPatientService patientService;
	
	@PostMapping("/save")
	public ResponseEntity<PatientResponse> registerPatient(@RequestBody PatientRequest patientRequest) {
	     System.out.println("hy");
		return new ResponseEntity<PatientResponse>(this.patientService.createPatient(patientRequest),
				HttpStatus.OK);

	}
}
