package com.myp.util;

import java.util.List;
import java.util.Map;

public interface BaseService {
	List<?> findAll();

	List<?> findWithPage(int page, int rows);

	List<?> findWithPageAndCondition(Map<Object, Object> params, int page, int rows);

	int getRows();

	int getRowsWithCondition(Map<Object, Object> params);

	void delete(Object dept);

	void add(Object dept);

	void update(Object dept);
}
