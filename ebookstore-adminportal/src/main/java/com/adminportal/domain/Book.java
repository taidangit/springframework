package com.adminportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Category;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int bookId;
	
	@NotBlank(message = "is required")
	@Column(name="title")
	private String title;
	
	@NotBlank(message = "is required")
	@Column(name="author")
	private String author;
	
	@NotBlank(message = "is required")
	@Column(name="publisher")
	private String publisher;
	
	@NotBlank(message = "is required")
	@Column(name="publication_date")
	private String publicationDate;
	
	@Column(name="language")
	private String language;

	@Min(value = 0, message = "must be greater than zero.")
	@Column(name="number_of_pages")
	private int numberOfPages;
	
	@NotBlank(message = "is required")
	@Column(name="format")
	private String format;
	
	
	@NotBlank(message = "is required")
	@Column(name="isbn")
	private String isbn;
	
	@Min(value = 0, message = "must be greater than zero.")
	@Column(name="shipping_weight")
	private double shippingWeight;
	
	@Min(value = 0, message = "must be greater than zero.")
	@Column(name="list_price")
	private double listPrice;
	
	@Min(value = 0, message = "must be greater than zero.")
	@Column(name="our_price")
	private double ourPrice;
	
	@Column(name="active")
	private boolean active;
	
	@NotBlank(message = "is required")
	@Column(name="description", columnDefinition="text")
	private String description;
	
	@Min(value = 0, message = "must be greater than zero.")
	@Column(name="in_stock_number")
	private int inStockNumber;
	
	@Transient
	private MultipartFile image;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
