package com.tedu.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.entity.Auth;

@Repository
@Transactional(rollbackFor = Exception.class)
public class AuthDAOImpl extends BaseDAOImpl<Auth> implements AuthDAO<Auth> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Auth> findAuth(int roleId, int menuId) {
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from ").append(getGenericName()).
			append(" as model where model.role.roleId =:roleId and model.menu.menuId=:menuId");
		Query<Auth> query  = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		query.setParameter("roleId", roleId);
		query.setParameter("menuId", menuId);
		
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
