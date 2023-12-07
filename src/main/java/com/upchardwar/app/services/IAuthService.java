package com.upchardwar.app.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.Varification;
import com.upchardwar.app.entity.payload.UserRequest;

public interface IAuthService {
	public UserDetails loadUserByUsername(String username);
	
	public Varification EmailVarification(Varification var);


	void verifyUser(String email,String otp);
}
