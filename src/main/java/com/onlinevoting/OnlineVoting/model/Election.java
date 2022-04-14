package com.onlinevoting.OnlineVoting.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "election")
public class Election {

    @Id
    @Column(nullable = false) 
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false) 
    private String name;
    @Column(nullable = false) 
    private String type;
    @Column(nullable = false) 
    private Date startDate;
    @Column(nullable = false) 
    private Date endDate;
    @ElementCollection
    @CollectionTable(name = "parties", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "party")
    private List<Long> parties;

    public Election() {
    }

    public Election(long id, String name, String type, List<Long> parties) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parties = parties;
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

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Long> getParties() {
        return this.parties;
    }

    public void setParties(List<Long> parties) {
        this.parties = parties;
    }
}
