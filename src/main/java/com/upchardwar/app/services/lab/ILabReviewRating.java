package com.upchardwar.app.services.lab;

import java.util.Map;

import com.upchardwar.app.entity.payload.DoctorReviewRatingRequest;
import com.upchardwar.app.entity.payload.LabReviewRatingRequest;

public interface ILabReviewRating {
	 public Map<String, Object> addReviewRating(LabReviewRatingRequest request);
}
