package com.dangphattai.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bill_detail")
public class BillDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bill_detail_id")
	private int billDetailId;
	
	@Column(name="price")
	private String price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="size")
	private String size;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bill_id")
	private Bill bill;
	
	@Column(name="product_name")
	private String productName;
	

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}






}
