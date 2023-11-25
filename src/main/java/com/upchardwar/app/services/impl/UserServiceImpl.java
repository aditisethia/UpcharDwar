package com.upchardwar.app.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.Role;
import com.upchardwar.app.entity.User;
import com.upchardwar.app.entity.UserRole;
import com.upchardwar.app.entity.doctor.Doctor;
import com.upchardwar.app.entity.payload.DoctorRequest;
import com.upchardwar.app.entity.payload.DoctorResponse;
import com.upchardwar.app.entity.payload.UserRequest;
import com.upchardwar.app.entity.payload.UserResponse;
import com.upchardwar.app.exception.ResourceAlreadyExistException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.RoleRepository;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
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
		
	    Optional<User> local =	this.urepo.findByName(request.getName());
	   if(local.isPresent())
		   throw new  ResourceAlreadyExistException("user with this name already exist");
	    
	    
//			for(UserRole ur:userRoles) {
//				rrepo.save(ur.getRole());
//			}
			
		//	request.getUserRole().addAll(userRoles);
	  
   User user =  this.userRequestToUser(request);
   
   
		 Set<UserRole> roles=new HashSet();
	     Role role=new Role();
	     role.setRoleId(1L);     
	     UserRole userRole = new UserRole();
	     userRole.setRole(role);
	     userRole.setUser(user);
	     roles.add(userRole);
         user.setUserRole(roles);		
         
		return 	this.userToUserResponse(this.urepo.save(user));
	
		
		
	}

	@Override
	public UserResponse getUserByName(String name) {
		
		return null;
	}

	@Override
	public void deleteUser(UserRequest request) {
		// TODO Auto-generated method stub

	}

	
	
	

}
