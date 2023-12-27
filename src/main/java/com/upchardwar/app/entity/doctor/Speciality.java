package com.upchardwar.app.entity.doctor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Speciality {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String spName;
	
	private String spDescription;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY ,mappedBy = "speciality")
//	@JsonIgnoreProperties(value="speciality")
	@JsonIgnore
	private List<Doctor> doctors;
	
}
