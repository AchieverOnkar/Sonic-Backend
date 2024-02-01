package com.tunehub.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name = "Songs")
public class Song {
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	String name;
	String artist;
	String posterLink;
	String genre;
	String link;
	
	@ManyToOne
//	@JsonManagedReference // Prevents infinite recursion during serialization
    private Artists artistObject;
	
	@ManyToMany
	List<Playlist> playlists;

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Song(int id, String name, String artist, String posterLink, String genre, String link, Artists artistObject,
			List<Playlist> playlists) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.posterLink = posterLink;
		this.genre = genre;
		this.link = link;
		this.artistObject = artistObject;
		this.playlists = playlists;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPosterLink() {
		return posterLink;
	}

	public void setPosterLink(String posterLink) {
		this.posterLink = posterLink;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Artists getArtistObject() {
		return artistObject;
	}

	public void setArtistObject(Artists artistObject) {
		this.artistObject = artistObject;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", posterLink=" + posterLink + ", genre="
				+ genre + ", link=" + link + ", artistObject="+ ", playlists=" + playlists + "]";
	}
	
	
}
