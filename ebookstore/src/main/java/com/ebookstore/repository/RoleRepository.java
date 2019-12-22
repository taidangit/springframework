package com.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String roleName);
}
