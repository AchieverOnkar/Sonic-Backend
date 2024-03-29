package com.sonic.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //this controller will provide mapping for html pages
public class NavController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	
	@GetMapping("/newSong")
	public String newSong() {
		return "newSong";
	}
	
}
