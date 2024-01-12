package com.tunehub.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.demo.entity.User;
import com.tunehub.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService uService;

	
	//request will come from register page
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		// that method will return truth value
		Boolean userStatus = uService.emailExist(user.getEmail()); // method to autheticate uniqueEmail

		// if user status is false i.e email is not duplicate
		if (!userStatus) {
			//adding the user
			uService.addUser(user);
			//checking user role
			if (user.getRole().equals("admin")) {
				// after that navigate to admin home page
				return "adminhome";
			}else {
				// after that navigate to user home page
			    return "userhome";
			}
		} else {
			// print msg on consol
			System.out.println("user already exists");
			//show registeration page to try again
			return "registration"; 
		}

	}

	
	
	//request will come from login page
	@PostMapping("/validate")
	public String validateLogin(@RequestParam String email, @RequestParam String password , HttpSession session) {
		
		// search further only if email is present inside db
		if (uService.emailExist(email)) {
			//this method will check whether credentials are present inside the db
			Boolean valid = uService.validateLogin(email, password);
			
			// this means credentials are correct
			if (valid) {
				//adding the email of the user as variable into session object
				session.setAttribute("email", email);
				
				// this method will return the role of user from the db
				String role = uService.getRole(email);
				
				// based on role of user redirecting on diffrent pages
				if (role.equals("admin")) {
					return "adminhome";
				} else {
					return "userhome";
				}
			} else {// login failed due incorrect password
				// giving another try to login
				return "login";
			}
		} else {// login failed due to email not present inside db
			// giving another try to login
			return "login";
		}
	}
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

}
