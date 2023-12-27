package com.upchardwar.app.services.lab;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;
=======
>>>>>>> 2189d25f36afff1f9a4d2a24d71f6a8c8bdd0c6b

import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;

public interface ILabService {
  public ResponseEntity<?> registerLab(LabRequest labRequest);

 public LabResponse updateLab(LabRequest request);

 public List<LabResponse> searchLab(Integer pageNo, Integer pageSize, LabRequest labRequest, String sortBy);

public ResponseEntity<?> deleteLabById(Long id);

 public Page<LabResponse> getAllLab(Integer pageNo, Integer pageSize);
 
 public ResponseEntity<?> addLab(LabRequest request,MultipartFile file,List<MultipartFile> multipartFiles);

}
