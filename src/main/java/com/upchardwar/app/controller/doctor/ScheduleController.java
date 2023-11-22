package com.upchardwar.app.controller.doctor;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.ScheduleRequest;
import com.upchardwar.app.entity.payload.ScheduleResponse;

import com.upchardwar.app.services.doctor.IScheduleService;

@RestController
@RequestMapping("upchardwar/schedule")
public class ScheduleController {

	@Autowired
	private IScheduleService service;
     
	//to create schedule
	@PostMapping("/")
	public ResponseEntity<ScheduleResponse> addSchdule(@RequestBody ScheduleRequest scheduleRequest) {
		
		return new ResponseEntity<ScheduleResponse>(this.service.createSchdule(scheduleRequest), HttpStatus.CREATED);
	}
	
	//to get schedule by id
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable("id") Long id){
    	return new ResponseEntity<ScheduleResponse> (this.service.getSchduleById(id),HttpStatus.OK);
    }
    
    //delete schedule by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable("id") Long id){
    	return new ResponseEntity<String> (this.service.deleteScheduleById(id),HttpStatus.OK);
    }
    
    //get all schedule
    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseEntity<Page<ScheduleResponse>> getAllSchedule(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        Page<ScheduleResponse> sr  =	this.service.getAllSchdule(pageNo, pageSize);
    	return new ResponseEntity<Page<ScheduleResponse>> (sr,HttpStatus.OK);
    }
    
    //for search schedule
    @PostMapping("/search/{pageNo}/{pageSize}/{sortBy}")
    public ResponseEntity<List<ScheduleResponse>> search(@RequestBody ScheduleRequest request,@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize,@PathVariable("sortBy") String sortBy){
    	List<ScheduleResponse> sr=this.service.searchShdule(pageNo, pageSize, request,sortBy);
    	return new ResponseEntity<List<ScheduleResponse>>(sr,HttpStatus.OK);
    }
    
    //for updating a schedule
    @PutMapping("/")
    public ResponseEntity<ScheduleResponse> update(@RequestBody ScheduleRequest request){
    	return new ResponseEntity<ScheduleResponse>(this.service.updateSchedule(request),HttpStatus.OK);
    }
}
