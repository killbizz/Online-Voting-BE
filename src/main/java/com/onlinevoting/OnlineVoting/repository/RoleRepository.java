package com.onlinevoting.OnlineVoting.repository;

import com.onlinevoting.OnlineVoting.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
    void deleteById(Long id);
}