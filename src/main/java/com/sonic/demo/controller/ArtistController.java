package com.sonic.demo.controller;

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

import com.sonic.demo.entity.Artists;
import com.sonic.demo.entity.Song;
import com.sonic.demo.service.ArtistService;
import com.sonic.demo.service.SongService;

import org.springframework.web.bind.annotation.RequestParam;




@CrossOrigin("*")
@RestController
public class ArtistController {
	@Autowired
	SongService songService ;
	@Autowired
	ArtistService artistService;
	
	@GetMapping("/createArtist")
	public List<Song> createArtist(Model model) {
		List<Song> songList = songService.viewAllSongs();
		for(Song song : songList) {
			song.setArtistObject(null);
		}
		return songList;
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
			System.out.println(artist.getName()+" "+"artist added successfully");
			return "success";
		}
		

		return "artist with same name already exists";
	}


	@GetMapping("/viewAllArtist")
	public List<Artists> viewAllArtist(Model model) {
	    List<Artists> artists = artistService.getAllArtists();
	    
//	    for (Artists artist : artists) {
//            artist.setSongs(null); 
//        }
	    
	    System.out.println("all artists viewd by frontend");
	    return artists;
	}
	
	@GetMapping("/artist/{id}")
	public Artists getArtistById(@PathVariable int id) {
		Artists a = artistService.getArtistById(id);
		System.out.println(" " + a.getName() + "artist view by frontend");

		
		return a;
	}
	

	
	

}
