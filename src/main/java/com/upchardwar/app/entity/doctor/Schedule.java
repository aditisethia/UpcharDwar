package com.upchardwar.app.entity.doctor;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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

	private Date selectedDate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
	private List<TimeSlote> timeSlots;

	private Boolean isActive;

	private Boolean isDeleted;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

}
