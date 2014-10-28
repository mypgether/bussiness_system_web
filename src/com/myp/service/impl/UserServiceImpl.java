package com.myp.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myp.dao.BaseDao;
import com.myp.model.UserDao;
import com.myp.service.UserService;
import com.myp.util.StringUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Resource
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void delete(UserDao user) {
		baseDao.deleteObject(user);
	}

	@Override
	public void add(UserDao user) {
		baseDao.addObject(user);
	}

	@Override
	public void update(UserDao user) {
		baseDao.updateObject(user);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from UserDao");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(getHql(new StringBuffer(
				"select count(*) from UserDao"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDao> findWithPage(int page, int rows) {
		return (List<UserDao>) baseDao.findWithPage(page, rows, "from UserDao");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<UserDao>) baseDao.findWithPage(page, rows,
				getHql(new StringBuffer("from UserDao where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDao> findAll() {
		return (List<UserDao>) baseDao.findAll("from UserDao");
	}

	@Override
	public UserDao load(int id) {
		return (UserDao) baseDao.getObject("from UserDao where userId=" + id);
	}

	@SuppressWarnings("rawtypes")
	private String getHql(StringBuffer sb, Map<String, String> params) {
		for (Iterator iterator = params.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			if (!StringUtils.isBlank(val)) {
				sb.append(" and " + key + " like '%" + val.trim() + "%'");
			}
		}
		return sb.toString();
	}
}
