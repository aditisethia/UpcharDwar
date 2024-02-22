package com.upchardwar.app.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.services.IPatientService;

@RestController   
@RequestMapping("/upchardwar/patient")
@CrossOrigin("*")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	
	
	
	
    @GetMapping("/by-email/{email}")
    public ResponseEntity<?> getPatientByEmail(@PathVariable("email") String email) {
             System.out.println(email);
            PatientResponse patientResponse = patientService.getPatientByemail(email);
            return ResponseEntity.ok(patientResponse);
        } 

//	@PostMapping("/save")
//	public ResponseEntity<PatientResponse> registerPatient(@RequestBody PatientRequest patientRequest) {
//		System.out.println("hy");
//		return new ResponseEntity<PatientResponse>(this.patientService.createPatient(patientRequest), HttpStatus.OK);
//
//	}

	@PostMapping(path = "/save1", consumes = { "multipart/form-data", "application/octet-stream" ,"application/json" })
	
	public ResponseEntity<?> addPatient(@RequestPart("data") PatientRequest request,
			@RequestPart(value = "files", required = false) MultipartFile files) {
		System.out.println("345678");

	return this.patientService.addPatient(request, files);
	}

}
