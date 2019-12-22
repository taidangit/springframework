package com.ebookstore.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ebookstore.domain.Role;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.repository.RoleRepository;
import com.ebookstore.repository.UserRepository;
import com.ebookstore.utility.SecurityUtility;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Roles
		if (roleRepository.findByName("ROLE_USER") == null) {
			Role role = new Role();
			role.setName("ROLE_USER");
			roleRepository.save(role);
		}

		// user account
		if (userRepository.findByEmail("dangphattai92@gmail.com") == null) {
			User user = new User();
			user.setUsername("taidang");
			user.setFirstName("tai");
			user.setLastName("dang");
			user.setEmail("dangphattai92@gmail.com");
			user.setPhone("0339781910");
			user.setPassword(SecurityUtility.passwordEncoder().encode("123456"));
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_USER"));
			user.setRoles(roles);
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
		
			userRepository.save(user);
		}

	}

}
