package com.alphaseq.translation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contact {
  
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String email;
  private String twitterHandle;
  private String facebookProfile;
  private String linkedInProfile;
  private String googlePlusProfile;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTwitterHandle() {
    return twitterHandle;
  }

  public void setTwitterHandle(String twitterHandle) {
    this.twitterHandle = twitterHandle;
  }

  public String getFacebookProfile() {
    return facebookProfile;
  }

  public void setFacebookProfile(String facebookProfile) {
    this.facebookProfile = facebookProfile;
  }

  public String getLinkedInProfile() {
    return linkedInProfile;
  }

  public void setLinkedInProfile(String linkedInProfile) {
    this.linkedInProfile = linkedInProfile;
  }

  public String getGooglePlusProfile() {
    return googlePlusProfile;
  }

  public void setGooglePlusProfile(String googlePlusProfile) {
    this.googlePlusProfile = googlePlusProfile;
  }  
 }