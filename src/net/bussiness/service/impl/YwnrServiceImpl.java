package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.YwnrDto;
import net.bussiness.service.YwnrService;
import net.bussiness.util.HqlUtils;

import org.springframework.stereotype.Service;

@Service("ywnrService")
public class YwnrServiceImpl implements YwnrService {

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
		Object rows = baseDao.getObject("select count(*) from YwnrDto");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwnrDto> findWithPage(int page, int rows) {
		return (List<YwnrDto>) baseDao.findWithPage(page, rows, "from YwnrDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwnrDto> findAll() {
		return (List<YwnrDto>) baseDao.findAll("from YwnrDto");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
				"select count(*) from YwnrDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public List<YwnrDto> findWithCondition(Map<String, String> params) {
		return (List<YwnrDto>) baseDao.findWithCondition(HqlUtils.getHql(
				new StringBuffer("from YwnrDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwnrDto> findWithPageAndCondition(Map<String, String> params,
			int page, int rows) {
		return (List<YwnrDto>) baseDao.findWithPage(
				page,
				rows,
				HqlUtils.getHql(new StringBuffer("from YwnrDto where 1=1"),
						params) + " order by nrTime asc");
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
	public YwnrDto load(int id) {
		return (YwnrDto) baseDao.getObject("from YwnrDto where ywId=" + id);
	}

	@Override
	public List<YwnrDto> findWithPageAndHql(String hql, int page, int rows) {
		return (List<YwnrDto>) baseDao.findWithPageAndHql(page, rows, hql);
	}

	@Override
	public int getRowsWithHql(String hql) {
		Object rows = baseDao.getObject(hql);
		long result = (Long) rows;
		return (int) result;
	}
}
