package com.upchardwar.app.services;

import java.util.List;
import java.util.Map;

import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.entity.payload.UserResponse;

public interface IUserService {

	public UserResponse createUser(UserRequest request) ;
	
	public UserResponse getUserByName(String name);
	
	public void deleteUser(UserRequest request);

	 public Map<String, Object> search(String searchTerm);
}
