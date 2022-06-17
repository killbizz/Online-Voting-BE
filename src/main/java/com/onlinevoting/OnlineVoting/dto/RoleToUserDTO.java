package com.onlinevoting.OnlineVoting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleToUserDTO {

    private String role;
    private String userEmail;
    
}
