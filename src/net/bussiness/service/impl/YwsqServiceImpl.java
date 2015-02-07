package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.YwsqDto;
import net.bussiness.service.YwsqService;
import net.bussiness.util.HqlUtils;

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
	public void delete(String hql) {
		baseDao.delete(hql);
	}

	@Override
	public int getRows() {
		Object rows = baseDao.getObject("select count(*) from YwsqDto");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDto> findWithPage(int page, int rows) {
		return (List<YwsqDto>) baseDao.findWithPage(page, rows, "from YwsqDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDto> findAll() {
		return (List<YwsqDto>) baseDao.findAll("from YwsqDto");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
				"select count(*) from YwsqDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public List<YwsqDto> findWithCondition(Map<String, String> params) {
		return (List<YwsqDto>) baseDao.findWithCondition(HqlUtils.getHql(
				new StringBuffer("from YwsqDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwsqDto> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<YwsqDto>) baseDao.findWithPage(page, rows, HqlUtils
				.getHql(new StringBuffer("from YwsqDto where 1=1"), params));
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
	public YwsqDto load(int id) {
		return (YwsqDto) baseDao.getObject("from YwsqDto where ywId=" + id);
	}

	@Override
	public List<YwsqDto> findWithPageAndHql(String hql, int page, int rows) {
		return (List<YwsqDto>) baseDao.findWithPageAndHql(page, rows, hql);
	}

	@Override
	public int getRowsWithHql(String hql) {
		Object rows = baseDao.getObject(hql);
		long result = (Long) rows;
		return (int) result;
	}
}
