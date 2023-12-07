package com.upchardwar.app.entity.doctor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.message.Message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upchardwar.app.entity.Conversation;
import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.Messages;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.lab.LabReviewRating;
import com.upchardwar.app.entity.pharma.PharmaReviewRating;
import com.upchardwar.app.entity.status.AppConstant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Doctor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String drName;
	
	private Long drAge;
	
	private String drQualifications;
	
	private Long drExperience;
	
	private String password;
	
	private String email;
	
	private String phone;
	
	private String status=AppConstant.DOCTOR_STATUS_NEW;
	
	private Boolean isRejected=false;
	
	
	@ManyToOne
	private Speciality speciality;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<Schedule> schedules;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<DoctorInvoice> doctorInvoices;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<PatientAppointmentFile> appointmentFiles;
	
	@JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Prescription> prescriptions;
    
    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<PharmaReviewRating> pharmaReviewRatings;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<LabReviewRating> labReviewRatings;
    
    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Messages> messages;
    
    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Conversation> conversations;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<Achievements> achievements;
    
}
