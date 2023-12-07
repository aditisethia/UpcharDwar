package com.upchardwar.app.services.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.upchardwar.app.entity.status.AppConstant;
import com.upchardwar.app.exception.OtpExpireException;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.repository.VarificationRepository;
import com.upchardwar.app.services.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService ,UserDetailsService{

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
     System.out.println( urepo.findByEmail(email).get());
		
		User user = urepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email+"Not Exist User "));
		System.out.println(email);
		
  
		List<GrantedAuthority> list= user.getUserRole()
				.stream().map(role->new SimpleGrantedAuthority(role.getRole().getRoleName()))
				.collect(Collectors.toList());
 
		return new org.springframework.security.core.userdetails.User(email,user.getPassword(),list);
	}


	public Varification EmailVarification(Varification var) {
		   String otp=eServices.generateOtp();
		   String email=var.getEmail();
   eServices.sendEmail(otp,email);
		  
	   var.setOtp(otp);
//		   var.setEmail(email);
//		   System.out.println(var.getEmail()+"......");
		   var.setExprireTime(LocalDateTime.now().plusSeconds(59));
		  return varRepository.save(var);
		  // System.out.println(var.getEmail());
		 //return var;
	}


	public Varification getVarifiedByEmailAndIsActive(String email) {
	    Optional<Varification> var= varRepository.findByEmailAndIsActive(email, false);
	    if(var.isEmpty())
	         throw   new ResourceNotFoundException(AppConstant.THIS_USER_IS_NOT_VARIFIED);
	         
	        return var.get();
	            
	            
	}	
	
	

	@Override
	public void verifyUser(String email,String otp) {
		    System.out.println(".........");
		   Varification var=getVarifiedByEmailAndIsActive(email);
		   var.setIsActive(true);
		  System.out.println(var.getIsActive());
		 if (var.getExprireTime().isBefore(LocalDateTime.now())&& var.getOtp().equals(otp)) {
	           
	            throw new OtpExpireException(AppConstant.OTP_EXPIRE);
	        }
		 User user=new User();
			user.setName(var.getName());
			user.setEmail(var.getEmail());
			user.setPassword(var.getPassword());
			String encPwd = passwordEncoder.encode(user.getPassword());
			user.setPassword(encPwd);
			Set<UserRole> roles=new HashSet();
		     Role role=new Role();
		     role.setRoleId(var.getRoleId());     
		     UserRole userRole = new UserRole();
		     userRole.setRole(role);
		     userRole.setUser(user);
		     roles.add(userRole);
	        user.setUserRole(roles);
	   
            user.setRole(role);
	       // user.setStatus(AppConstant.USER_STATUS_ACTIVE);
	       
	        urepo.save(user);
	        
	        
	       varRepository.save(var);

	}


	
	



	
			}
	


