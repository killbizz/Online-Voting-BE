package com.onlinevoting.OnlineVoting.controller;

import java.util.UUID;

import com.onlinevoting.OnlineVoting.lib.JWT;
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
            return new ResponseEntity<>("Email is linked to an already existing account", HttpStatus.CONFLICT);
        }
        user.setId(UUID.randomUUID().toString());
        // JWT with no expiration (I HOPE)
        user.setJWT(JWT.createJWT(user.getId(), "user", user.getId(), 0));
        User newUser = userRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
}
