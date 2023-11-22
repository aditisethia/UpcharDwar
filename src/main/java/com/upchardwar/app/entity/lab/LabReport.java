package com.upchardwar.app.entity.lab;

import com.upchardwar.app.entity.patient.Patient;

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
public class LabReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String originalReport;

	private String fileName;
	@ManyToOne
	private Patient patient;

	@ManyToOne
	private Lab lab;

}
