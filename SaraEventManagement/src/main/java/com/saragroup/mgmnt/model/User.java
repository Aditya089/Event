package com.saragroup.mgmnt.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "USER")
public class User {
	
	@Id	
	private String userId;
	
	@Indexed(unique = true)
	private String username;
	private String password;

	@Indexed(unique = true)
	private String email;
	private Date lastLogin;
	private Date creationDate;
	private String firstName;
	private String lastName;
	private Boolean enabled;
	private List<AuthorityName> authorities;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<AuthorityName> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<AuthorityName> authorities) {
		this.authorities = authorities;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password 
				+ ", email=" + email + ", lastLogin=" + lastLogin + ", creationDate=" + creationDate + ", firstName="
				+ firstName + ", lastName=" + lastName + ", enabled=" + enabled + ", authorities=" + authorities + "]";
	}

	

}
