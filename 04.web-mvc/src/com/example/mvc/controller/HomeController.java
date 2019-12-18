package com.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {
	
	public String processRequest(
		HttpServletRequest req, HttpServletResponse resp) {
		
		// 요청 처리
		
		// view-name 반환
		return "home";
		
	}

}
