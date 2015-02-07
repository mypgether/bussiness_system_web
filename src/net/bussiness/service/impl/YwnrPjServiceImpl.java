package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.YwnrDto;
import net.bussiness.model.YwnrPhotosDto;
import net.bussiness.model.YwpjDto;
import net.bussiness.service.YwnrPjService;
import net.bussiness.util.HqlUtils;

import org.springframework.stereotype.Service;

@Service("ywnrPjService")
public class YwnrPjServiceImpl implements YwnrPjService {

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
		Object rows = baseDao.getObject("select count(*) from YwpjDto");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwpjDto> findWithPage(int page, int rows) {
		return (List<YwpjDto>) baseDao.findWithPage(page, rows, "from YwpjDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwpjDto> findAll() {
		return (List<YwpjDto>) baseDao.findAll("from YwpjDto");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
				"select count(*) from YwpjDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public List<YwpjDto> findWithCondition(Map<String, String> params) {
		return (List<YwpjDto>) baseDao.findWithCondition(HqlUtils.getHql(
				new StringBuffer("from YwpjDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwpjDto> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<YwpjDto>) baseDao.findWithPage(page, rows, HqlUtils
				.getHql(new StringBuffer("from YwpjDto where 1=1"), params));
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
	public YwpjDto load(int id) {
		return (YwpjDto) baseDao.getObject("from YwpjDto where ywId=" + id);
	}

	@Override
	public List<YwpjDto> findWithPageAndHql(String hql, int page, int rows) {
		return (List<YwpjDto>) baseDao.findWithPageAndHql(page, rows, hql);
	}

	@Override
	public int getRowsWithHql(String hql) {
		Object rows = baseDao.getObject(hql);
		long result = (Long) rows;
		return (int) result;
	}
}
