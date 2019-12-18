package com.demoweb.controller;

import lombok.Data;

/**
 * 개별 요청 처리 Controller가 반환하는 데이터 저장 클래스
 * 
 * @author instructor
 *
 */
@Data
public class ActionResult {
	
	private String viewName;	// 다음에 이동(forward or redirect)할 장소
	private boolean redirect;	// forward or redirect

}
