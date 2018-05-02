package com.saragroup.mgmnt.model;

import org.springframework.data.annotation.Id;

public class AbstractDocument {

	@Id private String documentId;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
}
