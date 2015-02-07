package net.bussiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.bdpush.AndroidPushMessage;
import net.bussiness.model.YwnrDto;
import net.bussiness.service.YwnrService;
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
@RequestMapping("/ywnr")
public class YwnrController {
	private YwnrService ywnrService;

	public YwnrService getYwnrService() {
		return ywnrService;
	}

	@Resource
	public void setYwnrService(YwnrService ywnrService) {
		this.ywnrService = ywnrService;
	}

	@RequestMapping(value = "/ywnrs", method = RequestMethod.GET)
	public String list() {
		return "ywnr/ywnrs";
	}

	@RequestMapping(value = "/{userId}/{ywId}/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new YwnrDto());
		return "ywnr/add";
	}

	@RequestMapping(value = "/{userId}/{ywId}/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(String ywnrDto) {
		YwnrDto dto = (YwnrDto) JacksonUtils.json2Bean(ywnrDto, YwnrDto.class);
		ywnrService.add(dto);
		AndroidPushMessage.pushMessage(dto.getYwsq().getUserByApproverId(),
				Constants.BD_PUSHTYPE_YWNR, dto);
		return dto.getId() + "";
	}

	@RequestMapping(value = "/{userId}/{ywId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(String ywnrDto) {
		YwnrDto dto = (YwnrDto) JacksonUtils.json2Bean(ywnrDto, YwnrDto.class);
		ywnrService.delete(dto);
	}

	@RequestMapping(value = "/{userId}/{ywId}/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(String ywnrDto) {
		YwnrDto dto = (YwnrDto) JacksonUtils.json2Bean(ywnrDto, YwnrDto.class);
		if (dto.getId() == 0) {
			ywnrService.add(dto);
		} else {
			ywnrService.update(dto);
		}
	}

	@RequestMapping(value = "/{userId}/{ywId}/findYwnrsWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findYwnrsWithPC(@PathVariable String ywId,
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
		params.put("ywId", ywId);
		List<YwnrDto> list = (List<YwnrDto>) ywnrService
				.findWithPageAndCondition(params, page, rows);
		int totol = ywnrService.getRowsWithCondition(params);
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
	}
}
