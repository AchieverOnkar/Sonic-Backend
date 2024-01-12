package com.tunehub.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.tunehub.demo.entity.User;
import com.tunehub.demo.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class PayController {

	@Autowired
	UserService userService;
	
	
	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}
	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session) {
		int amount = 1999;
		Order order = null;
		try {
			// Initialize client
			RazorpayClient razorpay = new RazorpayClient("rzp_test_vx3Eg3PqiTEmHO", "2rtg2rw5w8YUj8W8aOuRUuFF");
	        
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);
    
	        String mail =  (String) session.getAttribute("email");
	         User user = userService.getUser(mail);
	         user.setIsPremium(true);
	         userService.updateUser(user);
		
		
		
		} catch (RazorpayException e) {
			e.printStackTrace();
		}finally {
			return order.toString();
			
		}
		
		
	}
	
	
}
