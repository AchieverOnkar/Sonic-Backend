package com.sonic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonic.demo.entity.User;


public interface UserRepositoy extends JpaRepository<User, Integer>{
	//this is custom method based on findById which will return the user object of matched email
	public User findByEmail(String email);
		
	

}
