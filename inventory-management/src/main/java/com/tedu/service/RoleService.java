package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.RoleDAO;
import com.tedu.dao.UserDAO;
import com.tedu.entity.Paging;
import com.tedu.entity.Role;
import com.tedu.entity.User;
import com.tedu.util.HashingPassword;

@Service
public class RoleService {

	private final static Logger log = Logger.getLogger(RoleService.class);
	
	@Autowired
	private RoleDAO<Role> roleDAO;

	
	public List<Role> getRoles(Paging paging) {
		log.info("Get all role");
		return roleDAO.findAll(paging);
	}
	
	public List<Role> findByProperty(String property, Object value) {
		log.info("Find role by property");
		
		return roleDAO.findByProperty(property, value);
	}
	

	public Role findRoleById(Integer id) {
		log.info("Find role by id ");
		return roleDAO.findById(Role.class, id);
	}

	public void saveRole(Role role) {
		role.setActiveFlag(1);
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		
		roleDAO.save(role);
	
	}

	public void updateRole(Role role) {
		
		Role role2 = findRoleById(role.getRoleId());
		
		role.setActiveFlag(1);
		role.setCreateDate(role2.getCreateDate());
		role.setUpdateDate(new Date());
		roleDAO.update(role);
	}

	public void deleteRole(String property, int id) {
		roleDAO.delete(property, id);
	}
	
	public List<Role> searchRole(String property, String roleName, Paging paging) {
		return roleDAO.searchRole(property, roleName, paging);
	}
}
