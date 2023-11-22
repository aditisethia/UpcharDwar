package com.upchardwar.app.services.impl.doctor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.Speciality;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.SpecialityRequest;
import com.upchardwar.app.entity.payload.SpecialityResponse;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.DoctorRepository;
import com.upchardwar.app.services.doctor.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private ModelMapper modelMapper;

	public DoctorResponse doctorToDoctorResponse(Doctor doctor) {
		return this.modelMapper.map(doctor, DoctorResponse.class);
	}

	public Doctor doctorRequestToDoctor(DoctorRequest doctorRequest) {
		return this.modelMapper.map(doctorRequest, Doctor.class);
	}

	@Override
	public DoctorResponse createDoctor(DoctorRequest request) {
		Optional<Doctor> s = this.doctorRepository.findByDrName(request.getDrName());

		if (s.isPresent())
			throw new ResourceAlreadyExistException("this doctor already exist");

		Doctor dr = this.doctorRequestToDoctor(request);

		return this.doctorToDoctorResponse(this.doctorRepository.save(dr));
	}

	@Override
	public DoctorResponse getDoctorById(Long id) {
		Optional<Doctor>  s=this.doctorRepository.findById(id);
		if(s.isPresent()) 
			return this.doctorToDoctorResponse(s.get());
		
			throw new ResourceNotFoundException("Doctor with this id not exist");
		
	}

	@Override
	public String deleteDoctorById(Long id) {
Optional<Doctor>  s=this.doctorRepository.findById(id);
	    
		if(s.isEmpty()) {
			throw new ResourceNotFoundException("Doctor with this id not exist");
		}
		   this.doctorRepository.delete(s.get());	
          		return "deleted successfully";
	}

	@Override
	public Page<DoctorResponse> getAllDoctor(Integer pageNo, Integer pageSize) {
		PageRequest page = PageRequest.of(pageNo, pageSize);
        Page<Doctor> pag = this.doctorRepository.findAll(page);
	 return pag.map(u ->this.doctorToDoctorResponse(u));
	}

	@Override
	public List<DoctorResponse> searchDoctor(Integer pageNo, Integer pageSize, DoctorRequest doctorRequest,
			String sortBy) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Match anywhere in the string
                .withIgnoreCase() // Ignore case when matching strings
                .withMatcher("id", match->match.transform(value->value.map(id->((Integer)id==0)?null:id)));
		
		Example<Doctor> example = Example.of(doctorRequestToDoctor(doctorRequest),exampleMatcher);
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC,sortBy);
		Page<Doctor> findAllDoctor = this.doctorRepository.findAll(example, pageable);
		return findAllDoctor.getContent().stream().map(s->doctorToDoctorResponse(s)).collect(Collectors.toList());
		
	}

	@Override
	public DoctorResponse updateDoctor(DoctorRequest request) {
		 Doctor doc=this.doctorRepository.save(this.doctorRequestToDoctor(request));
		return this.doctorToDoctorResponse(doc);
	}

}
