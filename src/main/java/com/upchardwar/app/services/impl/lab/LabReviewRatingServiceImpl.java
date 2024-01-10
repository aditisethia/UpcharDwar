package com.upchardwar.app.services.impl.lab;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.lab.LabReviewRating;

import com.upchardwar.app.entity.payload.LabReviewRatingRequest;
import com.upchardwar.app.entity.payload.LabReviewRatingResponse;
import com.upchardwar.app.entity.status.AppConstant;

import com.upchardwar.app.repository.LabReviewRatingRepo;
import com.upchardwar.app.services.lab.ILabReviewRating;


@Service
public class LabReviewRatingServiceImpl implements ILabReviewRating {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LabReviewRatingRepo labReviewRatingRepository;
	
	public LabReviewRatingResponse labReviewRatingToResponse(LabReviewRating labReviewRating) {
		return this.modelMapper.map(labReviewRating, LabReviewRatingResponse.class);
	}


	public LabReviewRating requestToLabReviewRating(LabReviewRatingRequest request) {
		return this.modelMapper.map(request,LabReviewRating .class);
	}
	
	@Override
	public Map<String, Object> addReviewRating(LabReviewRatingRequest request) {
			Map<String, Object> response=new HashMap<>();
			 LabReviewRating lb =this.requestToLabReviewRating(request);
			    
		lb=this.labReviewRatingRepository.save(lb);
			 response.put(AppConstant.LAB_REVIEW_RATING, labReviewRatingToResponse(lb));
			return response;
		}
}
