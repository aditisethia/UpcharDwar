package com.upchardwar.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.helper.EmailRequest;
import com.upchardwar.app.security.payload.UserResponseS;
import com.upchardwar.app.services.IAuthService;
import com.upchardwar.app.util.JwtUtils;
@RestController
@RequestMapping("upchardwar/auth")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IAuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseS> loginUser(@RequestBody UserRequest userRequest) {
		
		/** Authentication.... */
         System.out.println(userRequest.getEmail());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

		/** if Authenticate so generate token */

		String token = jwtUtils.generateToken(userRequest.getEmail());
		System.out.println("hello");
		System.out.println(token);
		return ResponseEntity.ok(new UserResponseS(token, "getenared By aditi"));

	}
	
	//to send email
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		
		this.authService.sendEmail(request.getMessage(), request.getTo(), request.getSubject());
		return ResponseEntity.ok("done  ");
	}
}
