package com.myp.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myp.dao.BaseDao;
import com.myp.model.DeptDao;
import com.myp.service.DeptService;
import com.myp.util.StringUtils;

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
	public void delete(DeptDao dept) {
		baseDao.deleteObject(dept);
	}

	@Override
	public void add(DeptDao dept) {
		baseDao.addObject(dept);
	}

	@Override
	public void update(DeptDao dept) {
		baseDao.updateObject(dept);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from DeptDao");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(String deptName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("deptName", deptName);
		Object rows = baseDao.getObject(getHql(new StringBuffer(
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
	public List<DeptDao> findWithPageAndCondition(String deptName, int page,
			int rows) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("deptName", deptName);
		return (List<DeptDao>) baseDao.findWithPage(page, rows,
				getHql(new StringBuffer("from DeptDao where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDao> findAll() {
		return (List<DeptDao>) baseDao.findAll("from DeptDao");
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
