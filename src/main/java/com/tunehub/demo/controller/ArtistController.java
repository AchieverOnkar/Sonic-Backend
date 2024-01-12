package com.tunehub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.demo.entity.Artists;
import com.tunehub.demo.entity.Song;
import com.tunehub.demo.service.ArtistService;
import com.tunehub.demo.service.SongService;




@Controller
public class ArtistController {
	@Autowired
	SongService songService ;
	@Autowired
	ArtistService artistService;
	
	@GetMapping("/createArtist")
	public String createArtist(Model model) {
		List<Song> songList = songService.viewAllSongs();
		model.addAttribute("songs", songList);
		return "createArtist";
	}
	@PostMapping("/addArtist")
	public String createArtist(@ModelAttribute Artists artist, Model model) {
		//check if artist exist or not
		boolean artistExists = artistService.artistExists(artist.getName());
		if(!artistExists) {
			artistService.createArtist(artist);
	        //updating the songs with artist object
			List<Song> songs = artist.getSongs();
			//travell through songs and set artist for that songs
			for(Song song : songs) {
				song.setArtistObject(artist);
				songService.updateSong(song);
			}
		}
		
		List<Song> songList = songService.viewAllSongs();
		model.addAttribute("songs", songList);
		return "createArtist";
	}
	@GetMapping("/viewAllArtist")
	public String viewAllArtist(Model model) {
		List<Artists> artists = artistService.getAllArtists();
		model.addAttribute("artists", artists);
		return "viewAllArtist";
	}
	
	
	

}
