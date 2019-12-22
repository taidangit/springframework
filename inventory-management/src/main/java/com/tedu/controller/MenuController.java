package com.tedu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.entity.Auth;
import com.tedu.entity.Menu;
import com.tedu.entity.Paging;
import com.tedu.entity.Role;
import com.tedu.service.MenuService;
import com.tedu.service.RoleService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/menu/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showMenuList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		
		List<Menu> menus = menuService.getMenus(paging);
		List<Role> roles = roleService.getRoles(null);
		
		if(session.getAttribute(Constant.MSG_SUCCESS) != null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("menus", menus);
		model.addAttribute("roles", roles);
		
		return "menu-list";
	}
	
	@GetMapping("/change-status/{id}")
	public String change(Model model, @PathVariable("id") int id, HttpSession session) {
		
		menuService.changeStatus(id);
		session.setAttribute(Constant.MSG_SUCCESS, "Change status success!!!");
		
		return "redirect:/menu/list";
	}
	
//	@GetMapping("/permission")
//	public String permission(Model model) {
//		List<Role> roles = roleService.getRoles(null);
//		List<Menu> menus = menuService.getMenus(null);
//		
//		model.addAttribute("auth", new Auth());
//		model.addAttribute("roles", roles);
//		model.addAttribute("menus", menus);
//		
//		return "menu-permission";
//	}
	
//	@PostMapping("/update-permission")
//	public String updatePermission(@ModelAttribute("auth") Auth auth, Model model,HttpSession session) {
//		menuService.updatePermission(auth);
//		session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
//		return "redirect:/menu/list";
//	}
//	
}
