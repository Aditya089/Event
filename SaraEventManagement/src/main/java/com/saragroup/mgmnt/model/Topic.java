package com.saragroup.mgmnt.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TOPICS")
@TypeAlias("Topic")
public class Topic extends AbstractDocument{
	@NotEmpty(message="Topic Code is mandatory.")
	@Indexed(unique=true)
	private String code;
	
	@NotEmpty(message="Topic description is mandatory.")
	private String name;
	
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
}
