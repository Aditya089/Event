package com.saragroup.mgmnt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Document(collection = "USER")
@TypeAlias("user")
public class User extends AbstractDocument{
	
	@NotEmpty(message="Username field is mandatory.")
	@Indexed(unique=true)
	private String username;
	
	@NotEmpty(message="Password should not be empty.")
	private String password;

	@NotEmpty(message="Retype Password should not be empty.")	
	@Transient
	private String rePassword = null;
	
	@Email
	@NotEmpty(message="Email field is mandatory.")
	@Indexed(unique=true)
	private String email;
	
	@NotEmpty(message="Username field is mandatory.")
	private String fullName;
	
	@Length(max=10,min=10,message="Phone number is not valid. Should be of length 10.")
	@NotEmpty(message="Phone field is mandatory.") @NumberFormat(style= Style.NUMBER)
	@Indexed(unique=true)
	private String phone;
	
	private Date lastLogin = null;
	private Date creationDate;
	
	private boolean enabled;
	private List<AuthorityName> authorities;
	
	private List<String> events = new ArrayList<String>();
	
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
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<AuthorityName> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<AuthorityName> authorities) {
		this.authorities = authorities;
	}

	public List<String> getEvents() {
		return events;
	}
	public void addEvents(String eventId) {
		this.events.add(eventId);
	}
	
	
	
}
