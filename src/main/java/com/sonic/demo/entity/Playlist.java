package com.sonic.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Playlist {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String name;
    String color;
    String genre;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Song> songs;

    public Playlist() {
        super();
    }

    public Playlist(int id, String name, String color, String genre, List<Song> songs) {
        super();
        this.id = id;
        this.name = name;
        this.color = color;
        this.genre = genre;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + ", name=" + name + ", color=" + color + ", genre=" + genre + ", songs=" + "]";
    }
}
