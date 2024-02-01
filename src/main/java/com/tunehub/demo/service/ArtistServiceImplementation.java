package com.tunehub.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.entity.Artists;
import com.tunehub.demo.repository.ArtistRepository;

@Service
public class ArtistServiceImplementation implements ArtistService{
    @Autowired
	ArtistRepository artistRepository;
	
    @Override
	public void createArtist(Artists artist) {
	   artistRepository.save(artist);	
	}

	@Override
	public boolean artistExists(String name) {
		Artists artist = artistRepository.getByName(name);
		if(artist == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Artists> getAllArtists() {
		return artistRepository.findAll();
		 
	}
	

}
