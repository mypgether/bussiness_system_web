package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.YwsqDao;
import net.bussiness.service.YwsqService;
import net.bussiness.util.HqlUtil;

import org.springframework.stereotype.Service;

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
		Object rows = baseDao.getObject(HqlUtil.getHql(new StringBuffer(
				"select count(*) from YwsqDao where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDao> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<YwsqDao>) baseDao.findWithPage(page, rows, HqlUtil.getHql(
				new StringBuffer("from YwsqDao where 1=1"), params));
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

	@Override
	public YwsqDao load(int id) {
		return (YwsqDao) baseDao.getObject("from YwsqDao where ywId=" + id);
	}
}
