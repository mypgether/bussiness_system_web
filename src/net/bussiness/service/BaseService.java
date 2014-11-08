package net.bussiness.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
	List<?> findAll();

	List<?> findWithPage(int page, int rows);

	List<?> findWithPageAndCondition(Map<String, String> params, int page,
			int rows);

	int getRows();

	int getRowsWithCondition(Map<String, String> params);

	void delete(Object dto);

	void add(Object dto);

	void update(Object dto);

	Object load(int id);
}
