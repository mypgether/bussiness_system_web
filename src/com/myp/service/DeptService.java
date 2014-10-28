package com.myp.service;

import java.util.List;

import com.myp.model.DeptDao;

public interface DeptService {
	List<DeptDao> findAll();

	List<DeptDao> findWithPage(int page, int rows);

	List<DeptDao> findWithPageAndCondition(String deptName, int page, int rows);

	int getRows();

	int getRowsWithCondition(String deptName);

	void delete(DeptDao dept);

	void add(DeptDao dept);

	void update(DeptDao dept);
}
