package com.upchardwar.app.services.doctor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.payload.AppointmentRequest;

public interface IAppointmentService {
	// public Map<String, Object> bookAppointment(Doctor doctor, Patient patient,
	// LocalDate appointmentDate, LocalTime appointmentTime);

	public Map<String, Object> bookAppointment(Appointment appointment);
	
    Page<AppointmentRequest> getAppointmentsByDoctorId(Long doctorId, Pageable pageable);
    
    Page<AppointmentRequest> getAppointmentsByPatientId(Long patientId, Pageable pageable);

	List<Appointment> findAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate appointmentDate);

	 
	List<Appointment> findUpcomingAppointmentsByDoctorId(Long doctorId, LocalDate startDate);

}
