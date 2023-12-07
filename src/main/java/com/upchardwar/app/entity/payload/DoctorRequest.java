package com.upchardwar.app.entity.payload;

import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.doctor.Speciality;
import com.upchardwar.app.entity.status.AppConstant;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
	private Long id;

	private String drName;

	private Long drAge;

	private String drQualifications;

	private Long drExperience;
	
	private String email;
	
	private String password;
	
	private String phone;

	private Speciality speciality;
	
	private String status=AppConstant.DOCTOR_NOT_APPROVED;
	
	private Location location;

}
