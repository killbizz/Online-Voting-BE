package com.onlinevoting.OnlineVoting.dto;

public class Identity {

    private String jwtToken;
    private String role;


    public Identity() {
    }

    public Identity(String jwtToken, String role) {
        this.jwtToken = jwtToken;
        this.role = role;
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

    
}
