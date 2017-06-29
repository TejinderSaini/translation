package com.alphaseq.translation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Service;

import com.alphaseq.translation.db.EntryRepository;
import com.alphaseq.translation.db.OrganizationRepository;
import com.alphaseq.translation.entity.Entry;
import com.alphaseq.translation.entity.LocaleValue;
import com.alphaseq.translation.entity.Organization;
import com.alphaseq.translation.entity.Status;
import com.alphaseq.translation.util.Constants;
import com.alphaseq.translation.entity.TranslationResponse;

@Service
public class DatabaseMessageSource{
	
	@Autowired
    private EntryRepository entryRepository;
	
	@Autowired
    private OrganizationRepository orgRepository;
	
	protected final Logger log = Logger.getLogger(getClass());
	/** Cache to hold already loaded properties */
	private List<Entry> cachedEntries = new CopyOnWriteArrayList<Entry>();
	private List<Organization> cachedOrgs = new CopyOnWriteArrayList<Organization>();
	/** Milliseconds to hold the cash */
	private long cacheMillis = -1;
	/** reset everytime the timestamp was changed */
	private long refreshTimestamp = -1;

	/**
	 * The method that does the loading of all the properties from the database
	 * 
	 * @return properties
	 */
	private List<Entry> refreshEntries() {
		this.cachedEntries = entryRepository.findAll();
		return this.cachedEntries;
	}
	
	private List<Organization> refreshOrgs() {
		this.cachedOrgs = orgRepository.findAll();
		return this.cachedOrgs;
	}
	
	public boolean updateStatusLevel(String org, String level){
		for(Organization o : this.cachedOrgs){
			if(org.equals(o.getName())){
				o.setStatusLevel(level);
			}
		}
		for(Entry ent : this.cachedEntries){
			List<Organization> oList = ent.getOrganizations();
			for(Organization o : oList){
				if(org.equals(o.getName())){
					o.setStatusLevel(level);
				}
			}
		}
		return true;
	}
	
	public void doRefreshCache() {
		log.info("Getting refresh.....1");
		this.cachedEntries = entryRepository.findAll();
		log.info("Getting refresh.....2");
	}
	
	public void doRefreshOrgs() {
		log.info("Getting refreshqqq.....1");
		this.cachedOrgs = orgRepository.findAll();
		log.info("Getting refreshqqq.....2");
	}
	
	public void addEntryToCache(Entry ent) {
		log.info("Adding Entry key......1 "+ent.getKey());
		log.info("Adding Entry to cache......1 "+this.cachedEntries);
		this.cachedEntries.add(ent);
		log.info("Adding Entry to cache......2 "+this.cachedEntries);
		for(Organization org : ent.getOrganizations()){
			log.info("Adding Org to cache...... 1"+this.cachedOrgs);
			this.cachedOrgs.add(org);
			log.info("Adding Org to cache...... 2"+this.cachedOrgs);
		}		
	}
	
	public void removeEntryToCache(Entry ent) {
		for(Organization org : ent.getOrganizations()){
			this.cachedOrgs.remove(org);
		}			
		this.cachedEntries.remove(ent);
	}

	private List<Entry> getTranslations() {
		log.info("Getting translation.....1");
		if (this.cachedEntries != null && (refreshTimestamp < 0 || refreshTimestamp > System.currentTimeMillis() - this.cacheMillis)) {
			// up to date
			log.info("Getting translation.....2");
			return this.cachedEntries;
		}
		log.info("Getting translation.....3");
		return refreshEntries();
	}
	
	public Set<com.alphaseq.translation.entity.Locale> getSupportedLocales(String org){
		log.info("Getting Valueqqq.....1");
		List<Organization> orgList = this.cachedOrgs;
		HashSet<com.alphaseq.translation.entity.Locale> locSet = new HashSet<com.alphaseq.translation.entity.Locale>();
		log.info("Getting Valueqqq.....2");
		for(Organization o : orgList){
			log.info("Getting Valueqqq.....3");
			if(org.equals(o.getName())){
				log.info("Getting Valueqqq.....4");
				List<LocaleValue> locValList = o.getLocaleValueList();
				for(LocaleValue locVal : locValList){
					log.info("Getting Valueqqq.....5");
					if(!locSet.contains(locVal.getLocale())){
						log.info("Getting Valueqqq.....6");
						locSet.add(locVal.getLocale());
					}
				}				
			}
		}
		log.info("Getting Valueqqq.....20");
		return locSet;		
	}
	
	public TranslationResponse getTranslation(String sentence, String org, String locale){
		String[] parts = sentence.split("\\[.*?\\]");
		List<Status> statusList = new ArrayList<Status>();
		Status status=null;
		String replacement="";
		for(String part : parts){
			String[] keys = part.split("\\s+");
			for(String key : keys){
				if(!key.isEmpty()){
					//log.info("Translating Value.....key "+key);
					status = this.getTranslatedMessage(key, org, locale);
					//log.info("Getting Value.....****************11="+status);
					if(status != null){
						//log.info("Getting Value.....****************12="+status);
						if(status.getReturnStatus()==true){
							//log.info("Getting Value.....****************13="+status);
							statusList.add(status);
						}
						//log.info("Getting Value.....****************14="+status);
						replacement = status.getValue();
						//log.info("Translating Value.....rep "+replacement);
						if(!replacement.isEmpty()){							
							//log.info("Translating Value.....sentenceBefore "+sentence);
							sentence = sentence.replaceFirst(key, replacement);
							//log.info("Translating Value.....sentenceAfter "+sentence);
						}
					}
					//log.info("Getting Value.....****************15="+status);
				}
			}
		}
		log.info("Translating Value.....FinalSentence "+sentence);
		return new TranslationResponse(sentence, statusList);
	}
	
	public boolean returnStatusForOrg(Organization org, Status status){
		try{

			System.out.println("returnStatusForOrg......1");
			if("errors".equals(org.getStatusLevel()) && Integer.parseInt(status.getCode())>=0){
				System.out.println("returnStatusForOrg......errors");
				return false;
			}else if("info".equals(org.getStatusLevel()) && Integer.parseInt(status.getCode())>0){
				System.out.println("returnStatusForOrg......info");
				return false;
			}else if("trace".equals(org.getStatusLevel())){
				System.out.println("returnStatusForOrg......trace");
				return true;
			}else{
				System.out.println("returnStatusForOrg......nothing");
				return true;
			}
		}catch(Exception ex){
			System.out.println("returnStatusForOrg......exception");
			log.error(ex.getStackTrace());;
			return false;
		}
	}
	
	public boolean returnStatusForStringOrg(String orgStr, Status status){
		Organization org=null;
		for(Organization o : this.cachedOrgs){
			if(orgStr.equals(o.getName())){
				org = o;
				return returnStatusForOrg(org, status);
			}
		}
		return true;
	}
	
	public Status getTranslatedMessage(String key, String org, String locale){
		log.info("Getting Value.....1 key = "+key);
		Status status = null;
		List<Entry> entries = this.getTranslations();
		log.info("Getting Value.....2");
		for(Entry ent : entries){
			log.info("Getting Value.....3 entKey = "+ent.getKey());
			if(key.equals(ent.getKey())){
				log.info("Getting Value.....4");
				List<Organization> orgList= ent.getOrganizations();
				for(Organization o : orgList){
					log.info("Getting Value.....5");
					if(org.equals(o.getName())){
						log.info("Getting Value.....6");
						List<LocaleValue> locValList= o.getLocaleValueList();
						for(LocaleValue lv : locValList){
							log.info("Getting Value.....7"+lv.getLocale().getCode());
							if(locale.equals(lv.getLocale().getCode())){
								log.info("Getting Value.....8");
								status = new Status(key, "0", Constants.ALPHASEQ_ALLOK, lv.getValue());
								status.setReturnStatus(returnStatusForOrg(o, status));
								return status;
							}else{
								status = new Status(key, "-2", Constants.ALPHASEQ_LOCALETRANSNOTFOUND, "");
								status.setReturnStatus(returnStatusForOrg(o, status));
								return status;
							}
						}
					}else{
						status = new Status(key, "-3", Constants.ALPHASEQ_ORGNOTFOUND, "");
						status.setReturnStatus(returnStatusForOrg(o, status));
						return status;
					}
				}
			}			
		}
		
		if(status==null){
			log.info("Getting Value.....****************9");
			status = new Status(key, "-1", Constants.ALPHASEQ_KEYNOTFOUNDERROR, "");
			status.setReturnStatus(returnStatusForStringOrg(org, status));
			log.info("Getting Value.....****************10="+status);
		}
		
		log.info("Getting Value.....20");
		return status;		
	}

	@PostConstruct
	public void init() {
		log.info("Initializing message source");
		entryRepository.deleteAll();
		orgRepository.deleteAll();
		this.refreshEntries();
		this.refreshOrgs();
	}
	
	/**
	 * Set the number of seconds to cache loaded properties .
	 * <ul>
	 * <li>Default is "-1", indicating to cache forever (just like
	 * <code>java.util.ResourceBundle</code>).
	 * <li>A positive number will cache loaded properties files for the given
	 * number of seconds. This is essentially the interval between refresh
	 * checks. Note that a refresh attempt will first check the last-modified
	 * timestamp of the file before actually reloading it; so if files don't
	 * change, this interval can be set rather low, as refresh attempts will not
	 * actually reload.
	 * <li>A value of "0" will check the last-modified timestamp of the file on
	 * every message access. <b>Do not use this in a production environment!</b>
	 * </ul>
	 */
	public void setCacheSeconds(int cacheSeconds) {
		this.cacheMillis = (cacheSeconds * 1000);
	}

	/*@Override
	public String toString() {
		StringBuilder str = new StringBuilder().append("\n");
		for (String msg : getProperties().keySet()) {
			str.append(msg);
			str.append(" = ");
			str.append(getProperties().get(msg));
			str.append("\n");
		}
		return str.toString();
	}*/
}