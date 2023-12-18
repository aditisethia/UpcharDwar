package com.upchardwar.app.entity.payload;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.patient.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReviewRatingResponse {

     private Long id;
	
	private Patient patient;

	
	private Doctor doctor;
	
	private Integer rating;
	
	private String review;
}
