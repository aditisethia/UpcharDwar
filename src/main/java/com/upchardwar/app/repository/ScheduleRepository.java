package com.upchardwar.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	 public  List<Schedule> findByDoctorId(Long doctorId);
//public	Schedule findByDoctorAndDaysAndIsActiveAndIsDeleted(Doctor doctor, String name, boolean b, boolean c);

}
