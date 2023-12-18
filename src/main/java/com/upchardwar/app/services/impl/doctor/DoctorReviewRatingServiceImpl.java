package com.upchardwar.app.services.impl.doctor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.DoctorReviewRating;
import com.upchardwar.app.entity.doctor.Schedule;
import com.upchardwar.app.entity.payload.DoctorReviewRatingRequest;
import com.upchardwar.app.entity.payload.DoctorReviewRatingResponse;
import com.upchardwar.app.entity.payload.ScheduleRequest;
import com.upchardwar.app.entity.payload.ScheduleResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.DoctorReviewRatingRepository;
import com.upchardwar.app.services.doctor.IDoctorReviewRatingService;

@Service
public class DoctorReviewRatingServiceImpl implements IDoctorReviewRatingService {
    
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DoctorReviewRatingRepository doctorReviewRatingRepository;
	
	public DoctorReviewRatingResponse doctorReviewRatingToResponse(DoctorReviewRating doctorReviewRating) {
		return this.modelMapper.map(doctorReviewRating, DoctorReviewRatingResponse.class);
	}


	public DoctorReviewRating requestToDoctorReviewRating(DoctorReviewRatingRequest request) {
		return this.modelMapper.map(request,DoctorReviewRating .class);
	}
	
	@Override
	public Map<String, Object> addReviewRating(DoctorReviewRatingRequest request) {
			Map<String, Object> response=new HashMap<>();
			 DoctorReviewRating drr =this.requestToDoctorReviewRating(request);
			    
			drr=this.doctorReviewRatingRepository.save(drr);
			 response.put(AppConstant.DOCTOR_REVIEW_RATING, doctorReviewRatingToResponse(drr));
			return response;
		}
		

}
