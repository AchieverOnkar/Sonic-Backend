package com.tunehub.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.entity.Song;
import com.tunehub.demo.repository.SongRepository;

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




}
