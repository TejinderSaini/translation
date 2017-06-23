package com.alphaseq.translation.db;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.alphaseq.translation.entity.Entry;

public interface EntryRepository extends MongoRepository<Entry, String> {
	public List<Entry> findById(String Id);
}