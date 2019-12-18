package com.demoweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component("home") // getBean("home") 하면 HomeController 객체 반환
public class HomeController implements Controller {

	@Override
	public ActionResult processRequest(HttpServletRequest req, HttpServletResponse resp) {
		
		//1. 요청 처리		
		//2. 반환
		ActionResult ar = new ActionResult();
		ar.setViewName("home");
		ar.setRedirect(false);
		
		return ar;
		
	}

}
