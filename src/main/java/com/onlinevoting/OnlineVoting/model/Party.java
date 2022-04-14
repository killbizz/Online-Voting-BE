package com.onlinevoting.OnlineVoting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "party")
public class Party {
    
    @Id
    @Column(nullable = false) 
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) 
    private String name;
    @Column(nullable = false) 
    private String candidate;
    @Lob
    private String base64logo;

    public Party() {
    }

    public Party(Long id, String name, String candidate, String base64logo) {
        this.id = id;
        this.name = name;
        this.candidate = candidate;
        this.base64logo = base64logo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCandidate() {
        return this.candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getBase64logo() {
        return this.base64logo;
    }

    public void setBase64logo(String base64logo) {
        this.base64logo = base64logo;
    }

}
