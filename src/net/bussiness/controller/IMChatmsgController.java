package net.bussiness.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bussiness.bdpush.AndroidPushMessage;
import net.bussiness.model.ChatmsgDto;
import net.bussiness.model.UserDto;
import net.bussiness.service.ChatmsgService;
import net.bussiness.service.UserService;
import net.bussiness.util.Constants;
import net.bussiness.util.JacksonUtils;
import net.bussiness.util.StringUtils;

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
@RequestMapping("/im/person")
public class IMChatmsgController {
	private ChatmsgService imService;
	private UserService userService;

	public ChatmsgService getImService() {
		return imService;
	}

	@Resource
	public void setImService(ChatmsgService imService) {
		this.imService = imService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/ims", method = RequestMethod.GET)
	public String list() {
		return "im/ims/person";
	}

	@RequestMapping(value = "/{userId}/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute(new ChatmsgDto());
		return "im/person/add";
	}

	@RequestMapping(value = "/{userId}/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(String chatmsgDto) {
		ChatmsgDto dto = (ChatmsgDto) JacksonUtils.json2Bean(chatmsgDto,
				ChatmsgDto.class);
		imService.add(dto);
		// AndroidPushNotification.pushNotification(userDto,
		// Constants.BD_PUSHTYPE_IM, dto);
		if (dto.getMsgType() == 1) {
			UserDto userDto = (UserDto) userService.load(dto
					.getUserByReceiverId().getUserId());
			AndroidPushMessage.pushMessage(userDto,
					Constants.BD_PUSHTYPE_IM_PERSON, dto);
		}
		return dto.getMsgId() + "";
		// return "im/ims";
	}

	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(String chatmsgDto) {
		System.out.println(chatmsgDto);
		ChatmsgDto dto = (ChatmsgDto) JacksonUtils.json2Bean(chatmsgDto,
				ChatmsgDto.class);
		imService.delete(dto);
	}

	@RequestMapping(value = "/{senderId}/{receiverId}/clear", method = RequestMethod.POST)
	@ResponseBody
	public void clear(@PathVariable String senderId,
			@PathVariable String receiverId) {
		String hql = "delete from ChatmsgDto as c where (c.userBySenderId="
				+ senderId + " and userByReceiverId=" + receiverId
				+ " ) or (userBySenderId=" + receiverId
				+ " and userByReceiverId=" + senderId + " )";
		imService.delete(hql);
	}

	@RequestMapping(value = "/{userBySenderId}/{userByReceiverId}/downloadPhoto/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadPhoto(@PathVariable String userBySenderId,
			@PathVariable String userByReceiverId,
			@PathVariable String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String dirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Constants.IM_PERSON_CHAT_PHOTOS_PATH
				+ "//"
				+ userBySenderId
				+ "//" + userByReceiverId + "//";
		String filePath = dirPath + fileName + Constants.PHOTOS_POSTFIX;
		System.out.println("下载聊天图片，地址：" + filePath);
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

	@RequestMapping(value = "/{userBySenderId}/{userByReceiverId}/{msgId}/uploadPhoto", method = RequestMethod.POST)
	@ResponseBody
	public String onSubmitPhoto(@PathVariable String userBySenderId,
			@PathVariable String userByReceiverId, @PathVariable String msgId,
			@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		String fileName = null;
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				fileName = System.currentTimeMillis() + "";
				// 文件保存路径
				String dirPath = request.getSession().getServletContext()
						.getRealPath("/")
						+ Constants.IM_PERSON_CHAT_PHOTOS_PATH
						+ "//"
						+ userBySenderId + "//" + userByReceiverId + "//";
				File dirPathFile = new File(dirPath);
				if (!dirPathFile.exists()) {
					dirPathFile.mkdirs();
				}
				String filePath = dirPath + fileName + Constants.PHOTOS_POSTFIX;
				System.out.println("上传聊天图片----地址：" + filePath);
				// 保存文件
				saveFile(file, filePath);
				// 保存到数据库
				ChatmsgDto chatMsg = (ChatmsgDto) imService.load(Integer
						.parseInt(msgId));
				chatMsg.setMsgContent(fileName.getBytes());
				imService.update(chatMsg);

				UserDto userDto = (UserDto) userService.load(Integer
						.parseInt(userByReceiverId));
				AndroidPushMessage.pushMessage(userDto,
						Constants.BD_PUSHTYPE_IM_PERSON, chatMsg);
			}
		}
		return fileName;
	}

	@RequestMapping(value = "/{userBySenderId}/{userByReceiverId}/downloadRecord/{fileName}", method = RequestMethod.POST)
	@ResponseBody
	public void downloadRecord(@PathVariable String userBySenderId,
			@PathVariable String userByReceiverId,
			@PathVariable String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String dirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Constants.IM_PERSON_CHAT_RECORDS_PATH
				+ "//"
				+ userBySenderId
				+ "//" + userByReceiverId + "//";
		String filePath = dirPath + fileName + Constants.RECORD_POSTFIX;
		System.out.println("下载用户语音，地址：" + filePath);
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

	@RequestMapping(value = "/{userBySenderId}/{userByReceiverId}/{msgId}/uploadRecord", method = RequestMethod.POST)
	@ResponseBody
	public String onSubmitRecord(@PathVariable String userBySenderId,
			@PathVariable String userByReceiverId, @PathVariable String msgId,
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
						+ Constants.IM_PERSON_CHAT_RECORDS_PATH
						+ "//"
						+ userBySenderId + "//" + userByReceiverId + "//";
				File dirPathFile = new File(dirPath);
				if (!dirPathFile.exists()) {
					dirPathFile.mkdirs();
				}
				fileName = System.currentTimeMillis() + "";
				String filePath = dirPath + fileName + Constants.RECORD_POSTFIX;
				System.out.println("上传用户语音----地址：" + filePath);
				// 保存文件
				saveFile(file, filePath);
				// 保存到数据库
				ChatmsgDto chatMsg = (ChatmsgDto) imService.load(Integer
						.parseInt(msgId));
				chatMsg.setMsgContent(fileName.getBytes());
				imService.update(chatMsg);

				UserDto userDto = (UserDto) userService.load(Integer
						.parseInt(userByReceiverId));
				AndroidPushMessage.pushMessage(userDto,
						Constants.BD_PUSHTYPE_IM_PERSON, chatMsg);
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

	@RequestMapping(value = "/{userId}/findPersonIMsWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findPersonIMsWithPC(@PathVariable String userId,
			HttpServletRequest request) {
		int page = 0;
		int rows = 0;
		String receiverId = "";
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!StringUtils.isBlank(request.getParameter("rows"))) {
			rows = Integer.parseInt(request.getParameter("rows"));
		}
		if (!StringUtils.isBlank(request.getParameter("receiverId"))) {
			receiverId = request.getParameter("receiverId");
			params.put("receiverId", receiverId);
		}
		params.put("senderId", userId);
		List<ChatmsgDto> list = (List<ChatmsgDto>) imService
				.findWithPageAndCondition(params, page, rows);
		int totol = imService.getRowsWithCondition(params);
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
	}
}
