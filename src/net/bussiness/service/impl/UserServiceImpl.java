package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.UserDto;
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
	public void delete(Object dto) {
		baseDao.deleteObject(dto);
	}

	@Override
	public void add(Object dto) {
		baseDao.addObject(dto);
	}

	@Override
	public void update(Object dto) {
		baseDao.updateObject(dto);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from UserDto");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
				"select count(*) from UserDto"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> findWithPage(int page, int rows) {
		return (List<UserDto>) baseDao.findWithPage(page, rows, "from UserDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<UserDto>) baseDao.findWithPage(page, rows, HqlUtils
				.getHql(new StringBuffer("from UserDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> findAll() {
		return (List<UserDto>) baseDao.findAll("from UserDto");
	}

	@Override
	public UserDto load(int id) {
		return (UserDto) baseDao.getObject("from UserDto where userId=" + id);
	}
}
