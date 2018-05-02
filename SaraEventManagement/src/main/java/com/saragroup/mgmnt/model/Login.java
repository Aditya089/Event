package com.saragroup.mgmnt.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Login {

	@NotEmpty(message="Username field is mandatory.")
	@Size(min=2, max=30)
	private String username;
	
	@NotEmpty(message="Password field is mandatory.")
	@Size(min=4, max=16)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
