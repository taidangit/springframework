package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.AuthDAO;
import com.tedu.dao.MenuDAO;
import com.tedu.entity.Auth;
import com.tedu.entity.Menu;
import com.tedu.entity.Paging;
import com.tedu.entity.Role;
import com.tedu.entity.User;
import com.tedu.util.HashingPassword;

@Service
public class MenuService {

	private final static Logger log = Logger.getLogger(MenuService.class);
	
	@Autowired
	private MenuDAO<Menu> menuDAO;
	
	@Autowired
	private AuthDAO<Auth> authDAO;
	
	public List<Menu> getMenus(Paging paging) {
		log.info("Get all menu");
		return menuDAO.findAll(paging);
	}
	
	public Menu findMenuById(int id) {
		log.info("Find menu by id ");
		return menuDAO.findById(Menu.class, id);
	}

	public void changeStatus(int id) {
		Menu menu = menuDAO.findById(Menu.class, id);
		if(menu != null) { 
			menu.setActiveFlag(menu.getActiveFlag() == 1 ? 0 : 1);
			menuDAO.update(menu);
		}
		
	}
	

//	public void updatePermission(Auth auth) {
//		List<Auth> auths = authDAO.findAuth(auth.getRole().getRoleId(), auth.getMenu().getMenuId());
//		if(auths.get(0) != null && auths.size() > 0) {
//			auths.get(0).setPermission(auth.getPermission());
//			auths.get(0).setUpdateDate(new Date());
//			authDAO.update(auths.get(0));
//		}
//		
//	}
	
	
}
