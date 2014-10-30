package net.bussiness.controller;

import javax.annotation.Resource;

import net.bussiness.model.UserDao;
import net.bussiness.model.YwsqDao;
import net.bussiness.service.UserService;
import net.bussiness.service.YwsqService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ywsq")
public class YwsqController {
	private YwsqService ywsqService;
	private UserService userService;

	public YwsqService getYwsqService() {
		return ywsqService;
	}

	@Resource
	public void setYwsqService(YwsqService ywsqService) {
		this.ywsqService = ywsqService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list() {
		return "user/users";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(String name) {
		UserDao userDao = userService.load(116040179);
		System.out.println(userDao.getYwsqsForProposerId());
		for (YwsqDao dao : userDao.getYwsqsForProposerId()) {
			dao.setLocation("上海");
		}
		UserDao userDao1 = userService.load(116040179);
		System.out.println(userDao.getYwsqsForProposerId());
		System.out.println(userDao1.getYwsqsForProposerId());
		// YwsqDao dao = new YwsqDao();
		// dao.setLocation("杭州");
		// dao.setTimestamp(new Date());
		// dao.setReason("出差");
		// dao.setApplyTime(new Date());
		// dao.setApproveState(0);
		// dao.setApproveReason(null);
		// dao.setUserByProposerId(userDao);
		// userDao.getYwsqsForProposerId().add(dao);
		// System.out.println(dao.toString());
		// System.out.println(userDao.toString());
		// userService.update(userDao);
		// ywsqService.add(dao);
	}

	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// public void add() {
	// YwsqDao dao = new YwsqDao();
	// UserDao user1 = userService.load(116040182);
	// UserDao user2 = userService.load(116040179);
	// System.out.println(user1.toString());
	// System.out.println(user2.toString());
	// dao.setUserByApproverId(user1);
	// dao.setUserByProposerId(user2);
	// dao.setLocation("杭州");
	// dao.setTimestamp(new Date());
	// dao.setReason("出差");
	// dao.setApplyTime(new Date());
	// dao.setApproveState(0);
	// dao.setApproveReason("");
	// System.out.println(dao.toString());
	// ywsqService.add(dao);
	// }
}
