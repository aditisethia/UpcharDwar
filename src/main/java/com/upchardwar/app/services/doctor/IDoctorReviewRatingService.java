package com.upchardwar.app.services.doctor;

import java.util.Map;

import com.upchardwar.app.entity.payload.DoctorReviewRatingRequest;

public interface IDoctorReviewRatingService {
   public Map<String, Object> addReviewRating(DoctorReviewRatingRequest request);
}
