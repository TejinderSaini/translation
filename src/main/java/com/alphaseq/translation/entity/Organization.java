package com.alphaseq.translation.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "organization")
public class Organization {
	@Id
	private String id;
	private String name;
	private String iconURL;
	private String statusLevel;
	private List<LocaleValue> localeValueList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	
	public String getStatusLevel() {
		return statusLevel;
	}

	public void setStatusLevel(String statusLevel) {
		this.statusLevel = statusLevel;
	}
	
	public List<LocaleValue> getLocaleValueList() {
		return localeValueList;
	}

	public void setLocaleValueList(List<LocaleValue> localeValueList) {
		this.localeValueList = localeValueList;
	}	
	
	public String toString(){
		return "name="+this.name+" ||LocaleValueList={\n\t"+this.localeValueList+"}";
	}
}
