package com.ebookstore.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebookstore.domain.Book;
import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.service.BookService;
import com.ebookstore.service.CartItemService;
import com.ebookstore.service.ShoppingCartService;
import com.ebookstore.service.UserService;
import com.ebookstore.utility.MessageConstants;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);
		if(cartItems.size() == 0) {
			shoppingCartService.updateShoppingCart(shoppingCart);
			model.addAttribute(MessageConstants.EMPTY_CART, true);
			model.addAttribute("shoppingCart", shoppingCart);
			model.addAttribute("cartItems", cartItems);
			return "shoppingCart";
		}
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItems", cartItems);
		
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
		
	}
	
	@GetMapping("/addItem/{id}")
	public String addItem(@PathVariable("id") int bookId, 
			@RequestParam("qty") int qty,  
			Model model, Principal principal, HttpSession session) {
		
		User user = userService.findByUsername(principal.getName());
		
		Book book = bookService.findById(bookId);
		if(qty > book.getInStockNumber()) {
			session.setAttribute(MessageConstants.NOT_ENOUGH_STOCK, true);
			return "redirect:/book/bookDetail/"+bookId;
		}
		
		cartItemService.addBookToCartItem(book, user, qty);
		
		session.setAttribute(MessageConstants.ADD_BOOK_SUCCESS, true);
		
		return "redirect:/book/bookDetail/"+bookId;
	}
	
	@GetMapping("/updateCartItem/{id}")
	public String updateCartItem(@PathVariable("id") int cartItemId, @RequestParam("qty") int qty, Model model) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "redirect:/shoppingCart/cart";
		
	}
	
	@GetMapping("/removeItem/{id}")
	public String removeItem(@PathVariable("id") int cartItemId, Model model) {
		cartItemService.removeCartItem(cartItemId);
		
		return "redirect:/shoppingCart/cart";
	}
	
	@GetMapping("/removeAll/{id}")
	public String removeAll(@PathVariable("id") int shoppingCartId, Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		//ShoppingCart shoppingCart = shoppingCartService.findById(shoppingCartId);
		
		shoppingCartService.removeAllCartItems(shoppingCart);
		
		return "redirect:/shoppingCart/cart";
	}
	
}
