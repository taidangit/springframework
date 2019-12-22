package com.dangphattai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dangphattai.entity.Cart;
import com.dangphattai.entity.Product;
import com.dangphattai.service.ProductService;


@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			model.addAttribute("username", username);
		} else {
			model.addAttribute("username", null);
		}
		
		List<Product> products = productService.getProductsLimit(0);
		model.addAttribute("products", products);
		
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			model.addAttribute("cartSize", carts.size());
		}
	
		return "home";
	}
}
