package com.upchardwar.app.entity.payload;

import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.doctor.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
	private Long id;

	private String drName;

	private Long drAge;

	private String drQualifications;


	private Long drExperience;

	private Speciality speciality;
	
	private String email;
	
	private String password;
	
	private String phone;
	
	private Boolean isApproved=false;
	
	private Location location;
}
