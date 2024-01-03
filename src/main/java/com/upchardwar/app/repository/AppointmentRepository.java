package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	
}





//
//@Query("SELECT new com.upchardwar.app.dto.TodaysAppointmentDto(" + "a.id, " + "p.patientName, "
//		+ "a.appointmentDate, " + "a.purpose, " + "p.id, " + "a.paidAmount, " + "a.status) " + "FROM Appointment a "
//		+ "JOIN a.patient p " + "WHERE a.appointmentDate = :today")
//
//List<TodaysAppointmentDto> findByAppointmentDate(LocalDate today);
//
//@Query("SELECT new com.upchardwar.app.dto.TodaysAppointmentDto(" + "a.id, " + "p.patientName, "
//		+ "a.appointmentDate, " + "a.purpose, " + "p.id, " + "a.paidAmount, " + "a.status) " + "FROM Appointment a "
//		+ "JOIN a.patient p " + "WHERE a.appointmentDate > :today "
//		+ "ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
//List<TodaysAppointmentDto> findByAppointmentDateAfterOrderByAppointmentDateAscAppointmentTimeAsc(LocalDate today);
//
//List<Appointment> getByAppointmentDate(LocalDate date);
//
//@Query("SELECT COUNT(DISTINCT a.patient) FROM Appointment a WHERE a.doctor.email = :email")
//Long countPatientsForDoctor(String email);
//
//@Query("SELECT COUNT(a.patient) FROM Appointment a where a.doctor.email =:email AND a.appointmentDate =:date ")
//Long countTodaysTotalPatientForDoctor(String email, LocalDate date);
//
//@Query("SELECT COUNT(*)  FROM Appointment a where a.doctor.email =:email And Date(a.appointmentDate) >=:today ORDER BY a.appointmentDate ASC, a.appointmentTime ASC ")
//Long countUpcomingAppointments(LocalDate today, String email);
//
//Page<Appointment> findByDoctorId(Long doctorId, Pageable pageable);
//
//@Query("SELECT a FROM Appointment a where a.patient.email=:email")
//Page<Appointment> findByEmail(String email, Pageable pageable);
//


