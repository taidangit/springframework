package com.tedu.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer productId;
	
	@NotBlank(message="is required")
	@Column(name="name")
	private String name;
	
	@NotBlank(message="is required")
	@Column(name="code")
	private String code;
	
	@NotBlank(message="is required")
	@Column(name="description")
	private String description;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="active_flag")
	private int activeFlag;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="update_date")
	private Date updateDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="product")
	private List<ProductInStock> productInStocks = new ArrayList<ProductInStock>();

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="product")
	private List<History> histories = new ArrayList<History>();

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="product")
	private List<Invoice> invoices = new ArrayList<Invoice>();

	@OneToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@Transient 
	private MultipartFile multipartFile;
	
	
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public List<ProductInStock> getProductInStocks() {
		return productInStocks;
	}

	public void setProductInStocks(List<ProductInStock> productInStocks) {
		this.productInStocks = productInStocks;
	}

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	
}
