package net.bussiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.bdpush.AndroidPushMessage;
import net.bussiness.model.UserDto;
import net.bussiness.model.YwpjDto;
import net.bussiness.service.YwnrPjService;
import net.bussiness.util.Constants;
import net.bussiness.util.JacksonUtils;
import net.bussiness.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ywnrPj")
public class YwnrPjController {
	private YwnrPjService ywnrPjService;

	public YwnrPjService getYwnrPjService() {
		return ywnrPjService;
	}

	@Resource
	public void setYwnrPjService(YwnrPjService ywnrPjService) {
		this.ywnrPjService = ywnrPjService;
	}

	@RequestMapping(value = "/ywnrPj", method = RequestMethod.GET)
	public String list() {
		return "ywnrPj/ywnrPjs";
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new YwpjDto());
		return "ywnrPj/add";
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(@PathVariable String userId, String ywpjDto) {
		YwpjDto dto = (YwpjDto) JacksonUtils.json2Bean(ywpjDto, YwpjDto.class);
		UserDto approver = dto.getYwnr().getYwsq().getUserByApproverId();
		UserDto proposer = dto.getYwnr().getYwsq().getUserByProposerId();
		if (approver.getUserId().equals(userId)) {
			AndroidPushMessage.pushMessage(proposer,
					Constants.BD_PUSHTYPE_YWPJ, dto);
		} else {
			AndroidPushMessage.pushMessage(approver,
					Constants.BD_PUSHTYPE_YWPJ, dto);
		}
		ywnrPjService.add(dto);
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(String ywpjDto) {
		YwpjDto dto = (YwpjDto) JacksonUtils.json2Bean(ywpjDto, YwpjDto.class);
		ywnrPjService.delete(dto);
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(String ywpjDto) {
		YwpjDto dto = (YwpjDto) JacksonUtils.json2Bean(ywpjDto, YwpjDto.class);
		if (dto.getId() == 0) {
			ywnrPjService.add(dto);
		} else {
			ywnrPjService.update(dto);
		}
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/findYwnrPjWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findYwnrPjsWithPC(@PathVariable String ywnrId,
			HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		params.put("ywnrId", ywnrId);
		List<YwpjDto> list = (List<YwpjDto>) ywnrPjService
				.findWithPageAndCondition(params, page, rows);
		int totol = ywnrPjService.getRowsWithCondition(params);
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
	}
}
