package com.upchardwar.app.entity.lab;

import java.time.LocalDate;
import java.util.List;

import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.status.LabStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabRequests {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Patient patient;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "labReq")
	private List<LabTest> labTest;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<LabStatus> labStatus;
	
	@ManyToOne
	private Lab lab; 
	
	private LocalDate requestDate;
}
