package com.upchardwar.app.services;

import java.util.List;

import org.springframework.data.domain.Page;


import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;

public interface IPatientService {
	public PatientResponse createPatient(PatientRequest request);

	public PatientResponse getPatientById(Long id);

	public String deletePatientById(Long id);

	public Page<PatientResponse> getAllPatient(Integer pageNo, Integer pageSize);

	public List<PatientResponse> searchPatient(Integer pageNo, Integer pageSize, PatientRequest patientRequest,
			String sortBy);

	public PatientResponse updatePatient(PatientRequest request);
}
