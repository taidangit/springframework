package com.dangphattai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dangphattai.entity.Cart;
import com.dangphattai.entity.Category;
import com.dangphattai.entity.Product;
import com.dangphattai.service.CategoryService;
import com.dangphattai.service.ProductService;

@Controller
public class ShopController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/shop")
	public String shop(HttpSession session, Model model) {
		
		/*
		 * List<Product> products = productService.getProducts();
		 * model.addAttribute("products", products);
		 */
		
		List<Product> products = productService.getProductsByCategory(1);
		model.addAttribute("productByCategory", products);
		
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			model.addAttribute("cartSize", carts.size());
		}
		
		return "shop";
	}
	
	@GetMapping("/productByCategory/{id}")
	public String getproductsByCategory(@PathVariable int id, Model model, HttpSession session) {
		
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		
		List<Product> products = productService.getProductsByCategory(id);
		model.addAttribute("productByCategory", products);
		
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			model.addAttribute("cartSize", carts.size());
		}
		
		return "shop";
	}
}
