package com.tunehub.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.entity.Playlist;
import com.tunehub.demo.repository.PlaylistRepository;

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

	
		
	

}
