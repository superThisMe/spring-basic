package com.demoweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.common.Util;

@WebServlet("/home.action")
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 데이터 읽기
		//2. 처리
		
		//3. 응답컨텐츠 생산
		//3-1. JSP에서 읽을 수 있도록 데이터 저장
		//3-2. JSP로 이동
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(req, resp);
		
		Util util = new Util();
		System.out.println(util.getClientIP(req));
		
	}

}













