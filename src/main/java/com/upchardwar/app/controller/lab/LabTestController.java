package com.upchardwar.app.controller.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.dto.AppointmentDto;
import com.upchardwar.app.dto.PageAppointmentDto;
import com.upchardwar.app.dto.PageLabDto;
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
	     System.out.println(labTestRequest.getTestName());
	    
		return this.labTestService.createLabTest(labTestRequest);
	}
	
	
	//get All LabTest of perticular labTest
	
	@GetMapping("/all/{pageNo}/{pageSize}/{sortBy}/{labId}")
	public ResponseEntity<PageLabDto> getAllLabTest(
			@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize,
			@PathVariable("sortBy") String sortBy,@PathVariable("labId") Long labId) {
     System.out.println("kuchhhhhh");
	PageLabDto plto= this.labTestService.viewAllLabTest(pageNo, pageSize, sortBy, labId);
	return new ResponseEntity<PageLabDto>(plto, HttpStatus.OK);
	}
}
