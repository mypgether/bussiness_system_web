package com.myp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myp.dao.BaseDao;
import com.myp.model.DeptDao;
import com.myp.service.DeptService;
import com.myp.util.HqlUtil;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Resource
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void delete(DeptDao dao) {
		baseDao.deleteObject(dao);
	}

	@Override
	public void add(DeptDao dao) {
		baseDao.addObject(dao);
	}

	@Override
	public void update(DeptDao dao) {
		baseDao.updateObject(dao);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from DeptDao");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtil.getHql(new StringBuffer(
				"select count(*) from DeptDao where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDao> findWithPage(int page, int rows) {
		return (List<DeptDao>) baseDao.findWithPage(page, rows, "from DeptDao");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<DeptDao>) baseDao.findWithPage(page, rows, HqlUtil.getHql(
				new StringBuffer("from DeptDao where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDao> findAll() {
		return (List<DeptDao>) baseDao.findAll("from DeptDao");
	}

	@Override
	public DeptDao load(int id) {
		return (DeptDao) baseDao.getObject("from DeptDao where id=" + id);
	}
}
