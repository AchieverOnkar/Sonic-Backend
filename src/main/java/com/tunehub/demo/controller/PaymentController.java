package com.tunehub.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.tunehub.demo.entity.User;
import com.tunehub.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class PaymentController {

	@Autowired
	UserService userService;

//	@GetMapping("/pay")
//	public String pay() {
//		return "pay";
//	}

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
			orderRequest.put("amount", amount * 100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);

		} catch (RazorpayException e) {
			e.printStackTrace();
		} finally {
			return order.toString();

		}

	}

	@GetMapping("/payment-success")
	public String paymentSuccess(HttpSession session, Model model) {

		String mail = (String) session.getAttribute("email");
		System.out.println("email from session: "+mail);
		User user = userService.getUser(mail);
		System.out.println("User object to update the prmium status"+user);
		user.setIsPremium(true);
		userService.updateUser(user);
		//also seding premium status to user home
		model.addAttribute("isPremium",true);
		return "userhome";
	}

	@GetMapping("/payment-failure")
	public String paymentFailure() {
		return "userhome";
	}

	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
			@RequestParam String signature) {
		try {
			// Initialize Razorpay client with your API key and secret
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_vx3Eg3PqiTEmHO", "2rtg2rw5w8YUj8W8aOuRUuFF");
			// Create a signature verification data string
			String verificationData = orderId + "|" + paymentId;

			// Use Razorpay's utility function to verify the signature
			boolean isValidSignature = Utils.verifySignature(verificationData, signature, "2rtg2rw5w8YUj8W8aOuRUuFF");

			return isValidSignature;
		} catch (RazorpayException e) {
			e.printStackTrace();
			return false;
		}
	}

}
