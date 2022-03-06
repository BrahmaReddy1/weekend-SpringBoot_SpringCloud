package com.training.basicauthentication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<Users, Integer>{

}
