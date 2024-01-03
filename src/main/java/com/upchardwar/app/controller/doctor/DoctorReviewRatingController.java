package com.upchardwar.app.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.DoctorReviewRatingRequest;
import com.upchardwar.app.services.doctor.IDoctorReviewRatingService;

@RestController
@RequestMapping("upchardwar/reviewrating")
@CrossOrigin("*")
public class DoctorReviewRatingController {

	@Autowired
	private IDoctorReviewRatingService service;
	
	@PostMapping("/")
	public ResponseEntity<?> addreview(@RequestBody DoctorReviewRatingRequest request) {
		System.out.println("pipip");
		return new ResponseEntity<>(this.service.addReviewRating(request), HttpStatus.CREATED);
	}
}
