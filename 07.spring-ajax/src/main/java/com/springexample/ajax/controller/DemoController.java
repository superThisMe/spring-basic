package com.springexample.ajax.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demoweb.service.MemberService;
import com.demoweb.vo.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/")
public class DemoController {
	
	@GetMapping("/ajax-example")
	public void showExampleView() {
		
		//RequestMapping 메서드의 반환 값이 void인 경우 요청 경로가 View-Name
		//return ajax-example
		
	}
	
	@GetMapping("/get-time")
	@ResponseBody // 반환 값은 ViewName이 아니고 응답 컨텐츠
	public String getTime() {
		
		//날짜를 지정된 형식에 따라 문자열로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(new Date());
		
	}
	
	@GetMapping("/member-search-1")
	public String showMemberSearchForm() {
		
		return "account/update";
		
	}
	
	@GetMapping(path = { "/search-member" }, 
				produces = "text/plain;charset=utf-8")
	@ResponseBody // return value가 viewname이 아니고 응답 컨텐츠
	public String searchMember(String memberId) {
		
		MemberService memberService = new MemberService();
		Member member = memberService.searchMemberById(memberId);
		
		if (member == null) {
			return "fail to search member";
		} else {
			return String.format("%s|%s|%s|%s|%s", 
					member.getMemberId(), 
					member.getEmail(),
					member.getUserType(), 
					member.getRegDate().toString(),
					member.isActive() ? "활성사용자" : "비활성사용자");
		}
	}
	
	////////////////////////////////////////////////////////////
	
	@GetMapping("/member-search-2")
	public String showMemberSearchForm2() {
		
		return "account/update2";
		
	}
	
	@GetMapping(path = { "/search-member2" }, 
			produces = "application/json;charset=utf-8")
	@ResponseBody // return value가 viewname이 아니고 응답 컨텐츠
	public String searchMember2(String memberId) {
	
		MemberService memberService = new MemberService();
		Member member = memberService.searchMemberById(memberId);
		
		if (member == null) {
			return "{ \"error\": \"검색 실패\" }";
		} else {
			
			//Gson gson = new Gson();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String json = gson.toJson(member); // 객체 -> JSON 문자열
			
			return json;
			
		}
	}
	
	/////////////////////////////////////////////////////////
	
	@GetMapping("/member-search-3")
	public String showMemberSearchForm3() {
		
		return "account/update3";
		
	}
	
	@GetMapping(path = { "/search-member3" }, 
			produces = "application/json;charset=utf-8")
	@ResponseBody // return value가 viewname이 아니고 응답 컨텐츠
	public Member searchMember3(String memberId) {
	
		MemberService memberService = new MemberService();
		Member member = memberService.searchMemberById(memberId);
		
		if (member == null) {
			throw new RuntimeException("검색 실패");
		} else {
			
			return member;
			
		}
	}
	
	//////////////////////////////////////////
	
	@GetMapping("/member-search-4")
	public String showMemberSearchForm4() {
		
		return "account/update4";
		
	}
	
	@GetMapping(path = { "/get-suggestions" })
	@ResponseBody
	public String getSeggestions(String id) {
		
		MemberService memberService = new MemberService();
		List<String> idList = memberService.searchIdList(id);
		
		String result = "[";
		for(int i = 0; i < idList.size(); i++) {
			result += "'" + idList.get(i) + "'";
			if (i < idList.size() - 1) {
				result += ",";
			}
		}
		result += "]";
	
		return result;
	}
	
	///////////////////////////////////
	
	@GetMapping(path = { "/load-html" })
	public String loadHtml() {
		
		return "view-snippet";
	}
	
	

}














