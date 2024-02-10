package com.sonic.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sonic.demo.entity.Song;
import com.sonic.demo.service.SongService;
@CrossOrigin("*")
@RestController
public class SongController {
	@Autowired
	SongService service;
	
	//controller to handle add new song request
	@PostMapping("/addSong")
	public String addSong(@RequestBody Song song) {
		//before adding new song checking whether song with same name exist or not
		Boolean songExist = service.songExist(song.getName());
		if(!songExist) {//song with same name not exists
		   service.addSong(song);
		   return "success";
		}else {//duplicate exists
			System.out.println("song already exists");
			return "song already exists";
		}
		
	}
	
	//controller to handle view all song request
	@Transactional(readOnly = true)
	@GetMapping("/viewAllSongs")
	public List<Song> viewAllSongs() {
	    List<Song> songs = service.viewAllSongs();
	    songs.forEach(song -> {
	        // Trigger lazy loading within a transaction
	        song.getPlaylists().size();
	    });
	    return songs;
	}
	
	//controller to handle play song request
		@GetMapping("/playSongs")
		public String playSongs(Model model) {
			//check whether user is premiumuser
            Boolean premiumUser = true;
            if(premiumUser) {//show songs list to premium customers only
            	List<Song> songsList = service.viewAllSongs();
    		    model.addAttribute("songsList", songsList);//adding the songsList into model with attribute name songList so that thymeleaf can access the list
    			return "viewAllSongs";
            	
            }else {//otherwise take them to payment page
            	return "makePayment";
            }
		}

}
