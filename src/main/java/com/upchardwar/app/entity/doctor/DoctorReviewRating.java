package com.upchardwar.app.entity.doctor;

import com.upchardwar.app.entity.patient.Patient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorReviewRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Patient patient;

	@ManyToOne
	private Doctor doctor;
	
	private Integer rating;
	
	private String review;
	
	
}
