package com.upchardwar.app.entity.payload;

import java.util.List;

import com.upchardwar.app.entity.doctor.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
	
	private Long id;

	private Boolean isDeleted;

	private Doctor doctor;

	private String selectedDate;
	
	private List<TimeSlotRequest> timeSlots;
	
	private Boolean isActive;

}
