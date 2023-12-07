package com.upchardwar.app.services.impl.lab;

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
import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotApprovedException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.LabRepository;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.services.lab.ILabService;

@Service
public class LabServiceImpl implements ILabService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LabRepository labRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	

	public LabResponse labToLabResponse(Lab lab) {
		return this.modelMapper.map(lab, LabResponse.class);
	}

	public Lab labRequestToLab(LabRequest labRequest) {
		return this.modelMapper.map(labRequest, Lab.class);
	}

	@Override
	public LabResponse registerLab(LabRequest labRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabResponse updateLab(LabRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabResponse> searchLab(Integer pageNo, Integer pageSize, LabRequest labRequest, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteLabById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LabResponse> getAllLab(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public LabResponse registerLab(LabRequest request) {
//		Optional<Lab> s = this.labRepository.findByLabName(request.getLabName());
//
//		if (s.isPresent())
//			throw new ResourceAlreadyExistException(AppConstant.LAB_ALREADY_EXIST);
//
//		Lab l =this.labRequestToLab(request);
//		
//		User user=new User();
//		user.setName(l.getLabName());
//		user.setEmail(l.getEmail());
//		user.setPassword(l.getPassword());
//		String encPwd = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encPwd);
//		user.setPhone(l.getPhone());
//		Set<UserRole> roles=new HashSet();
//	     Role role=new Role();
//	     role.setRoleId(4L);     
//	     UserRole userRole = new UserRole();
//	     userRole.setRole(role);
//	     userRole.setUser(user);
//	     roles.add(userRole);
//        user.setUserRole(roles);
//       
//        this.userRepository.save(user);
//
//		return this.labToLabResponse(this.labRepository.save(l));
//	}
//    
//	public LabResponse getLabById(Long id) {
//
//		Optional<Lab> s = this.labRepository.findByIdAndIsapproved(true,id);
//		if (s.isPresent())
//
//			return this.labToLabResponse(s.get());
//
//		throw new ResourceNotFoundException(AppConstant.LAB_WITH_ID_NOT_EXIST);
//
//	}
//
//	@Override
//	public String deleteLabById(Long id) {
//		Optional<Lab> s = this.labRepository.findById(id);
//
//		if (s.isEmpty()) {
//			throw new ResourceNotFoundException(AppConstant.LAB_WITH_ID_NOT_EXIST);
//		}
//		this.labRepository.delete(s.get());
//		return "deleted successfully";
//	}
//
//	@Override
//	public Page<LabResponse> getAllLab(Integer pageNo, Integer pageSize) {
//		PageRequest page = PageRequest.of(pageNo, pageSize);
//		Page<Lab> pag = this.labRepository.findByIsApproved(true,page);
//		return pag.map(u -> this.labToLabResponse(u));
//	}
//
//	@Override
//	public List<LabResponse> searchLab(Integer pageNo, Integer pageSize, LabRequest labRequest,
//			String sortBy) {
//		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
//				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Match anywhere in the string
//				.withIgnoreCase() // Ignore case when matching strings
//				.withMatcher("id", match -> match.transform(value -> value.map(id -> ((Integer) id == 0) ? null : id)));
//       
//		Example<Lab> example = Example.of(labRequestToLab(labRequest), exampleMatcher);
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);
//		Page<Lab> findAllLab = this.labRepository.findAll(example, pageable);
//		return findAllLab.getContent().stream().map(s -> labToLabResponse(s)).collect(Collectors.toList());
//
//	}
//
//	@Override
//	public LabResponse updateLab(LabRequest request) {
//
//		if (!request.getIsApproved()) {
//			throw new ResourceNotApprovedException(AppConstant.LAB_NOT_APPROVED);
//		}
//		Lab lab = this.labRepository.save(this.labRequestToLab(request));
//		return this.labToLabResponse(lab);
//	}

}
