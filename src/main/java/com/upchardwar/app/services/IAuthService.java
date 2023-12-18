package com.upchardwar.app.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.Varification;
import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.entity.payload.VarificationRequest;

public interface IAuthService {
	public UserDetails loadUserByUsername(String username);
	
	public ResponseEntity<?> EmailVarification(Varification var);


	public ResponseEntity<?> verifyUser(VarificationRequest request);
}
