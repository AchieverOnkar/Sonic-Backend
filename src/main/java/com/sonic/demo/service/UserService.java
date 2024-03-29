package com.sonic.demo.service;

import com.sonic.demo.entity.User;

public interface UserService {

	String addUser(User user);

	Boolean emailExist(String email);

	Boolean validateLogin(String email, String password);

	String getRole(String email);

	User getUser(String email);

	void updateUser(User user);

	Boolean getPremiumStatus(String email);

	Boolean validateSecurityQuestion(String securityQuestion, String securityAnswer, String email);



}
