package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<User,String> {

    // customized method
    @Override
    @RestResource(exported=false)
    <S extends User> S save(S user);

    User findByEmail(String email);

}