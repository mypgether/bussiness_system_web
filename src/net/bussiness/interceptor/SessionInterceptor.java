package net.bussiness.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author yh
 */
public class SessionInterceptor implements HandlerInterceptor {

	private Logger logger = Logger
			.getLogger(SessionInterceptor.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object o) throws Exception {
//		// 1、请求到登录页面 放行
//		logger.log(Level.INFO, request.getServletPath());
//		if (request.getServletPath().startsWith("/login")) {
//			return true;
//		}
//		// 2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求
//
//		// 3、如果用户已经登录 放行
//		if (request.getSession().getAttribute("currentUser") != null) {
//			// 更好的实现方式的使用cookie
//			return true;
//		}
//
//		// 4、非法请求 即这些请求需要登录后才能访问
//		// 重定向到登录页面
//		logger.log(Level.INFO, "user not login");
//		response.sendRedirect("hello.jsp");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1,
			Object o, ModelAndView mav) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest hsr,
			HttpServletResponse hsr1, Object o, Exception excptn)
			throws Exception {
	}
}