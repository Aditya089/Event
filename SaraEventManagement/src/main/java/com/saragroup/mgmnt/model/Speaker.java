package com.saragroup.mgmnt.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SPEAKER")
public class Speaker {
	
	@Id	
	private String speakerId;
	
	private String name;
	private String qualification;
	
	@Indexed(unique = true)
	private String speakerEmail;
	
	@Indexed(unique = true)
	private String mobile;
	private List<String> topics;
	private List<Event> events;
	private String address;
	
	public String getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
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
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
