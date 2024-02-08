package com.sonic.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonic.demo.entity.Playlist;
import com.sonic.demo.repository.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService{

	@Autowired
	PlaylistRepository repo;
	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);
	}
	@Override
	public List<Playlist> getAllPlaylists() {
		
		return repo.findAll();
	}
	@Override
	public Playlist getPlaylistById(int plaplaylistId) {
		return repo.getPlaylistById(plaplaylistId);
		
	}
	@Override
	public void updatePlaylist(Playlist playlist) {
		repo.save(playlist);
		
	}
	@Override
	public boolean playlistExists(String name) {
		Playlist p = repo.getPlaylistByName(name);
		if(p != null) {
			return true;
		}
		return false;
	}

	
		
	

}
