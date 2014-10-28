package com.myp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public void addObject(Object obj) {
		this.getHibernateTemplate().save(obj);
	}

	@Override
	public void deleteObject(Object obj) {
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public void updateObject(Object obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	public Object getObject(String hql) {
		Object result = this.getSession().createQuery(hql).uniqueResult();
		return result;
	}

	@Override
	public List<?> findAll(String hql) {
		List<?> list = this.getSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<?> findWithPage(int page, int rows, String hql) {
		List<?> list = this.getSession().createQuery(hql)
				.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		return list;
	}
}
