package com.sonic.demo.service;

import java.util.List;

import com.sonic.demo.entity.Artists;

public interface ArtistService {

	void createArtist(Artists artist);

	boolean artistExists(String name);

	List<Artists> getAllArtists();

	Artists getArtistById(int id);

}
