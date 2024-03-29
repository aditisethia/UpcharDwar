package com.upchardwar.app.controller.doctor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.services.doctor.IDoctorService;

@RestController
@RequestMapping("/upchardwar/doctor")
@CrossOrigin("*")
public class DoctorController {
	@Autowired
	private IDoctorService doctorService;

	// to create Doctor
	// @PostMapping("/save")
	@PostMapping(path = "/save", consumes = { "multipart/form-data", "application/octet-stream" })
	public ResponseEntity<?> addDoctor(@RequestPart("data") DoctorRequest doctorRequest,
			@RequestPart(value = "files[]", required = false) List<MultipartFile> multipartFiles) {

		return new ResponseEntity<>(this.doctorService.createDoctor(doctorRequest, multipartFiles), HttpStatus.OK);

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

	@PostMapping(path = "/save1", consumes = { "multipart/form-data", "application/octet-stream" })
	public ResponseEntity<?> addDoctor2(@RequestPart("data") DoctorRequest request,
			@RequestPart("files") List<MultipartFile> multipartFiles) {
		return doctorService.addDoctor(request, multipartFiles.get(0), multipartFiles);
		// Adjust the response based on your use case
	}

	@GetMapping("userid/{userId}")
	public ResponseEntity<?> getDoctorByUser(@PathVariable("userId") Long userId) {
		return doctorService.getDoctorByUserId(userId);
	}

	@GetMapping("/doctors/by-keyword")
	public List<Doctor> filterDoctors(@RequestParam String keyword) {
		return doctorService.filterDoctorsByKeyword(keyword);
	}

	@GetMapping("/cities")
	public ResponseEntity<List<String>> getAllCities() {
		List<String> uniqueCities = doctorService.getAllDoctorses().stream().map(Doctor::getCity).distinct()
				.collect(Collectors.toList());

		return ResponseEntity.ok(uniqueCities);
	}
}
