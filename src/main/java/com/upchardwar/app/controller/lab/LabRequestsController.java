package com.upchardwar.app.controller.lab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.LabRequestsRequest;
import com.upchardwar.app.entity.payload.LabRequestsResponse;
import com.upchardwar.app.services.lab.ILabRequestsService;

@RestController
@RequestMapping("upchardwar/labrequest")
public class LabRequestsController {

	
	@Autowired
	private ILabRequestsService labService;


	@PostMapping("/save")
	public ResponseEntity<?> addLabRequests(@RequestBody LabRequestsRequest labRequest) {
		System.out.println("at labrequest controller");

		return new ResponseEntity<>(this.labService.registerLabRequest(labRequest), HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllLabrequestts(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize) {
		System.out.println("at getall lab controller");
		List<LabRequestsResponse> labrequest = labService.getAllLabRequests(pageNo, pageSize).getContent();
		
		return new ResponseEntity<>(labrequest, HttpStatus.OK);
	}
}
