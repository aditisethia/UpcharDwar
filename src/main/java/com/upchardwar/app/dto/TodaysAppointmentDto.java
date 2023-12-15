package com.upchardwar.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodaysAppointmentDto {
	    private Long id;
	    private String patientName;
	    private LocalDate appointmentDate;
	    private String purpose;
	    private Float paidAmount;
	    private String status;
}
