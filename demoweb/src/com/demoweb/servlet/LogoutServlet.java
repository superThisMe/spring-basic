package com.demoweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/account/logout.action")
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 데이터 읽기
		//2. 처리
		HttpSession session = req.getSession();
		session.removeAttribute("loginuser"); // loginuser 데이터 제거
		//session.invalidate(); // Session 자체 제거 (모든 Session 데이터 제거)
		
		//3. 응답컨텐츠 생산
		//   여기서는 Home으로 이동
		resp.sendRedirect("/demoweb/home.action");
		
	}

}













