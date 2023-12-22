package com.upchardwar.app.services.lab;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.upchardwar.app.entity.payload.LabRequestsRequest;
import com.upchardwar.app.entity.payload.LabRequestsResponse;

public interface ILabRequestsService {

	
	
    ResponseEntity<?> registerLabRequest(LabRequestsRequest labRequestsRequest);

    LabRequestsResponse updateLabRequest(LabRequestsRequest labRequestsRequest);

    List<LabRequestsResponse> searchLabRequests(Integer pageNo, Integer pageSize, LabRequestsRequest labRequestsRequest, String sortBy);

    String deleteLabRequestById(Long id);

    Page<LabRequestsResponse> getAllLabRequests(Integer pageNo, Integer pageSize);
    
    List<LabRequestsResponse> getTodayLabRequests();

    List<LabRequestsResponse> getUpcomingLabRequests();
}
