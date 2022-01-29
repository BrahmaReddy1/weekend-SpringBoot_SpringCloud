package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.userservice.dao.UserRepository;
import com.training.userservice.exceptions.UserNotFoundException;
import com.training.userservice.model.User;
import com.training.userservice.service.UserService;


@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@RequestMapping("/users")
	public ResponseEntity<List<User>>  getUserList(){
		return new ResponseEntity<List<User>>(service.getUserList(),HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		return new ResponseEntity<User>(service.getUserById(id),HttpStatus.OK);
	}
	
	@GetMapping("/username/{name}")
	public ResponseEntity<User> getUserByName(@PathVariable String name) {
		return new ResponseEntity<User>(service.getUserByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/useradress/{adress}")
	public ResponseEntity<User> getUserByAdress(@PathVariable String adress) {
		return new ResponseEntity<User>(service.getUserByAdress(adress),HttpStatus.OK);
	}
	
	@PostMapping(value = "/user")
	public ResponseEntity<User>  saveUser(@RequestBody User u) {
		return new ResponseEntity<User>(service.saveUser(u), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateuser/{id}")
	public ResponseEntity<User>  updateUser(@RequestBody User u,@PathVariable int id) {
		return new ResponseEntity<User>(service.updateUser(u, id),HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		return new ResponseEntity<String>(service.deleteUser(id),HttpStatus.OK);
	}
	
}
