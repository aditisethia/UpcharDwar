package com.upchardwar.app.controller.lab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;
import com.upchardwar.app.services.lab.ILabRequestsService;
import com.upchardwar.app.services.lab.ILabService;

@RestController
@RequestMapping("upchardwar/lab")
@CrossOrigin("*")
public class LabController {

	@Autowired
	private ILabService labService;


	@PostMapping("/save")
	public ResponseEntity<?> addLab(@RequestBody LabRequest labRequest) {
		System.out.println("at lab controller");

		return new ResponseEntity<>(this.labService.registerLab(labRequest), HttpStatus.OK);

	}

	
	@PostMapping(path = "/save1", consumes = {"multipart/form-data", "application/octet-stream"})
    public ResponseEntity<?> lab(
            @RequestPart("data") LabRequest request,
//            @RequestPart("file") MultipartFile file,
            @RequestPart("files") List<MultipartFile> multipartFiles) {
   
     return   labService.addLab(request, multipartFiles.get(0), multipartFiles);

    }
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLab(@PathVariable("id") Long id){
		return this.labService.deleteLabById(id);
	}
	


	@GetMapping("/all")
	public ResponseEntity<?> getAllLabs(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize) {
		System.out.println("at getall lab controller");
		List<LabResponse> labs = labService.getAllLab(pageNo, pageSize).getContent();
		
		return new ResponseEntity<>(labs, HttpStatus.OK);
	}

}
