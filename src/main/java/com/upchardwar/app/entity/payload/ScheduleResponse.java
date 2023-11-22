package com.upchardwar.app.entity.payload;

import java.time.LocalTime;
import java.util.List;

import com.upchardwar.app.entity.doctor.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
private Long id;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private List<String> days;
	
	private Boolean isActive;
	
	private Boolean isDeleted;
	
	private Doctor doctor;
}
