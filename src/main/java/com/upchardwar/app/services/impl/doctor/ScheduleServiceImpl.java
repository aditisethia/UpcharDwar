package com.upchardwar.app.services.impl.doctor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.upchardwar.app.entity.doctor.Schedule;
import com.upchardwar.app.entity.payload.ScheduleRequest;
import com.upchardwar.app.entity.payload.ScheduleResponse;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.ScheduleRepository;
import com.upchardwar.app.services.doctor.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {
	@Autowired
	private ScheduleRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	public ScheduleResponse scheduleToScheduleResponse(Schedule schedule) {
		return this.modelMapper.map(schedule, ScheduleResponse.class);
	}

	public Schedule scheduleRequestToSchedule(ScheduleRequest scheduleRequest) {
		return this.modelMapper.map(scheduleRequest, Schedule.class);
	}

	@Override
	public ScheduleResponse getSchduleById(Long id) {
		Optional<Schedule>  s=this.repository.findById(id);
		if(s.isPresent()) 
			return this.scheduleToScheduleResponse(s.get());
		
			throw new ResourceNotFoundException("Doctor with this id not exist");
	}

	@Override
	public String deleteScheduleById(Long id) {
Optional<Schedule>  s=this.repository.findById(id);
	    
		if(s.isEmpty()) {
			throw new ResourceNotFoundException("schedule with this id not exist");
		}
		   this.repository.delete(s.get());	
          		return "deleted successfully";
		
	}

	@Override
	public Page<ScheduleResponse> getAllSchdule(Integer pageNo, Integer pageSize) {
		PageRequest page = PageRequest.of(pageNo, pageSize);
        Page<Schedule> pag = this.repository.findAll(page);
	 return pag.map(u ->this.scheduleToScheduleResponse(u));
		
	}

	@Override
	public List<ScheduleResponse> searchShdule(Integer pageNo, Integer pageSize, ScheduleRequest request,
			String sortBy) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Match anywhere in the string
                .withIgnoreCase() // Ignore case when matching strings
                .withMatcher("id", match->match.transform(value->value.map(id->((Integer)id==0)?null:id)));
		
		Example<Schedule> example = Example.of(scheduleRequestToSchedule(request),exampleMatcher);
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC,sortBy);
		Page<Schedule> findAllSchedule = this.repository.findAll(example, pageable);
		return findAllSchedule.getContent().stream().map(s->scheduleToScheduleResponse(s)).collect(Collectors.toList());
	}

	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest request) {
		
		Schedule s=	this.repository.save(scheduleRequestToSchedule(request));
		return this.scheduleToScheduleResponse(s);
	}

	@Override
	public ScheduleResponse createSchdule(ScheduleRequest request) {
		 Schedule s=this.repository.save(this.scheduleRequestToSchedule(request));
		return this.scheduleToScheduleResponse(s);
	}

}
