package com.tedu.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tedu.dao.ProductDAO;
import com.tedu.entity.Paging;
import com.tedu.entity.Product;
import com.tedu.util.ConfigLoader;


@Service
public class ProductService {

	private static final Logger log = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductDAO<Product> productDAO;
	
	public void saveProduct(Product product) {
		log.info("Insert product "+product.getName());
		
		product.setActiveFlag(1);
		product.setCreateDate(new Date());
		product.setUpdateDate(new Date());
		String fileName = System.currentTimeMillis()+"_"+product.getMultipartFile().getOriginalFilename();
		processUploadFile(product.getMultipartFile(), fileName);
		product.setImageUrl(fileName);
		productDAO.save(product);
	}
	
	public void updateProduct(Product product) {
		log.info("Update product "+product.getName());
		
		Product product2 = productDAO.findById(Product.class, product.getProductId());
	
		String fileName =System.currentTimeMillis()+"_"+product.getMultipartFile().getOriginalFilename(); 
		processUploadFile(product.getMultipartFile(), fileName);
		product.setImageUrl(fileName); 
		
		product.setActiveFlag(1);
		product.setCreateDate(product2.getCreateDate());
		product.setUpdateDate(new Date());
		productDAO.update(product);
	}
	
	public void deleteProduct(Product product) {
		log.info("Delete product "+product.getName());
		product.setActiveFlag(0);
		productDAO.update(product);
	}
	
	public List<Product> findProduct(String property, Object value) {
		log.info("Find product by property= "+property+", value= "+value);
		return productDAO.findByProperty(property, value);
	}
	
	public List<Product> getProducts(Paging paging) {
		log.info("Get all product");
		return productDAO.findAll(paging);
	}
	
	public Product findProductById(int id) {
		log.info("Find product by id= "+id);
		return productDAO.findById(Product.class, id);
	}
	
	public void deleteProduct(String property, int id) {
		productDAO.delete(property, id);
	}
	
	public List<Product> searchProducts(String property, String productName, Paging paging) {
		return productDAO.searchProducts(property,  productName, paging);
	}
	
	private void processUploadFile(MultipartFile multipartFile, String fileName) {
		if(multipartFile != null) {
			File dir = new File(ConfigLoader.getInstance().getValue("upload.servlet.location"));
			if(!dir.exists()) {
				dir.mkdirs();
			}

			File file = new File(ConfigLoader.getInstance().getValue("upload.servlet.location"), fileName);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
