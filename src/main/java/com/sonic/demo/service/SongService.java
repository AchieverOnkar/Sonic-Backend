package com.sonic.demo.service;

import java.util.*;

import com.sonic.demo.entity.Song;

public interface SongService {
	
	public String addSong(Song song);

	public List<Song> viewAllSongs();


	public Boolean songExist(String name);

	public void updateSong(Song s);

	public Song getSongById(int songId);

	

}
