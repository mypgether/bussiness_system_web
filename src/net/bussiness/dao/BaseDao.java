package net.bussiness.dao;

import java.util.List;

public interface BaseDao {
	void addObject(Object obj);

	void deleteObject(Object obj);

	void delete(String hql);

	void updateObject(Object obj);

	Object getObject(String hql);

	List<?> findAll(String hql);

	List<?> findWithPage(int page, int rows, String hql);

	List<?> findWithCondition(String hql);

	List<?> findWithPageAndHql(int page, int rows, String hql);
}
