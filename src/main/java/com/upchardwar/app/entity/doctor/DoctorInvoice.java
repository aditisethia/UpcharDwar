package com.upchardwar.app.entity.doctor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.AppConstant;
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
	private Long id;

	private LocalDate invoiceGenerateDate;

	private float amount;

	private String invoiceStatus=AppConstant.INVOICE_STATUS_AWAITED;

	private String paymentMethod;

	@ManyToOne
	private Doctor doctor;
	
	@ManyToOne
	private Patient patient;
     
	@JsonIgnore
	@OneToOne(mappedBy = "doctorInvoice")
	private Appointment appointment;
}
