package com.upchardwar.app.entity.lab;

import java.util.List;

import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.pharma.PharmaPayment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LabTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String testName;
	
	
	private Long rates; 
	
	@ManyToOne
	private Patient patient;
	
	@ManyToOne
	private LabRequests labReq;

	@OneToMany(mappedBy = "labTest",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<LabPayment> labPayments;
	
}
