package com.luv2code.dao;

import com.luv2code.entity.Role;

public interface RoleDAO {

	Role findRoleByName(String roleName);
}
