package com.onlinevoting.OnlineVoting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Party {
    
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String candidate;
    @Lob
    private String base64logo;


    public Party() {
    }

    public Party(long id, String name, String candidate, String base64logo) {
        this.id = id;
        this.name = name;
        this.candidate = candidate;
        this.base64logo = base64logo;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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
