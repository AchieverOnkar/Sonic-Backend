package com.tunehub.demo.controller;

import java.util.Map;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tunehub.demo.entity.ChangePassword;
import com.tunehub.demo.entity.LoginData;
import com.tunehub.demo.entity.User;
import com.tunehub.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	UserService uService;

	// request will come from register page
	@PostMapping("/register")
	public String addUser(@RequestBody User user) {
		System.out.println("User data: " + user);
		// that method will return truth value
		Boolean userStatus = uService.emailExist(user.getEmail()); // method to autheticate uniqueEmail

		// if user status is false i.e email is not duplicate
		if (!userStatus) {
			// adding the user
			uService.addUser(user);
			// checking user role
			if (user.getRole().equals("admin")) {
				// after that navigate to admin home page
				return "adminhome";
			} else {
				// after that navigate to user home page
				return "dashboard";
			}
		} else {
			// print msg on consol
			System.out.println("user already exists");
			// show registeration page to try again
			return "registration";
		}

	}

	// request will come from login page
	@PostMapping("/validate")
	public Map<String, Object> validateLogin(@RequestBody LoginData data, HttpSession session, Model model) {
		Map<String, Object> response = new HashMap<>();
		String email = data.getEmail();
		String password = data.getPassword();

		if (uService.emailExist(email)) {
			Boolean valid = uService.validateLogin(email, password);
			System.out.println("is valid ?"+valid);

			if (valid) {
				session.setAttribute("email", email);
				Boolean isPremium = uService.getPremiumStatus(email);
				String role = uService.getRole(email);

				response.put("success", true);
				response.put("role", role);
				response.put("isPremium", isPremium);
			} else {
				response.put("success", false);
				response.put("noPassword",true);
			}
		} else {
			response.put("success", false);
			response.put("noEmail",true);
		}

		return response;
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "resetPassword";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody User user, Model model) {

		String email =user.getEmail();
		String securityQuestion = user.getSecurityQuestion();
		String securityAnswer = user.getSecurityAnswer();
		
		System.out.println(email+securityQuestion+securityAnswer);
		// checking whether user exist with email
		Boolean emailExist = uService.emailExist(email);
		if (emailExist) {
			// match the security answer
			Boolean match = uService.validateSecurityQuestion(securityQuestion, securityAnswer, email);
			model.addAttribute("email", email);
			if (match)
				return "changePassword";
		}

		return "resetPassword";
	}

	@PostMapping("/changePassword")
	public String changePassword( @RequestBody ChangePassword newdata) {
		User user = uService.getUser(newdata.getEmail());
		
		String newPassword =  newdata.getNewPassword();
		user.setPassword(newPassword);
		uService.updateUser(user);

		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();// ending users session
		return "login";
	}

}
