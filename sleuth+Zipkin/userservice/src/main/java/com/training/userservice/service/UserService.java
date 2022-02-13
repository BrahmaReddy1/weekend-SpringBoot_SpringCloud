package com.training.userservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.userservice.dao.UserRepository;
import com.training.userservice.dto.OrdersDto;
import com.training.userservice.dto.UserDto;
import com.training.userservice.exceptions.UserNotFoundException;
import com.training.userservice.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	 
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${orderservice.adress}")
	String orderserviceUrl;
	
	public List<OrdersDto> greetOrders(int id) {
		logger.info("Order service requested");
		return restTemplate.getForObject(orderserviceUrl+"/orderlist/"+id,List.class);
	}
	
	public UserDto getUserOrders(int id) {
		List<OrdersDto> orders= restTemplate.getForObject(orderserviceUrl+"/orderlist/"+id,List.class);
		User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("No user Avilable with Id "+ id));
		UserDto udto = new UserDto();
		udto.setId(user.getId());
		udto.setName(user.getName());
		udto.setAdress(user.getAdress());
		udto.setOrders(orders);
		
		return udto;
	}
	
	
	public List<User> gethydUsers(String addr){
		return userRepo.getUserbyHyd(addr);
	}
	
	public List<User>  getUserList(){
		return (List<User>) userRepo.findAll();
	}
	
	public List<User> getUsersPage(int pageNo,int pageSize){
		Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by("adress"));
		 Page<User> page= userRepo.findAll(pageable);
		return page.toList();
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
