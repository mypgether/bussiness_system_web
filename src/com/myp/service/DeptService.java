package com.myp.service;

import java.util.List;
import java.util.Map;

import com.myp.model.DeptDao;

public interface DeptService {
	List<DeptDao> findAll();

	List<DeptDao> findWithPage(int page, int rows);

	List<DeptDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows);

	int getRows();

	int getRowsWithCondition(Map<String, String> params);

	void delete(DeptDao dao);

	void add(DeptDao dao);

	void update(DeptDao dao);

	DeptDao load(int id);
}
