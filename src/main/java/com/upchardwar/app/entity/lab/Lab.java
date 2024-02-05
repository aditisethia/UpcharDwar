package com.upchardwar.app.entity.lab;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.doctor.DoctorReviewRating;
import com.upchardwar.app.entity.doctor.Speciality;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String labName;

	private String email;

	private String password;

	private Boolean isApproved = false;

	private String phone;

	private String documentType;

	private String imageName;

	private String biography;

	private Boolean isDeleted = false;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", unique = true)
	private Location location;

	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lab_id")
	private List<LabDocument> labDocument;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lab_id")
	private List<LabTest> labTests;

	
	@OneToMany(mappedBy = "lab", cascade = CascadeType.ALL)
	private List<LabReviewRating> labReviewRatings;

	
	@OneToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	
	 @OneToMany(cascade = CascadeType.ALL)
	    @JoinColumn(name = "lab_id")
	 private List<PatientFavoriteLab> favoriteLabs;

}
