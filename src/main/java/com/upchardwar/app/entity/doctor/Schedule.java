package com.upchardwar.app.entity.doctor;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private Long id;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	@ElementCollection
    @CollectionTable(
        name = "days", // Name of the join table
        joinColumns = @JoinColumn(name = "scidfk") // Name of the foreign key column
    )
	private List<String> days;
	
	private Boolean isActive;
	
	private Boolean isDeleted;
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "schedule")
	private List<TimeSlote> timeSlotes;

	
}
