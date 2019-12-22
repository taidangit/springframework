package com.tedu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tedu.entity.Auth;
import com.tedu.entity.Menu;
import com.tedu.entity.MenuChild;
import com.tedu.entity.Role;
import com.tedu.entity.User;
import com.tedu.entity.UserRole;
import com.tedu.service.MenuService;
import com.tedu.service.UserService;
import com.tedu.util.Constant;
import com.tedu.util.HashingPassword;

@Controller
public class LoginController {

	final static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired 
	private UserService userService;

	@Autowired 
	private MenuService menuService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		
		return "login/login";
	}
	
	@PostMapping("/processLogin")
	public String processLogin(@Valid @ModelAttribute("user") User user ,
			BindingResult result , HttpSession session, Model model) {
	
		//log.info(user.getUsername());
		List<User> users = userService.findByProperty("username", user.getUsername());
		
		if(users != null && users.size() > 0) {
			if(!users.get(0).getPassword().equals(HashingPassword.encrypt(user.getPassword()))) {
				model.addAttribute("msgerrorpass", "Password is incorrect");
				return "login/login";
			}
			
		} else {
			model.addAttribute("msgerrorusername", "Username do not exists.");
			return "login/login";
		}
		
		
		List<Menu> menus = new ArrayList<Menu>();
		Role role =  users.get(0).getRoles().iterator().next();
		
		for(Menu menu : role.getMenus()) {
			
			if(menu.getParentId()==0 && 
					menu.getOrderIndex()!=-1 && 
					menu.getActiveFlag()==1) {
				
				menus.add(menu);
			}
		}
		
		sortMenu(menus);
		
		for(Menu menu : menus) { 
			sortMenuChild(menu.getMenuChilds()); 
		}
		
		
		session.setAttribute(Constant.MENU_SESSION, menus);
		session.setAttribute(Constant.USER_INFO_SESSION, users.get(0));
		
		return "redirect:/home";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.MENU_SESSION);
		session.removeAttribute(Constant.USER_INFO_SESSION);
		return "redirect:/login";
	}
	
	private void sortMenu(List<Menu> menus) {
		Collections.sort(menus, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getOrderIndex() - o2.getOrderIndex();
			}
		});
	}
	
	private void sortMenuChild(List<MenuChild> menuChilds) {
		Collections.sort(menuChilds, new Comparator<MenuChild>() {
			@Override
			public int compare(MenuChild o1, MenuChild o2) {
				return o1.getOrderIndex() - o2.getOrderIndex();
			}
		});
	}
}
