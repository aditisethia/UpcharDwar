package com.upchardwar.app.services.impl.doctor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.Speciality;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.SpecialityRequest;
import com.upchardwar.app.entity.payload.SpecialityResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotApprovedException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.DoctorRepository;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.services.doctor.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public DoctorResponse doctorToDoctorResponse(Doctor doctor) {
		return this.modelMapper.map(doctor, DoctorResponse.class);
	}

	public Doctor doctorRequestToDoctor(DoctorRequest doctorRequest) {
		return this.modelMapper.map(doctorRequest, Doctor.class);
	}

	@Override
	public DoctorResponse createDoctor(DoctorRequest request) {

		Optional<Doctor> s = this.doctorRepository.findByEmail(request.getEmail());

		if (s.isPresent())
			throw new ResourceAlreadyExistException("this doctor already exist");

		Doctor dr = this.doctorRequestToDoctor(request);

		User user = new User();
		user.setName(dr.getDrName());
		user.setEmail(dr.getEmail());
		user.setPassword(dr.getPassword());
		String encPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);
		
		Set<UserRole> roles = new HashSet();
		Role role = new Role();
		role.setRoleId(2L);
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		roles.add(userRole);
		user.setUserRole(roles);
		this.userRepository.save(user);
		

		return this.doctorToDoctorResponse(this.doctorRepository.save(dr));
	}

	@Override
	public DoctorResponse getDoctorById(Long id) {

		Optional<Doctor> s = this.doctorRepository.findByIdAndStatus(AppConstant.DOCTOR_STATUS_APPROVED,id);
		if (s.isPresent())

			return this.doctorToDoctorResponse(s.get());

		throw new ResourceNotFoundException(AppConstant.DOCTOR_WITH_ID_NOT_EXIST);

	}

	@Override
	public String deleteDoctorById(Long id) {
		Optional<Doctor> s = this.doctorRepository.findById(id);

		if (s.isEmpty()) {
			throw new ResourceNotFoundException(AppConstant.DOCTOR_WITH_ID_NOT_EXIST);
		}
		this.doctorRepository.delete(s.get());
		return "deleted successfully";
	}

	@Override
	public Page<DoctorResponse> getAllDoctor(Integer pageNo, Integer pageSize) {
		PageRequest page = PageRequest.of(pageNo, pageSize);
		Page<Doctor> pag = this.doctorRepository.findByStatus(AppConstant.DOCTOR_STATUS_APPROVED,page);
		
		return pag.map(u -> this.doctorToDoctorResponse(u));
	}

	@Override
	public List<DoctorResponse> searchDoctor(Integer pageNo, Integer pageSize, DoctorRequest doctorRequest,
			String sortBy) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Match anywhere in the string
				.withIgnoreCase() // Ignore case when matching strings
				.withMatcher("id", match -> match.transform(value -> value.map(id -> ((Integer) id == 0) ? null : id)));
       
		Example<Doctor> example = Example.of(doctorRequestToDoctor(doctorRequest), exampleMatcher);
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);
		Page<Doctor> findAllDoctor = this.doctorRepository.findAll(example, pageable);
		return findAllDoctor.getContent().stream().map(s -> doctorToDoctorResponse(s)).collect(Collectors.toList());

	}

	@Override
	public DoctorResponse updateDoctor(DoctorRequest request) {

		if (request.getStatus()==AppConstant.DOCTOR_NOT_APPROVED) {
			throw new ResourceNotApprovedException(AppConstant.DOCTOR_NOT_APPROVED);
		}
		Doctor doc = this.doctorRepository.save(this.doctorRequestToDoctor(request));
		return this.doctorToDoctorResponse(doc);
	}

}
