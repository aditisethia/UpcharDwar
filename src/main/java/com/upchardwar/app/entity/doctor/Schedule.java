package com.upchardwar.app.entity.doctor;

import java.time.LocalTime;
import java.util.List;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabTest;
import com.upchardwar.app.entity.patient.Patient;

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
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sId;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private List<String> days;
	
	private Boolean isActive;
	
	private Boolean isDeleted;
	@ManyToOne
	private Doctor doctor;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "schedule")
	private List<TimeSlote> timeSlotes;

	
}
