package net.bussiness.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.bussiness.model.UserDto;
import net.bussiness.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "base/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public UserDto login(UserDto user, HttpSession session) {
		UserDto iUser = (UserDto) userService.load(user.getUserId());
		/*
		 * 用户名和密码匹配，登录成功
		 */
		if (iUser != null && iUser.getPassword().equals(user.getPassword())) {
			/*
			 * 保存用户Id到session当中
			 */
			session.setAttribute("currentUser", iUser.getUserId());
			return iUser;
		}
		return null;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String goIndex() {
		return "base/index";
	}
}
