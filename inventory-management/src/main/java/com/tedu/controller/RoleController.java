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
import com.tedu.service.RoleService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/role")
public class RoleController {

private static final Logger log = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/role/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showRolesList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		
		List<Role> roles = roleService.getRoles(paging);
		
		if(session.getAttribute(Constant.MSG_SUCCESS) != null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("roles", roles);
		
		return "role-list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		model.addAttribute("titlePage", "Add role");
		model.addAttribute("role", new Role());
		
		model.addAttribute("viewOnly", false);
		return "role-action";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		log.info("Edit role with id="+id);
		
		Role role = roleService.findRoleById(id);
		if(role != null) {
			model.addAttribute("role", role);
			model.addAttribute("titlePage", "Edit role");
			model.addAttribute("viewOnly", false);

			return "role-action";
		}
		
		return "redirect:/role/list";
	}
	@GetMapping("/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		
		log.info("View role with id= "+id);
		
		Role role = roleService.findRoleById(id);
		if(role != null) {
			model.addAttribute("role", role);
			model.addAttribute("titlePage", "View role");
			model.addAttribute("viewOnly", true);

			return "role-action";
		}
		
		return "redirect:/role/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("role") Role role, 
			BindingResult result,HttpSession session, Model model) {
		
		if(result.hasErrors()) {
			if(role.getRoleId() != null) {
				model.addAttribute("titlePage", "Edit role");
				
			}else {
				model.addAttribute("titlePage", "Add role");
				
			}
	
			model.addAttribute("viewOnly", false);
			return "role-action";
			
		}
		
		if(role.getRoleId() != null && role.getRoleId() != 0) {
			
			roleService.updateRole(role);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
		} else {
			List<Role> roles = roleService.findByProperty("roleName", role.getRoleName());
			
			if(roles.size() > 0 && roles.get(0).getRoleName().equals(role.getRoleName())) {
				model.addAttribute("roleAlreadyExist", "Role already exist.");
				return "role-action";
			}
			
			roleService.saveRole(role);
			session.setAttribute(Constant.MSG_SUCCESS, "Insert success!!!");
		}
		return "redirect:/role/list";
		
	}
	@GetMapping("/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id,HttpSession session) {
		log.info("Delete role with id="+id);
		roleService.deleteRole("roleId", id);
		session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		return "redirect:/role/list";
	}
	
	@GetMapping("/search/{page}/{roleName}")
	public String searchCategories(@PathVariable("page") int page, @PathVariable("roleName") String roleName, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<Role> roles = roleService.searchRole("roleName", roleName, paging);
			
		model.addAttribute("roleName", roleName);
		model.addAttribute("roles", roles);
		model.addAttribute("pageInfo", paging);
		
		return "role-list";		
	}
}
