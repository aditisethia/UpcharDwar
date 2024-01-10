package com.upchardwar.app.controller.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.DoctorReviewRatingRequest;
import com.upchardwar.app.entity.payload.LabReviewRatingRequest;
import com.upchardwar.app.services.doctor.IDoctorReviewRatingService;
import com.upchardwar.app.services.lab.ILabReviewRating;

@RestController
@RequestMapping("upchardwar/labreviewrating")
@CrossOrigin("*")
public class LabReviewRatingController {

	@Autowired
	private ILabReviewRating service;
	
	@PostMapping("/")
	public ResponseEntity<?> addreview(@RequestBody LabReviewRatingRequest request) {
		System.out.println("pipip");
		return new ResponseEntity<>(this.service.addReviewRating(request), HttpStatus.CREATED);
	}
	
}
