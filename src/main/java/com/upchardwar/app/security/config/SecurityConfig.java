package com.upchardwar.app.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.upchardwar.app.security.SecurityFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	/** For PassWord Encode */
	@Autowired
	private BCryptPasswordEncoder passwoerEncoder;
	/** For User Details Services */
	@Autowired
	private UserDetailsService userDetailsService;

	private String[] pAll = {"/upchardwar/schedule/{id}", "/upchardwar/appointment/book","upchardwar/schedule/doctor/{doctorId}","upchardwar/reviewrating/","/upchardwar/doctor/userid/{userId}","/upchardwar/auth/login", "/upchardwar/doctor/save", "/upchardwar/speciality/"

			,"/upchardwar/patient/save", "/upchardwar/lab/save","/upchardwar/lab/all", "/upchardwar/pharmacy/save",
			"/upchardwar/auth/sendemail", "/upchardwar/auth/generate-otp", "/upchardwar/auth/verify",
			"upchardwar/auth/current-user", "upchardwar/speciality/all", "/upchardwar/schedule/",
			"/upchardwar/appointment/book-appointment", "/upchardwar/appointment/notify" ,"upchardwar/labrequest/save",

			"/upchardwar/patient/save", "/upchardwar/lab/save", "/upchardwar/pharmacy/save",
			"/upchardwar/auth/sendemail", "/upchardwar/auth/generate-otp", "/upchardwar/auth/verify",
			"upchardwar/auth/current-user", "upchardwar/speciality/all", "/upchardwar/schedule/",

			"/upchardwar/appointment/book-appointment", "/upchardwar/appointment/notify" ,"/upchardwar/doctor/userid/{id}","/upchardwar/doctor/{pageNo}/{pageSize}",

			"/upchardwar/appointment/book-appointment", "/upchardwar/appointment/notify" ,
			"/api/getImageApi/{imgName}","upchardwar/lab/save1","upchardwar/labBooking/"};

	private String[] accessByAdmin = { "/user/admin" ,"upchardwar/lab/delete/{id}"};

	private String[] accessByAdminDoctor = { "/upchardwar/doctor/", "/upchardwar/appointment/updateAppointment",
			"/upchardwar/appointment/rescheduleAppointment" };

	private String[] accessByAdminDoctorPatient = { "/upchardwar/doctor/{id}", "/upchardwar/doctor/{pageNo}/{pageSize}",
			"/upchardwar/appointment/appointmentDetails/{id}",
			"/upchardwar/appointment/all/{pageNo}/{pageSize}/{sortBy}" };


	private String[] accessByDoctor = {"/upchardwar/schedule/create", "/upchardwar/appointment/createSchedule",
			"/upchardwar/appointment/createTimeSlote", "/upchardwar/appointment/todaysAppointments",
			"/upchardwar/appointment/cancelAppointment/{id}", "/upchardwar/appointment/countPatient",
			

			"/upchardwar/appointment/countTodaysPetient", "/upchardwar/appointment/countUpcomingAppointments" };

    private String[] accessByLabPatientAdmin = {"upchardwar/labTest/all/{pageNo}/{pageSize}/{sortBy}/{labId}","upchardwar/labTest/get/{labTestId}","upchardwar/lab/all/{pageNo}/{pageSize}/{sortBy}","upchardwar/lab/search/{pn}/{ps}/{sortBy}"};
	


	
	private String[] accessByPatient = {  "/upchardwar/patient/save1" , "/upchardwar/appointment/all/patient/{pageNo}/{pageSize}/{sortBy}" ,"upchardwar/labreviewrating/"};
	
	private String[] accessByLab = {"upchardwar/labTest/save","upchardwar/lab/user/{userId}"};
	
	private String[] accessByLabAdmin= {"upchardwar/labTest/delete/{labTestId}","upchardwar/labTest/update/{labTestId}"};

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private SecurityFilter securityfilter;

	/** For Authentication..... */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwoerEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	/** For authorization */
	@Bean
	public SecurityFilterChain configurePaths(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().requestMatchers(pAll).permitAll().requestMatchers(accessByAdmin)
				.hasAuthority("ADMIN").requestMatchers(accessByAdminDoctor).hasAnyAuthority("ADMIN", "DOCTOR")
				.requestMatchers("accessByLabPatientAdmin").hasAnyAuthority("ADMIN", "PATIENT","ADMIN")

				.requestMatchers(accessByDoctor).hasAuthority("DOCTOR")
				.requestMatchers(accessByPatient).hasAuthority("PATIENT")
				.requestMatchers(accessByLab).hasAuthority("LAB")
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(securityfilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

}
