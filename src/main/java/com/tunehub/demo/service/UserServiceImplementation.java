package com.tunehub.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.entity.User;
import com.tunehub.demo.repository.UserRepositoy;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepositoy uRepo;
	
	

	//service to add the user obj/data in db
	@Override
	public String addUser(User user) {
		//save the user in db 
		uRepo.save(user);
		//return success msg
		return ("added succesfully");
	}
	
	

	//service to check for duplicate email
	@Override
	public Boolean emailExist(String email) {
        //check whether email is present in db
		if (uRepo.findByEmail(email) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	

	// service for validating login
	@Override
	public Boolean validateLogin(String email, String password) {
		//get user obj/ all data of specific user based on email given 
		User user = uRepo.findByEmail(email);
		//check password from db matched the password  user entered
		if (user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	

	//service fot getting role of user based on email give
	@Override
	public String getRole(String email) {
		//get the user object based on email given 
        User user = uRepo.findByEmail(email);
        //extract and return  role of user from user object
        return user.getRole();  
	}



	@Override
	public User getUser(String email) {
	    
		return uRepo.findByEmail(email);
	}



	@Override
	public void updateUser(User user) {
		uRepo.save(user);
		
	}

}
