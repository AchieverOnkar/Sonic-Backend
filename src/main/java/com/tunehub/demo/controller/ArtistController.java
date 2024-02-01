package com.tunehub.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tunehub.demo.entity.Artists;
import com.tunehub.demo.entity.Song;
import com.tunehub.demo.service.ArtistService;
import com.tunehub.demo.service.SongService;
import org.springframework.web.bind.annotation.RequestParam;




@CrossOrigin("*")
@RestController
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
	public String createArtist(@RequestBody Artists artist, Model model) {
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
//	@GetMapping("/viewAllArtist")
//	public String viewAllArtist(Model model) {
//		List<Artists> artists = artistService.getAllArtists();
//		model.addAttribute("artists", artists);
//		return "viewAllArtist";
//	}

	@GetMapping("/viewAllArtist")
	public List<Artists> viewAllArtist(Model model) {
	    List<Artists> artists = artistService.getAllArtists();
	    System.out.println(artists);
	    return artists;
	}
	
	@GetMapping("/artist")
	public Artists getArtistById(@RequestParam("id") int id) {
		Artists a = artistService.getArtistById(id);
		System.out.println(a);
		
		return a;
	}
	

	
	

}
