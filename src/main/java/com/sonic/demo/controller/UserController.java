package com.sonic.demo.controller;

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

import com.sonic.demo.entity.ChangePassword;
import com.sonic.demo.entity.LoginData;
import com.sonic.demo.entity.User;
import com.sonic.demo.service.UserService;

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
			return "success";
		} else {
			// print msg on consol
			System.out.println("user already exists");
			return "email already exists !!";
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

			if (valid) {
				session.setAttribute("email", email);
				System.out.println("user email from session is:" + session.getAttribute("email"));

				Boolean isPremium = uService.getPremiumStatus(email);
				String role = uService.getRole(email);

				response.put("success", true);
				response.put("role", role);
				response.put("isPremium", isPremium);
			} else {
				response.put("success", false);
				response.put("noPassword", true);
			}
		} else {
			response.put("success", false);
			response.put("noEmail", true);
		}

		return response;
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "resetPassword";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody User user, Model model) {

		String email = user.getEmail();
		String securityQuestion = user.getSecurityQuestion();
		String securityAnswer = user.getSecurityAnswer();

		System.out.println(email + securityQuestion + securityAnswer);
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
	public String changePassword(@RequestBody ChangePassword newdata) {
//		System.out.println("data coming is :"+newdata.getEmail()+""+newdata.getNewPassword());
		User user = uService.getUser(newdata.getEmail());
		if (user != null) {
			String newPassword = newdata.getNewPassword();
			user.setPassword(newPassword);
			uService.updateUser(user);

			return "success";
		}
		return "failed";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("user email from session is:" + session.getAttribute("email"));

		session.invalidate();// ending users session
		return "login";
	}

	@GetMapping("/userStatus")
	public String userStatus(@RequestParam String email) {
		if (uService.getPremiumStatus(email) != null) {
			boolean premium = uService.getPremiumStatus(email);
			if (premium) {
				String name = uService.getUser(email).getUsername();
				System.out.println("user name is :" + name);
				return name;
			}
		}
		return "false";
	}

}
