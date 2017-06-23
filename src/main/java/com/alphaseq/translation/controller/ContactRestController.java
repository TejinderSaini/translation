package com.alphaseq.translation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alphaseq.translation.db.ContactRepository;
import com.alphaseq.translation.entity.Contact;
import com.googlecode.ehcache.annotations.Cacheable;

@RestController
@RequestMapping("/contacts")
public class ContactRestController {
  
  @Autowired
  private ContactRepository repo;
  
  @Cacheable( cacheName = "messageCache" )
  @RequestMapping(method=RequestMethod.GET)
  public List<Contact> getAll() {
	System.out.println("Getting all contacts.....");
    return repo.findAll();
  }
  
  @RequestMapping(method=RequestMethod.POST)
  public Contact create(@RequestBody Contact contact) {
    return repo.save(contact);
  }
  
  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
  public void delete(@PathVariable String id) {
    repo.delete(id);
  }
  
  @RequestMapping(method=RequestMethod.PUT, value="{id}")
  public Contact update(@PathVariable String id, @RequestBody Contact contact) {
    Contact update = repo.findOne(id);
    update.setAddress(contact.getAddress());
    update.setEmail(contact.getEmail());
    update.setFacebookProfile(contact.getFacebookProfile());
    update.setFirstName(contact.getFirstName());
    update.setGooglePlusProfile(contact.getGooglePlusProfile());
    update.setLastName(contact.getLastName());
    update.setLinkedInProfile(contact.getLinkedInProfile());
    update.setPhoneNumber(contact.getPhoneNumber());
    update.setTwitterHandle(contact.getTwitterHandle());
    return repo.save(update);
  }

}
