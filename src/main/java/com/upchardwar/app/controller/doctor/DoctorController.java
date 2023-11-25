package com.upchardwar.app.controller.doctor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.doctor.Speciality;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.SpecialityRequest;
import com.upchardwar.app.entity.payload.SpecialityResponse;
import com.upchardwar.app.repository.SpecialityRepository;
import com.upchardwar.app.services.doctor.IDoctorService;
import com.upchardwar.app.services.impl.doctor.DoctorServiceImpl;

@RestController
@RequestMapping("/upchardwar/doctor")
public class DoctorController {
	@Autowired
	private IDoctorService doctorService;

	

	// to create Doctor
	@PostMapping("/save")
	public ResponseEntity<DoctorResponse> addDoctor(@RequestBody DoctorRequest doctorRequest) {
	
		return new ResponseEntity<DoctorResponse>(this.doctorService.createDoctor(doctorRequest),
				HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorResponse> getDoctor(@PathVariable("id") Long id) {
		return new ResponseEntity<DoctorResponse>(this.doctorService.getDoctorById(id), HttpStatus.OK);
	}

	// to delete specific by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable("id") Long id) {
		return new ResponseEntity<String>(this.doctorService.deleteDoctorById(id), HttpStatus.OK);
	}

	@GetMapping("/{pageNo}/{pageSize}")
	public ResponseEntity<Page<DoctorResponse>> getAllDoctor(@PathVariable("pageNo") Integer pageNo,
			@PathVariable("pageSize") Integer pageSize) {
		Page<DoctorResponse> dr = this.doctorService.getAllDoctor(pageNo, pageSize);
		return new ResponseEntity<Page<DoctorResponse>>(dr, HttpStatus.OK);
	}

	@PostMapping("/search/{pageNo}/{pageSize}/{sortBy}")
	public ResponseEntity<List<DoctorResponse>> search(@RequestBody DoctorRequest request,
			@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize,
			@PathVariable("sortBy") String sortBy) {

		List<DoctorResponse> sr = this.doctorService.searchDoctor(pageNo, pageSize, request, sortBy);
		return new ResponseEntity<List<DoctorResponse>>(sr, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<DoctorResponse> update(@RequestBody DoctorRequest request) {

		return new ResponseEntity<DoctorResponse>(this.doctorService.updateDoctor(request), HttpStatus.OK);
	}

}
