package com.onlinevoting.OnlineVoting.controller;

import com.onlinevoting.OnlineVoting.dto.Credential;
import com.onlinevoting.OnlineVoting.dto.Identity;
import com.onlinevoting.OnlineVoting.lib.JWT;
import com.onlinevoting.OnlineVoting.model.Admin;
import com.onlinevoting.OnlineVoting.model.User;
import com.onlinevoting.OnlineVoting.repository.AdminRepository;
import com.onlinevoting.OnlineVoting.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST, produces="application/hal+json")
    public ResponseEntity<?> login( @RequestBody Credential user) {
        User registeredUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(registeredUser != null){
            String token = JWT.createJWT(registeredUser.getId(), "user", registeredUser.getId(), 0);
            return new ResponseEntity<>(new Identity(token, "user") , HttpStatus.OK);
        }
        Admin admin = adminRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(admin != null){
            String token = JWT.createJWT(admin.getId(), "admin", admin.getId(), 0);
            return new ResponseEntity<>(new Identity(token, "admin") , HttpStatus.OK);
        }
        return new ResponseEntity<>("Authentication Failed", HttpStatus.UNAUTHORIZED);
    }
    
}