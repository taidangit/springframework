package com.tedu.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tedu.entity.Menu;
import com.tedu.entity.Role;
import com.tedu.entity.User;
import com.tedu.util.Constant;

public class FilterSystem implements HandlerInterceptor {

	Logger logger = Logger.getLogger(FilterSystem.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Request URI ="+request.getRequestURI());
		
		User user = (User) request.getSession().getAttribute(Constant.USER_INFO_SESSION);
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		if(user != null) {
			String url = request.getServletPath();
			logger.info("URL ="+url);
			if(!hasPermission(url, user)) {
				logger.error("ACCESS DENIED URI ="+request.getRequestURI());
				response.sendRedirect(request.getContextPath()+"/access-denied");
				return false;
			}
		}
		return true;

	}
	private boolean hasPermission(String url , User user) {
		if(
				url.contains("/home") || 
				url.contains("/category") || 
				url.contains("/product") || 
				url.contains("/user") || 
				url.contains("/menu") ||
				url.contains("/history") ||  
				url.contains("/role") || 
				url.contains("/search") || 
				url.contains("/goods-issue") || 
				url.contains("/goods-receipt") ||
				url.contains("/access-denied") || 
				url.contains("/logout")) {
			return true;
		}
		
		Role role = user.getRoles().iterator().next();
		for(Menu menu : role.getMenus()) {
			if(url.contains(menu.getUrl())) {
				return true;
			}
		}
		return false;
		
	}
}
