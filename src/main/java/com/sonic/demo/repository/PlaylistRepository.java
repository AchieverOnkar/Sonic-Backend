package com.sonic.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonic.demo.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

	Playlist getPlaylistById(int plaplaylistId);

	Playlist getPlaylistByName(String name);

}
