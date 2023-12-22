package com.upchardwar.app.entity.payload;

import java.time.LocalTime;
import java.util.List;

import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.doctor.Doctor;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    
	private Long id;
	
	private String patientName;
	
	private String password;
	
	
	private String address;
	
	private String email;
	
	private String mobile;
	
	private String age;
	
	 private String city;
	    
	    private String country;
	    
	    private String zipcode;
	    
	    private String state;
	    
	    private String bloodGroup;
	    
	    public String documentType;
		
	
		
		
	
}
