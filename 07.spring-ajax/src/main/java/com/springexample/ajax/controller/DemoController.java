package com.springexample.ajax.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class DemoController {

	@GetMapping("ajax-example")
	public void showExampleView() {

		// RequestMapping method의 return값이 void인 경우 요청경로가 View-Name이 됨
		// return "ajax-example"

	}
	
	@GetMapping("ajax-example2")
	public String showMemberSearchForm() {

		return "account/update";

	}

	@GetMapping("get-time")
	@ResponseBody // return값이 ViewName이 아니라 응답컨텐츠
	public String getTime() {

		// Date를 지정된 format에 따라 String으로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(new Date());
		
	}

}
