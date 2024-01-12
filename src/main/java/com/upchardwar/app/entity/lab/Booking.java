package com.upchardwar.app.entity.lab;

import java.time.LocalDateTime;

import com.upchardwar.app.entity.doctor.PatientAppointmentFile;
import com.upchardwar.app.entity.patient.Patient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long bookingId;

	    @ManyToOne
	    @JoinColumn(name = "patient_id")
	    private Patient patient;

	    @ManyToOne
	    @JoinColumn(name = "test_id")
	    private LabTest labTest;

	    private LocalDateTime bookingTime;
	    
	    private boolean confirmed;

	    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	    private LabReport labTestReport;

	    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	    private LabPayment payment;

	    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
	    private LabInvoice invoice;
	    

	
}
