package com.luv2code.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.luv2code.entity.User;
import com.luv2code.user.CrmUser;

public interface UserService extends UserDetailsService {

	 User findByUserName(String userName);

	 void save(CrmUser crmUser);
}
