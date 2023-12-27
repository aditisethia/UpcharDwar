package com.upchardwar.app.services.impl.lab;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabTest;
import com.upchardwar.app.entity.payload.CreateLabTestRequest;
import com.upchardwar.app.entity.payload.CreateLabTestResponse;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.repository.LabTestRepo;
import com.upchardwar.app.services.lab.ILabTestService;

@Service
public class LabTestServiceImpl implements ILabTestService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LabTestRepo labTestRepo;
	
	
	
	public CreateLabTestResponse labTestToLabTestResponse(LabTest labTest) {
		return this.modelMapper.map(labTest, CreateLabTestResponse.class);
	}

	public LabTest creatLabTestRequestToLabTest(CreateLabTestRequest labTestRequest) {
		return this.modelMapper.map(labTestRequest, LabTest.class);
	}

	

	@Override
	public ResponseEntity<?> createLabTest(CreateLabTestRequest request) {
		Map<String, Object> response=new HashMap<>();
		LabTest l = this.creatLabTestRequestToLabTest(request);
		
	      l=this.labTestRepo.save(l);
	      CreateLabTestResponse labTestResponse= this.labTestToLabTestResponse(l);
		response.put(AppConstant.MESSAGE , AppConstant.LAB_TEST_CREATED);
		response.put(AppConstant.LAB_TEST, labTestResponse);
		
	return new ResponseEntity(response,HttpStatus.CREATED);
	}

}
