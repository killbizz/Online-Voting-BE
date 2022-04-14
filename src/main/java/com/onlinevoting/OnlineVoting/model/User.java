package com.onlinevoting.OnlineVoting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    
    @Id
    @Column(nullable = false) 
    private String id;
    @Column(nullable = false) 
    private String email;
    @Column(nullable = false) 
    private String password;
    @Column(nullable = false) 
    private String username;

    public User() {
    }

    public User(String email, String password, String firstname, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

