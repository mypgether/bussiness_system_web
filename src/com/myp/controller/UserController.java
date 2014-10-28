package com.myp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myp.model.UserDao;
import com.myp.service.UserService;
import com.myp.util.StringUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;

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

	@RequestMapping(value = "/findUserWithPage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findUserWithPage(HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		List<UserDao> list = userService.findWithPage(page, rows);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", userService.getRows());
		map.put("rows", list);
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = "";
		try {
			jsonResult = mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	@RequestMapping(value = "/findUsersName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findUsersName() {
		List<UserDao> list = userService.findAll();
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (UserDao user : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("lable", user.getUserName());
			map.put("value", user.getUserId());
			result.add(map);
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = "";
		try {
			jsonResult = mapper.writeValueAsString(result);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(UserDao user) {
		userService.delete(user);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(UserDao user) {
		if (user.getId() == 0) {
			userService.add(user);
		} else {
			userService.update(user);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new UserDao());
		return "user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(UserDao user) {
		userService.add(user);
		return "user/users";
	}
}
