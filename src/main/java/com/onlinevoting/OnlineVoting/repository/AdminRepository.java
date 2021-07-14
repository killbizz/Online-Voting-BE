package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "admin", path = "admin")
public interface AdminRepository extends CrudRepository<Admin,Long> {

    Admin findByEmail(String email);

    Admin findByEmailAndPassword(String email, String password);

}
