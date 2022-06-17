package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Party;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "party", path = "party")
public interface PartyRepository extends CrudRepository<Party,Long> {

}
