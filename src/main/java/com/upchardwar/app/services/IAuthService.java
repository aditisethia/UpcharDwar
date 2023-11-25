package com.upchardwar.app.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthService {
	public UserDetails loadUserByUsername(String email);
	
	public Boolean sendEmail(String message,String to,String from);
}
