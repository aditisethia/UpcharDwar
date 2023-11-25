package com.upchardwar.app.controller.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.repository.LabRepository;
import com.upchardwar.app.services.lab.ILabService;

@RestController
@RequestMapping("upchardwar/lab")
public class LabController {
    
	@Autowired
	private ILabService labService;
	
	@PostMapping("/save")
	public ResponseEntity<LabResponse> addDoctor(@RequestBody LabRequest labRequest) {
	
		return new ResponseEntity<LabResponse>(this.labService.registerLab(labRequest),
				HttpStatus.OK);

	}
}
