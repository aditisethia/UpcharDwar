package com.upchardwar.app.services.impl;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.upchardwar.app.entity.User;
import com.upchardwar.app.exception.ResourceNotFoundException;
import com.upchardwar.app.repository.UserRepository;
import com.upchardwar.app.services.IAuthService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class AuthServiceImpl implements IAuthService ,UserDetailsService{

	@Autowired
	private UserRepository urepo;
	
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

	@Override
	public Boolean sendEmail(String message, String to, String subject) {
		//variable for gmail
		
		      
		        boolean f=false;
				String host="smtp.gmail.com";
				
				String  from="upchardwar@gmail.com";
				//get the system properties
				Properties properties=System.getProperties();
				
				Random r=new Random();
				int rn=r.nextInt(10000);
				
				message+="otp is :" +rn;
				//setting imp information to properties object
				
				//host set
				
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.socketFactory.host", "465");
				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail.smtp.auth","true");
				
				//Step 1: to get session object
				
			Session session=	Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication(from, "dgno knnf fbmu tcsn");
					}
					
				});
				session.setDebug(true);
				//step 2 : compose the message [text , multimedia]
				
				MimeMessage m=new MimeMessage(session);
				
				//from
				try {
					m.setFrom(from);
					
					// adding recipent
					
					m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					
					//adding subject to message
					
					m.setSubject(subject);
					
					m.setText(message);
					
					Transport.send(m);
					f=true;
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return f;
			}
	

}
