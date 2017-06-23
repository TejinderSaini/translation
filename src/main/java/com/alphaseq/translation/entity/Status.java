package com.alphaseq.translation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "status")
public class Status {
	@Id
	private String id;
	private String key;
	private String code;
	private String description;
	private String value;
	private boolean returnStatus;
	
	public Status(String key, String code, String description, String value, boolean returnStatus){
		this.key = key;
		this.code = code;
		this.description = description;
		this.value = value;
		this.returnStatus = returnStatus;
	}
	
	public Status(String key, String code, String description, String value){
		this.key = key;
		this.code = code;
		this.description = description;
		this.value = value;
	}
	
	public Status(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(boolean returnStatus) {
		this.returnStatus = returnStatus;
	}
}
