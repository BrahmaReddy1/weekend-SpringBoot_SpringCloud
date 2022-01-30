package com.training.orderservice.dao;

import org.springframework.data.repository.CrudRepository;

import com.training.orderservice.model.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer>{

}
