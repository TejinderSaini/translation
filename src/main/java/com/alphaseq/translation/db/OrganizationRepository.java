package com.alphaseq.translation.db;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.alphaseq.translation.entity.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
	public List<Organization> findByName(String name);
}