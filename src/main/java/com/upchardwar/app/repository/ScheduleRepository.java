package com.upchardwar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upchardwar.app.entity.doctor.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
