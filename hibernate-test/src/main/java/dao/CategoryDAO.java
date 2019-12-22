package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Category114;

public class CategoryDAO {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public void insert(Category114 category114) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(category114);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}
	
	public List<Category114> getData() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Category114> category114s = session.createQuery("from Category114").list();
		
		session.flush();
		session.close();
		
		return category114s;
		
	}
	
	public Category114 getCategoryById(Long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Category114 category114 = session.get(Category114.class, id);
		
		session.flush();
		session.close();
		
		return category114;
	}
	
	public void update(Category114 category114) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			session.update(category114);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.flush();
			session.close();
		}
		
	}
	
	public void deleteCategory(Long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Category114 category114 = session.get(Category114.class, id);
		session.delete(category114);
		
		session.flush();
		session.close();
	}
}
