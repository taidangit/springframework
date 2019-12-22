package com.tedu.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.entity.Paging;
import com.tedu.entity.Role;
import com.tedu.entity.User;
import com.tedu.service.RoleService;
import com.tedu.service.UserService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/user/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showUsersList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		
		List<User> users = userService.getUsers(paging);
		
		if(session.getAttribute(Constant.MSG_SUCCESS) != null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("users", users);
		
		return "user-list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		model.addAttribute("titlePage", "Add User");
		model.addAttribute("user", new User());
		
		model.addAttribute("viewOnly", false);
		model.addAttribute("editMode", false);
		return "user-action";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		log.info("Edit user with id="+id);
		
		User user = userService.findUserById(id);
		if(user != null) {
			model.addAttribute("user", user);
			model.addAttribute("titlePage", "View user");
			model.addAttribute("viewOnly", false);
			model.addAttribute("editMode", false);
			return "user-action";
		}
		
		return "redirect:/user/list";
	}
	@GetMapping("/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		
		log.info("View user with id= "+id);
		
		User user = userService.findUserById(id);
		if(user != null) {
			model.addAttribute("user", user);
			model.addAttribute("titlePage", "View user");
			model.addAttribute("viewOnly", true);
			model.addAttribute("editMode", true);
			return "user-action";
		}
		
		return "redirect:/user/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("user") User user, 
			BindingResult result,HttpSession session, Model model) {
		
		if(result.hasErrors()) {
			if(user.getUserId() != null) {
				model.addAttribute("titlePage", "Edit User");
				model.addAttribute("editMode", false);
			}else {
				model.addAttribute("titlePage", "Add User");
				model.addAttribute("editMode", false);
			}
	
			model.addAttribute("viewOnly", false);
			return "user-action";
			
		}
		
		
		if(user.getUserId() != null && user.getUserId() != 0) {
			List<Role> roles = roleService.findByProperty("roleName", "admin");
			user.setRoles(roles);
			userService.updateUser(user);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
		} else {
			List<User> users = userService.findByProperty("username", user.getUsername());
			
			if(users.size() > 0 && users.get(0).getUsername().equals(user.getUsername())) {
				model.addAttribute("usernameAlreadyExist", "Username already exist.");
				return "user-action";
			}
			List<Role> roles = roleService.findByProperty("roleName", "admin");
			user.setRoles(roles);
		
			userService.saveUser(user);
			session.setAttribute(Constant.MSG_SUCCESS, "Insert success!!!");
		}
		return "redirect:/user/list";
		
	}
	@GetMapping("/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id,HttpSession session) {
		log.info("Delete user with id="+id);
		userService.deleteUser("userId", id);
		session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		return "redirect:/user/list";
	}
	
	@GetMapping("/search/{page}/{name}")
	public String searchCategories(@PathVariable("page") int page, @PathVariable("name") String name, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<User> users = userService.searchUser("name", name, paging);
			
		model.addAttribute("name", name);
		model.addAttribute("users", users);
		model.addAttribute("pageInfo", paging);
		
		return "user-list";		
	}
	
}
