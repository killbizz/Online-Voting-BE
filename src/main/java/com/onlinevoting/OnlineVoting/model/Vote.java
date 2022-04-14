package com.onlinevoting.OnlineVoting.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @Column(nullable = false) 
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false) 
    private String userId;

    @Column(nullable = false) 
    private long partyId;

    @Column(nullable = false) 
    private long electionId;

    @Column(nullable = false) 
    private Date date;


    public Vote() {
    }

    public Vote(long id, String userId, long partyId, long electionId) {
        this.id = id;
        this.userId = userId;
        this.partyId = partyId;
        this.electionId = electionId;
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
