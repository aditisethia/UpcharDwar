package com.upchardwar.app.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.emailutil.EmailServices;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.Varification;
import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.entity.payload.UserResponse;
import com.upchardwar.app.exception.OtpExpireException;
import com.upchardwar.app.helper.EmailRequest;
import com.upchardwar.app.security.JwtUtils;
import com.upchardwar.app.security.UserResponseS;
import com.upchardwar.app.services.IAuthService;
import com.upchardwar.app.services.IUserService;

@RestController
@RequestMapping("upchardwar/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private EmailServices emailServices;
	
	@Autowired
	private IUserService uservice;
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseS> loginUser(@RequestBody UserRequest userRequest) {
		
		
         System.out.println(userRequest.getEmail());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

		String token = jwtUtils.generateToken(userRequest.getEmail());
		System.out.println("hello");
		System.out.println(token);
		return ResponseEntity.ok(new UserResponseS(token, "getenared By aditi"));

	}
	
	//to send email
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		
		this.emailServices.sendEmail(emailServices.generateOtp(), request.getTo());
		return ResponseEntity.ok("done  ");
	}
	
	 @PostMapping(value = "/generate-otp", produces = "application/json") 
	 public ResponseEntity<?> sendOtpToEmail(@RequestBody Varification var) {
	        try {
	        	System.err.println(var.getEmail());
	            
	            return   ResponseEntity.ok( authService.EmailVarification(var));
	        } catch (Exception e) {
	        	
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate OTP: " + e.getMessage());
	        }
		
	    }

	 @GetMapping("/verify")
	 public ResponseEntity<Object> verifyUser(@RequestParam("email") String email, @RequestParam("otp") String otp) {
	     Map<String,Object> data = new HashMap<>();
		 try {
	         authService.verifyUser(email, otp);
	         // Change the response type to your desired type
	         // For example, return a success message
	         data.put("data", "User verified successfully.");
	         return ResponseEntity.status(HttpStatus.OK).body(data);
	     } catch (OtpExpireException e) {
	    	 data.put("data", "OTP has expired. Please generate a new one.");
	         // Return a bad request response with a message
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
	     } catch (Exception e) {
	         // Return an internal server error response with an error message
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to verify user: " + e.getMessage());
	     }
	 }
	 

		@GetMapping("/current-user")
		public UserResponse getCurrentUser(Principal p) {
			System.out.println("fghgj");
			return this.uservice.getUserByName(p.getName());
			//return this.userDetailsService.loadUserByUsername(p.getName());
		}
}
