package net.bussiness.service;

import java.util.List;
import java.util.Map;

import net.bussiness.model.UserDao;

public interface UserService {
	List<UserDao> findAll();

	List<UserDao> findWithPage(int page, int rows);

	List<UserDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows);

	int getRows();

	int getRowsWithCondition(Map<String, String> params);

	void delete(UserDao dao);

	void add(UserDao dao);

	void update(UserDao dao);

	UserDao load(int id);
}
