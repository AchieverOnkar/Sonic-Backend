package com.sonic.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sonic.demo.entity.Playlist;
import com.sonic.demo.entity.Song;
import com.sonic.demo.service.PlaylistService;
import com.sonic.demo.service.SongService;

@CrossOrigin("*")
@RestController
public class PlaylistController {
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playlistService;

	// method to navigate to create playlist page
	@GetMapping("/createPlaylist")
	public ResponseEntity<List<Song>> addPlaylist(Model model) {
		List<Song> songList = songService.viewAllSongs();

		if (songList != null) {
			return ResponseEntity.ok().body(songList);
		} else {
			return ResponseEntity.badRequest().body(Collections.emptyList());
		}
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@RequestBody Playlist playlist) {
		boolean isExists = playlistService.playlistExists(playlist.getName());
		if (!isExists) {
			// adding the playlist to db
			playlistService.addPlaylist(playlist);
			System.out.println("playlist added");
			// whenever we are adding playlist we also have to update that songs and add the
			// playlist name for songs
			List<Song> songList = playlist.getSongs(); // getting songlist using setter method

			for (Song s : songList) {
				// adding playlist object in each song object i.e achiving manytomany for songs
				// first getting the playlist list from song which will be empty then adding
				// current playlist obj into it
				s.getPlaylists().add(playlist);
				// after making changes to song oject saving/updatign the song in db
				songService.updateSong(s);
			}
			return "success";
		}

		return "Playlist with same name already exist";

	}

	@GetMapping("/viewPlaylist")
	public List<Playlist> viewPlaylist(Model model) {
		List<Playlist> playlists = playlistService.getAllPlaylists();
//		System.out.println(playlists);
		return playlists;
	}

	@GetMapping("/viewUpdatePlaylist")
	public String viewUpdatePlaylist(@RequestParam int playlistId, Model model) {
		Playlist playlist = playlistService.getPlaylistById(playlistId);
		model.addAttribute("playlist", playlist);
		System.out.println(playlist);
		return "viewUpdatePlaylist";
	}

	@PostMapping("/updatePlaylist")
	public String updatePlaylist(@RequestBody Playlist playlist, @RequestParam List<Integer> removeSongs, Model model) {
		// fetching original songlist for that playlist
		List<Song> originalSongs = playlistService.getPlaylistById(playlist.getId()).getSongs();

		// removing song from playlist based on removeSongs list
		List<Song> songToRemove = new ArrayList<>();
		// travelling through original songs list
		for (Song song : originalSongs) {
			// if current song id is present inside removesongs list then ass that song to
			// remove song list
			if (removeSongs.contains(song.getId())) {
				songToRemove.add(song);
			}
		}
		// remove all the song from original list which are present in songToRemove
		originalSongs.removeAll(songToRemove);

		// now assining the updated originalsonglist to our playlist from user
		playlist.setSongs(originalSongs);
		// now updating the playlist
		playlistService.updatePlaylist(playlist);
		// removing playlist from songs
		for (Song s : songToRemove) {
			// removing playlist object in each song object i.e achiving manytomany for
			// songs
			// first getting the playlist list from song which will be empty then adding
			// current playlist obj into it
			s.getPlaylists().remove(playlist);
			// after making changes to song object saving/updatign the song in db
			songService.updateSong(s);
		}

		// retriving the playlists and send it to viewPlaylist page
		List<Playlist> playlists = playlistService.getAllPlaylists();
		model.addAttribute("playlists", playlists);
		return "viewPlaylist";
	}
	
	
	@GetMapping("/playlist/{id}")
	public Playlist getPLaylistById(@PathVariable int id) {
		System.out.println("playlist viewed by frontend");
		return playlistService.getPlaylistById(id);
		
	}
	

}
