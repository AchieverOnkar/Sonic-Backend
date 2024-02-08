package com.sonic.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sonic.demo.entity.Song;
import com.sonic.demo.entity.User;
import com.sonic.demo.service.SongService;
import com.sonic.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class FavoriteController {

	@Autowired
	SongService songService;

	@Autowired
	UserService userService;

	@GetMapping("/viewFavorite")
	public List<Song> viewFavorite(@RequestParam String userEmail, HttpSession session, Model model) {

		System.out.println("user email is:" + userEmail);
		// fetching user
		User user = userService.getUser(userEmail);
		// fetching favorite songs list
		if (user.getFavoriteSongs() != null) {
			List<Song> favoriteSongs = user.getFavoriteSongs();
			return favoriteSongs;
		}

//		model.addAttribute("favoriteSongs", favoriteSongs);
		return Collections.emptyList();
	}

	// changes to made when moved to react remove the return and dont reload the
	// page when song added to favorite
	@PostMapping("/addToFavorite")
	public String addToFavorite(@RequestParam int songId, Model model, @RequestParam String email) {

		Song song = songService.getSongById(songId);
		User user = userService.getUser(email);

		// adding song to favorite song list of user
		List<Song> songs = user.getFavoriteSongs();
		boolean isPresent = false;
		for(Song s : songs) {
			if(s.getId()==songId) {
				isPresent=true;
			}
		}
		if(!isPresent) {
			user.getFavoriteSongs().add(song);
			userService.updateUser(user);
			System.out.println("song added to playlist :"+songId);
			return "success";
		}
		
		return "song exists";
		
	}

	@PostMapping("/removeFromFavorite")
	public String removeFromFavorite(@RequestParam int songId, @RequestParam String email ) {

		Song song = songService.getSongById(songId);
		// Fetching user
		User user = userService.getUser(email);
		System.out.println("Song to be removed: " + song);
		// Removing song from favorite song list of user
		user.getFavoriteSongs().remove(song);
		// Updating user
		userService.updateUser(user);
		// Refreshing the list of favorite songs in the model
		List<Song> favoriteSongs = user.getFavoriteSongs();
		return "song removed !!";
	}

}
