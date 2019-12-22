package com.dangphattai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangphattai.dao.ProductDAO;
import com.dangphattai.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;


	@Override
	public List<Product> getProducts() {
		
		return productDAO.getProducts();
	}
	
	
	@Override
	public List<Product> getProductsLimit(int indexStart) {
		
		return productDAO.getProductsLimit(indexStart);
	}

	@Override
	public Product getProduct(int productId) {
		
		return productDAO.getProduct(productId);
	}


	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		return productDAO.getProductsByCategory(categoryId);
	}


	@Override
	public void deleteProduct(int productId) {
		productDAO.deleteProduct(productId);
	}


	@Override
	public Product saveProduct(Product product) {
		return productDAO.saveProduct(product);
	}


	@Override
	public Product updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}


	@Override
	public List<Product> searchProducts(String searchName) {
		return productDAO.searchProducts(searchName);
	}

	
}
