package com.alphaseq.translation.db;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alphaseq.translation.entity.Entry;

//@RepositoryRestResource(collectionResourceRel = "language", path = "language")
public interface EntryRepository extends MongoRepository<Entry, String> {
	public List<Entry> findById(String Id);
}