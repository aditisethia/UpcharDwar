package com.upchardwar.app.controller.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.CreateLabTestRequest;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.services.lab.ILabTestService;

@RestController
@RequestMapping("upchardwar/labTest")
@CrossOrigin("*")
public class LabTestController {

	@Autowired
	private ILabTestService labTestService;
	
	@PostMapping("/save")
	public ResponseEntity<?> addDoctor(@RequestBody CreateLabTestRequest labTestRequest) {
	
		return this.labTestService.createLabTest(labTestRequest);
	}
}
