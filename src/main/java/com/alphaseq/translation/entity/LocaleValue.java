package com.alphaseq.translation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "localeValue")
public class LocaleValue {
	@Id
	private String id;
	private String value;
	private Locale locale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public String toString(){
		return "value="+this.value+" ||LocaleValueList={\n\tLocaleValueList={"+this.locale+"}";
	}
}
