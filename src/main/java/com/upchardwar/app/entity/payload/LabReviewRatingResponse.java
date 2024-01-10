package com.upchardwar.app.entity.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.patient.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabReviewRatingResponse {
	 private Long id;
		
	    @JsonIgnore
		private Patient patient;

		@JsonIgnore
		private Lab lab;
		
		private Integer rating;
		
		private String review;
}
