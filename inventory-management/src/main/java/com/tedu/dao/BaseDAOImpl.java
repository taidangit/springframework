package com.tedu.dao;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.entity.Paging;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<E> implements BaseDAO<E> {

	private static final Logger log = Logger.getLogger(BaseDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<E> findAll(Paging paging) {
		log.info("find all record from database");
		
		StringBuilder queryString = new StringBuilder();
		StringBuilder countQuery = new StringBuilder();
		countQuery.append("select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
		queryString.append("from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
		Query<E> query  = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		Query<E> count  = sessionFactory.getCurrentSession().createQuery(countQuery.toString());
		
		if(paging != null) {
			query.setFirstResult(paging.getOffset()).setMaxResults(paging.getRecordPerPage());
			long totalRecords = (long) count.getSingleResult();
			paging.setTotalRows(totalRecords);
		}
		
		log.info( "Query find all ====>" +queryString.toString());
		
		return query.getResultList();
	}

	@Override
	public E findById(Class<E> e, Serializable id) {
		log.info("find by id");
		
		return sessionFactory.getCurrentSession().get(e, id);
	}

	@Override
	public List<E> findByProperty(String property, Object value) {
		log.info("Find by property");
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from ").append(getGenericName()).append(" as model where model.activeFlag = 1 and model.").append(property).append("=:value");
		log.info("query find by property ===>"+queryString.toString());
		
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		query.setParameter("value", value);
		
		return query.getResultList();
	}

	@Override
	public void save(E instance) {
		log.info(" save instance");
		sessionFactory.getCurrentSession().save(instance);
	}

	@Override
	public void update(E instance) {
		log.info("update instance");
		sessionFactory.getCurrentSession().update(instance);
	}
	
	
	@Override
	public void delete(String property, int id) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("delete from ").append(getGenericName()).append(" as model where model.").append(property).append("=:id");
		Query query = 
				sessionFactory.getCurrentSession().createQuery(queryString.toString());
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	public String getGenericName() {
		String s = getClass().getGenericSuperclass().toString();
		Pattern pattern = Pattern.compile("\\<(.*?)\\>");
		Matcher matcher = pattern.matcher(s);
		String generic = "";
		if(matcher.find()) {
			generic = matcher.group(1);
		}
		return generic;
	}


}
