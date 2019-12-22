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
import org.springframework.web.servlet.ModelAndView;

import com.tedu.entity.Invoice;
import com.tedu.entity.Paging;
import com.tedu.entity.Product;
import com.tedu.entity.ProductInStock;
import com.tedu.service.InvoiceReport;
import com.tedu.service.InvoiceService;
import com.tedu.service.ProductInStockService;
import com.tedu.service.ProductService;
import com.tedu.util.Constant;

@Controller
@RequestMapping("/goods-issue")
public class GoodsIssueController {

private static final Logger log = Logger.getLogger(GoodsIssueController.class);
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductInStockService productInStockService;
	
	@GetMapping("/list")
	public String redirect() {
		return "redirect:/goods-issue/list/1";
	}
	
	@GetMapping("/list/{page}")
	public String showInvoiceList(@PathVariable("page") int page, Model model, HttpSession session) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);

		Invoice invoice = new Invoice();
		invoice.setType(Constant.TYPE_GOODS_ISSUES);
		List<Invoice> invoices = invoiceService.getInvoices(paging);
		
		if(session.getAttribute(Constant.MSG_SUCCESS) != null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		
		model.addAttribute("pageInfo", paging);
		model.addAttribute("invoices", invoices);
		
		return "goods-issue-list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		List<Product> products = productService.getProducts(null);
		model.addAttribute("products",products);
		model.addAttribute("invoice", new Invoice());
		model.addAttribute("titlePage", "Add invoice");
		model.addAttribute("viewOnly", false);
		
		return "goods-issue-action";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		log.info("Edit invoice with id= "+id);
		
		Invoice invoice = invoiceService.findInvoiceById(id);
		if(invoice != null) {
			List<Product> products = productService.getProducts(null);
			model.addAttribute("products",products);
			model.addAttribute("invoice", invoice);
			model.addAttribute("titlePage", "Edit invoice");
			model.addAttribute("viewOnly", false);
			
			return "goods-issue-action";
		}
		
		return "redirect:/goods-issue/list";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") int id, Model model) {
		log.info("View invoice with id= "+id);
		
		Invoice invoice = invoiceService.findInvoiceById(id);
		if(invoice != null) {
			
			model.addAttribute("invoice", invoice);
			model.addAttribute("titlePage", "View invoice");
			model.addAttribute("viewOnly", true);
			
			return "goods-issue-action";
		}
		
		return "redirect:/goods-issue/list";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("invoice") Invoice invoice,
			BindingResult result, Model model, HttpSession session) {
		log.info("Save or update invoice");
		
		List<Product> products = productService.getProducts(null);
		
		if(result.hasErrors()) {
			
			model.addAttribute("products",products);
			
			if(invoice.getInvoiceId() != null) {
				model.addAttribute("titlePage", "Edit invoice");
			} else {
				model.addAttribute("titlePage", "Add invoice");
			}
		
			model.addAttribute("viewOnly", false);
			return "goods-issue-action";
		}
		
		invoice.setType(Constant.TYPE_GOODS_ISSUES);
		
		ProductInStock  productInStock = 
				productInStockService.findProductInStock("product.productId", invoice.getProduct().getProductId()).get(0);
		
		if(invoice.getInvoiceId() != null && invoice.getInvoiceId() != 0) {
			invoiceService.updateInvoice(invoice);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success!!!");
		} else {
			if(invoice.getQuantity() > productInStock.getQuantity()) {
				model.addAttribute("products",products);
				model.addAttribute("msgQty", "Only "+productInStock.getQuantity()+" product in stock");
				return "goods-issue-action";
			}
			invoiceService.saveInvoice(invoice);	
			session.setAttribute(Constant.MSG_SUCCESS, "Add new success!!!");
		}
		
		return "redirect:/goods-issue/list";
	}
	
	@GetMapping("/export")
	public ModelAndView exportReport() {
		ModelAndView modelAndView = new ModelAndView();
		List<Invoice> invoices = invoiceService.getInvoices(null);
		modelAndView.addObject(Constant.KEY_GOODS_RECEIPT_REPORT, invoices);
		modelAndView.setView(new InvoiceReport());
		return modelAndView;
	}


	@GetMapping("/search/{page}/{productName}")
	public String searchInvoice(@PathVariable("page") int page, @PathVariable("productName") String productName, Model model) {
		Paging paging = new Paging(3);
		paging.setCurrentPage(page);
		List<Invoice> invoices = invoiceService.searchInvoice("product.name", productName, paging);
		
		model.addAttribute("invoices", invoices);
		model.addAttribute("productName", productName);
		model.addAttribute("pageInfo", paging);
		
		return "goods-issue-list";		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, HttpSession session) {
		log.info("Delete invoice with id= "+id);
		
		invoiceService.deleteInvoice("invoiceId", id);
	
		session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		
		return "redirect:/goods-issue/list";
	}
	
}
