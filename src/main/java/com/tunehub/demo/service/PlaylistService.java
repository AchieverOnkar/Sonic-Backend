package com.tunehub.demo.service;

import java.util.List;


import com.tunehub.demo.entity.Playlist;

public interface PlaylistService {

	void addPlaylist(Playlist playlist);

	List<Playlist> getAllPlaylists();

	Playlist getPlaylistById(int plaplaylistId);

	void updatePlaylist(Playlist playlist);

	

	
	

}
