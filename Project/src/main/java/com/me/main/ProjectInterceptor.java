package com.me.main;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProjectInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		Enumeration<String> paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {

				String paramName = paramNames.nextElement();
				String input = (String) request.getAttribute(paramName);
				String newInput = filterData(input);
				request.setAttribute(paramName, newInput);
			}
//		HttpSession session = request.getSession(false);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static String filterData(String input) {
		if (!checkCharacters(input)) {
			return (input);
		}

		StringBuffer filteredString = new StringBuffer(input.length());
		char c;

		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filteredString.append("&lt");
				break;
			case '>':
				filteredString.append("&gt");
				break;
			case '"':
				filteredString.append("&quot");
				break;
			case '&':
				filteredString.append("&amp");
				break;
			case '\'':
				filteredString.append("&apos");
				break;
			default:
				filteredString.append(c);
			}
		}
		return (filteredString.toString());
	}

	private static boolean checkCharacters(String input) {
		boolean flag = false;
		if (input != null) {
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				switch (c) {
				case '<':
					flag = true;
					break;
				case '>':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return (flag);
	}


}
