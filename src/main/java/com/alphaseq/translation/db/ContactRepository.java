package com.alphaseq.translation.db;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.alphaseq.translation.entity.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
	public List<Contact> findById(String Id);
}
