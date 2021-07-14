package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Party;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "party", path = "party")
public interface PartyRepository extends CrudRepository<Party,Long> {

    Party findById(long id);

}
