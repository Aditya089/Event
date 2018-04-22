package com.saragroup.mgmnt.model;

import org.springframework.data.annotation.Id;

public class Topic {

	@Id	
	private String topicId;
	
	private String code;
	private String name;
	
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Topic [code=" + code + ", name=" + name + "]";
	}
	
	
}
