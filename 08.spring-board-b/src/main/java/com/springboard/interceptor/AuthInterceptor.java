package com.springboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//public class AuthInterceptor implements HandlerInterceptor {
public class AuthInterceptor extends HandlerInterceptorAdapter {

	// Controller 실행 (호출) 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		
		if (uri.contains("/board/") || uri.contains("/reply/")) {
			if (session.getAttribute("loginuser") == null) {
				response.sendRedirect("/spring-board-b/resources/login.html");
				return false;
			}
		}
		
		return true;
	}
	
	// Controller 실행 (호출) 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	// Request 처리 후 (View 처리 후)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
