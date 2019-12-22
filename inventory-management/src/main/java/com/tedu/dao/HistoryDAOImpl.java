package com.tedu.dao;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.entity.History;
import com.tedu.entity.Paging;

@Repository
@Transactional(rollbackFor=Exception.class)
public class HistoryDAOImpl extends BaseDAOImpl<History> implements HistoryDAO<History> {

	private static final Logger log = Logger.getLogger(HistoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<History> searchHistories(String property, int type, Paging paging) {
		log.info("seach all record from database by history");
		
		StringBuilder queryString = new StringBuilder();
		StringBuilder countQuery = new StringBuilder();
		queryString.append("from ").append(getGenericName()).append(" as model where model.activeFlag = 1 and model.").append(property).append("=:type");
		countQuery.append("select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1 and model.").append(property).append("=:type");
		Query<History> query  = null;
		Query count  = null;
		
		if (type != 0) {

			query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
			query.setParameter("type", type);
			
			count = sessionFactory.getCurrentSession().createQuery(countQuery.toString());
			count.setParameter("type", type);
			
			if(paging != null) {
				query.setFirstResult(paging.getOffset()).setMaxResults(paging.getRecordPerPage());
				
				long totalRecords = (long) count.getSingleResult();
				paging.setTotalRows(totalRecords);
			}

		} else {
			StringBuilder queryString2 = new StringBuilder();
			StringBuilder countQuery2 = new StringBuilder();
			queryString2.append("from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
			countQuery2.append("select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1");
			query = sessionFactory.getCurrentSession().createQuery(queryString2.toString());	
			count = sessionFactory.getCurrentSession().createQuery(countQuery2.toString());
			if(paging != null) {
				query.setFirstResult(paging.getOffset()).setMaxResults(paging.getRecordPerPage());
				long totalRecords = (long) count.getSingleResult();
				paging.setTotalRows(totalRecords);
			}
		} 
							
		log.info( "Query find all by history ====>" +queryString.toString());
		
		return query.getResultList();
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
