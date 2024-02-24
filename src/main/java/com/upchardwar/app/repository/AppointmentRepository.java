package com.upchardwar.app.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.status.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("SELECT a FROM Appointment a WHERE a.timeslote.id=:aid ")
	Appointment findAllAppointmentByTimeSlotId(Long aid);
	

    List<Appointment> findByDoctorId(Long doctorId);
    Page<Appointment> findByDoctorId(Long doctorId, Pageable pageable);
    
    List<Appointment> findByPatientId(Long patientId);
    Page<Appointment> findByPatientId(Long patientId, Pageable pageable);


    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
  
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate >= :startDate")
    List<Appointment> findByDoctorIdAndAppointmentDateAfter(Long doctorId, LocalDate startDate);
    
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.status >= :status")
    List<Appointment> findByDoctorIdAndAppointmentStatus(Long doctorId, AppointmentStatus status);

    

}




