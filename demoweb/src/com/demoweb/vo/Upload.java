package com.demoweb.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Upload {

	private int uploadNo;
	private String title;
	private String uploader;
	private String content;
	private int readCount;
	private Date regDate;
	private boolean deleted;
	
	// Upload 테이블과 UploadFile 테이블 사이의 1:Many 관계를 구현하는 필드
	private List<UploadFile> files;
	
}
