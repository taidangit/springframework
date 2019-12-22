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
import org.springframework.web.bind.annotation.RequestParam;

import com.tedu.entity.Category;
import com.tedu.entity.Paging;
import com.tedu.service.CategoryService;
import com.tedu.service.ProductService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private static final Logger log = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/category/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showCategoryList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<Category> categories = categoryService.getCategories(paging);
		
		if(session.getAttribute(Constant.MSG_SUCCESS) != null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("categories", categories);
		
		return "category-list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("titlePage", "Add category");
		model.addAttribute("viewOnly", false);
		
		return "category-action";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		log.info("Edit category with id= "+id);
		
		Category category = categoryService.findCategoryById(id);
		if(category != null) {
			model.addAttribute("category", category);
			model.addAttribute("titlePage", "Edit category");
			model.addAttribute("viewOnly", false);
			
			return "category-action";
		}
		
		return "redirect:/category/list";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") int id, Model model) {
		log.info("View category with id= "+id);
		
		Category category = categoryService.findCategoryById(id);
		if(category != null) {
			model.addAttribute("category", category);
			model.addAttribute("titlePage", "View category");
			model.addAttribute("viewOnly", true);
			
			return "category-action";
		}
		
		return "redirect:/category/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("category") Category category,
			BindingResult result, Model model, HttpSession session) {
		log.info("Save or update category");
		
		if(result.hasErrors()) {
			if(category.getCategoryId() != null) {
				model.addAttribute("titlePage", "Edit category");
			} else {
				model.addAttribute("titlePage", "Add category");
			}
		
			model.addAttribute("viewOnly", false);
			return "category-action";
		}
		if(category.getCategoryId() != null && category.getCategoryId() != 0) {
			categoryService.updateCategory(category);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
		} else {
			categoryService.saveCategory(category);	
			session.setAttribute(Constant.MSG_SUCCESS, "Add new success!!!");
		}
		
		return "redirect:/category/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, HttpSession session) {
		log.info("Delete category with id= "+id);
		
		productService.deleteProduct("category.categoryId", id);
		categoryService.deleteCategory("categoryId", id);
		session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		
		return "redirect:/category/list";
	}
	
	@GetMapping("/search/{page}/{categoryName}")
	public String searchCategories(@PathVariable("page") int page, @PathVariable("categoryName") String categoryName, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<Category> categories = categoryService.searchCategories("name", categoryName, paging);
			
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("categories", categories);
		model.addAttribute("pageInfo", paging);
		
		return "category-list";		
	}
	
}
