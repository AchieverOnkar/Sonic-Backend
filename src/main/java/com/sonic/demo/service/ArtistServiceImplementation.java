package com.sonic.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonic.demo.entity.Artists;
import com.sonic.demo.repository.ArtistRepository;

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

	@Override
	public Artists getArtistById(int id) {
		
		 Optional<Artists> optionalArtist = artistRepository.findById(id);
		    
		    // Check if the artist is present in the Optional
		    if (optionalArtist.isPresent()) {
		        return optionalArtist.get();
		    } else {
		        // Handle the case when the artist with the given id is not found
		        // You can throw an exception, return null, or handle it based on your use case.
		        return null; // For demonstration purposes, returning null. You may want to throw an exception or handle it differently.
		    }
		 
	}
	

}
