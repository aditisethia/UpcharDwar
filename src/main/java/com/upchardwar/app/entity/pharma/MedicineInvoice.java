package com.upchardwar.app.entity.pharma;

import java.time.LocalDate;
import java.util.List;

import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.PharmaStatus;

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
public class MedicineInvoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medInvoiceId;

	private LocalDate invoiceGenerDate;

	private float amount;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<PharmaStatus> pharmaStatusts ;

	private String paymentMethod;

	@ManyToOne
	private Pharmacy pharmacy;

	@ManyToOne
	private Patient patient;
	
	@OneToOne
	private MedicineOrder medicineOrder;
}
