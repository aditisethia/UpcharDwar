package com.upchardwar.app.services.impl.doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.upchardwar.app.dto.AppointmentDto;
import com.upchardwar.app.dto.TodaysAppointmentDto;
import com.upchardwar.app.emailutil.EmailServices;
import com.upchardwar.app.entity.doctor.Appointment;
import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.doctor.DoctorInvoice;
import com.upchardwar.app.entity.doctor.PatientAppointmentFile;
import com.upchardwar.app.entity.doctor.Schedule;
import com.upchardwar.app.entity.doctor.TimeSlote;
import com.upchardwar.app.entity.patient.Patient;
import com.upchardwar.app.entity.payload.ApiResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.AppointmentRepository;
import com.upchardwar.app.repository.DoctorInvoiceRepository;
import com.upchardwar.app.repository.PatientAppointmentFileRepository;
import com.upchardwar.app.repository.PatientRepository;
import com.upchardwar.app.repository.ScheduleRepository;
import com.upchardwar.app.repository.TimeSlotRepository;
import com.upchardwar.app.services.doctor.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private PatientAppointmentFileRepository fileRepository;
	
	@Autowired
	private EmailServices emailServices;
	
	@Autowired
	private DoctorInvoiceRepository doctorInvoiceRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	public TodaysAppointmentDto convertToTodaysAppointmentDto(Appointment appointment) {
	    // Assuming TodaysAppointmentDto has a constructor with parameters
	    // like (patientName, appointmentDate, purpose, paidAmount)
	    return new TodaysAppointmentDto(
	    		appointment.getId(),
	            appointment.getPatient().getPatientName(),
	            appointment.getAppointmentDate(),
	            appointment.getPurpose(),
	            appointment.getPaidAmount(),
	            appointment.getStatus()
	    );
	}
	
	public AppointmentDto convertToAppointmentDto(Appointment appointment) {
	    // Assuming TodaysAppointmentDto has a constructor with parameters
	    // like (patientName, appointmentDate, purpose, paidAmount)
	    return new AppointmentDto(
	    		appointment.getId(),
	            appointment.getPatient().getPatientName(),
	            appointment.getAppointmentDate(),
	            appointment.getPurpose(),
	            appointment.getPaidAmount(),
	            appointment.getStatus(),
	            appointment.getAppointmentTime(),
	            appointment.getDoctor(),
	            appointment.getPatient().getEmail()
	    );
	}

	@Override
//	public Map<String, Object> bookAppointment(Doctor doctor, Patient patient, LocalDate appointmentDate,
//			LocalTime appointmentTime) {
//
//		Map<String, Object> response = new HashMap<>();
//		Schedule s = scheduleRepository.findByDoctorAndDaysAndIsActiveAndIsDeleted(doctor,
//				appointmentDate.getDayOfWeek().name(), true, false);
//
//		TimeSlote timeSlot = s.getTimeSlotes().stream().filter(slot -> slot.getStartTime().equals(appointmentTime))
//				.findFirst().orElse(null);
//
//		if (timeSlot == null || timeSlot.getIsBooked()) {
//			throw new RuntimeException("Time slot not available or already booked");
//		}
//		PatientAppointmentFile appointmentFile = fileRepository.findByDoctorAndDate(doctor, appointmentDate);
//		if (appointmentFile == null) {
//
//			appointmentFile = new PatientAppointmentFile();
//			appointmentFile.setDoctor(doctor);
//			appointmentFile.setDate(appointmentDate);
//			appointmentFile.setPatient(patient);
//
//			appointmentFile = fileRepository.save(appointmentFile);
//		}
//		
//		
//
//		Appointment appointment = new Appointment();
//		appointment.setDoctor(doctor);
//		appointment.setPatient(patient);
//		appointment.setAppointmentDate(appointmentDate);
//		appointment.setAppointmentTime(appointmentTime);
//		appointment.setStatus(AppConstant.APPOINTMENT_SCHDULED);
//		appointment.setPatientAppointmentFile(appointmentFile);
//		appointment.setPaidAmount(paidAmmount);
//		
//		DoctorInvoice invoice = new DoctorInvoice();
//		invoice.setInvoiceGenerateDate(LocalDate.now()); // Set the invoice generation date
//		invoice.setAmount(appointment.getPaidAmount()); // Set the amount for the invoice
//		invoice.setInvoiceStatus(AppConstant.INVOICE_STATUS_AWAITED); // Set the initial status
//		invoice.setPaymentMethod("YourPaymentMethod");
//		doctorInvoiceRepository.save(invoice);
//		appointment.setDoctorInvoice(invoice);
//		
//		appointmentRepository.save(appointment);
//
//		timeSlot.setIsBooked(true);
//
//		timeSlotRepository.save(timeSlot);
//
//		response.put("Appointment", appointment);
//		return response;
//	}

	
	//for trying...........
	
	
	public Map<String, Object> bookAppointment(AppointmentDto appointmentDTO) {
	    Map<String, Object> response = new HashMap<>();
	    
	    

	    Doctor doctor = appointmentDTO.getDoctor();
	    Patient patient = patientRepository.findByEmail(  appointmentDTO.getEmail() ).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PAITENT_NOT_FOUND ));
	    LocalDate appointmentDate = appointmentDTO.getAppointmentDate();
	    LocalTime appointmentTime = appointmentDTO.getAppointmentTime();
	    Float paidAmount = appointmentDTO.getPaidAmount();
	    
	    Schedule s = scheduleRepository.findByDoctorAndDaysAndIsActiveAndIsDeleted(doctor,
				appointmentDate.getDayOfWeek().name(), true, false);

		TimeSlote timeSlot = s.getTimeSlotes().stream().filter(slot -> slot.getStartTime().equals(appointmentTime))
				.findFirst().orElse(null);

		if (timeSlot == null || timeSlot.getIsBooked()) {
			throw new RuntimeException("Time slot not available or already booked");
		}
		PatientAppointmentFile appointmentFile = fileRepository.findByDoctorAndDate(doctor, appointmentDate);
		if (appointmentFile == null) {

			appointmentFile = new PatientAppointmentFile();
			appointmentFile.setDoctor(doctor);
			appointmentFile.setDate(appointmentDate);
			appointmentFile.setPatient(patient);

			appointmentFile = fileRepository.save(appointmentFile);
		}

	    // Rest of your code remains unchanged...
	    
	    Appointment appointment = new Appointment();
	    appointment.setDoctor(doctor);
	    appointment.setPatient(patient);
	    appointment.setAppointmentDate(appointmentDate);
	    appointment.setAppointmentTime(appointmentTime);
	    appointment.setStatus(AppConstant.APPOINTMENT_SCHDULED);
	    appointment.setPatientAppointmentFile(appointmentFile);
	    appointment.setPaidAmount(paidAmount); // Use the value from DTO

	    DoctorInvoice invoice = new DoctorInvoice();
		invoice.setInvoiceGenerateDate(LocalDate.now());// Set the invoice generation date
		invoice.setAmount(appointment.getPaidAmount()); // Set the amount for the invoice
		invoice.setInvoiceStatus(AppConstant.INVOICE_STATUS_AWAITED); // Set the initial status
		invoice.setPaymentMethod("dfgh");
		invoice.setDoctor(doctor);
		invoice.setPatient(patient);
		doctorInvoiceRepository.save(invoice);
		appointment.setDoctorInvoice(invoice);
		
		doctorInvoiceRepository.save(invoice);
		
	AppointmentDto dto=	convertToAppointmentDto(appointmentRepository.save(appointment));

		timeSlot.setIsBooked(true);

		timeSlotRepository.save(timeSlot);

		response.put("Appointment", dto);
		return response;
	}

	
	
	
	
	
	
	
	
	
	
	
	// to get todays appointment
	@Override
	public Map<String, Object> todaysAppointment() {
		Map<String, Object> response = new HashMap<>();
		LocalDate today = LocalDate.now();
		List<TodaysAppointmentDto> todaysAppointment = appointmentRepository.findByAppointmentDate(today);
		//response.put("todaysAppointment", todaysAppointment);
		
//	List<AppointmentDto> adto=	todaysAppointment.stream()
//        .map(this::convertToDTO)
//        .collect(Collectors.toList());
	response.put("todaysAppointment",todaysAppointment);
		return response;
	}

	//to get upcoming appointment
	public Map<String, Object> upcomingAppointment() {
		Map<String, Object> response = new HashMap<>();
		LocalDate today = LocalDate.now();
		List<TodaysAppointmentDto> upcomingAppointments = appointmentRepository.findByAppointmentDateAfterOrderByAppointmentDateAscAppointmentTimeAsc(today);
				
		response.put("upcomingAppointment", upcomingAppointments);
		return response;

	}
	
	public Map<String, Object> getAppointmentDetails(Long appointmentId) {
	 
		Map<String, Object> response = new HashMap<>();
	    Appointment appointment = appointmentRepository.findById(appointmentId)
	            .orElseThrow(() -> new  ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + appointmentId));

	    
	    TodaysAppointmentDto appointmentdetaile= convertToTodaysAppointmentDto(appointment);
	    response.put("appointmentDetails", appointmentdetaile);
	    return response;
	}

	@Override
	public Map<String, Object> cancelAppointment(Long appointmentId) {
		   
		  Map<String, Object> response = new HashMap<>();
		  Appointment appointment = appointmentRepository.findById(appointmentId)
		            .orElseThrow(() -> new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + appointmentId));

		  
		    appointment.setStatus(AppConstant.APPOINTMENT_STATUS_CANCELED);

		   TodaysAppointmentDto canceledAppointment=convertToTodaysAppointmentDto( appointmentRepository.save(appointment));
		   
	ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.APPOINTMENT_CANCELED,HttpStatus.OK);
		    response.put("response", apiResponse);
		    response.put("appointment canceled",canceledAppointment );
		    emailServices.sendEmail(  appointment.getPatient().getPatientName()+
	                " has been  " + AppConstant.APPOINTMENT_CANCELED + "."+"at time" + appointment.getAppointmentTime(), appointment.getPatient().getEmail());
		return  response;
	}
	
	
	public Map<String, Object> rescheduledAppointment(AppointmentDto appointmentDto) {
		   
		  Map<String, Object> response = new HashMap<>();
		  Appointment appointment = appointmentRepository.findById(appointmentDto.getId())
		            .orElseThrow(() -> new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + appointmentDto.getId()));

		  
		    appointment.setStatus(AppConstant.APPOINTMENT_STATUS_RESCHDULED);
		     appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
		     appointment.setAppointmentDate(appointmentDto.getAppointmentDate());

		   AppointmentDto reschduledAppointment=convertToAppointmentDto( appointmentRepository.save(appointment));
		   
	ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.APPOINTMENT_RESCHEDULED,HttpStatus.OK);
		    response.put("response", apiResponse);
		    response.put(AppConstant.APPOINTMENT_RESCHEDULED,reschduledAppointment );
		    emailServices.sendEmail("Your appointment with ID " + appointment.getPatient().getPatientName()+
	                " has been updated to " + AppConstant.APPOINTMENT_RESCHEDULED + "."+"at time" + appointment.getAppointmentTime(), appointment.getPatient().getEmail());
		return  response;
	}
	
	
	
	
	
	public Map<String, Object> updateAppointment( AppointmentDto updatedAppointmentDto) {
		Map<String, Object> response =new HashMap<>();
		
		Optional<Appointment> existingAppointmentOptional = appointmentRepository.findById(updatedAppointmentDto.getId());

		 if (existingAppointmentOptional.isPresent()) {
	            Appointment existingAppointment = existingAppointmentOptional.get();

	         
	            existingAppointment.setAppointmentDate(updatedAppointmentDto.getAppointmentDate());
	            existingAppointment.setPurpose(updatedAppointmentDto.getPurpose());
	      Patient p=existingAppointment.getPatient();
	         p.setPatientName(updatedAppointmentDto.getPatientName());
	         
	         p.setPaidAmount(updatedAppointmentDto.getPaidAmount());
	           
	         ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.APPOINTMENT_UPDATED,HttpStatus.OK);
			    response.put("response", apiResponse);

			   AppointmentDto updatededAppointment=convertToAppointmentDto(appointmentRepository.save(existingAppointment) );
			   
	           
	            //appointmentRepository.save(existingAppointment);
	            response.put("response",updatededAppointment );
	            
	            return response;
	            

		 }
		 else 
			throw new ResourceNotFoundException(AppConstant.APPOINTMENT_NOT_FOUND + updatedAppointmentDto.getId());
	}
	
	
	
	 public Map<String, Object> notifyPatientBeforeAppointment( ) {
		 
		 Map<String, Object> response=new HashMap<>();
		 LocalDate today = LocalDate.now();
		 LocalDate upcoming= today.plusDays(2);
		
		List<Appointment> appointments=appointmentRepository.getByAppointmentDate(upcoming);
			 
			
			 
			 for (Appointment app : appointments) {
				 
				 
		            String patientEmail = app.getPatient().getEmail();
		          
		            String content = "Your appointment is scheduled for " + app.getAppointmentDate() + " at " + app.getAppointmentTime();

		            emailServices.sendEmail(content, patientEmail);
		        }
			  ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.NOTIFICATION_UPCOMING,HttpStatus.OK);
			    response.put("response", apiResponse);
                 return response;
		 }

	
	public Map<String, Object> countTotalPatientOfDoctor(String email) {
		 Map<String, Object> response=new HashMap<>();
		 Long totalPatient=appointmentRepository.countPatientsForDoctor(email);
		 ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.DOCTORS_TOTAL_PETIENT,HttpStatus.OK);
		    response.put("response", apiResponse);
		    
		    response.put(AppConstant.TOTAL_PETIENT, totalPatient);
		return   response ;
	}

	@Override
	public Map<String, Object> countTodaysTotalPatientOfDoctor(String email) {
		 Map<String, Object> response=new HashMap<>();
		 LocalDate today=LocalDate.now();
		 Long totalPatient=appointmentRepository.countTodaysTotalPatientForDoctor(email, today);
		 ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.DOCTORS_TODAYS_TOTAL_PETIENT,HttpStatus.OK);
		    response.put("response", apiResponse);
		    
		    response.put(AppConstant.TOTAL_PETIENT, totalPatient);
		return   response;
	}

	@Override
	public Map<String, Object> doctorsUpcomingTotalAppointment(String email) {
		 Map<String, Object> response=new HashMap<>();
		 LocalDate today=LocalDate.now();
	Long totalAppontments=	appointmentRepository.countUpcomingAppointments(today, email);
	 ApiResponse apiResponse= new ApiResponse(Boolean.TRUE,AppConstant.DOCTORS_UPCOMING_APPOINTMENT_COUNT,HttpStatus.OK);
	    response.put("response", apiResponse);
	    
	    response.put(AppConstant.TOTAL_UPCOMING_APPOINTMENT, totalAppontments);
	return   response;
		
	}
		 
		            

	 }

