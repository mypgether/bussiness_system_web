package net.bussiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.model.YwnrPhotosDto;
import net.bussiness.service.YwnrPhotosService;
import net.bussiness.util.JsonStrUtils;
import net.bussiness.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ywnrPhotos")
public class YwnrPhotosController {
	private YwnrPhotosService ywnrPhotosService;

	public YwnrPhotosService getYwnrPhotosService() {
		return ywnrPhotosService;
	}

	@Resource
	public void setYwnrPhotosService(YwnrPhotosService ywnrPhotosService) {
		this.ywnrPhotosService = ywnrPhotosService;
	}

	@RequestMapping(value = "/ywnrPhotos", method = RequestMethod.GET)
	public String list() {
		return "ywnrPhotos/ywnrPhotos";
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new YwnrPhotosDto());
		return "ywnrPhotos/add";
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/add", method = RequestMethod.POST)
	public String add(YwnrPhotosDto ywnrPhotosDto) {
		ywnrPhotosService.add(ywnrPhotosDto);
		return "ywnrPhotos/ywnrPhotos";
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(YwnrPhotosDto ywnrPhotosDto) {
		ywnrPhotosService.delete(ywnrPhotosDto);
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(YwnrPhotosDto ywnrPhotosDto) {
		if (ywnrPhotosDto.getId() == 0) {
			ywnrPhotosService.add(ywnrPhotosDto);
		} else {
			ywnrPhotosService.update(ywnrPhotosDto);
		}
	}

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/findYwnrPhotosWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findYwnrsWithPC(@PathVariable String ywnrId,
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
		List<YwnrPhotosDto> list = (List<YwnrPhotosDto>) ywnrPhotosService
				.findWithPageAndCondition(params, page, rows);
		int totol = ywnrPhotosService.getRowsWithCondition(params);
		System.out.println(JsonStrUtils.getJsonResult(totol, list));
		return JsonStrUtils.getJsonResult(totol, list);
	}
}
