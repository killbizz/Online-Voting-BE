package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Vote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "party", path = "party")
public interface VoteRepository extends CrudRepository<Vote,Long> {

    Vote findById(long id);

}

