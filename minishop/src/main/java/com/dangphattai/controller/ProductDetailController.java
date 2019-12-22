package com.dangphattai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dangphattai.entity.Cart;
import com.dangphattai.entity.Product;
import com.dangphattai.service.ProductService;

@Controller
public class ProductDetailController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/productSingle/{id}")
	public String productSingle(@PathVariable int id, Model model, HttpSession session) {
		
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		
		model.addAttribute("productDetails", product.getProductDetails());
		
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			model.addAttribute("cartSize", carts.size());
		}
		
		return "product-single";
	}
}
