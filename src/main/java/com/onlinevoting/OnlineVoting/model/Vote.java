package com.onlinevoting.OnlineVoting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vote {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String userId;
    private long partyId;
    private long electionId;


    public Vote() {
    }

    public Vote(long id, String userId, long partyId) {
        this.id = id;
        this.userId = userId;
        this.partyId = partyId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPartyId() {
        return this.partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public long getElectionId() {
        return this.electionId;
    }

    public void setElectionId(long electionId) {
        this.electionId = electionId;
    }

    
}
