package com.dangphattai.service;

import java.util.List;

import com.dangphattai.entity.Product;

public interface ProductService {

	List<Product> getProducts();
	
	List<Product> getProductsLimit(int indexStart);
	
	Product getProduct(int productId);
	
	List<Product> getProductsByCategory(int categoryId);
	
	void deleteProduct(int productId);
	
	Product saveProduct(Product product);
	
	Product updateProduct(Product product);
	
	List<Product> searchProducts(String searchName);
}
