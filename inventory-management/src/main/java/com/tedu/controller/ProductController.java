package com.tedu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
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

import com.tedu.entity.Category;
import com.tedu.entity.Paging;
import com.tedu.entity.Product;
import com.tedu.service.CategoryService;
import com.tedu.service.ProductService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/product")
public class ProductController {

private static final Logger log = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/product/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showProductList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<Product> products = productService.getProducts(paging);
		
		if(session.getAttribute(Constant.MSG_SUCCESS) != null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("products", products);
		
		return "product-list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		List<Category> categories = categoryService.getCategories(null);
		
		model.addAttribute("categories",categories);
		model.addAttribute("product", new Product());
		model.addAttribute("titlePage", "Add product");
		model.addAttribute("viewOnly", false);
		
		return "product-action";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		log.info("Edit product with id= "+id);
		
		Product product = productService.findProductById(id);
		if(product != null) {
			List<Category> categories = categoryService.getCategories(null);
			
			model.addAttribute("categories",categories);
			model.addAttribute("product", product);
			model.addAttribute("titlePage", "Edit product");
			model.addAttribute("viewOnly", false);
			
			return "product-action";
		}
		
		return "redirect:/product/list";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") int id, Model model) {
		log.info("View product with id= "+id);
		
		Product product =  productService.findProductById(id);
		if(product != null) {
			model.addAttribute("product", product);
			model.addAttribute("titlePage", "View product");
			model.addAttribute("viewOnly", true);
			
			return "product-action";
		}
		
		return "redirect:/product/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("product") Product product,
			BindingResult result, Model model, HttpSession session) {
		log.info("Save or update prouct");
		
		if(result.hasErrors()) {
			List<Category> categories = categoryService.getCategories(null);
			model.addAttribute("categories",categories);
			
			if(product.getProductId() != null) {
				model.addAttribute("titlePage", "Edit product");
			} else {
				model.addAttribute("titlePage", "Add product");
			}
			
			model.addAttribute("viewOnly", false);
			return "product-action";
		}
		
		if(product.getProductId() != null && product.getProductId() != 0) {
			if(product.getMultipartFile() != null) {
				String extionsion = FilenameUtils.getExtension(product.getMultipartFile().getOriginalFilename());
				log.info(extionsion);
				
				if(!extionsion.equals("jpg") && !extionsion.equals("png")) {
					model.addAttribute("errorUpload", "System do not support this file");
					List<Category> categories = categoryService.getCategories(null);
					model.addAttribute("categories",categories);
					return "product-action";
				}
			}
			productService.updateProduct(product);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
		} else {
			if(product.getMultipartFile() != null) {
				String extionsion = FilenameUtils.getExtension(product.getMultipartFile().getOriginalFilename());
				log.info(extionsion);
				
				if(!extionsion.equals("jpg") && !extionsion.equals("png")) {
					model.addAttribute("errorUpload", "System do not support this file");
					List<Category> categories = categoryService.getCategories(null);
					model.addAttribute("categories",categories);
					return "product-action";
				}
			}
			productService.saveProduct(product);	
			session.setAttribute(Constant.MSG_SUCCESS, "Add new success!!!");
		}
		
		return "redirect:/product/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, HttpSession session) {
		log.info("Delete product with id= "+id);
		
		productService.deleteProduct("productId", id);
		session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		
		return "redirect:/product/list";
	}
	
	@GetMapping("/search/{page}/{productName}")
	public String searchProducts(@PathVariable("page") int page, @PathVariable("productName") String productName, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<Product> products = productService.searchProducts("name", productName, paging);
			
		model.addAttribute("productName", productName);
		model.addAttribute("products", products);
		model.addAttribute("pageInfo", paging);
		
		return "product-list";		
	}
}
