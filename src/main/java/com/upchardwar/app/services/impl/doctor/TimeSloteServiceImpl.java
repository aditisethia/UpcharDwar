package com.upchardwar.app.services.impl.doctor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.doctor.Schedule;
import com.upchardwar.app.entity.doctor.TimeSlote;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.ScheduleRepository;
import com.upchardwar.app.repository.TimeSlotRepository;
import com.upchardwar.app.services.doctor.ITimeSlotService;


@Service
public class TimeSloteServiceImpl implements ITimeSlotService {
  
	@Autowired
	private TimeSlotRepository repository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public Map<String,Object> createTimeSlots(Long scheduleId, int intervalInMinutes) {
       Map<String, Object> response=new HashMap<>();
		Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        LocalTime startTime=schedule.getStartTime();
        LocalTime endTime=schedule.getEndTime();
        List<TimeSlote> timeSlots = new ArrayList<>();
        LocalTime currentTime = startTime;

        while (currentTime.isBefore(endTime)) {
            TimeSlote timeSlot = new TimeSlote();
            timeSlot.setSchedule(schedule);
            timeSlot.setStartTime(currentTime);
            timeSlot.setEndTime(currentTime.plusMinutes(intervalInMinutes));
            timeSlot.setIsBooked(false);

            timeSlots.add(timeSlot);

            currentTime = currentTime.plusMinutes(intervalInMinutes);
        }

        
        List<TimeSlote>timeSlotes=  repository.saveAll(timeSlots);
        response.put("timeSlot", timeSlotes);
        return response;
    }

}
