package com.upchardwar.app.entity.patient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.message.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.upchardwar.app.entity.Conversation;
import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.Messages;
import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.doctor.DoctorInvoice;
import com.upchardwar.app.entity.doctor.DoctorReviewRating;
import com.upchardwar.app.entity.doctor.PatientAppointmentFile;
import com.upchardwar.app.entity.doctor.Prescription;
import com.upchardwar.app.entity.lab.LabDocument;
import com.upchardwar.app.entity.lab.LabInvoice;
import com.upchardwar.app.entity.lab.LabPayment;
import com.upchardwar.app.entity.lab.LabReport;
import com.upchardwar.app.entity.lab.LabRequests;
import com.upchardwar.app.entity.lab.LabReviewRating;
import com.upchardwar.app.entity.lab.LabTest;
import com.upchardwar.app.entity.pharma.PharmaRequest;
import com.upchardwar.app.entity.pharma.PharmaReviewRating;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String patientName;
	
	private String password;
	
	private String email;
	
	private String mobile;
	
	private String age;
	
    private String address;
    
    private String city;
    
    private String country;
    
    private Float paidAmount; 
    
    private String zipcode;
    
    private String state;
    
    private String bloodGroup;
    
    
	public String documentType;
	
	public String imageName;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	private List<LabTest> labTest;
	
	@OneToMany(mappedBy = "patient")
	private Set<LabReport> labReport;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	private List<LabInvoice> labInvoices;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	 @JsonIgnoreProperties(value = {"patient"})
	private List<DoctorInvoice> doctorInvoices;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
	private List<Appointment> appointments;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
	private List<PatientAppointmentFile> appointmentFiles;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
	private List<Prescription> Prescriptions;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
	private List<LabPayment> labPayments;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
	private List<PharmaRequest>  pharma_requests;
    
    @OneToMany(mappedBy = "patient" ,cascade = CascadeType.ALL)
	private List<PharmaReviewRating> pharmaReviewRatings;
    
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<LabReviewRating> labReviewRatings;
    
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<DoctorReviewRating> doctorReviewRatings;
    
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Messages> messages;
    
    @OneToMany(mappedBy = "patient" ,cascade = CascadeType.ALL)
    private List<Conversation> conversations;
    
    
}
