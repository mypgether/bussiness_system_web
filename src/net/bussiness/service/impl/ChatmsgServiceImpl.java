package net.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.bussiness.dao.BaseDao;
import net.bussiness.model.ChatmsgDto;
import net.bussiness.service.ChatmsgService;

import org.springframework.stereotype.Service;

@Service("chatmsgService")
public class ChatmsgServiceImpl implements ChatmsgService {

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
		Object rows = baseDao.getObject("select count(*) from ChatmsgDto");
		long result = (Long) rows;
		return (int) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatmsgDto> findWithPage(int page, int rows) {
		return (List<ChatmsgDto>) baseDao.findWithPage(page, rows,
				"from ChatmsgDto");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatmsgDto> findAll() {
		return (List<ChatmsgDto>) baseDao.findAll("from ChatmsgDto");
	}

	@Override
	public int getRowsWithCondition(Map<String, String> params) {
		Object rows = baseDao.getObject(getHql(new StringBuffer(
				"select count(*) from ChatmsgDto where 1=1"), params));
		long result = (Long) rows;
		return (int) result;
	}

	@Override
	public List<ChatmsgDto> findWithCondition(Map<String, String> params) {
		return (List<ChatmsgDto>) baseDao.findWithCondition(getHql(
				new StringBuffer("from ChatmsgDto where 1=1"), params));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatmsgDto> findWithPageAndCondition(
			Map<String, String> params, int page, int rows) {
		String hql = getHql(new StringBuffer("from ChatmsgDto where 1=1"),
				params);
		return (List<ChatmsgDto>) baseDao.findWithPage(page, rows, hql);
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
	public ChatmsgDto load(int id) {
		return (ChatmsgDto) baseDao.getObject("from ChatmsgDto where msgId="
				+ id);
	}

	@Override
	public List<ChatmsgDto> findWithPageAndHql(String hql, int page, int rows) {
		return (List<ChatmsgDto>) baseDao.findWithPageAndHql(page, rows, hql);
	}

	@Override
	public int getRowsWithHql(String hql) {
		Object rows = baseDao.getObject(hql);
		long result = (Long) rows;
		return (int) result;
	}

	private String getHql(StringBuffer sb, Map<String, String> params) {
		String receiverId = params.get("receiverId");
		String senderId = params.get("senderId");
		sb.append(" and (receiverId =" + receiverId + " and senderId ="
				+ senderId + ") or (receiverId =" + senderId
				+ " and senderId =" + receiverId + ") order by msgTime desc");
		// sb.append(" and (receiverId like '%" + receiverId
		// + "%' and senderId like '%" + senderId
		// + "%') or (receiverId like '%" + senderId
		// + "%' and senderId like '%" + receiverId
		// + "%') order by msgTime desc");
		return sb.toString();
	}

	@Override
	public void delete(String hql) {
		baseDao.delete(hql);
	}
}
