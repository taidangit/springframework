package com.tedu.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="product_in_stock")
public class ProductInStock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_in_stock_id")
	private Integer productInStockId;

	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private BigDecimal price;
	
	
	@Column(name="active_flag")
	private int activeFlag;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="update_date")
	private Date updateDate;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;

	public Integer getProductInStockId() {
		return this.productInStockId;
	}

	public void setProductInStockId(Integer productInStockId) {
		this.productInStockId = productInStockId;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
