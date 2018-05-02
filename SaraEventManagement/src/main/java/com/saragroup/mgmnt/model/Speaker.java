package com.saragroup.mgmnt.model;

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

@Document(collection = "SPEAKER")
@TypeAlias("Speaker")
public class Speaker extends AbstractDocument{
	
	private String speakerNum;
	
	@NotEmpty(message="Speaker name is mandatory.")
	@Indexed(unique=true)
	private String name;
	
	@NotEmpty(message="Qualification is mandatory.")
	private String qualification;
	
	@Email
	@NotEmpty(message="Email is mandatory.")
	@Indexed(unique = true)
	private String speakerEmail;
	
	@Length(max=10,min=10,message="Phone number is not valid. Should be of length 10.")
	@NotEmpty(message="Phone field is mandatory.") @NumberFormat(style= Style.NUMBER)
	@Indexed(unique = true)
	private String mobile;
	
	@NotEmpty(message="Address is mandatory.")
	private String address;
	
	private List<String> topics;
	
	@Transient
	private List<String> events;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getSpeakerEmail() {
		return speakerEmail;
	}
	public void setSpeakerEmail(String speakerEmail) {
		this.speakerEmail = speakerEmail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	public List<String> getEvents() {
		return events;
	}
	public void setEvents(List<String> events) {
		this.events = events;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpeakerId() {
		return speakerNum;
	}
	public void setSpeakerId(String speakerId) {
		this.speakerNum = speakerId;
	}
	
	
	
}
