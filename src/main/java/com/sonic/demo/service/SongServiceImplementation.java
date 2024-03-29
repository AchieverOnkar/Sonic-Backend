package com.sonic.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonic.demo.entity.Song;
import com.sonic.demo.repository.SongRepository;

@Service
public class SongServiceImplementation implements SongService{
   
	@Autowired
	SongRepository repo;

	@Override
	public String addSong(Song song) {
	       repo.save(song);
		   return "added succesfully";
	}

	@Override
	public List<Song> viewAllSongs() {
		
		return repo.findAll();
	}



	@Override
	public Boolean songExist(String name) {
		Song song = repo.findByName(name);
        if( song == null) {
        	return false;	
		}else {
			return true;
		}
		
	}

	@Override
	public void updateSong(Song song) {
		// TODO Auto-generated method stub
		repo.save(song);
		
	}

	@Override
	public Song getSongById(int songId) {
		Optional<Song> optionalSong = repo.findById(songId);
		if (optionalSong.isPresent()) {
		    Song song = optionalSong.get();
		    return song;
		} else {
			return null;
		}
	}




}
