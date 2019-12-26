package com.demoweb.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demoweb.service.UploadService;
import com.demoweb.vo.UploadFile;

@WebServlet("/rboard/download.action")
public class ResourceBoardDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 사용자가 선택한 파일 번호 읽기
		String sFileNo = req.getParameter("fileNo");
		int fileNo = -1;
		try {
			fileNo = Integer.parseInt(sFileNo);
		} catch (Exception ex) {
			resp.sendRedirect("list.action");
			return;
		}
		
		// 2. 서비스 객체에 파일 번호에 해당하는 파일 정보 요청 (UploadService)
		UploadService uploadservice = new UploadService();
		UploadFile file = uploadservice.findUploadFileByFileNo(fileNo);
				
		// ServletContext : JSP의 application객체와 동일한 객체
		ServletContext application = req.getServletContext();
		String path = application.getRealPath("/upload-files/" + file.getSavedFileName());

		// 브라우저가 응답 컨텐츠를 다운로드로 처리하도록 정보 설정
		resp.setContentType("application/octet-stream;charset=utf-8");
		
		// 브라우저에게 다운로드하는 파일의 이름을 알려주는 코드
		resp.addHeader("Content-Disposition", 
//				"Attachment;filename=\"" + fileName + "\""); // firefox에서 filename에 space가 있어도 처리하도록 ""로 묶기
				"Attachment; filename=\"" + new String(file.getUserFileName().getBytes("UTF-8"), "ISO-8859-1") + "\""); // UTF-8 string을 ISO-8859-1로 변환

		FileInputStream fis = new FileInputStream(path); // 파일을 읽는 도구
		OutputStream fos = resp.getOutputStream(); // 브라우저에게 전송하는 도구

		while (true) {
			int data = fis.read(); // 파일에서 1byte 읽기
			if (data == -1) { // 더 이상 읽을 데이터가 없다면 (EOF)
				break;
			}
			fos.write(data); // 응답 스트림에 1byte 쓰기
		}

		fis.close();
		fos.close();

	}

}
