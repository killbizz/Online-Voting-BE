package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Vote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "vote", path = "vote")
public interface VoteRepository extends CrudRepository<Vote,Long> {

}

