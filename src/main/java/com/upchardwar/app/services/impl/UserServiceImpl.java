package com.upchardwar.app.services.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.entity.payload.UserResponse;
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.RoleRepository;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository urepo;

	@Autowired
	private RoleRepository rrepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	public UserResponse userToUserResponse(User user) {
		return this.modelMapper.map(user, UserResponse.class);
	}

	public User userRequestToUser(UserRequest userRequest) {
		return this.modelMapper.map(userRequest, User.class);
	}

	@Override
	public UserResponse createUser(UserRequest request) {

		Optional<User> local = this.urepo.findByEmail(request.getEmail());
		if (local.isPresent())
			throw new ResourceAlreadyExistException(AppConstant.USER_EMAIL_ALREADY_EXIST);

//			for(UserRole ur:userRoles) {
//				rrepo.save(ur.getRole());
//			}

		// request.getUserRole().addAll(userRoles);

		User user = this.userRequestToUser(request);

		System.err.println("...");
		Set<UserRole> roles = new HashSet();
		Role role = new Role();
		role.setRoleId(1L);
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		roles.add(userRole);
		user.setUserRole(roles);

		return this.userToUserResponse(this.urepo.save(user));

	}

	public ResponseEntity<?> changeStatus(Long id, String status) {
		Map<String, Object> response = new HashMap<>();

		Optional<User> local = this.urepo.findByIdAndStatus(id, AppConstant.USER_STATUS_NOT_VARIFIED);
		if (local.isPresent()) {
			User u = local.get();
			u.setStatus(status);
		} else {
			throw new ResourceNotFoundException(AppConstant.USER_NOT_FOUND);
		}
		response.put("Status", AppConstant.USER_STATUS_CHANGED_SUCCESSFULLY);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@Override
	public void deleteUser(UserRequest request) {
		// TODO Auto-generated method stub

	}

	public UserResponse getUserByName(String name) {

		Optional<User> local = this.urepo.findByEmail(name);
		if (local.isPresent())
			return this.userToUserResponse(local.get());

		else
			throw new ResourceNotFoundException(AppConstant.USER_NOT_FOUND);
	}

}
