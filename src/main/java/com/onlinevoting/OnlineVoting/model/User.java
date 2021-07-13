package com.onlinevoting.OnlineVoting.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    
    @Id
    private String id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String JWT;


    public User() {
    }


    public User(String email, String password, String firstname, String lastname, String JWT) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.JWT = JWT;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJWT() {
        return this.JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

}

