package com.alphaseq.translation.entity;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entry")
public class Entry {
	@Id
	private String id;
	private String key;
	private List<Organization> organizations;

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
	
	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
	
	public String toString(){
		return "key="+this.key+" ||organization={\n\t"+this.organizations+"}";
	}

}
