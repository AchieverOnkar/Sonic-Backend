package com.tunehub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tunehub.demo.entity.Song;
import com.tunehub.demo.entity.User;
import com.tunehub.demo.service.SongService;
import com.tunehub.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
@CrossOrigin("*")
@RestController
public class FavoriteController {
	
	@Autowired
	SongService songService;

	@Autowired
	UserService userService;

	@GetMapping("/viewFavorite")
	public String viewFavorite(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		// fetching user
		User user = userService.getUser(email);
		// fetching favorite songs list
		List<Song> favoriteSongs = user.getFavoriteSongs();
		model.addAttribute("favoriteSongs", favoriteSongs);
		return "viewFavorite";
	}

	// changes to made when moved to react remove the return and dont reload the
	// page when song added to favorite
	@PostMapping("/addToFavorite")
	public String addToFavorite(@RequestParam int songId, Model model, HttpSession session) {
		Song song = songService.getSongById(songId);
		// fetching user
		String email = (String) session.getAttribute("email");
		User user = userService.getUser(email);

		// adding song to favorite song list of user
		user.getFavoriteSongs().add(song);
		userService.updateUser(user);

		List<Song> songList = songService.viewAllSongs();
		model.addAttribute("songsList", songList);
		return "viewAllSongs";
	}

	@PostMapping("/removeFromFavorite")
	public String removeFromFavorite(@RequestParam int songId, Model model, HttpSession session) {

		Song song = songService.getSongById(songId);
		// Fetching user
		String email = (String) session.getAttribute("email");
		User user = userService.getUser(email);

		System.out.println("Song to be removed: " + song);

		// Removing song from favorite song list of user
		user.getFavoriteSongs().remove(song);

		// Updating user
		userService.updateUser(user);

		// Refreshing the list of favorite songs in the model
		List<Song> favoriteSongs = user.getFavoriteSongs();
		model.addAttribute("favoriteSongs", favoriteSongs);

		return "viewFavorite";
	}

}
