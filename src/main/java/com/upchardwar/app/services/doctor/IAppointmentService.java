package com.upchardwar.app.services.doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.upchardwar.app.dto.AppointmentDto;
import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.patient.Patient;

public interface IAppointmentService {
	// public Map<String, Object> bookAppointment(Doctor doctor, Patient patient, LocalDate appointmentDate, LocalTime appointmentTime);
	 
	public Map<String, Object> todaysAppointment();
	
	public Map<String, Object> upcomingAppointment();
	
	public Map<String, Object> getAppointmentDetails(Long appointmentId) ;
	
	public Map<String, Object>  cancelAppointment(Long appointmentId);
	
	public Map<String, Object> rescheduledAppointment(AppointmentDto appointmentDto);
	
	public Map<String, Object> updateAppointment( AppointmentDto updatedAppointmentDto);
	
	 public Map<String, Object> notifyPatientBeforeAppointment();

	 public Map<String, Object> bookAppointment(AppointmentDto appointmentDTO);
	 
	 public Map<String , Object> countTotalPatientOfDoctor(String email);
	 
	 public Map<String, Object> countTodaysTotalPatientOfDoctor(String email);
	 
	

     public	Map<String, Object> doctorsUpcomingTotalAppointment(String email);
	 
}
