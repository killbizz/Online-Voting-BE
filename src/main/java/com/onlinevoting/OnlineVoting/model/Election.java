package com.onlinevoting.OnlineVoting.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Election {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    @ElementCollection
    @CollectionTable(name = "parties", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "party")
    private List<Long> parties;
    @ElementCollection
    @CollectionTable(name = "votes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "vote")
    private List<Long> votes;


    public Election() {
    }

    public Election(long id, String name, String type, List<Long> parties, List<Long> votes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parties = parties;
        this.votes = votes;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getParties() {
        return this.parties;
    }

    public void setParties(List<Long> parties) {
        this.parties = parties;
    }

    public List<Long> getVotes() {
        return this.votes;
    }

    public void setVotes(List<Long> votes) {
        this.votes = votes;
    }

    
}
