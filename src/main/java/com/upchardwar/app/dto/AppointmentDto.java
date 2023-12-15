package com.upchardwar.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.upchardwar.app.entity.doctor.Doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class AppointmentDto {
	private Long id;
	private String patientName;
    private LocalDate appointmentDate;
    private String purpose;
    private Float PaidAmount;
    private String status;
    private LocalTime appointmentTime; 
    private  Doctor doctor;
    private String email;
}
