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

import com.tedu.entity.Paging;
import com.tedu.entity.Role;
import com.tedu.entity.User;

@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleDAOImpl extends BaseDAOImpl<Role> implements RoleDAO<Role> {

private static final Logger log = Logger.getLogger(CategoryDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public List<Role> searchRole(String property, String roleName, Paging paging) {
		log.info("seach all record from database by name");
		
		StringBuilder queryString = new StringBuilder();
		StringBuilder countQuery = new StringBuilder();
		queryString.append("from ").append(getGenericName()).append(" as model where model.activeFlag = 1 and model.").append(property).append("=:roleName");
		countQuery.append("select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1 and model.").append(property).append("=:roleName");
		Query<Role> query  = null;
		Query count  = null;
		
		if (roleName != null && roleName.trim().length() > 0) {

			query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
			query.setParameter("roleName", roleName.trim());
			
			count = sessionFactory.getCurrentSession().createQuery(countQuery.toString());
			count.setParameter("roleName", roleName.trim());
			
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
							
		log.info( "Query find all by role name ====>" +queryString.toString());
		
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
