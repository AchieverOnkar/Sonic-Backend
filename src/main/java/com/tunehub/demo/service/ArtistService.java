package com.tunehub.demo.service;

import java.util.List;

import com.tunehub.demo.entity.Artists;

public interface ArtistService {

	void createArtist(Artists artist);

	boolean artistExists(String name);

	List<Artists> getAllArtists();

	Artists getArtistById(int id);

}
