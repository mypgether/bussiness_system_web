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

import net.bussiness.model.YwnrDto;
import net.bussiness.model.YwnrPhotosDto;
import net.bussiness.service.YwnrPhotosService;
import net.bussiness.util.Constants;
import net.bussiness.util.JacksonUtils;
import net.bussiness.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/downloadPhoto/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public void download(@PathVariable String userId,
			@PathVariable String ywId, @PathVariable String ywnrId,
			@PathVariable String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String dirPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Constants.YWNRPHOTO_PHOTOS_PATH
				+ "//"
				+ userId
				+ "//"
				+ ywId
				+ "//" + ywnrId + "//";
		String filePath = dirPath + fileName + Constants.PHOTOS_POSTFIX;
		System.out.println("下载业务内容图片，地址：" + filePath);
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

	// @RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/{photoPath}", method =
	// RequestMethod.GET)
	// public void downloadPhoto(@PathVariable String photoPath,
	// HttpServletResponse response) {
	// response.setContentType("image/*");
	// // response.setContentType("application/x-download");
	// // setContentType("image/jpeg");
	// FileInputStream fis = null;
	// OutputStream os = null;
	// try {
	// fis = new FileInputStream(photoPath);
	// os = response.getOutputStream();
	// int count = 0;
	// byte[] buffer = new byte[1024 * 8];
	// while ((count = fis.read(buffer)) != -1) {
	// os.write(buffer, 0, count);
	// os.flush();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// fis.close();
	// os.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/uploadPhoto", method = RequestMethod.POST)
	@ResponseBody
	public void onSubmit(@PathVariable String userId,
			@PathVariable String ywId, @PathVariable String ywnrId,
			@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				String fileName = System.currentTimeMillis() + "";
				// 文件保存路径
				String dirPath = request.getSession().getServletContext()
						.getRealPath("/")
						+ Constants.YWNRPHOTO_PHOTOS_PATH
						+ "//"
						+ userId
						+ "//" + ywId + "//" + ywnrId + "//";
				File dirPathFile = new File(dirPath);
				if (!dirPathFile.exists()) {
					dirPathFile.mkdirs();
				}
				String filePath = dirPath + fileName + Constants.PHOTOS_POSTFIX;
				System.out.println("上传业务内容图片----地址：" + filePath);
				// 保存文件
				saveFile(file, filePath);
				// 保存到数据库
				YwnrDto ywnr = new YwnrDto();
				ywnr.setId(Integer.parseInt(ywnrId));
				ywnrPhotosService.add(new YwnrPhotosDto(ywnr, fileName));
			}
		}
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

	@RequestMapping(value = "/{userId}/{ywId}/{ywnrId}/findYwnrPhotosWithPC", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String findYwnrPhotosWithPC(@PathVariable String ywnrId,
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
		System.out.println(JacksonUtils.getJsonResult(totol, list));
		return JacksonUtils.getJsonResult(totol, list);
	}
}
