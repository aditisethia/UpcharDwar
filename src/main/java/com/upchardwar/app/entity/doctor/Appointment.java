package com.upchardwar.app.entity.doctor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.upchardwar.app.entity.patient.Patient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
    
    private LocalDate appointmentDate;
    
    private LocalTime appointmentTime;
    
    private String status;
    
    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Doctor doctor;
    
    @OneToOne(cascade = CascadeType.ALL)
    private DoctorInvoice doctorInvoice;
    
    @ManyToOne
    private PatientAppointmentFile patientAppointmentFile;
 
    
}
