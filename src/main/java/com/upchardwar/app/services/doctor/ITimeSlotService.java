package com.upchardwar.app.services.doctor;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.upchardwar.app.entity.doctor.Schedule;
import com.upchardwar.app.entity.doctor.TimeSlote;

public interface ITimeSlotService {
	public Map<String, Object> createTimeSlots(Long scheduleId, int intervalInMinutes);
}
