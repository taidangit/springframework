package com.tedu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tedu.entity.Paging;
import com.tedu.entity.Product;
import com.tedu.entity.ProductInStock;
import com.tedu.service.ProductInStockService;

@Controller
@RequestMapping("/product-in-stock")
public class ProductInStockController {
	
	@Autowired
	private ProductInStockService productInStockService;
	
	@GetMapping(value= {"/list", "/list/"})
	public String redirect() {
		return "redirect:/product-in-stock/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showProductInStockList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<ProductInStock> productInStocks = productInStockService.getProductInStocks(paging);
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("productInStocks", productInStocks);
		
		return "product-in-stock";
	}
	
	@GetMapping("/search/{page}/{productName}")
	public String searchProductInStocks(@PathVariable("page") int page, @PathVariable("productName") String productName, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<ProductInStock> productInStocks = productInStockService.searchProductInStocks("product.name", productName, paging);
			
		model.addAttribute("productName", productName);
		model.addAttribute("productInStocks", productInStocks);
		model.addAttribute("pageInfo", paging);
		
		return "product-in-stock";		
	}
}
