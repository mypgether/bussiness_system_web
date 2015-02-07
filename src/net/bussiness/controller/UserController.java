package net.bussiness.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bussiness.mail.MailUtils;
import net.bussiness.model.DeptDto;
import net.bussiness.model.PositionDto;
import net.bussiness.model.UserDto;
import net.bussiness.service.UserService;
import net.bussiness.util.Constants;
import net.bussiness.util.DtoUpdateUtils;
import net.bussiness.util.JacksonUtils;
import net.bussiness.util.StringUtils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(String userDto) {
		UserDto dto = (UserDto) JacksonUtils.json2Bean(userDto, UserDto.class);
		userService.delete(dto);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(String userDto) {
		UserDto dto = (UserDto) JacksonUtils.json2Bean(userDto, UserDto.class);
		if (dto.getId() == 0) {
			userService.add(dto);
		} else {
			UserDto temp = (UserDto) userService.load(dto.getUserId());
			DtoUpdateUtils.updateUserDto(temp, dto);
			userService.update(temp);
		}
	}

	@RequestMapping(value = "{userId}/findPsd", method = RequestMethod.POST)
	@ResponseBody
	public String findPsd(@PathVariable String userId) {
		UserDto dao = (UserDto) userService.load(Integer.parseInt(userId));
		System.out.println(dao.toString());
		if (dao.getEmail() == null || dao.getEmail().equals("")) {
			return "0";
		}
		MailUtils.sendMail(dao.getEmail(), "出差管理系统----密码找回",
				"密码找回!\n\n您好：" + dao.getUserName() + "\n\n您的员工账号是：" + userId
						+ "\n密码：" + dao.getPassword() + "\n请重新登录系统...");
		return "1";
	}

	@RequestMapping(value = "{userId}/load", method = RequestMethod.POST)
	@ResponseBody
	public UserDto load(@PathVariable String userId) {
		System.out.println(userId);
		UserDto dao = (UserDto) userService.load(Integer.parseInt(userId));
		System.out.println(dao.toString());
		return dao;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new UserDto());
		return "user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(String userDto) {
		UserDto dto = (UserDto) JacksonUtils.json2Bean(userDto, UserDto.class);
		UserDto loadUser = (UserDto) userService.load(dto.getUserId());
		if (loadUser == null) {
			dto.setUserName("");
			dto.setJoinTime(new Date());

			PositionDto position = new PositionDto();
			position.setId(Constants.IDLE_POSITION_ID);
			dto.setPosition(position);
			DeptDto dept = new DeptDto();
			dept.setId(Constants.IDLE_DEPT_ID);
			dto.setDept(dept);

			userService.add(dto);
			return "OK";
		}
		return "NO";
	}

	@RequestMapping(value = "/{userId}/downloadLogo/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public void download(@PathVariable String userId,
			@PathVariable String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String dirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Constants.USER_LOGO_PATH + "//" + userId + "//";
		String filePath = dirPath + fileName + Constants.PHOTOS_POSTFIX;
		System.out.println("下载用户头像，地址：" + filePath);
		try {
			long fileLength = new File(filePath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	@RequestMapping(value = "/{userId}/uploadLogo", method = RequestMethod.POST)
	@ResponseBody
	public String onSubmit(@PathVariable String userId,
			@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		String fileName = null;
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 文件保存路径
				String dirPath = request.getSession().getServletContext()
						.getRealPath("/")
						+ Constants.USER_LOGO_PATH + "//" + userId + "//";
				File dirPathFile = new File(dirPath);
				if (!dirPathFile.exists()) {
					dirPathFile.mkdirs();
				}
				fileName = System.currentTimeMillis() + "";
				String filePath = dirPath + fileName + Constants.PHOTOS_POSTFIX;
				System.out.println("上传用户头像----地址：" + filePath);
				// 保存文件
				saveFile(file, filePath);
				// 保存到数据库
				UserDto user = (UserDto) userService.load(Integer
						.parseInt(userId));
				user.setPhotoPath(fileName);
				userService.update(user);
			}
		}
		return fileName;
	}

	/***
	 * 保存文件
	 * 
	 * @param file
	 * @return
	 */
	private boolean saveFile(MultipartFile file, String filePath) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// file.transferTo(new File(filePath));
				FileCopyUtils.copy(file.getBytes(), new File(filePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@RequestMapping(value = "/findUsersName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findUsersName() {
		List<UserDto> list = (List<UserDto>) userService.findAll();
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (UserDto user : list) {
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
		List<UserDto> list = (List<UserDto>) userService.findWithPage(page,
				rows);
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
}
