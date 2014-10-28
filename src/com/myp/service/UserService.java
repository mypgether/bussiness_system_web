package com.myp.service;

import java.util.List;
import java.util.Map;

import com.myp.model.UserDao;

public interface UserService {
	List<UserDao> findAll();

	List<UserDao> findWithPage(int page, int rows);

	List<UserDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows);

	int getRows();

	int getRowsWithCondition(Map<String, String> params);

	void delete(UserDao user);

	void add(UserDao user);

	void update(UserDao user);

	UserDao load(int id);
}
