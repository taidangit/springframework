package com.ebookstore.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shopping_cart_id")
	private int shoppingCartId;
	
	@Column(name="grand_total")
	private BigDecimal grandTotal;
		
	@OneToMany(mappedBy="shoppingCart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItem> cartItems;
	
	@OneToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
