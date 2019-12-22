package com.tedu.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.entity.UserRole;

@Repository
@Transactional(rollbackFor=Exception.class)
public class UserRoleDAOImpl extends BaseDAOImpl<UserRole> implements UserRoleDAO<UserRole> {

}
