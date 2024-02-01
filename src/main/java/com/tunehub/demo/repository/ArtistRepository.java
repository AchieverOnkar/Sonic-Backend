package com.tunehub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.demo.entity.Artists;

public interface ArtistRepository extends JpaRepository<Artists, Integer>{

	Artists getByName(String name);

	

}
