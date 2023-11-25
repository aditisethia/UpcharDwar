package com.upchardwar.app.services.impl.lab;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
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
	public LabResponse registerLab(LabRequest request) {
		Optional<Lab> s = this.labRepository.findByLabName(request.getLabName());

		if (s.isPresent())
			throw new ResourceAlreadyExistException(AppConstant.LAB_ALREADY_EXIST);

		Lab l =this.labRequestToLab(request);
		
		User user=new User();
		user.setName(l.getLabName());
		user.setEmail(l.getEmail());
		user.setPassword(l.getPassword());
		String encPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);
		user.setPhone(l.getPhone());
		Set<UserRole> roles=new HashSet();
	     Role role=new Role();
	     role.setRoleId(4L);     
	     UserRole userRole = new UserRole();
	     userRole.setRole(role);
	     userRole.setUser(user);
	     roles.add(userRole);
        user.setUserRole(roles);
       
       
        this.userRepository.save(user);
      		
		

		return this.labToLabResponse(this.labRepository.save(l));
	}

}
