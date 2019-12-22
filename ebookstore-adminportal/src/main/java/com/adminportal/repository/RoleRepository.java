package com.adminportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminportal.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String roleName);
}
