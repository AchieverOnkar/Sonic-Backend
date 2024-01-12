package com.tunehub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.demo.entity.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

  public Song findByName(String name);

}
