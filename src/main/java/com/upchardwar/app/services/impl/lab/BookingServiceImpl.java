package com.upchardwar.app.services.impl.lab;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.lab.Booking;
import com.upchardwar.app.entity.lab.LabTest;
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
	
	@Override
	public ResponseEntity<?> BookingLabTest(BookingRequest request) {
		
		Map<String,Object> response =new HashMap<>();
		
		Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PAITENT_NOT_FOUND));

        LabTest labTest = labTestRepository.findById(request.getLabTestId())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.LAB_NOT_FOUND));
        


        Booking booking = new Booking();
        booking.setPatient(patient);
        booking.setLabTest(labTest);
        booking.setBookingTime(request.getBookingTime());
        booking.setConfirmed(false);

        // Other booking details can be set here

        labBookingRepository.save(booking);
		
		Long bookingId=booking.getBookingId();
		BookingResponse bRes=new BookingResponse();
		bRes.setBookingId(bookingId);
		
		response.put(AppConstant.MESSAGE, AppConstant.LAB_TEST_BOOKED);
		response.put(AppConstant.BOOKING_RESPONSE, bRes);
		return new ResponseEntity<>(response ,HttpStatus.OK);
	}

}
