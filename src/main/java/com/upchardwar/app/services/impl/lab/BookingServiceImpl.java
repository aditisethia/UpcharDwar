package com.upchardwar.app.services.impl.lab;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.upchardwar.app.entity.lab.Booking;
import com.upchardwar.app.entity.lab.LabTest;
import com.upchardwar.app.entity.lab.LabTransaction;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.BookingRequest;
import com.upchardwar.app.entity.payload.BookingResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.LabBookingRepository;
import com.upchardwar.app.repository.LabTestRepo;
import com.upchardwar.app.repository.PatientRepository;
import com.upchardwar.app.services.lab.IBookingService;

@Service
public class BookingServiceImpl implements IBookingService {
  
	@Autowired
	private LabBookingRepository labBookingRepository;
	
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private LabTestRepo labTestRepository;
	
	private static final String KEY= "rzp_test_odnr6VrScjlKUB";
	
	private static final String KEY_SECRET="eKyCFd1LAi81MaQdRPV8ZVFr";
	
	private static final String CURRENCY="INR";
	
	@Override
	public ResponseEntity<?> BookingLabTest(BookingRequest request) {
		
		Map<String,Object> response =new HashMap<>();
		Patient patient = patientRepository.findById(request.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PAITENT_NOT_FOUND));

        LabTest labTest = labTestRepository.findById(request.getLabTest().getId())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.LAB_NOT_FOUND));
        

        System.err.println((request.getBookingDate()));
        Booking booking = new Booking();
        booking.setPatient(patient);
        booking.setLabTest(labTest);
        booking.setAmount(request.getAmount());
        booking.setPurpose(request.getPurpose());
       booking.setBookingDate(LocalDateTime.now());

        // Other booking details can be set here

        labBookingRepository.save(booking);
		
		Long bookingId=booking.getBookingId();
		BookingResponse bRes=new BookingResponse();
		bRes.setBookingId(bookingId);
		
		response.put(AppConstant.MESSAGE, AppConstant.LAB_TEST_BOOKED);
		response.put(AppConstant.BOOKING_RESPONSE, bRes);
		return new ResponseEntity<>(response ,HttpStatus.OK);
	}

	
	public LabTransaction createTransaction(Double amount) {
		//amount
		try {
			JSONObject jsonObject=new JSONObject();
			
			jsonObject.put("amount", (amount*100));
			jsonObject.put("currency", CURRENCY);
			
			RazorpayClient razorpayClient=new RazorpayClient(KEY, KEY_SECRET);
		Order order=	razorpayClient.Orders.create(jsonObject);
		System.err.println(order);
		
		return prepareTransactionDetails(order);
			
		} catch (RazorpayException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	 public LabTransaction prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount=order.get("amount");
		
	LabTransaction labTransaction= new LabTransaction(orderId,currency,amount);
	return labTransaction;
	}
	
	
}
