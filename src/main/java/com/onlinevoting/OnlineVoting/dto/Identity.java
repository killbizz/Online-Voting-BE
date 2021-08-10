package com.onlinevoting.OnlineVoting.dto;

public class Identity {

    private String username;
    private String role;
    private String userId;


    public Identity() {
    }

    public Identity(String username, String role, String userId) {
        this.username = username;
        this.role = role;
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
}
