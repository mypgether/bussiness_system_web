package net.bussiness.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.model.DeptDto;
import net.bussiness.model.UserDto;
import net.bussiness.service.DeptService;
import net.bussiness.service.UserService;
import net.bussiness.util.StringUtils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class DeptController {
	private DeptService deptService;
	private UserService userService;

	public DeptService getDeptService() {
		return deptService;
	}

	@Resource
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/depts", method = RequestMethod.GET)
	public String list() {
		return "dept/depts";
	}

	@RequestMapping(value = "/findDeptWithPage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findDeptWithPage(HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		List<DeptDto> list = (List<DeptDto>) deptService.findWithPage(page,
				rows);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("total", deptService.getRows());
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

	@RequestMapping(value = "/{createrId}/findDeptCreater", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public UserDto findDeptCreater(@PathVariable String createrId) {
		UserDto user = (UserDto) userService.load(Integer.parseInt(createrId));
		return user;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(DeptDto dept) {
		deptService.delete(dept);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(DeptDto dept) {
		System.out.println(dept.toString());
		if (dept.getId() == 0) {
			deptService.add(dept);
		} else {
			deptService.update(dept);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new DeptDto());
		return "dept/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(DeptDto dept) {
		deptService.add(dept);
	}
}
