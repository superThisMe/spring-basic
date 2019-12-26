package com.demoweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.service.MemberService;
import com.demoweb.vo.Member;

@WebServlet("/account/register.action")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 데이터 읽기
		//2. 처리
		
		//3. 응답컨텐츠 생산
		//3-1. JSP에서 읽을 수 있도록 데이터 저장
		//3-2. JSP로 이동
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/account/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 데이터 읽기
		req.setCharacterEncoding("utf-8");
		String memberId = req.getParameter("memberId");
		String passwd = req.getParameter("passwd");
		String email = req.getParameter("email");
		
		//2. 처리 (데이터베이스에 데이터 저장)
		//System.out.printf("[%s][%s][%s]", memberId, passwd, email);
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		member.setEmail(email);
		
		MemberService memberService = new MemberService();
		memberService.registerMember(member);
		
		//3. 홈으로 이동		
		resp.sendRedirect("/demoweb/home.action");
		
	}

}













