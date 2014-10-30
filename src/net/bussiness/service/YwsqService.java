package net.bussiness.service;

import java.util.List;
import java.util.Map;

import net.bussiness.model.YwsqDao;

public interface YwsqService {
	List<YwsqDao> findAll();

	List<YwsqDao> findWithPage(int page, int rows);

	List<YwsqDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows);

	int getRows();

	int getRowsWithCondition(Map<String, String> params);

	void delete(YwsqDao dao);

	void add(YwsqDao dao);

	void update(YwsqDao dao);

	YwsqDao load(int id);
}
