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

	private String[] pAll = { "/upchardwar/auth/login", "/upchardwar/doctor/save", "/upchardwar/speciality/",
			"/upchardwar/patient/save", "/upchardwar/lab/save","/upchardwar/lab/all", "/upchardwar/pharmacy/save",
			"/upchardwar/auth/sendemail", "/upchardwar/auth/generate-otp", "/upchardwar/auth/verify",
			"upchardwar/auth/current-user", "upchardwar/speciality/all", "/upchardwar/schedule/",
			"/upchardwar/appointment/book-appointment", "/upchardwar/appointment/notify" ,"upchardwar/labrequest/save"};

	private String[] accessByAdmin = { "/user/admin" };

	private String[] accessByAdminDoctor = { "/upchardwar/doctor/", "/upchardwar/appointment/updateAppointment",
			"/upchardwar/appointment/rescheduleAppointment" };

	private String[] accessByAdminDoctorPatient = { "/upchardwar/doctor/{id}", "/upchardwar/doctor/{pageNo}/{pageSize}",
			"/upchardwar/appointment/appointmentDetails/{id}" };

	private String[] accessByDoctor = { "/upchardwar/appointment/createSchedule",
			"/upchardwar/appointment/createTimeSlote", "/upchardwar/appointment/todaysAppointments",
			"/upchardwar/appointment/cancelAppointment/{id}", "/upchardwar/appointment/countPatient",
			"/upchardwar/appointment/countTodaysPetient", "/upchardwar/appointment/countUpcomingAppointments" };

	private String[] accessByPatient = { "upchardwar/reviewrating/" };

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
				.requestMatchers(accessByAdminDoctorPatient).hasAnyAuthority("ADMIN", "DOCTOR", "PATIENT")
				.requestMatchers(accessByDoctor).hasAuthority("DOCTOR").requestMatchers(accessByPatient)
				.hasAuthority("PETIENT").anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(securityfilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

}
