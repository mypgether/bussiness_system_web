package com.myp.service;

import java.util.List;
import java.util.Map;

import com.myp.model.YwsqDao;

public interface YwsqService {
	List<YwsqDao> findAll();

	List<YwsqDao> findWithPage(int page, int rows);

	List<YwsqDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows);

	int getRows();

	int getRowsWithCondition(Map<String, String> params);

	void delete(YwsqDao dept);

	void add(YwsqDao dept);

	void update(YwsqDao dept);
}
