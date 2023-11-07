package com.upchardwar.app.entity.doctor;

import java.util.List;

import org.apache.logging.log4j.message.Message;

import com.upchardwar.app.entity.Conversation;
import com.upchardwar.app.entity.Messages;
import com.upchardwar.app.entity.lab.LabReviewRating;
import com.upchardwar.app.entity.pharma.PharmaReviewRating;

import jakarta.persistence.Entity;
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
public class Doctor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long drId;
	
	private String drName;
	
	private Long drAge;
	
	private String drQualifications;
	
	private String drAchivement;
	
	private Long drExperience;
	
	@ManyToOne
	private Speciality speciality;
	
	@OneToMany(mappedBy = "doctor")
	private List<Schedule> schedules;
	
	@OneToMany(mappedBy = "doctor")
	private List<DoctorInvoice> doctorInvoices;
	
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments;
	
	@OneToMany(mappedBy = "doctor")
	private List<PatientAppointmentFile> appointmentFiles;
	
    @OneToMany(mappedBy = "doctor")
	private List<Prescription> prescriptions;
    
    @OneToMany(mappedBy = "doctor")
	private List<PharmaReviewRating> pharmaReviewRatings;
    
    
    @OneToMany(mappedBy = "doctor")
    private List<LabReviewRating> labReviewRatings;
    
    
    @OneToMany(mappedBy = "doctor")
    private List<Messages> messages;
    
    @OneToMany(mappedBy = "doctor")
    private List<Conversation> conversations;
    
    
    
}
