package com.upchardwar.app.entity.doctor;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class TimeSlote {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tsId;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private Boolean isBooked;
	
	private Boolean isDeleted;
	
	@ManyToOne
	private Schedule schedule;

}
