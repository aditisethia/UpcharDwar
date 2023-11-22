package com.upchardwar.app.entity.lab;

import java.util.List;

import com.upchardwar.app.entity.Location;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Lab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String labName;
	
	private String email;
	
	private String password;
	
	@OneToOne
	private Location location;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lab")
	private List<LabReq> labReq;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="lab")
	private List<LabDocument> labDocument;
	
	@OneToMany(mappedBy = "lab",cascade = CascadeType.ALL)
	private List<LabReport> labReports;

	@OneToMany(mappedBy = "lab",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<LabInvoice> labinvoices;
	
	@OneToMany(mappedBy = "lab",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<LabPayment> labPayments;
	
	@OneToMany(mappedBy = "lab",cascade = CascadeType.ALL)
	private List<LabReviewRating> reviewRatings;
	
	@OneToMany(mappedBy = "lab",cascade = CascadeType.ALL)
	private List<LabTestRate> labTestRates;
	
	
}
