package com.upchardwar.app.services.impl.lab;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.lab.Lab;
import com.upchardwar.app.entity.lab.LabRequests;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabRequestsRequest;
import com.upchardwar.app.entity.payload.LabRequestsResponse;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.repository.LabRequestRepo;
import com.upchardwar.app.services.lab.ILabRequestsService;

@Service
public class LabRequestsImpl implements ILabRequestsService {

	
    @Autowired
    private LabRequestRepo labRequestsRepo;

    @Autowired
    private ModelMapper modelMapper;

	public LabRequestsResponse labToLabResponse(LabRequests lab) {
		return this.modelMapper.map(lab, LabRequestsResponse.class);
	}

	public LabRequests labRequestToLab(LabRequestsRequest labRequestsRequest) {
		return this.modelMapper.map(labRequestsRequest, LabRequests.class);
	}

	
	@Override
	public ResponseEntity<?> registerLabRequest(LabRequestsRequest labRequestsRequest) {
		// TODO Auto-generated method stub
		System.out.println("hello at registerlabrequest lab request service implements");
		Map<String, Object> response = new HashMap<>();
		// TODO Auto-generated method stub
		LabRequests l = this.labRequestToLab(labRequestsRequest);
		   LabRequestsResponse lr= labToLabResponse(this.labRequestsRepo.save(l));
	      
		return new ResponseEntity<>(l,HttpStatus.CREATED);
	}

	@Override
	public LabRequestsResponse updateLabRequest(LabRequestsRequest labRequestsRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabRequestsResponse> searchLabRequests(Integer pageNo, Integer pageSize,
			LabRequestsRequest labRequestsRequest, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteLabRequestById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LabRequestsResponse> getAllLabRequests(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<LabRequestsResponse> getTodayLabRequests() {
        // Implementation for getting lab requests for today
        LocalDate today = LocalDate.now();
        List<LabRequests> todayLabRequests = labRequestsRepo.findByRequestDate(today);
        System.out.println(todayLabRequests+"at checkpoint labrequestimpl/gettodaylabrequests ");
        return todayLabRequests.stream().map(this::labRequestsToLabRequestsResponse).collect(Collectors.toList());
    }

	@Override
	public List<LabRequestsResponse> getUpcomingLabRequests() {
		// TODO Auto-generated method stub
		return null;
	}
	
    private LabRequestsResponse labRequestsToLabRequestsResponse(LabRequests labRequests) {
        return modelMapper.map(labRequests, LabRequestsResponse.class);
    }

}
