package com.anp.Registration;

import javax.persistence.*;

@Entity
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Username;
	private String password;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Registration(int id, String username, String password, String email) {
		super();
		this.id = id;
		Username = username;
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Registration [id=" + id + ", Username=" + Username + ", password=" + password + ", email=" + email
				+ "]";
	}
}
