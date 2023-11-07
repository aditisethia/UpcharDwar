package com.upchardwar.app.entity.doctor;

import java.time.LocalDate;
import java.util.List;

import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.InvoiceStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long drinvoiceId;

	private LocalDate invoiceGenerDate;

	private float amount;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<InvoiceStatus> invoiceStatus;

	private String paymentMethod;

	@ManyToOne
	private Doctor doctor;
	
	@ManyToOne
	private Patient patient;
     
	@OneToOne(mappedBy = "doctorInvoice")
	private Appointment appointment;
}
