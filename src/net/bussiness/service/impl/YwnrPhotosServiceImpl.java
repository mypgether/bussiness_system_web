package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.YwnrPhotosDto;
import net.bussiness.service.YwnrPhotosService;
import net.bussiness.util.HqlUtils;

import org.springframework.stereotype.Service;

@Service("ywnrPhotosService")
public class YwnrPhotosServiceImpl implements YwnrPhotosService {

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
		Object rows = baseDao.getObject("select count(*) from YwnrPhotosDto");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwnrPhotosDto> findWithPage(int page, int rows) {
		return (List<YwnrPhotosDto>) baseDao.findWithPage(page, rows,
				"from YwnrPhotosDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwnrPhotosDto> findAll() {
		return (List<YwnrPhotosDto>) baseDao.findAll("from YwnrPhotosDto");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(HqlUtils.getHql(new StringBuffer(
				"select count(*) from YwnrPhotosDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YwnrPhotosDto> findWithPageAndCondition(
			Map<String, String> params, int page, int rows) {
		return (List<YwnrPhotosDto>) baseDao.findWithPage(page, rows, HqlUtils
				.getHql(new StringBuffer("from YwnrPhotosDto where 1=1"),
						params));
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
	public YwnrPhotosDto load(int id) {
		return (YwnrPhotosDto) baseDao
				.getObject("from YwnrPhotosDto where ywId=" + id);
	}
}
