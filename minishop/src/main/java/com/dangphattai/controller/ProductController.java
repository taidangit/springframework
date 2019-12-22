package com.dangphattai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dangphattai.entity.Category;
import com.dangphattai.entity.Product;
import com.dangphattai.entity.Size;
import com.dangphattai.service.CategoryService;
import com.dangphattai.service.ProductService;
import com.dangphattai.service.SizeService;


@Controller
@RequestMapping("/admin")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SizeService sizeService;
	
	
	@GetMapping("/productList")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		List<Product> productsLimit = productService.getProductsLimit(0);
		model.addAttribute("products", products);
		model.addAttribute("productsLimit", productsLimit);
		
		int totalItemsPerPage = 8;
		int totalPages = 0;
		if ((products.size() % totalItemsPerPage) != 0){
			totalPages = (products.size() / totalItemsPerPage) + 1;
		}else if((products.size() % totalItemsPerPage) == 0){
			totalPages = products.size() / totalItemsPerPage;
		}
		model.addAttribute("totalPages", totalPages);
		
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		
		List<Size> sizes = sizeService.getSizes();
		model.addAttribute("sizes", sizes);
		
		return "list-products";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		
		List<Size> sizes = sizeService.getSizes();
		model.addAttribute("sizes", sizes);
		
		
		return "product-form";
	}
	
	
}
