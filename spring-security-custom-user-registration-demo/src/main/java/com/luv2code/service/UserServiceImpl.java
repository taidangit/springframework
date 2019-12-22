package com.luv2code.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luv2code.dao.RoleDAO;
import com.luv2code.dao.UserDAO;
import com.luv2code.entity.Role;
import com.luv2code.entity.User;
import com.luv2code.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found.");
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}
	
	
	@Override
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDAO.findByUserName(userName);
	}

	@Override
	public void save(CrmUser crmUser) {
		User user = new User();
		// assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		// give user default role of "employee"
		Set<Role> roles = new HashSet<>();
		roles.add(roleDAO.findRoleByName("ROLE_EMPLOYEE"));
		user.setRoles(roles);

		// save user in the database
		userDAO.save(user);
		
	}

}
