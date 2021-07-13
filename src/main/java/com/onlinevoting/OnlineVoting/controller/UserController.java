package com.onlinevoting.OnlineVoting.controller;

import java.util.UUID;

import com.onlinevoting.OnlineVoting.model.User;
import com.onlinevoting.OnlineVoting.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "user", method = RequestMethod.POST, produces="application/hal+json")
    public ResponseEntity<?> insertUser( @RequestBody User user) {
        User userWithThisEmail = userRepository.findByEmail(user.getEmail());
        if(userWithThisEmail != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setId(UUID.randomUUID().toString());
        User newUser = userRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
}
