package com.sonic.demo.entity;

public class ChangePassword {
	String email;
	String newPassword;
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePassword(String email, String newPassword) {
		super();
		this.email = email;
		this.newPassword = newPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ChangPassword [email=" + email + ", newPassword=" + newPassword + "]";
	}
	

}
