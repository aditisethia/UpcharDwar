package com.upchardwar.app.services.doctor;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.ScheduleRequest;
import com.upchardwar.app.entity.payload.ScheduleResponse;

public interface IScheduleService {
    public ScheduleResponse createSchdule(ScheduleRequest request);
	  
	public ScheduleResponse getSchduleById(Long id);
	
	public String deleteScheduleById(Long id);
	
	public Page<ScheduleResponse> getAllSchdule(Integer pageNo, Integer pageSize);
	
	public List<ScheduleResponse> searchShdule(Integer pageNo,Integer pageSize,ScheduleRequest request,String sortBy);
	
	public ScheduleResponse updateSchedule(ScheduleRequest request);
	
	public Map<String, Object> createSchedule(ScheduleRequest request, String doctorEmail);
}
