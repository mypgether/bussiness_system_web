package net.bussiness.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
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
	public Object getObject(final String hql) {
		// Object result = this.getHibernateTemplate().find(hql).get(0);
		// return this.getSession().createQuery(hql).uniqueResult();
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					@Override
					public Object doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						Query query = arg0.createQuery(hql);
						return query.uniqueResult();
					}
				});
	}

	@Override
	public List<?> findAll(String hql) {
		List<?> list = this.getHibernateTemplate().find(hql);
		// this.getSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<?> findWithCondition(final String hql) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
	}

	@Override
	public List<?> findWithPage(final int page, final int rows, final String hql) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult((page - 1) * rows);
				query.setMaxResults(rows);
				return query.list();
			}
		});
		// List<?> list = this.getSession().createQuery(hql)
		// .setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		// return list;
	}

	@Override
	public List<?> findWithPageAndHql(final int page, final int rows,
			final String hql) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				System.out.println(hql);
				Query query = session.createQuery(hql);
				query.setFirstResult((page - 1) * rows);
				query.setMaxResults(rows);
				return query.list();
			}
		});
	}

	@Override
	public void delete(final String hql) {
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
