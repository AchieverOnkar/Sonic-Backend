package com.tunehub.demo.service;

import com.tunehub.demo.entity.User;

public interface UserService {

	String addUser(User user);

	Boolean emailExist(String email);

	Boolean validateLogin(String email, String password);

	String getRole(String email);

	User getUser(String email);

	void updateUser(User user);

}
