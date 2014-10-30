package net.bussiness.dao;

import java.util.List;

public interface BaseDao {
	void addObject(Object obj);

	void deleteObject(Object obj);

	void updateObject(Object obj);

	Object getObject(String hql);

	List<?> findAll(String hql);

	List<?> findWithPage(int page, int rows, String hql);
}
