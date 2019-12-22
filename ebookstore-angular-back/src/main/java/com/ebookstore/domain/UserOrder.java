package com.ebookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_order")
public class UserOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_order_id")
	private int userOrderId;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column(name="shipping_date")
	private LocalDate shippingDate;
	
	@Column(name="shipping_method")
	private String shippingMethod;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="order_total")
	private BigDecimal orderTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id")
	private ShippingAddress shippingAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id")
	private BillingAddress billingAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartItem> cartItems;

	public int getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(int userOrderId) {
		this.userOrderId = userOrderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
