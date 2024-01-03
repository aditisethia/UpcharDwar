package com.upchardwar.app.controller.lab;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.BookingRequest;
import com.upchardwar.app.services.lab.IBookingService;

@RestController
@RequestMapping("upchardwar/labBooking")
@CrossOrigin("*")
public class LabBookingController {

	@Autowired
	private IBookingService bookingService;
	
	
	@PostMapping("/")
	public ResponseEntity<?> booking(@RequestBody BookingRequest request){
		 
		return this.bookingService.BookingLabTest(request);
	}
	
}
