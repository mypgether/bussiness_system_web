package net.bussiness.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.model.DeptDao;
import net.bussiness.service.DeptService;
import net.bussiness.util.StringUtils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class DeptController {
	private DeptService deptService;

	public DeptService getDeptService() {
		return deptService;
	}

	@Resource
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
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
		List<DeptDao> list = deptService.findWithPage(page, rows);
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

	@RequestMapping(value = "/findDeptsName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findDeptsName() {
		List<DeptDao> list = deptService.findAll();
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (DeptDao dept : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("lable", dept.getDeptName());
			map.put("value", dept.getId());
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
	public void delete(DeptDao dept) {
		deptService.delete(dept);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(DeptDao dept) {
		System.out.println(dept.toString());
		if (dept.getId() == 0) {
			deptService.add(dept);
		} else {
			deptService.update(dept);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new DeptDao());
		return "dept/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(DeptDao dept) {
		deptService.add(dept);
		return "dept/depts";
	}
}
