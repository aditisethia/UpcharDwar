package com.upchardwar.app.services.lab;

import org.springframework.http.ResponseEntity;

import com.razorpay.Order;
import com.upchardwar.app.entity.lab.LabTransaction;
import com.upchardwar.app.entity.payload.BookingRequest;

public interface IBookingService {

	public ResponseEntity<?> BookingLabTest(BookingRequest request);
	
	public LabTransaction createTransaction(Double amount);
	
     public LabTransaction prepareTransactionDetails(Order order) ;
}
