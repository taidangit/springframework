package com.adminportal.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.adminportal.domain.Role;
import com.adminportal.domain.User;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.utility.SecurityUtility;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			roleRepository.save(role);
		}

		// Admin account
		if (userRepository.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(SecurityUtility.passwordEncoder().encode("123456789"));
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}

	}

}
