package com.dangphattai.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dangphattai.entity.Bill;
import com.dangphattai.entity.BillDetail;
import com.dangphattai.entity.Cart;

import com.dangphattai.service.BillService;

@Controller
public class CheckoutController {


	@Autowired
	private BillService billService;
	
	@GetMapping("/checkout")
	public String checkout(HttpSession session, Model model) {
		int cartTotal = 0;
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			model.addAttribute("cartSize", carts.size());
			
			
			for(Cart cartItem : carts) {
				cartTotal += cartItem.getQuantity()*Integer.parseInt(cartItem.getPrice().substring(1,4));
			}
			
			model.addAttribute("carTotal", "$"+cartTotal);
		}
		
		model.addAttribute("bill", new Bill());
		
		return "checkout";
	}
	
	@PostMapping("/processCheckout")
	public String processCheckout(@Valid @ModelAttribute("bill") Bill bill, 
			BindingResult bindingResult, Model model, HttpSession session) {
		
		
		if (bindingResult.hasErrors()) {
			return "checkout";	
		}
		
		int cartTotal = 0;
		
		List<BillDetail> billDetails = new ArrayList<>();
		
		if(session.getAttribute("carts") != null) {
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			
			for(Cart cartItem : carts) {
				cartTotal += cartItem.getQuantity()*Integer.parseInt(cartItem.getPrice().substring(1,4));
				
				BillDetail billDetail = new BillDetail();
				billDetail.setPrice(cartItem.getPrice());
				billDetail.setQuantity(cartItem.getQuantity());
				billDetail.setSize(cartItem.getSize());
				billDetail.setProductName(cartItem.getProduct());
				
				billDetail.setBill(bill);
				
				billDetails.add(billDetail);
			}

			bill.setTotal("$"+cartTotal);
			
		
			bill.setBillDetails(billDetails);
					
			if(billService.addBill(bill) != null) {
				model.addAttribute("msgordersuccess", "Order Successfully!!");
				return "confirm-checkout";
			}
			
			
		}
		
		return "checkout";
	}
}
