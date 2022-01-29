package com.training.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.userservice.dao.UserRepository;
import com.training.userservice.exceptions.UserNotFoundException;
import com.training.userservice.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public List<User>  getUserList(){
		return (List<User>) userRepo.findAll();
	}
	
	
	public User getUserById(int id) {
		User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No user Avilable with Id "+ id));
		return user;
	
	}
	
	public User getUserByName(String name) {
		return userRepo.findByName(name);
	}
	
	public User getUserByAdress(String adress) {
		return userRepo.findByAdress(adress);
	}
	
	
	
	public User  saveUser(User u) {
		return userRepo.save(u);
	}
	
	
	
	public User  updateUser(User u, int id) {
		User existing =  userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No user Avilable with Id "+ id));
		if(u.getName()!=null)
			existing.setName(u.getName());
		if(u.getAdress()!=null)
			existing.setAdress(u.getAdress());
		userRepo.save(existing);
		return existing;
			
	}
	
	public String deleteUser( int id) {
		User u = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No user Avilable with Id "+ id));
		if(u!=null)
		userRepo.deleteById(id);
		return "User Removed SUccesfully";
		
	}
}
