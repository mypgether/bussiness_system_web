package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.UserDao;
import net.bussiness.service.UserService;
import net.bussiness.util.HqlUtils;

import org.springframework.stereotype.Service;

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
	public void delete(UserDao dao) {
		baseDao.deleteObject(dao);
	}

	@Override
	public void add(UserDao dao) {
		baseDao.addObject(dao);
	}

	@Override
	public void update(UserDao dao) {
		baseDao.updateObject(dao);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from UserDao");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
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
		return (List<UserDao>) baseDao.findWithPage(page, rows, HqlUtils.getHql(
				new StringBuffer("from UserDao where 1=1"), params));
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
}
