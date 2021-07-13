package com.onlinevoting.OnlineVoting.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
    
    @Id
    private String id;
    private String email;
    private String password;


    public Admin() {
    }


    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
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

}


