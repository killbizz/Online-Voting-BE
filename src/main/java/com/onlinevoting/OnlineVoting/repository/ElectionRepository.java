package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Election;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "election", path = "election")
public interface ElectionRepository extends CrudRepository<Election,Long> {
    
}
