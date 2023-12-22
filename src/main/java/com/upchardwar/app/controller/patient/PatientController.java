package com.upchardwar.app.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;
import com.upchardwar.app.services.IPatientService;

@RestController
@RequestMapping("/upchardwar/patient")
@CrossOrigin("*")
public class PatientController {
    
	@Autowired
	private IPatientService patientService;
	
	@PostMapping("/save")
	public ResponseEntity<PatientResponse> registerPatient(@RequestBody PatientRequest patientRequest) {
	     System.out.println("hy");
		return new ResponseEntity<PatientResponse>(this.patientService.createPatient(patientRequest),
				HttpStatus.OK);

	}
	
	@PostMapping(path="/save1" ,  consumes= { "multipart/form-data"  ,"application/octet-stream"})
	// @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
		//        produces = MediaType.APPLICATION_JSON_VALUE)
		 
	public  ResponseEntity<?> addPatient(@RequestPart("data") PatientRequest request,@RequestPart(value="files",required = false)MultipartFile multipartFile)
	{
		System.out.println("345678");
		
		return this.patientService.addPatient(request, multipartFile);
	}
}
