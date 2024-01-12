package com.tunehub.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.demo.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

	Playlist getPlaylistById(int plaplaylistId);

}
