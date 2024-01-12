package com.tunehub.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class FavoriteController {
   
	@GetMapping("/viewFavorite")
	public String viewFavorite() {
		return "viewFavorite";
	}
	@PostMapping("/addToFavorite")
	public void addToFavorite(@RequestParam int songId) {
		System.out.println(songId);
	}
	
	
}
