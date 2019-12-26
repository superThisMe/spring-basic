package com.demoweb.service;

import java.util.List;

import com.demoweb.dao.UploadDao;
import com.demoweb.vo.Upload;
import com.demoweb.vo.UploadFile;

public class UploadService {
	
	private UploadDao uploadDao = new UploadDao();

	public void writeUpload(Upload upload) {
		
		//데이터베이스에 데이터 저장 요청 (UploadDao 객체)
//		uploadDao.insertUpload(upload);
		
		int newUploadNo = uploadDao.insertUpload(upload); // title, uploader, content, uploadno(자동생산)
		
		for (UploadFile file : upload.getFiles()) {
			file.setUploadNo(newUploadNo);
			uploadDao.insertUploadFile(file);// savedfilename, userfilename, uploadfileno(자동생산), uploadno(insertUpload에서
												// 자동생산한 값)
		}
		
	}

	// 데이터 조회 요청 (UploadDao 객체)
	public List<Upload> findAll() {
		List<Upload> uploads = uploadDao.selectUpload();
		return uploads;
	}

	// 상세 데이터 조회 요청 (UploadDao 객체)
	public Upload findByUploadNo(int uploadNo) {
		Upload upload = uploadDao.selectUploadByUploadNo(uploadNo);
		List<UploadFile> files = uploadDao.selectUploadFileByUploadNo(uploadNo);
		upload.setFiles(files);
		
		return upload;
	}

	// 데이터 삭제 요청 (UploadDao 객체)
	public void delete(int uploadNo) {
		uploadDao.deleteUpload(uploadNo);		
	}

	public void updateUpload(Upload upload) {
		uploadDao.updateUpload(upload);
		for (UploadFile file : upload.getFiles()) {
			file.setUploadNo(upload.getUploadNo());
			uploadDao.insertUploadFile(file);
		}
	}

	// UploadDao 객체에 데이터 조회 요청
	public UploadFile findUploadFileByFileNo(int fileNo) {
		UploadFile file = uploadDao.selectUploadFileByFileNo(fileNo);
		
		return file;
	}

	
	
}
