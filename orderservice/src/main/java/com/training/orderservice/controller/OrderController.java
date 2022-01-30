package com.training.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.orderservice.dao.OrderRepository;
import com.training.orderservice.model.Orders;

@RestController
public class OrderController {
	
	@Autowired
	OrderRepository orderrepo;
	
	@GetMapping("/orders")
	public List<Orders> orders(){
		return (List<Orders>) orderrepo.findAll(); 
	}
	
	@PostMapping("/order")
	public Orders createOrder(@RequestBody Orders order) {
		return orderrepo.save(order);
	}

}
