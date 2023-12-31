package com.upchardwar.app.services.impl.patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;

import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.PatientRequest;
import com.upchardwar.app.entity.payload.PatientResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.PatientRepository;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.services.IPatientService;


@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
	private PatientRepository patientRepository;
    
    @Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	

	public PatientResponse patientToPatientResponse(Patient patient) {
		return this.modelMapper.map(patient, PatientResponse.class);
	}

	public Patient patientRequestToPatient(PatientRequest patientRequest) {
		return this.modelMapper.map(patientRequest, Patient.class);
	}

	@Override
	public PatientResponse createPatient(PatientRequest request) {
		Optional<Patient> s = this.patientRepository.findByEmail(request.getEmail());

		if (s.isPresent())
			throw new ResourceAlreadyExistException(AppConstant.THIS_PATIENT_ALREADY_EXIST);

		Patient p =this.patientRequestToPatient(request);
		
//		User user=new User();
//		user.setName(p.getPatientName());
//		user.setEmail(p.getEmail());
//		user.setPassword(p.getPassword());
//		String encPwd = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encPwd);
//		Set<UserRole> roles=new HashSet();
//	     Role role=new Role();
//	     role.setRoleId(2L);     
//	     UserRole userRole = new UserRole();
//	     userRole.setRole(role);
//	     userRole.setUser(user);
//	     roles.add(userRole);
//        user.setUserRole(roles);
//     
//       
//        this.userRepository.save(user);
      		
		

		return this.patientToPatientResponse(this.patientRepository.save(p));
		
	}



	@Override
	public ResponseEntity<?> addPatient(PatientRequest request, MultipartFile file) {
		Optional<User> u= this.userRepository.findByEmail(request.getEmail());
		if(u.isPresent())
			u.get().setStatus(AppConstant.User_verified);
		
		else
			throw new ResourceNotFoundException(AppConstant.USER_NOT_FOUND);
		System.out.println("hvs");
	   Map<String,Object> response =new HashMap<>();
		Patient p =this.patientRequestToPatient(request);
		   String imageName = UUID.randomUUID().toString()+file.getOriginalFilename();
		   p.setImageName(imageName);

		
		if(file!=null) {
		
			String filename=StringUtils.cleanPath(imageName);
			p.setDocumentType(file.getContentType());
		      
			Path fileStorage=  Paths.get(AppConstant.DIRECTORY, filename).toAbsolutePath().normalize();
			
			try {
				Files.copy(file.getInputStream(),fileStorage,StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				
			}
		}
			
			response.put(AppConstant.MESSAGE,AppConstant.PATIENT_CREATED);
		PatientResponse res=this .patientToPatientResponse(this.patientRepository.save(p));
			response.put(AppConstant.PATIENT,res );
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	

	@Override
	public PatientResponse getPatientById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePatientById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PatientResponse> getAllPatient(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientResponse> searchPatient(Integer pageNo, Integer pageSize, PatientRequest patientRequest,
			String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientResponse updatePatient(PatientRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
