package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.ProductInStockDAO;
import com.tedu.entity.Category;
import com.tedu.entity.Invoice;
import com.tedu.entity.Paging;
import com.tedu.entity.Product;
import com.tedu.entity.ProductInStock;

@Service
public class ProductInStockService {

	private static final Logger log = Logger.getLogger(ProductInStockService.class);
	
	@Autowired
	private ProductInStockDAO<ProductInStock> productInStockDAO;
	
	public List<ProductInStock> getProductInStocks(Paging paging) {
		log.info("Get all product in stock");
		return productInStockDAO.findAll(paging);
	}
	
	public List<ProductInStock> findProductInStock(String property, Object value) {
		log.info("Find product in stock by property= "+property+", value= "+value);
		return productInStockDAO.findByProperty(property, value);
	}
	
	public List<ProductInStock> searchProductInStocks(String property, String productInStockName, Paging paging) {
		return productInStockDAO.searchProductInStocks(property,  productInStockName, paging);
	}
	
	public void saveOrUpdateProductInStock(Invoice invoice) {
		log.info("insert to stock qty="+invoice.getQuantity()+" and price="+invoice.getPrice());
		
		if(invoice.getProduct() != null) {
			int productId = invoice.getProduct().getProductId();
			List<ProductInStock>  productInStocks = productInStockDAO.findByProperty("product.productId", productId);
			
			if(productInStocks != null && productInStocks.size() > 0) {
				ProductInStock productInStock = productInStocks.get(0);
				if(invoice.getType() == 2) {
					productInStock.setQuantity(productInStock.getQuantity() - invoice.getQuantity());
				} else {
					productInStock.setQuantity(productInStock.getQuantity() + invoice.getQuantity());
				}
				
				productInStock.setUpdateDate(new Date());
		
				productInStockDAO.update(productInStock);
			} else {
				ProductInStock  productInStock2 = new ProductInStock();
				
				productInStock2.setPrice(invoice.getPrice());
				productInStock2.setQuantity(invoice.getQuantity());
				productInStock2.setProduct(invoice.getProduct());
				productInStock2.setActiveFlag(1);
				productInStock2.setCreateDate(new Date());
				productInStock2.setUpdateDate(new Date());
				
				productInStockDAO.save(productInStock2);
			}
		}
		
	}
	
	
}
