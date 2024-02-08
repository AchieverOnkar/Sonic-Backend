package com.sonic.demo.service;

import java.util.List;

import com.sonic.demo.entity.Playlist;

public interface PlaylistService {

	void addPlaylist(Playlist playlist);

	List<Playlist> getAllPlaylists();

	Playlist getPlaylistById(int plaplaylistId);

	void updatePlaylist(Playlist playlist);

	boolean playlistExists(String name);

	

	
	

}
