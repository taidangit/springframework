package com.ebookstore.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.Book;
import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.ShoppingCart;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserOrder;
import com.ebookstore.repository.CartItemRepository;
import com.ebookstore.repository.ShoppingCartRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@Override
	public void updateCartItem(CartItem cartItem) {
		
		cartItem.setSubtotal(
				new BigDecimal(cartItem.getBook().getOurPrice()).
				multiply(new BigDecimal(cartItem.getQty())).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		cartItemRepository.save(cartItem);
	}

	@Override
	public void addBookToCartItem(Book book, User user, int qty) {
		List<CartItem> cartItems = findByShoppingCart(user.getShoppingCart());
		
		int position = findBook(book, cartItems);
		if(position != -1) {
			CartItem cartItem = cartItems.get(position);
			cartItem.setQty(cartItem.getQty()+qty);
			
			cartItem.setSubtotal(
					new BigDecimal(book.getOurPrice()).
					multiply(new BigDecimal(cartItem.getQty())).setScale(2,  BigDecimal.ROUND_HALF_UP));
			cartItemRepository.save(cartItem);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setShoppingCart(user.getShoppingCart());
			cartItem.setBook(book);
			
			cartItem.setQty(qty);
			cartItem.setSubtotal(new BigDecimal(
					book.getOurPrice()).
					multiply(new BigDecimal(qty)).setScale(2,  BigDecimal.ROUND_HALF_UP));
			cartItemRepository.save(cartItem);
		}
	}
	
	private int findBook(Book book, List<CartItem> cartItems) {
		for(int i=0; i<cartItems.size(); i++) {
			if(book.getBookId() == cartItems.get(i).getBook().getBookId()) {
                return i;
            }
        }
        return -1;
    }

	@Override
	public CartItem findById(int cartItemId) {
		return cartItemRepository.findById(cartItemId).get();
	}

	@Override
	public void removeCartItem(int cartItemId) {
		cartItemRepository.deleteById(cartItemId);
		
	}
	
	@Override
	public void removeAllCartItems(ShoppingCart shoppingCart) {
		List<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItems) {
			cartItemRepository.delete(cartItem);
		}
		
	}


	@Override
	public List<CartItem> findByUserOrder(UserOrder userOrder) {
		return cartItemRepository.findByUserOrder(userOrder);
	}
	
	
}
