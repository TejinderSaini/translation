package com.alphaseq.translation.db;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alphaseq.translation.entity.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
	public List<Organization> findByName(String name);
}