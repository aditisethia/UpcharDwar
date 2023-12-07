package com.upchardwar.app.controller.doctor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.entity.payload.UserResponse;

import com.upchardwar.app.services.IUserService;
import com.upchardwar.app.services.impl.UserServiceImpl;


@RestController
@RequestMapping("/upchardwar/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	
	@PostMapping("/")
public  String createUser(@RequestBody UserRequest user) throws Exception {
	
  UserResponse u= service.createUser(user);
  

return null;
}
	
	
}