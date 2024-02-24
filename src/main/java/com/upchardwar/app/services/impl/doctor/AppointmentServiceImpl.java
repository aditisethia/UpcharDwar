package com.upchardwar.app.services.impl.doctor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.payload.AppointmentRequest;
import com.upchardwar.app.entity.status.AppointmentStatus;
import com.upchardwar.app.repository.AppointmentRepository;
import com.upchardwar.app.services.doctor.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ModelMapper modelMapper;	

	@Override
	public Map<String, Object> bookAppointment(Appointment appointment) {

		appointment.setBookingdate(LocalDate.now());
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		System.out.println(appointment.getBookingdate());
		System.out.println(appointment.getPurpose());
		// Save the appointment
		Appointment savedAppointment = appointmentRepository.save(appointment);
		Appointment app = new Appointment();
		app.setStatus(AppointmentStatus.SCHEDULED);
		// Create a response map
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Appointment booked successfully");
		response.put("appointment", savedAppointment);

		return response;
	}


    @Override
    public Page<AppointmentRequest> getAppointmentsByPatientId(Long patientId, Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.findByPatientId(patientId, pageable);
        return appointments.map(this::convertToRequestDto);
    }

    @Override
    public Page<AppointmentRequest> getAppointmentsByDoctorId(Long doctorId, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("appointmentDate").descending());

        // Retrieve appointments with sorting by date
        Page<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId, pageable);

        // Convert and return the data
        return appointments.map(this::convertToRequestDto);
    }

    private AppointmentRequest convertToRequestDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentRequest.class);
    }

    @Override
    public List<Appointment> findAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate appointmentDate) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
    }
    
    @Override
    public List<Appointment> findUpcomingAppointmentsByDoctorId(Long doctorId, LocalDate startDate) {

        return appointmentRepository.findByDoctorIdAndAppointmentDateAfter(doctorId, startDate);
    }


	@Override
	public List<Appointment> findAppointmentsByDoctorIdAndstatus(Long doctorId, AppointmentStatus status) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByDoctorIdAndAppointmentStatus(doctorId, status);
	}
	
	  
	
}
