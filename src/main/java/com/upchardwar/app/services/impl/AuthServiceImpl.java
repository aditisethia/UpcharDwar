package com.upchardwar.app.services.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upchardwar.app.emailutil.EmailServices;
import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.Varification;
import com.upchardwar.app.entity.payload.VarificationRequest;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.OtpExpireException;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.repository.VarificationRepository;
import com.upchardwar.app.services.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService, UserDetailsService {

	@Autowired
	private UserRepository urepo;

	@Autowired

	private EmailServices eServices;

	@Autowired
	private VarificationRepository varRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) {
	//	System.out.println(urepo.findByEmail(email).get());
		Optional<User> user = urepo.findByEmail(email);
		if(user.isEmpty()) {
			throw new ResourceNotFoundException(AppConstant.USER_NOT_FOUND);
		}
		
		System.out.println(email);
		//System.out.println(passwordEncoder.encode("1234"));

		List<GrantedAuthority> list = user.get().getUserRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getRoleName())).collect(Collectors.toList());

		//System.out.println(user.get().getPassword());
		return new org.springframework.security.core.userdetails.User(email, user.get().getPassword(), list);
	}

	public ResponseEntity<?> EmailVarification(Varification var) {
		Map<String, Object> response = new HashMap<>();
//		   String otp=eServices.generateOtp();
//		   String email=var.getEmail();
//		   Optional<Varification> varop = varRepository.findByEmailAndIsActive(email, true);
//		   
//		   if(varop.isPresent()) {
//			response.put(AppConstant.EMAIL_VARIFICATION, "user already exist pls login");
//			   return response;
//		   }
//		   
//		   
//		   Optional<Varification> var1 = varRepository.findByEmailAndIsActive(email, false);
//		   if(var1.isPresent()) {
//			   response.put(AppConstant.EMAIL_VARIFICATION, "user exist but not varified");
//			   return response;
//		   }
//		   
//        eServices.sendEmail(otp,email);
//		  
//	   var.setOtp(otp);
////		   var.setEmail(email);
////		   System.out.println(var.getEmail()+"......");
//		   var.setExprireTime(LocalDateTime.now().plusSeconds(59));
//		   varRepository.save(var);
//		   response.put(AppConstant.EMAIL_VARIFICATION, "email varified successfully");
//		 return  response;

		// .........................................
		Varification varification;

		Optional<Varification> user = this.varRepository.findByEmail(var.getEmail());

		// checking if user account is already present
		if (user.isPresent() && user.get().getIsActive()) {
			response.put(AppConstant.MESSAGE, AppConstant.USER_ALREADY_REGISTERED_WITH_EMAIL);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// checking if user account is already present and is verified or is active
		if (user.isPresent() && !user.get().getIsActive())
			varification = user.get();

		else {
			varification = new Varification();

		}

		String otp = eServices.generateOtp();
		
        varification.setRoleId(var.getRoleId());
        System.out.println(varification.getRoleId()+".......................");
		varification.setEmail(var.getEmail());
		varification.setName(var.getName());
		varification.setPassword(passwordEncoder.encode(var.getPassword()));
		varification.setOtp(otp);
		varification.setExprireTime(LocalDateTime.now().plusSeconds(2*60));

		// sending email for verification
		Boolean isSentSuccessfully = eServices.sendEmail(otp, var.getEmail());

		if (isSentSuccessfully) {

			response.put(AppConstant.MESSAGE, AppConstant.USER_REGISTRATION_SUCCESS);
			response.put(AppConstant.EMAIL_STATUS, AppConstant.VERIFICATION_EMAIL_SEND);
			response.put(AppConstant.EMAIL, var.getEmail());
			Optional<Varification> userAccount = this.varRepository.findByEmail(var.getEmail());

			if (userAccount.isPresent())
				this.varRepository.deleteById(userAccount.get().getId());

			this.varRepository.save(varification);

		} else {
			response.put(AppConstant.MESSAGE, AppConstant.VARIFICATION_FAILED);
			response.put(AppConstant.EMAIL_STATUS, AppConstant.EMAIL_SEND_STATUS_FAILED);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public Varification getVarifiedByEmailAndIsActive(String email) {
		Optional<Varification> var = varRepository.findByEmailAndIsActive(email, false);
		if (var.isEmpty())
			throw new ResourceNotFoundException(AppConstant.THIS_USER_IS_NOT_VARIFIED);

		return var.get();

	}

	@Override
	public ResponseEntity<?> verifyUser(VarificationRequest request) {
		System.err.println(request.getEmail()+"  "+request.getOtp());
//		System.out.println(".........");
//		Varification var = getVarifiedByEmailAndIsActive(email);
//		var.setIsActive(true);
//		System.out.println(var.getIsActive());
//		 if (var.getExprireTime().isBefore(LocalDateTime.now())&& var.getOtp().equals(otp)) {
//	           
//	            throw new OtpExpireException(AppConstant.OTP_EXPIRE);
//	        }
//		User user = new User();
//		user.setName(var.getName());
//		user.setEmail(var.getEmail());
//		user.setPassword(var.getPassword());
//		String encPwd = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encPwd);
//		Set<UserRole> roles = new HashSet();
//		Role role = new Role();
//		
//		System.err.println(var.getRoleId());
//		role.setRoleId(var.getRoleId());
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		roles.add(userRole);
//		user.setUserRole(roles);
//
//		user.setRole(role);
//	 user.setStatus(AppConstant.USER_STATUS_ACTIVE);
//
//		urepo.save(user);
//
//		varRepository.save(var);
		
		
		
		Optional<Varification> userRegistered = this.varRepository.findByEmailAndOtp(request.getEmail(),
				request.getOtp());
		System.err.println(userRegistered.isPresent());
		Map<String, Object> response = new HashMap<>();
		if (userRegistered.isEmpty()) {
			response.put(AppConstant.MESSAGE, AppConstant.INVALID_OTP);
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}

		if (userRegistered.get().getIsActive()) {
			response.put(AppConstant.MESSAGE, AppConstant.USER_ALREADY_REGISTERED_WITH_EMAIL);
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
		
		if (userRegistered.get().getExprireTime().isBefore(LocalDateTime.now())&& userRegistered.get().getOtp().equals(request.getOtp())) {
         response.put(AppConstant.MESSAGE, AppConstant.LINK_EXPIRED);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
      }

		Varification var=userRegistered.get();
		var.setIsActive(true);
		User user = new User();
	user.setName(var.getName());
	user.setEmail(var.getEmail());
	
	user.setPassword(var.getPassword());
	//String encPwd = passwordEncoder.encode(user.getPassword());
	user.setPassword(var.getPassword());
	Set<UserRole> roles = new HashSet();
	Role role = new Role();
	
	System.err.println(var.getRoleId());
	role.setRoleId(var.getRoleId());
	UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		roles.add(userRole);
	user.setUserRole(roles);	
	 user.setStatus(AppConstant.USER_STATUS_NOT_VARIFIED);
	 
		this.urepo.save(user);
		this.varRepository.save(userRegistered.get());
		response.put(AppConstant.MESSAGE, AppConstant.USER_VERIFICATION_SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}

	
	
}