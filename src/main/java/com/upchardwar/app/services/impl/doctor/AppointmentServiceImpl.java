package com.upchardwar.app.services.impl.doctor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upchardwar.app.dto.AppointmentDto;
import com.upchardwar.app.dto.PageAppointmentDto;
import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.status.AppointmentStatus;
import com.upchardwar.app.repository.AppointmentRepository;
import com.upchardwar.app.services.doctor.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	
	@Override
	public Map<String, Object> bookAppointment(Appointment appointment) {

		// Save the appointment
		Appointment savedAppointment = appointmentRepository.save(appointment);
       
		Appointment app=new Appointment();
		app.setStatus(AppointmentStatus.SCHEDULED);
		// Create a response map
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Appointment booked successfully");
		response.put("appointment", savedAppointment);

		return response;
	}

	@Override
	public Map<String, Object> todaysAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> upcomingAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAppointmentDetails(Long appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> cancelAppointment(Long appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> rescheduledAppointment(AppointmentDto appointmentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateAppointment(AppointmentDto updatedAppointmentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> notifyPatientBeforeAppointment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> countTotalPatientOfDoctor(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> countTodaysTotalPatientOfDoctor(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> bookAppointment(AppointmentDto appointmentDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> doctorsUpcomingTotalAppointment(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageAppointmentDto viewAllAppointments(int pageNo, int pageSize, String sortBy, AppointmentDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageAppointmentDto viewAllAppointments(int pageNo, int pageSize, String sortBy, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageAppointmentDto viewAppointmentsByPatient(int pageNo, int pageSize, String sortBy, String patientEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}

//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.ExampleMatcher;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import com.upchardwar.app.dto.AppointmentDto;
//import com.upchardwar.app.dto.PageAppointmentDto;
//import com.upchardwar.app.dto.PatientAppointmentDto;
//import com.upchardwar.app.dto.TodaysAppointmentDto;
//import com.upchardwar.app.entity.doctor.Appointment;
//import com.upchardwar.app.entity.doctor.Doctor;
//import com.upchardwar.app.entity.patient.Patient;
//import com.upchardwar.app.entity.payload.ApiResponse;
//import com.upchardwar.app.entity.status.AppConstant;
//import com.upchardwar.app.exception.ResourceNotFoundException;
//import com.upchardwar.app.repository.AppointmentRepository;
//import com.upchardwar.app.repository.DoctorInvoiceRepository;
//import com.upchardwar.app.repository.DoctorRepository;
//import com.upchardwar.app.repository.PatientAppointmentFileRepository;
//import com.upchardwar.app.repository.PatientRepository;
//import com.upchardwar.app.repository.ScheduleRepository;
//import com.upchardwar.app.repository.TimeSlotRepository;
//import com.upchardwar.app.services.doctor.IAppointmentService;
//import com.upchardwar.app.utils.EmailServices;
//
//@Service
//public class AppointmentServiceImpl implements IAppointmentService {
//	@Autowired
//	private AppointmentRepository appointmentRepository;
//
//	@Autowired
//	private ScheduleRepository scheduleRepository;
//
//	@Autowired
//	private TimeSlotRepository timeSlotRepository;
//
//	@Autowired
//	private PatientAppointmentFileRepository fileRepository;
//
//	@Autowired
//	private EmailServices emailServices;
//
//	@Autowired
//	private DoctorInvoiceRepository doctorInvoiceRepository;
//
//	@Autowired
//	private DoctorRepository drepo;
//
//	@Autowired
//	private PatientRepository patientRepository;
//
//	public TodaysAppointmentDto convertToTodaysAppointmentDto(Appointment appointment) {
//
//		return new TodaysAppointmentDto(appointment.getId(), appointment.getPatient().getPatientName(),
//				appointment.getAppointmentDate(), appointment.getPurpose(), appointment.getPatient().getId(),
//				appointment.getPaidAmount(), appointment.getStatus()
//
//		);
//	}
//
//	public AppointmentDto convertToAppointmentDto(Appointment appointment) {
//
//		return new AppointmentDto(appointment.getId(), appointment.getPatient().getPatientName(),
//				appointment.getAppointmentDate(), appointment.getPurpose(), appointment.getPaidAmount(),
//				appointment.getStatus(), appointment.getAppointmentTime(), appointment.getDoctor().getId(),
//				appointment.getPatient().getEmail(), appointment.getPatient().getMobile()
//
//		);
//	}
//
//	private Appointment convertToAppointmentEntity(AppointmentDto appointmentDto) {
//		Appointment appointment = new Appointment();
//		appointment.setId(appointmentDto.getId());
//		appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
//		appointment.setPurpose(appointmentDto.getPurpose());
//		appointment.setPaidAmount(appointmentDto.getPaidAmount());
//		appointment.setStatus(appointmentDto.getStatus());
//		appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
//
//		// Create and set Patient entity
//		Patient patient = new Patient();
//		patient.setPatientName(appointmentDto.getPatientName());
//		patient.setEmail(appointmentDto.getEmail());
//		// Set other Patient properties as needed
//		appointment.setPatient(patient);
//		Optional<Doctor> d = this.drepo.findById(appointmentDto.getDId());
//		Doctor doctor = null;
//		if (d.isPresent()) {
//			doctor = d.get();
//
//		} else
//			throw new ResourceNotFoundException(AppConstant.DOCTOR_WITH_ID_NOT_EXIST);
//		appointment.setDoctor(doctor);
//
//		return appointment;
//	}
//
//	// ..........................
//
//	private PatientAppointmentDto convertToPatientAppointmentDto(Appointment appointment) {
////	    PatientAppointmentDto patientAppointmentDto = new PatientAppointmentDto();
////	    
////	    // map existing fields
////	    patientAppointmentDto.setId(appointment.getId());
////	    patientAppointmentDto.setPatientName(appointment.getPatient().getPatientName());
////	    patientAppointmentDto.setAppointmentDate(appointment.getAppointmentDate());
////	    patientAppointmentDto.setPurpose(appointment.getPurpose());
////	    patientAppointmentDto.setPaidAmount(appointment.getPaidAmount());
////	    patientAppointmentDto.setStatus(appointment.getStatus());
////	    patientAppointmentDto.setAppointmentTime(appointment.getAppointmentTime());
////	    patientAppointmentDto.setDId(appointment.getDoctor().getId()); // Assuming you have a Doctor field in Appointment entity
////
////	    // Fetch and set Doctor information
////	    Doctor doctor = drepo.findById(appointment.getDoctor().getId()).orElse(null);
////
////	    if (doctor != null) {
////	        patientAppointmentDto.setDoctorName(doctor.getName());
////	        patientAppointmentDto.setDoctorSpeciality(doctor.getSpeciality());
////	    }
////
////	    return patientAppointmentDto;
//
//		Doctor doctor = appointment.getDoctor();
//		Patient patient = appointment.getPatient();
//
//		return new PatientAppointmentDto(appointment.getId(), patient.getPatientName(),
//				appointment.getAppointmentDate(), appointment.getPurpose(), appointment.getPaidAmount(),
//				appointment.getStatus(), appointment.getAppointmentTime(), doctor.getId(), patient.getEmail(),
//				patient.getMobile(), doctor.getName(), doctor.getSpeciality().getSpName());
//	}
//	
//	
//	
////	
////	public Map<String, Object> bookAppointment(AppointmentDto appointmentDTO) {
////	    Map<String, Object> response = new HashMap<>();
////	    Doctor doctor = drepo.findById(appointmentDTO.getDId())
////	            .orElseThrow(() -> new ResourceNotFoundException(AppConstant.DOCTOR_WITH_ID_NOT_EXIST));
////
////	    Patient patient = patientRepository.findByEmail(appointmentDTO.getEmail())
////	            .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PAITENT_NOT_FOUND));
////
////	    LocalDate appointmentDate = appointmentDTO.getAppointmentDate();
////	    LocalTime appointmentTime = appointmentDTO.getAppointmentTime();
////	    Float paidAmount = appointmentDTO.getPaidAmount();
////
////	    Schedule schedule = scheduleRepository.findByDoctorAndDaysAndIsActiveAndIsDeleted(doctor,
////	            appointmentDate.getDayOfWeek().name(), true, false);
////
////	    TimeSlote timeSlot = schedule.getTimeSlotes().stream()
////	            .filter(slot -> slot.getStartTime().equals(appointmentTime) && !slot.getIsBooked())
////	            .findFirst()
////	            .orElseThrow(() -> new RuntimeException("Time slot not available or already booked"));
////
////	    PatientAppointmentFile appointmentFile = fileRepository
////	            .findByDoctorAndDate(doctor, appointmentDate)
////	            .orElseGet(() -> fileRepository.save(new PatientAppointmentFile(doctor, patient, appointmentDate)));
////
////	    DoctorInvoice invoice = new DoctorInvoice(LocalDate.now(), paidAmount, AppConstant.INVOICE_STATUS_AWAITED,
////	            "dfgh", doctor, patient);
////	    invoice = doctorInvoiceRepository.save(invoice);
////
////	    Appointment appointment = new Appointment(doctor, patient, appointmentDate, appointmentTime,
////	            AppConstant.APPOINTMENT_SCHDULED, appointmentFile, paidAmount, appointmentDTO.getPurpose(), invoice);
////	    
////	    timeSlot.setIsBooked(true);
////	    timeSlotRepository.save(timeSlot);
////
////	    response.put("Appointment", convertToAppointmentDto(appointmentRepository.save(appointment)));
////	    return response;
////	}
////
//
////
////	public Map<String, Object> bookAppointment(AppointmentDto appointmentDTO) {
////		Map<String, Object> response = new HashMap<>();
////		System.out.println(appointmentDTO.getDId());
////Optional<Doctor> d= this.drepo.findById(appointmentDTO.getDId());
////	    Doctor doctor=null;
////	    if(d.isPresent())
////	    {
////	    	 doctor = d.get();
////	    }
////	    else
////	    	throw new ResourceNotFoundException(AppConstant.DOCTOR_WITH_ID_NOT_EXIST);
////		
////		Patient patient = patientRepository.findByEmail(appointmentDTO.getEmail())
////				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.PAITENT_NOT_FOUND));
////		LocalDate appointmentDate = appointmentDTO.getAppointmentDate();
////		LocalTime appointmentTime = appointmentDTO.getAppointmentTime();
////		Float paidAmount = appointmentDTO.getPaidAmount();
////
////		System.out.println(appointmentDate.getDayOfWeek().name());
////		
////		Schedule s = scheduleRepository.findByDoctorAndDaysAndIsActiveAndIsDeleted(doctor,
////				appointmentDate.getDayOfWeek().name(), true, false);
////
////		TimeSlote timeSlot = s.getTimeSlotes().stream().filter(slot -> slot.getStartTime().equals(appointmentTime))
////				.findFirst().orElse(null);
////		System.out.println(timeSlot.getIsBooked());
////		if (timeSlot == null || timeSlot.getIsBooked()) {
////			throw new RuntimeException("Time slot not available or already booked");
////		}
////		PatientAppointmentFile appointmentFile = fileRepository.findByDoctorAndDate(doctor, appointmentDate);
////		if (appointmentFile == null) {
////
////			appointmentFile = new PatientAppointmentFile();
////			appointmentFile.setDoctor(doctor);
////			appointmentFile.setDate(appointmentDate);
////			appointmentFile.setPatient(patient);
////
////			appointmentFile = fileRepository.save(appointmentFile);
////		}
////		Appointment appointment = new Appointment();
////		appointment.setDoctor(doctor);
////		appointment.setPatient(patient);
////		appointment.setAppointmentDate(appointmentDate);
////		appointment.setAppointmentTime(appointmentTime);
////		appointment.setStatus(AppConstant.APPOINTMENT_SCHDULED);
////		appointment.setPatientAppointmentFile(appointmentFile);
////		appointment.setPaidAmount(paidAmount); // Use the value from DTO
////		appointment.setPurpose(appointmentDTO.getPurpose());
////		DoctorInvoice invoice = new DoctorInvoice();
////		invoice.setInvoiceGenerateDate(LocalDate.now());// Set the invoice generation date
////		invoice.setAmount(appointment.getPaidAmount()); // Set the amount for the invoice
////		invoice.setInvoiceStatus(AppConstant.INVOICE_STATUS_AWAITED); // Set the initial status
////		invoice.setPaymentMethod("dfgh");
////		invoice.setDoctor(doctor);
////		invoice.setPatient(patient);
////		doctorInvoiceRepository.save(invoice);
////		appointment.setDoctorInvoice(invoice);
////		doctorInvoiceRepository.save(invoice);
////		AppointmentDto dto = convertToAppointmentDto(appointmentRepository.save(appointment));
////		timeSlot.setIsBooked(true);
////
////		timeSlotRepository.save(timeSlot);
////
////		response.put("Appointment", dto);
////		return response;
////	}
//
//	// to get todays appointment
//	@Override
//	public Map<String, Object> todaysAppointment() {
//		Map<String, Object> response = new HashMap<>();
//		LocalDate today = LocalDate.now();
//		List<TodaysAppointmentDto> todaysAppointment = appointmentRepository.findByAppointmentDate(today);
//		response.put("todaysAppointment", todaysAppointment);
//		return response;
//	}
//
//	// to get upcoming appointment
//	public Map<String, Object> upcomingAppointment() {
//		Map<String, Object> response = new HashMap<>();
//		LocalDate today = LocalDate.now();
//		List<TodaysAppointmentDto> upcomingAppointments = appointmentRepository
//				.findByAppointmentDateAfterOrderByAppointmentDateAscAppointmentTimeAsc(today);
//
//		response.put("upcomingAppointment", upcomingAppointments);
//		return response;
//
//	}
//
//	// to get details of aapointment by id
//	public Map<String, Object> getAppointmentDetails(Long appointmentId) {
//
//		Map<String, Object> response = new HashMap<>();
//		Appointment appointment = appointmentRepository.findById(appointmentId)
//				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + appointmentId));
//
//		TodaysAppointmentDto appointmentdetaile = convertToTodaysAppointmentDto(appointment);
//		response.put("appointmentDetails", appointmentdetaile);
//		return response;
//	}
//
//	// for cancel an appointment
//	@Override
//	public Map<String, Object> cancelAppointment(Long appointmentId) {
//
//		Map<String, Object> response = new HashMap<>();
//		Appointment appointment = appointmentRepository.findById(appointmentId)
//				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + appointmentId));
//
//		appointment.setStatus(AppConstant.APPOINTMENT_STATUS_CANCELED);
//
//		TodaysAppointmentDto canceledAppointment = convertToTodaysAppointmentDto(
//				appointmentRepository.save(appointment));
//
//		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.APPOINTMENT_CANCELED, HttpStatus.OK);
//		response.put("response", apiResponse);
//		response.put("appointment canceled", canceledAppointment);
//		emailServices
//				.sendEmail(
//						appointment.getPatient().getPatientName() + " has been  " + AppConstant.APPOINTMENT_CANCELED
//								+ "." + "at time" + appointment.getAppointmentTime(),
//						appointment.getPatient().getEmail());
//		return response;
//	}
//
//	public Map<String, Object> rescheduledAppointment(AppointmentDto appointmentDto) {
//
//		Map<String, Object> response = new HashMap<>();
//		Appointment appointment = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(
//				() -> new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + appointmentDto.getId()));
//
//		appointment.setStatus(AppConstant.APPOINTMENT_STATUS_RESCHDULED);
//		appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
//		appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
//
//		AppointmentDto reschduledAppointment = convertToAppointmentDto(appointmentRepository.save(appointment));
//
//		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.APPOINTMENT_RESCHEDULED, HttpStatus.OK);
//		response.put("response", apiResponse);
//		response.put(AppConstant.APPOINTMENT_RESCHEDULED, reschduledAppointment);
//		emailServices.sendEmail(
//				"Your appointment with ID " + appointment.getPatient().getPatientName() + " has been updated to "
//						+ AppConstant.APPOINTMENT_RESCHEDULED + "." + "at time" + appointment.getAppointmentTime(),
//				appointment.getPatient().getEmail());
//		return response;
//	}
//
//	// for updating an appointment
//	public Map<String, Object> updateAppointment(AppointmentDto updatedAppointmentDto) {
//		Map<String, Object> response = new HashMap<>();
//
//		Optional<Appointment> existingAppointmentOptional = appointmentRepository
//				.findById(updatedAppointmentDto.getId());
//
//		if (existingAppointmentOptional.isPresent()) {
//			Appointment existingAppointment = existingAppointmentOptional.get();
//
//			existingAppointment.setAppointmentDate(updatedAppointmentDto.getAppointmentDate());
//			existingAppointment.setPurpose(updatedAppointmentDto.getPurpose());
//			Patient p = existingAppointment.getPatient();
//			p.setPatientName(updatedAppointmentDto.getPatientName());
//
//			p.setPaidAmount(updatedAppointmentDto.getPaidAmount());
//
//			ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.APPOINTMENT_UPDATED, HttpStatus.OK);
//			response.put("response", apiResponse);
//
//			AppointmentDto updatededAppointment = convertToAppointmentDto(
//					appointmentRepository.save(existingAppointment));
//
//			// appointmentRepository.save(existingAppointment);
//			response.put("response", updatededAppointment);
//
//			return response;
//
//		} else
//			throw new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + updatedAppointmentDto.getId());
//	}
//
//	// notify user before an appointment
//	public Map<String, Object> notifyPatientBeforeAppointment() {
//
//		Map<String, Object> response = new HashMap<>();
//		LocalDate today = LocalDate.now();
//		LocalDate upcoming = today.plusDays(2);
//
//		List<Appointment> appointments = appointmentRepository.getByAppointmentDate(upcoming);
//
//		for (Appointment app : appointments) {
//
//			String patientEmail = app.getPatient().getEmail();
//
//			String content = "Your appointment is scheduled for " + app.getAppointmentDate() + " at "
//					+ app.getAppointmentTime();
//
//			emailServices.sendEmail(content, patientEmail);
//		}
//		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.NOTIFICATION_UPCOMING, HttpStatus.OK);
//		response.put("response", apiResponse);
//		return response;
//	}
//
//	public Map<String, Object> countTotalPatientOfDoctor(String email) {
//		Map<String, Object> response = new HashMap<>();
//		Long totalPatient = appointmentRepository.countPatientsForDoctor(email);
//		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.DOCTORS_TOTAL_PETIENT, HttpStatus.OK);
//		response.put("response", apiResponse);
//
//		response.put(AppConstant.TOTAL_PETIENT, totalPatient);
//		return response;
//	}
//
//	@Override
//	public Map<String, Object> countTodaysTotalPatientOfDoctor(String email) {
//		Map<String, Object> response = new HashMap<>();
//		LocalDate today = LocalDate.now();
//		Long totalPatient = appointmentRepository.countTodaysTotalPatientForDoctor(email, today);
//		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.DOCTORS_TODAYS_TOTAL_PETIENT,
//				HttpStatus.OK);
//		response.put("response", apiResponse);
//
//		response.put(AppConstant.TOTAL_PETIENT, totalPatient);
//		return response;
//	}
//
//	@Override
//	public Map<String, Object> doctorsUpcomingTotalAppointment(String email) {
//		Map<String, Object> response = new HashMap<>();
//		LocalDate today = LocalDate.now();
//		Long totalAppontments = appointmentRepository.countUpcomingAppointments(today, email);
//		ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, AppConstant.DOCTORS_UPCOMING_APPOINTMENT_COUNT,
//				HttpStatus.OK);
//		response.put("response", apiResponse);
//
//		response.put(AppConstant.TOTAL_UPCOMING_APPOINTMENT, totalAppontments);
//		return response;
//
//	}
//
//	// view all appointment and search on the ba
//	public PageAppointmentDto viewAllAppointments(int pageNo, int pageSize, String sortBy, AppointmentDto request) {
//
//		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
//				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase()
//				.withMatcher("id", match -> match.transform(value -> value.map(id -> ((Long) id == 0) ? null : id)))
//				.withMatcher("patientAppointmentFile.id",
//						match -> match.transform(value -> value.map(id -> ((Long) id == 0) ? null : id)))
//				.withMatcher("doctorInvoice.id",
//						match -> match.transform(value -> value.map(id -> ((Long) id == 0) ? null : id)))
//				.withMatcher("doctor.id",
//						match -> match.transform(value -> value.map(dId -> ((Long) dId == 0) ? null : dId)));
//
//		Example<Appointment> example = Example.of(convertToAppointmentEntity(request), exampleMatcher);
//
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.ASC, sortBy);
//		Page<Appointment> findAllAppointment = appointmentRepository.findAll(example, pageable);
//
////	    Page<AppointmentDto> map = findAllAppointment.map(this::convertToAppointmentDto);
//		Page<AppointmentDto> map = findAllAppointment.map(p -> convertToAppointmentDto(p));
//		List<AppointmentDto> content = map.getContent();
//
//		List<AppointmentDto> newList = null;
//		if (content != null && !content.isEmpty()) {
//			newList = new ArrayList<>(content);
//			Collections.reverse(newList);
//		}
//
//		PageAppointmentDto prr = new PageAppointmentDto();
//		prr.setContents(newList);
//		prr.setTotalElements(findAllAppointment.getTotalElements());
//
//		return prr;
//	}
//
////	@Override
////	public PageAppointmentDto viewAllAppointmentsofDoctore(int pageNo, int pageSize, String sortBy,
////		 Long doctorId) {
////		// TODO Auto-generated method stub
////		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
////				.withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING)
////				.withIgnoreCase();
////		Doctor d = new Doctor();
////		d.setId(doctorId);
////		
////		 
////		return null;
////	}
//
//	public PageAppointmentDto viewAllAppointments(int pageNo, int pageSize, String sortBy, String email) {
//		// Create Pageable object with pagination and sorting
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.ASC, sortBy);
//
//		Doctor doctor = drepo.findByEmail(email)
//				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.DOCTOR_WITH_EMAIL_NOT_EXIST));
//		;
//
//		Long doctorId = doctor.getId();
//		// Query the database directly based on doctorId
//		Page<Appointment> findAllAppointment = appointmentRepository.findByDoctorId(doctorId, pageable);
//
//		// Convert the Page of Appointment entities to a Page of AppointmentDto
//		Page<AppointmentDto> map = findAllAppointment.map(this::convertToAppointmentDto);
//
//		// Reverse the order of content if needed
//		List<AppointmentDto> content = map.getContent();
//		List<AppointmentDto> newList = null;
//		if (content != null && !content.isEmpty()) {
//			newList = new ArrayList<>(content);
//			Collections.reverse(newList);
//		}
//
//		// Create and return the result DTO
//		PageAppointmentDto prr = new PageAppointmentDto();
//		prr.setContents(newList);
//		prr.setTotalElements(findAllAppointment.getTotalElements());
//
//		return prr;
//	}
//
//	// get All Appointment of patient by email
//
//	public PageAppointmentDto viewAppointmentsByPatient(int pageNo, int pageSize, String sortBy, String patientEmail) {
//		// Create Pageable object with pagination and sorting
//		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.ASC, sortBy);
//
//		Page<Appointment> findAllAppointments = appointmentRepository.findByEmail(patientEmail, pageable);
//
//		// Convert the Page of Appointment entities to a Page of AppointmentDto
//		Page<PatientAppointmentDto> mappedAppointments = findAllAppointments.map(this::convertToPatientAppointmentDto);
//
//		// Reverse the order of content if needed
//		List<PatientAppointmentDto> content = mappedAppointments.getContent();
//		List<PatientAppointmentDto> reversedList = null;
//		if (content != null && !content.isEmpty()) {
//			reversedList = new ArrayList<>(content);
//			Collections.reverse(reversedList);
//		}
//
//		// Create and return the result DTO
//		PageAppointmentDto resultDto = new PageAppointmentDto();
//		resultDto.setContents(reversedList);
//		resultDto.setTotalElements(findAllAppointments.getTotalElements());
//
//		return resultDto;
//	}
//
//	@Override
//	public Map<String, Object> bookAppointment(AppointmentDto appointmentDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//
//}
