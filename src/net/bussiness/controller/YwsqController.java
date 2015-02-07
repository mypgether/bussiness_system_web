package net.bussiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.bussiness.bdpush.AndroidPushMessage;
import net.bussiness.model.UserDto;
import net.bussiness.model.YwsqDto;
import net.bussiness.service.UserService;
import net.bussiness.service.YwsqService;
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
	@ResponseBody
	public void add(String ywsqDto) {
		System.out.println(ywsqDto);
		YwsqDto dto = (YwsqDto) JacksonUtils.json2Bean(ywsqDto, YwsqDto.class);
		ywsqService.add(dto);
		// // 根据createrId找到领导
		// UserDto userDto = (UserDto)
		// userService.load(dto.getUserByProposerId()
		// .getDept().getCreaterId());

		// 根据positionId找到领导
		Map<String, String> params = new HashMap<String, String>();
		params.put("positionId", Constants.DEPT_LEADER_POSITION_ID + "");
		params.put("deptId", dto.getUserByProposerId().getDept().getId() + "");
		List<UserDto> users = (List<UserDto>) userService
				.findWithCondition(params);
		for (UserDto user : users) {
			AndroidPushMessage.pushMessage(user, Constants.BD_PUSHTYPE_YWSQ,
					dto);
		}
		// return "ywsq/ywsqs";
	}

	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(String ywsqDto) {
		System.out.println(ywsqDto);
		YwsqDto dto = (YwsqDto) JacksonUtils.json2Bean(ywsqDto, YwsqDto.class);
		ywsqService.delete(dto);
	}

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void update(String ywsqDto) {
		System.out.println(ywsqDto);
		YwsqDto dto = (YwsqDto) JacksonUtils.json2Bean(ywsqDto, YwsqDto.class);
		if (dto.getYwId() == 0) {
			ywsqService.add(dto);
		} else {
			AndroidPushMessage.pushMessage(dto.getUserByProposerId(),
					Constants.BD_PUSHTYPE_YWSP, dto);
			ywsqService.update(dto);
		}
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
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
	}

	@RequestMapping(value = "/{userId}/findApproveYwsqsApprovingWithPC/{deptId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findApproveYwsqsApprovingWithPC(@PathVariable String userId,
			@PathVariable String deptId, HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		String hql = "from YwsqDto y where y.approveState=0 and y.userByProposerId.dept.id="
				+ deptId;
		List<YwsqDto> list = (List<YwsqDto>) ywsqService.findWithPageAndHql(
				hql, page, rows);
		int totol = ywsqService
				.getRowsWithHql("select count(*) from YwsqDto as y where y.approveState=0 and y.userByProposerId.dept.id="
						+ deptId);
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
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
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
	}
}
