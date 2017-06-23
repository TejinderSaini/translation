package com.alphaseq.translation.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alphaseq.translation.db.EntryRepository;
import com.alphaseq.translation.entity.Entry;
import com.alphaseq.translation.entity.TranslationResponse;
import com.alphaseq.translation.service.DatabaseMessageSource;
import com.googlecode.ehcache.annotations.Cacheable;

@RestController
@RequestMapping("/language")
public class TranslationRestController {
  
  @Autowired
  private DatabaseMessageSource messageSource;
  
  @Autowired
  private EntryRepository repo;
  
  @Cacheable( cacheName = "messageCache" )
  @RequestMapping(value = "/translate/{org}/{locale}/{key}",method=RequestMethod.GET)
  public TranslationResponse getValue(@PathVariable("org") String org, @PathVariable("locale") String locale, @PathVariable("key") String key) {
	System.out.println("Getting Value.....");
    return messageSource.getTranslation(key, org, locale);
  }
  
  @Cacheable( cacheName = "messageCache" )
  @RequestMapping(value = "/supportedLocales/{org}",method=RequestMethod.GET)
  public Set<com.alphaseq.translation.entity.Locale> getOrg(@PathVariable("org") String org) {
	System.out.println("Getting Value.....");
    return messageSource.getSupportedLocales(org);
  }
  
  @RequestMapping(value = "/setStatusesReturnCodes/{org}/{level}",method=RequestMethod.GET)
  public boolean setLevel(@PathVariable("org") String org, @PathVariable("level") String level) {
	System.out.println("Getting Value.....");
    return messageSource.updateStatusLevel(org,level);
  }
  
  @RequestMapping(value = "/dbUpdateSignal",method=RequestMethod.GET)
  public void updateCache() {
	System.out.println("Getting dbUpdate.....start");
    messageSource.doRefreshCache();
    messageSource.doRefreshOrgs();
    System.out.println("Getting dbUpdate.....done");
    return;
  }
  
  @RequestMapping(method=RequestMethod.POST)
  public Entry create(@RequestBody Entry entry) {
	  System.out.println("Entry key is......."+entry.getKey());
	  Entry ent = repo.save(entry);
	  messageSource.addEntryToCache(ent);
	  return ent;
  }
  
  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
  public void delete(@PathVariable String id) {
	  List<Entry> entList = repo.findById(id);
	  repo.delete(id);
	  for(Entry ent : entList){
		  messageSource.removeEntryToCache(ent);
	  }
  }
  
  @RequestMapping(method=RequestMethod.PUT, value="{id}")
  public Entry update(@PathVariable String id, @RequestBody Entry entry) {
    Entry update = repo.findOne(id);
    update.setKey(entry.getKey());
    return repo.save(update);
  }

}
