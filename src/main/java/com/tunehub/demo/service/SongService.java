package com.tunehub.demo.service;

import java.util.*;
import com.tunehub.demo.entity.Song;

public interface SongService {
	
	public String addSong(Song song);

	public List<Song> viewAllSongs();


	public Boolean songExist(String name);

	public void updateSong(Song s);

	public Song getSongById(int songId);

	

}
