package com.luv2code.dao;

import com.luv2code.entity.User;

public interface UserDAO {

	 User findByUserName(String userName);
	    
	 void save(User user);
}
