package net.bussiness.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
	List<?> findAll();

	List<?> findWithPageAndHql(String hql, int page, int rows);

	List<?> findWithPage(int page, int rows);

	List<?> findWithCondition(Map<String, String> params);

	List<?> findWithPageAndCondition(Map<String, String> params, int page,
			int rows);

	int getRows();

	int getRowsWithHql(String hql);

	int getRowsWithCondition(Map<String, String> params);

	void delete(String hql);

	void delete(Object dto);

	void add(Object dto);

	void update(Object dto);

	Object load(int id);
}
