package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser,String> {

    AppUser findByEmail(String email);

}