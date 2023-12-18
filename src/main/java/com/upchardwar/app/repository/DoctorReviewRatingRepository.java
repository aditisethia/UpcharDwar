package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.DoctorInvoice;
import com.upchardwar.app.entity.doctor.DoctorReviewRating;

public interface DoctorReviewRatingRepository extends JpaRepository<DoctorReviewRating, Long> {

}
