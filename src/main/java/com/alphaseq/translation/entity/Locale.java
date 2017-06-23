package com.alphaseq.translation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locale")
public class Locale {
	@Id
	private String id;
	private String code;
	private String label;
	private String nativeLabel;
	private boolean isRTL;
	//private Org organizationID;

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getCode() {
	return code;
	}

	public void setCode(String code) {
	this.code = code;
	}

	public String getLabel() {
	return label;
	}

	public void setLabel(String label) {
	this.label = label;
	}

	public String getNativeLabel() {
	return nativeLabel;
	}

	public void setNativeLabel(String nativeLabel) {
	this.nativeLabel = nativeLabel;
	}

	public boolean getIsRTL() {
	return isRTL;
	}

	public void setIsRTL(boolean isRTL) {
	this.isRTL = isRTL;
	}

	/*public String getOrganizationID() {
	return organizationID;
	}

	public void setOrganizationID(String organizationID) {
	this.organizationID = organizationID;
	}*/
	
	@Override
    public boolean equals(Object o) {
		System.out.println("INside EQUALS--------1 thisCode= "+this.getCode());
        if (o == this) return true;
        System.out.println("INside EQUALS--------2 thisLabel= "+this.getLabel());
        if (!(o instanceof Locale)) {
            return false;
        }
        System.out.println("INside EQUALS--------3 = ");
        Locale loc = (Locale) o;
        System.out.println("INside EQUALS--------4 locCode= "+loc.getCode());
        System.out.println("INside EQUALS--------5 locLabel= "+loc.getLabel());
        System.out.println("INside EQUALS--------6 loctrue-false= "+loc.getCode().equals(this.getCode()));
        System.out.println("INside EQUALS--------7 labletrue-false= "+loc.getLabel() == this.getLabel());
        if(loc.getCode().equals(this.getCode()) && loc.getLabel().equals(this.getLabel())){
        	System.out.println("INside EQUALS--------8 equals = "+true);
        	return true;
        }
        return false;
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + code.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }
    
    @Override
    public String toString(){
		return "code="+this.code+" | label="+this.label+" | isRTL="+this.isRTL+" | hashcode="+this.hashCode();
	}
}
