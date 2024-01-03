package com.upchardwar.app.services.lab;

import org.springframework.http.ResponseEntity;

import com.upchardwar.app.entity.payload.BookingRequest;

public interface IBookingService {

	public ResponseEntity<?> BookingLabTest(BookingRequest request);
}
