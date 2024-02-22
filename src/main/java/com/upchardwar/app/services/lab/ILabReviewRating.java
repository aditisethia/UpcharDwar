package com.upchardwar.app.services.lab;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.upchardwar.app.entity.payload.DoctorReviewRatingRequest;
import com.upchardwar.app.entity.payload.LabReviewRatingRequest;
import com.upchardwar.app.entity.payload.LabReviewReplayRequest;

public interface ILabReviewRating {
//	 public Map<String, Object> addReview(Long labReviewRatingId, String reviewMessage);

	Map<String, Object> addReview(LabReviewRatingRequest request);
	
	public Map<String, Object> addReviewReplly(LabReviewReplayRequest request);
	
	public ResponseEntity<?> getLabReviewRatingById(Long id) ;
	
	public ResponseEntity<?> getAllRatingOfLab(long labId);
	
	public ResponseEntity<?> deleteReview(Long id, String email);
}
