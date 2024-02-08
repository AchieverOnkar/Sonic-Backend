package com.sonic.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	String username;
	String email;
	String password;
	String gender;
	String role;
	String dob;
	String securityQuestion;
	String securityAnswer;
	Boolean isPremium;
	@ManyToMany
	List<Song> favoriteSongs;
	public User(int id, String username, String email, String password, String gender, String role, String dob,
			String securityQuestion, String securityAnswer, Boolean isPremium, List<Song> favoriteSongs) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.dob = dob;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.isPremium = isPremium;
		this.favoriteSongs = favoriteSongs;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public Boolean getIsPremium() {
		return isPremium;
	}
	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}
	public List<Song> getFavoriteSongs() {
		return favoriteSongs;
	}
	public void setFavoriteSongs(List<Song> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", role=" + role + ", dob=" + dob + ", securityQuestion=" + securityQuestion
				+ ", securityAnswer=" + securityAnswer + ", isPremium=" + isPremium + ", favoriteSongs=" + favoriteSongs
				+ "]";
	}
    
	
	
}
