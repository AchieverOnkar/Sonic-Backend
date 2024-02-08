package com.sonic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonic.demo.entity.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

  public Song findByName(String name);

}
