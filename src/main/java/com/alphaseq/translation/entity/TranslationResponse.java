package com.alphaseq.translation.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "translationResponse")
public class TranslationResponse {
	@Id
	private String id;
	private String text;
	private List<Status> statuses;
	
	public TranslationResponse(String text, List<Status> statuses){
		this.text = text;
		this.statuses = statuses;
	}
	
	public TranslationResponse(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Status> getstatuses() {
		return statuses;
	}

	public void setstatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
}
