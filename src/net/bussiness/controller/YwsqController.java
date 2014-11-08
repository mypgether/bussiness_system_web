package net.bussiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.model.YwsqDto;
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
		model.addAttribute(new YwsqDto());
		return "ywsq/add";
	}

	@RequestMapping(value = "/{userId}/add", method = RequestMethod.POST)
	public String add(YwsqDto ywsqDao) {
		ywsqService.add(ywsqDao);
		return "ywsq/ywsqs";
	}

	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(YwsqDto ywsqDao) {
		ywsqService.delete(ywsqDao);
	}

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(YwsqDto ywsqDao) {
		if (ywsqDao.getYwId() == 0) {
			ywsqService.add(ywsqDao);
		} else {
			ywsqService.update(ywsqDao);
		}
	}

	@RequestMapping(value = "/findYwsqsWithPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
		List<YwsqDto> list = (List<YwsqDto>) ywsqService.findWithPage(page,
				rows);
		int totol = ywsqService.getRows();
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}

	@RequestMapping(value = "/{userId}/findApproveYwsqsWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findApproveYwsqsWithPC(@PathVariable String userId,
			HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		int approveState = 0;
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		if (!StringUtils.isBlank(request.getParameter("approveState"))) {
			approveState = Integer.parseInt(request
					.getParameter("approveState"));
			params.put("approveState", approveState + "");
		}
		params.put("approverId", userId);
		List<YwsqDto> list = (List<YwsqDto>) ywsqService
				.findWithPageAndCondition(params, page, rows);
		int totol = ywsqService.getRowsWithCondition(params);
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}

	@RequestMapping(value = "/{userId}/findProposeYwsqsWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findProposeYwsqsWithPC(@PathVariable String userId,
			HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		int approveState = 0;
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		if (!StringUtils.isBlank(request.getParameter("approveState"))) {
			approveState = Integer.parseInt(request
					.getParameter("approveState"));
			params.put("approveState", approveState + "");
		}
		params.put("proposerId", userId);
		List<YwsqDto> list = (List<YwsqDto>) ywsqService
				.findWithPageAndCondition(params, page, rows);
		int totol = ywsqService.getRowsWithCondition(params);
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}
}
