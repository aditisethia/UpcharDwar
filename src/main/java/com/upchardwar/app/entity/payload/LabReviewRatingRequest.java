package com.upchardwar.app.entity.payload;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.patient.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabReviewRatingRequest {

	 private Long id;
		
		private Patient patient;

		
		private Lab lab;
		
		private Integer rating;
		
		private String review;
}
