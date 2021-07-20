package com.onlinevoting.OnlineVoting.dto;

public class Identity {

    private String jwtToken;
    private String role;
    private String userId;


    public Identity() {
    }

    public Identity(String jwtToken, String role, String userId) {
        this.jwtToken = jwtToken;
        this.role = role;
        this.userId = userId;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
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
