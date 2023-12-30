package com.upchardwar.app.services.lab;

import org.springframework.http.ResponseEntity;

import com.upchardwar.app.dto.PageLabDto;
import com.upchardwar.app.entity.payload.CreateLabTestRequest;

public interface ILabTestService {

	public  ResponseEntity<?> createLabTest(CreateLabTestRequest request);
	public PageLabDto viewAllLabTest(int pageNo, int pageSize, String sortBy, Long labId);
}
