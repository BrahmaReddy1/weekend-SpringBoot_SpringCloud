package com.training.userservice.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.userservice.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

	
	User findByName(String name);
	
	User findByAdress(String adress);
	
	
	@Query(value = "select * from user where adress = :addr",nativeQuery = true)
	public List<User> getUserbyHyd(@Param("addr") String addr);
	
}
