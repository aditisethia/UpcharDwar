package com.upchardwar.app.services.doctor;

import java.util.List;

import org.springframework.data.domain.Page;

import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.SpecialityRequest;
import com.upchardwar.app.entity.payload.SpecialityResponse;

public interface IDoctorService {
	public DoctorResponse createDoctor(DoctorRequest request);

	public DoctorResponse getDoctorById(Long id);

	public String deleteDoctorById(Long id);

	public Page<DoctorResponse> getAllDoctor(Integer pageNo, Integer pageSize);

	public List<DoctorResponse> searchDoctor(Integer pageNo, Integer pageSize, DoctorRequest doctorRequest,
			String sortBy);

	public DoctorResponse updateDoctor(DoctorRequest request);

}
