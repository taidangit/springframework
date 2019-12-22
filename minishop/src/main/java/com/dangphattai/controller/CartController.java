package com.dangphattai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dangphattai.entity.Cart;

@Controller
public class CartController {

	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) {
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			model.addAttribute("cartSize", carts.size());
			model.addAttribute("carts", carts);
		}
		
		return "cart";
	}
}
