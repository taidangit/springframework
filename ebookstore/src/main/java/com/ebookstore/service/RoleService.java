package com.ebookstore.service;

import com.ebookstore.domain.Role;

public interface RoleService {

	Role findByName(String roleName);
}
