package net.bussiness.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import net.bussiness.model.UserDao;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController implements Serializable {

	public void addSessionUser(String name, UserDao user) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra)
				.getRequest();
		request.getSession().setAttribute(name, user);

	}

	public Object getUserAttribute(String name) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra)
				.getRequest();
		Object obj = null;
		if (!name.trim().equals("")) {
			obj = request.getSession().getAttribute(name);
		}
		return obj;
	}
}