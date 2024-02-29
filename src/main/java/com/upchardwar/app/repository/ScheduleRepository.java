package com.upchardwar.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upchardwar.app.entity.doctor.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("SELECT s FROM Schedule s WHERE s.doctor.id = :doctorId ORDER BY s.selectedDate DESC")
	 public  List<Schedule> findByDoctorId(Long doctorId);
//public	Schedule findByDoctorAndDaysAndIsActiveAndIsDeleted(Doctor doctor, String name, boolean b, boolean c);

}
