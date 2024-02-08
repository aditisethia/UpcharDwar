package com.upchardwar.app.entity.lab;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.InvoiceStatus;
import com.upchardwar.app.entity.status.LabStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LabInvoice {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long invoiceId;

	    @OneToOne
	    @JoinColumn(name = "booking_id")
	    private Booking booking;

	    @ManyToOne
	    @JoinColumn(name = "lab_test_id")
	    private LabTest labTest;

	    private Long totalAmount;
	    
	    private LocalDateTime issueTime;
	    
	    @ManyToOne
	    @JoinColumn(name="patient_id")
		@JsonIgnoreProperties(value="labInvoices")
	    private Patient patient;
	    
}
