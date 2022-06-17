package com.onlinevoting.OnlineVoting.controller;

import com.onlinevoting.OnlineVoting.dto.RoleToUserDTO;
import com.onlinevoting.OnlineVoting.model.Role;
import com.onlinevoting.OnlineVoting.model.AppUser;
import com.onlinevoting.OnlineVoting.service.RefreshTokenService;
import com.onlinevoting.OnlineVoting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @GetMapping("user")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("user")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        return ResponseEntity.created(null).body(userService.saveUser(user));
    }

    @PostMapping("user/admin")
    public ResponseEntity<AppUser> saveAdmin(@RequestBody AppUser user) {
        return ResponseEntity.created(null).body(userService.saveAdmin(user));
    }

    @DeleteMapping("user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.created(null).body(userService.saveRole(role));
    }

    @DeleteMapping("role/{name}")
    public ResponseEntity<?> deleteRole(@PathVariable String name) {
        userService.deleteRole(name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO dto) throws Exception {
        userService.addRoleToUser(dto.getUserEmail(), dto.getRole());
        return ResponseEntity.ok().build();
    }

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        refreshTokenService.refreshToken(request, response);
    }
    
}
