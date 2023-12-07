package com.upchardwar.app.services.lab;

import java.util.List;

import org.springframework.data.domain.Page;

import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;

public interface ILabService {
  public LabResponse registerLab(LabRequest labRequest);

 public LabResponse updateLab(LabRequest request);

 public List<LabResponse> searchLab(Integer pageNo, Integer pageSize, LabRequest labRequest, String sortBy);

public String deleteLabById(Long id);

 public Page<LabResponse> getAllLab(Integer pageNo, Integer pageSize);
}
