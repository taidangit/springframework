package com.dangphattai.entity;

public class Cart {

	private int cartId;
	private int quantity;
	private String product;
	private String price;
	private String size;
	private String image;
	
	
	public int getProductCartId() {
		return cartId;
	}
	public void setProductCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	
}
