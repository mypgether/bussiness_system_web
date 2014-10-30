package net.bussiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.model.YwsqDao;
import net.bussiness.service.YwsqService;
import net.bussiness.util.JsonStrUtils;
import net.bussiness.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ywsq")
public class YwsqController {
	private YwsqService ywsqService;

	public YwsqService getYwsqService() {
		return ywsqService;
	}

	@Resource
	public void setYwsqService(YwsqService ywsqService) {
		this.ywsqService = ywsqService;
	}

	@RequestMapping(value = "/ywsqs", method = RequestMethod.GET)
	public String list() {
		return "ywsq/ywsqs";
	}

	@RequestMapping(value = "/{userId}/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new YwsqDao());
		return "ywsq/add";
	}

	@RequestMapping(value = "/{userId}/add", method = RequestMethod.POST)
	public String add(YwsqDao ywsqDao) {
		ywsqService.add(ywsqDao);
		return "ywsq/ywsqs";
	}

	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(YwsqDao ywsqDao) {
		ywsqService.delete(ywsqDao);
	}

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(YwsqDao ywsqDao) {
		if (ywsqDao.getYwId() == 0) {
			ywsqService.add(ywsqDao);
		} else {
			ywsqService.update(ywsqDao);
		}
	}

	@RequestMapping(value = "/findYwsqsWithPage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findYwsqsWithPage(HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		List<YwsqDao> list = ywsqService.findWithPage(page, rows);
		int totol = ywsqService.getRows();
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}

	@RequestMapping(value = "/{userId}/findApproveYwsqsWithPA", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findApproveYwsqsWithPA(@PathVariable String userId,
			HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("approverId", userId);
		List<YwsqDao> list = ywsqService.findWithPageAndCondition(params, page,
				rows);
		int totol = ywsqService.getRowsWithCondition(params);
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}

	@RequestMapping(value = "/{userId}/findProposeYwsqsWithPA", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findProposeYwsqsWithPA(@PathVariable String userId,
			HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("proposerId", userId);
		List<YwsqDao> list = ywsqService.findWithPageAndCondition(params, page,
				rows);
		int totol = ywsqService.getRowsWithCondition(params);
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}
}
