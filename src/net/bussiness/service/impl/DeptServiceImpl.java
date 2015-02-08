package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.DeptDto;
import net.bussiness.service.DeptService;
import net.bussiness.util.HqlUtils;

import org.springframework.stereotype.Service;

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
		Object rows = baseDao.getObject("select count(*) from DeptDto");
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
				"select count(*) from DeptDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDto> findWithPage(int page, int rows) {
		return (List<DeptDto>) baseDao.findWithPage(page, rows, "from DeptDto");
	}

	@Override
	public List<DeptDto> findWithCondition(Map<String, String> params) {
		return (List<DeptDto>) baseDao.findWithCondition(HqlUtils.getHql(
				new StringBuffer("from DeptDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDto> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<DeptDto>) baseDao.findWithPage(page, rows, HqlUtils
				.getHql(new StringBuffer("from DeptDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptDto> findAll() {
		return (List<DeptDto>) baseDao.findAll("from DeptDto");
	}

	@Override
	public DeptDto load(int id) {
		return (DeptDto) baseDao.getObject("from DeptDto where id=" + id);
	}

	@Override
	public List<DeptDto> findWithPageAndHql(String hql, int page, int rows) {
		return (List<DeptDto>) baseDao.findWithPageAndHql(page, rows, hql);
	}

	@Override
	public int getRowsWithHql(String hql) {
		Object rows = baseDao.getObject(hql);
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public void delete(String hql) {
		baseDao.delete(hql);
	}
}
