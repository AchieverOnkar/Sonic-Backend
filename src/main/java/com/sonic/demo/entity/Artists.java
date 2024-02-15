package com.sonic.demo.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Artists {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	String name;
	String cover;
	String profile;
	
	
	@OneToMany(fetch = FetchType.EAGER)
	List<Song> songs;
	
	
	public Artists() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Artists(int id, String name, String cover, String profile, List<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.cover = cover;
		this.profile = profile;
		this.songs = songs;
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
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	@Override
	public String toString() {
		return "Artists [id=" + id + ", name=" + name + ", cover=" + cover + ", profile=" + profile + ", songs=" + songs
				+ "]";
	}
	
	
}
