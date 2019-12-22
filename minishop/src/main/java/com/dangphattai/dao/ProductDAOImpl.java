package com.dangphattai.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dangphattai.entity.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getProducts() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Product> theQuery = currentSession.createQuery("from Product", Product.class);
		List<Product> products = theQuery.getResultList();
		
		return products;
	}

	@Override
	public List<Product> getProductsLimit(int indexStart) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Product> theQuery = currentSession.createQuery("from Product", Product.class);
		List<Product> products = theQuery.setFirstResult(indexStart).setMaxResults(8).getResultList();
		
		return products;
	}

	@Override
	public Product getProduct(int productId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Product product = currentSession.get(Product.class, productId);
		
		return product;
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Product> theQuery = currentSession.createQuery("from Product where category.categoryId=:categoryId", 
				Product.class);
		theQuery.setParameter("categoryId", categoryId);
		List<Product> products = theQuery.getResultList();
		
		return products;
	}

	@Override
	public void deleteProduct(int productId) {
		Session currentSession = sessionFactory.getCurrentSession();
	
		Query theQuery = 
				currentSession.createQuery("delete from ProductDetail where product.id=:productId");
		theQuery.setParameter("productId", productId);
		theQuery.executeUpdate();
		
		Query theQuery2 = 
				currentSession.createQuery("delete from Product where id=:productId");
		theQuery2.setParameter("productId", productId);
		theQuery2.executeUpdate();
		
	}

	@Override
	public Product saveProduct(Product product) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(product);
		
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(product);
		
		return product;
	}

	@Override
	public List<Product> searchProducts(String searchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Product> theQuery = null;
			
		if (searchName != null && searchName.trim().length() > 0) {

			theQuery = currentSession.createQuery("from Product where name like :theName" , Product.class);
			theQuery.setParameter("theName", "%" + searchName.trim() + "%");

		} else {
			theQuery =currentSession.createQuery("from Product", Product.class);			
		}
				
		List<Product> products = theQuery.getResultList();		
		
		return products;
	}
}
