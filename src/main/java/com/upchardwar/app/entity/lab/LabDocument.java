package com.upchardwar.app.entity.lab;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.status.LabStatus;

import jakarta.persistence.CascadeType;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String docType;
	
	private String docOriginalName;
	
	private String filename;
	
	private LocalDate uploadDate;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<LabStatus> labStatus;
	
	@ManyToOne
	private Lab lab;

}
