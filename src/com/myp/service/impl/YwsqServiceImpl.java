package com.myp.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myp.dao.BaseDao;
import com.myp.model.YwsqDao;
import com.myp.service.YwsqService;
import com.myp.util.StringUtils;

@Service("ywsqService")
public class YwsqServiceImpl implements YwsqService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Resource
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from YwsqDao");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDao> findWithPage(int page, int rows) {
		return (List<YwsqDao>) baseDao.findWithPage(page, rows, "from YwsqDao");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDao> findAll() {
		return (List<YwsqDao>) baseDao.findAll("from YwsqDao");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(getHql(new StringBuffer(
				"select count(*) from YwsqDao where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<YwsqDao>) baseDao.findWithPage(page, rows,
				getHql(new StringBuffer("from YwsqDao where 1=1"), params));
	}

	@Override
	public void delete(YwsqDao dao) {
		baseDao.deleteObject(dao);
	}

	@Override
	public void add(YwsqDao dao) {
		baseDao.addObject(dao);
	}

	@Override
	public void update(YwsqDao dao) {
		baseDao.updateObject(dao);
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
