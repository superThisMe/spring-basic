package com.demoweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demoweb.vo.Member;

@WebFilter("*.action")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("loginuser");

		String url = req.getRequestURI(); // 현재 요청 경로

		if (url.contains("/rboard/") || url.contains("/fboard/") || url.contains("/admin/")
				|| url.contains("/email/")) { // 요청 경호 조건 체크
			if (member == null) {
				resp.sendRedirect("/demoweb/account/login.action");
				return; 
			}
		}
		if (url.contains("/admin/")) {
			if (!member.getUserType().equals("admin")) {
				resp.sendRedirect("/demoweb/account/login.action");
				return;
			}
		}
		
		chain.doFilter(request, response);

	}

}
