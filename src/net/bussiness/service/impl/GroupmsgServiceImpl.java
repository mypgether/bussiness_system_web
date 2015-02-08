package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.GroupmsgDto;
import net.bussiness.service.GroupmsgService;

import org.springframework.stereotype.Service;

@Service("groupmsgService")
public class GroupmsgServiceImpl implements GroupmsgService {

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
		Object rows = baseDao.getObject("select count(*) from GroupmsgDto");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupmsgDto> findWithPage(int page, int rows) {
		return (List<GroupmsgDto>) baseDao.findWithPage(page, rows,
				"from GroupmsgDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupmsgDto> findAll() {
		return (List<GroupmsgDto>) baseDao.findAll("from GroupmsgDto");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(getHql(new StringBuffer(
				"select count(*) from GroupmsgDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public List<GroupmsgDto> findWithCondition(Map<String, String> params) {
		return (List<GroupmsgDto>) baseDao.findWithCondition(getHql(
				new StringBuffer("from GroupmsgDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupmsgDto> findWithPageAndCondition(
			Map<String, String> params, int page, int rows) {
		String hql = getHql(new StringBuffer("from GroupmsgDto where 1=1"),
				params);
		return (List<GroupmsgDto>) baseDao.findWithPage(page, rows, hql);
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
	public GroupmsgDto load(int id) {
		return (GroupmsgDto) baseDao.getObject("from GroupmsgDto where msgId="
				+ id);
	}

	@Override
	public List<GroupmsgDto> findWithPageAndHql(String hql, int page, int rows) {
		return (List<GroupmsgDto>) baseDao.findWithPageAndHql(page, rows, hql);
	}

	@Override
	public int getRowsWithHql(String hql) {
		Object rows = baseDao.getObject(hql);
		long result = (Long) rows;
		return (int) result;
	}

	private String getHql(StringBuffer sb, Map<String, String> params) {
		String group = params.get("group");
		sb.append(" and group =" + group + " order by msgTime desc");
		return sb.toString();
	}

	@Override
	public void delete(String hql) {
		baseDao.delete(hql);
	}
}
