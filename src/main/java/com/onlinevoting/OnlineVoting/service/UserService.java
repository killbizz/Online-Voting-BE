package com.onlinevoting.OnlineVoting.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import com.onlinevoting.OnlineVoting.model.Role;
import com.onlinevoting.OnlineVoting.model.AppUser;
import com.onlinevoting.OnlineVoting.repository.RoleRepository;
import com.onlinevoting.OnlineVoting.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AppUser getUser(String email) {
        log.info("Retriving User");
        return userRepository.findByEmail(email);
    }

    public List<AppUser> getUsers() {
        log.info("Retriving Users");
        return (List<AppUser>) userRepository.findAll();
    }

    public AppUser saveUser(AppUser user) {
        log.info("Saving a new user");
        AppUser userWithThisEmail = userRepository.findByEmail(user.getEmail());
        if(userWithThisEmail != null){
            log.info("Email is linked to an already existing account");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is linked to an already existing account");
        }
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role((long) 1, "ROLE_USER"));

        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public AppUser saveAdmin(AppUser user) {
        log.info("Saving a new user");
        AppUser userWithThisEmail = userRepository.findByEmail(user.getEmail());
        if(userWithThisEmail != null){
            log.info("Email is linked to an already existing account");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is linked to an already existing account");
        }
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role((long) 2, "ROLE_ADMIN"));

        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        log.info("Deleting a user");
        AppUser user = userRepository.findByEmail(email);
        if(user == null){
            log.info("The user doesn't exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user doesn't exist");
        }
        userRepository.deleteById(user.getId());
    }

    public Role saveRole(Role role) {
        log.info("Saving a new role");
        Role alreadyExistingRole = roleRepository.findByName(role.getName());
        if(alreadyExistingRole != null){
            log.info("This role already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This role already exists");
        }

        return roleRepository.save(role);
    }

    public void deleteRole(String name) {
        log.info("Deleting a role");
        Role role = roleRepository.findByName(name);
        if(role == null){
            log.info("The role doesn't exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The role doesn't exist");
        }
        roleRepository.deleteById(role.getId());
    }

    public void addRoleToUser(String email, String role) {
        log.info("Adding Role to user");
        AppUser user = userRepository.findByEmail(email);
        Role roleObj = roleRepository.findByName(role);
        if (roleObj != null && user != null) {
            user.getRoles().add(roleObj);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user or the role doesn't exist");
        }
    }

    // here the username = email of the user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser User = userRepository.findByEmail(username);
        if (User != null) {
            List<GrantedAuthority> authorityList = User.getRoles().
                    stream().
                    map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(username, User.getPassword(), authorityList);
        } else {
            return null;
        }

    }

}
