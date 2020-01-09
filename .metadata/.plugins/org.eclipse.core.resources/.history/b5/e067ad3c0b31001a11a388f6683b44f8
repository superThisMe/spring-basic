package com.demoweb.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.Data;

//데이터베이스의 Member 테이블의 데이터를 저장하는 클래스
@Data // 자동으로 getter, setter, constructor, ... 을 만드는 어노테이션
public class Member {
	
	//필드 선언은 테이블의 컬럼과 일치하도록 구현
	private String memberId;
	private String passwd;
	private String email;
	private String userType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, 
				pattern = "yyyy-MM-dd",
				timezone = "Asia/Seoul")
	private Date regDate;
	private boolean active;

}









