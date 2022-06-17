package com.onlinevoting.OnlineVoting.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// NOTE: changed this type from User to AppUser to fix a JDBC error due to the usage of User, 
// which is a keyword in MySQL and caused problems creating the table and the foreign key constraints
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {
    
    @Id
    @Column(nullable = false) 
    private String id;
    @Column(nullable = false) 
    private String email;
    @Column(nullable = false) 
    private String password;
    @Column(nullable = false) 
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

}

