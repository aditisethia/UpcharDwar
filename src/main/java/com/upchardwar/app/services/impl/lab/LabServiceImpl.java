package com.upchardwar.app.services.impl.lab;

<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
<<<<<<< HEAD
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
=======
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import com.upchardwar.app.entity.Location;
import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.DoctorDocument;
import com.upchardwar.app.entity.doctor.DoctorQualification;
import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabDocument;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
=======
import com.upchardwar.app.entity.lab.Lab;
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.entity.status.AppConstant;
<<<<<<< HEAD
import com.upchardwar.app.exception.BadRequestException;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotApprovedException;
import com.upchardwar.app.exception.ResourceNotFoundException;
=======
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b
import com.upchardwar.app.repository.LabRepository;
import com.upchardwar.app.repository.LocationRepository;
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

	@Autowired
	private LocationRepository locationRepository;

	public LabResponse labToLabResponse(Lab lab) {
		return this.modelMapper.map(lab, LabResponse.class);
	}

	public Lab labRequestToLab(LabRequest labRequest) {
		return this.modelMapper.map(labRequest, Lab.class);
	}

	@Override
<<<<<<< HEAD
=======
	public ResponseEntity<?> registerLab(LabRequest labRequest) {
		Map<String, Object> response = new HashMap<>();
		// TODO Auto-generated method stub
		Lab l = this.labRequestToLab(labRequest);
		   LabResponse lr= labToLabResponse(this.labRepository.save(l));
	      response.put(AppConstant.LAB_CREATION, lr);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	
	}

	@Override
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b
	public LabResponse updateLab(LabRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabResponse> searchLab(Integer pageNo, Integer pageSize, LabRequest labRequest, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

<<<<<<< HEAD
	// @Override
=======
	@Override
	public String deleteLabById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Page<LabResponse> getAllLab(Integer pageNo, Integer pageSize) {
        PageRequest paging = PageRequest.of(pageNo, pageSize);
        Page<Lab> pagedResult = labRepository.findAll(paging);

        return  pagedResult.map(this::labToLabResponse);
    }
	
//	@Override
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b
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
	public ResponseEntity<?> deleteLabById(Long id) {
		Map<String, Object> response = new HashMap<>();

		Optional<Lab> s = this.labRepository.findById(id);

		if (s.isPresent()) {
			Lab l = s.get();
			l.setIsDeleted(true);

			System.out.println("dfghj");

			labRepository.save(l);
		} else {
			throw new ResourceNotFoundException(AppConstant.LAB_WITH_ID_NOT_EXIST);
		}

		response.put(AppConstant.MESSAGE, "deleted successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

//
	@Override
	public Page<LabResponse> getAllLab(Integer pageNo, Integer pageSize) {
		PageRequest page = PageRequest.of(pageNo, pageSize);
		Page<Lab> pag = this.labRepository.findByIsApprovedAndIsDeleted(true, page, false);
		return pag.map(u -> this.labToLabResponse(u));
	}
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

	@Override
	public ResponseEntity<?> addLab(LabRequest request, MultipartFile file, List<MultipartFile> multipartFiles) {
		Map<String, Object> response = new HashMap<>();

		Optional<Lab> op = this.labRepository.findByEmail(request.getEmail());
		if (op.isPresent())
			throw new ResourceAlreadyExistException(AppConstant.LAB_ALREADY_EXIST);
		Lab l = this.labRequestToLab(request);
		String imageName = UUID.randomUUID().toString() + file.getOriginalFilename();
		l.setImageName(imageName);

		if (file != null) {

			String filename = StringUtils.cleanPath(imageName);
			l.setDocumentType(file.getContentType());

			Path fileStorage = Paths.get(AppConstant.DIRECTORY, filename).toAbsolutePath().normalize();

			try {
				Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {

			}
		}
		List<LabDocument> labDocuments = new ArrayList<>();
		if (multipartFiles != null) {
			for (MultipartFile file1 : multipartFiles) {
				LabDocument l1 = new LabDocument();
				l1.setDocumentName(file.getOriginalFilename());
				l1.setDocType(file.getContentType());
				String filename = StringUtils.cleanPath(file.getOriginalFilename());
				l1.setFileName(filename);
				Path fileStorage = Paths.get(AppConstant.DIRECTORY, filename).toAbsolutePath().normalize();
				try {
					Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);

				} catch (Exception e) {

				}
				labDocuments.add(l1);
			}
		}

		l.setLabDocument(labDocuments);
//		
		Lab lb = this.labRepository.save(l);
		Location location = request.getLocation();
		location.setLab(lb);
		location = this.locationRepository.save(location);
		response.put(AppConstant.MESSAGE, AppConstant.LAB_CREATED_MESSAGE);
		response.put(AppConstant.LAB_CREATED, lb);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	
	
	
	
//find lab by id:	

	public ResponseEntity<?> findLabById(Long id) {
		Map<String, Object> response = new HashMap<>();

		Lab l = labRepository.findById(id).orElseThrow(() -> new BadRequestException(AppConstant.LAB_NOT_FOUND));

		LabResponse lr = labToLabResponse(l);
		response.put(AppConstant.MESSAGE, AppConstant.LAB_FIND);

		response.put(AppConstant.LAB, l);

		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	@Override
	public LabResponse registerLab(LabRequest labRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
