package com.upchardwar.app.services.doctor;

import java.util.Map;

public interface ITimeSlotService {
	public Map<String, Object> createTimeSlots(Long scheduleId, int intervalInMinutes);
}
