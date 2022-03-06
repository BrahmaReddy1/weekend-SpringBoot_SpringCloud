package com.training.basicauthentication.utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.basicauthentication.dao.UserRepository;
import com.training.basicauthentication.dao.Users;

@RestController
public class DefaultUserService {

	
	@Autowired
	UserRepository repo;
	@Autowired
	PasswordEncoder pwdencode;
	

	@PostMapping("/user")
	public String insertDefalurecords(@RequestBody Users user) {
		user.setPassword(pwdencode.encode(user.getPassword()));
		repo.save(user);
		
		return "User:===: "+user.getUsername();
		
	}
	
	
}
