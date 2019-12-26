package com.demoweb.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.demoweb.common.Util;
import com.demoweb.service.UploadService;
import com.demoweb.vo.Member;
import com.demoweb.vo.Upload;
import com.demoweb.vo.UploadFile;

//import com.demoweb.vo.Member;

@WebServlet("/rboard/update.action")
public class ResourceBoardUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 로그인 여부 확인 (로그인 하지 않은 경우 -> 로그인 화면으로 이동) -> AuthFilter
//		HttpSession session = req.getSession();
//		Member member = (Member) session.getAttribute("loginuser");
//		if (member == null) {
//			resp.sendRedirect("/demoweb/account/login.action");
//			return;
//		}

		// 2-1. 사용자가 선택한 자료 번호 읽기
		String sUploadNo = req.getParameter("uploadNo");
		int uploadNo = -1;
		try {
			uploadNo = Integer.parseInt(sUploadNo);
		} catch (Exception ex) { // 잘못된 자료번호인 경우
			resp.sendRedirect("list.acion"); // 다시 목록으로 이동
			return;
		}

		// 2-2. 사용자가 선택한 자료 정보 조회 (UploadService 객체에 요청)
		UploadService uploadService = new UploadService();
		Upload upload = uploadService.findByUploadNo(uploadNo);

		if (upload == null) { // 조회 실패
			resp.sendRedirect("list.action");
			return;
		}

		// 2-3. 작성자와 로그인한 사용자가 다른 경우 목록으로 이동
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("loginuser");
		if (!member.getMemberId().equals(upload.getUploader())) {
			resp.sendRedirect("list.action");
			return;
		}

		// 3. JSP에서 사용할 수 있도록 조회된 데이터를 request 객체에 저장
		req.setAttribute("upload", upload);

		// 4. JSP로 forward
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/rboard/update.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 로그인 여부 확인 (로그인 하지 않은 경우 -> 로그인 화면으로 이동) -> AuthFilter

		req.setCharacterEncoding("utf-8");

		// 2. 파일 업로드를 포함한 요청인지 확인 (multipart/form-data 형식 확인)
		if (ServletFileUpload.isMultipartContent(req) == false) {
			resp.sendRedirect("list.action");
			return;
		}

		// 3. 외부 라이브러리(apache commons fileupload) 동작 설정
		// 경로 문자열을 저장할 변수
		// application.getRealPath('웹경로')
		// --> 가상경로(웹경로) -> 물리경로(컴퓨터경로)
		// --> http://..... -> C:/......
		ServletContext application = req.getServletContext();
		String path = application.getRealPath("/upload-files"); // 최종 파일 저장 경로
		String tempPath = application.getRealPath("/upload-temp"); // 임시 파일 저장 경로

		// 전송 데이터 각 요소를 분리해서 개별 객체를 만들때 사용할 처리기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 1); // 임시 파일을 만들지 결정하는 기준 설정
		factory.setRepository(new File(tempPath)); // 임시 파일 저장 경로 지정

		// 요청 정보를 읽을 파서(Parser) 생성 (요청을 읽고 요소별로 분리)
		ServletFileUpload uploader = new ServletFileUpload(factory);
		uploader.setFileSizeMax(1024 * 1024 * 1);// 최대 파일 크기

		// 요청 데이터를 읽어서 저장할 객체 변수 만들기
		Upload upload = new Upload();
		ArrayList<UploadFile> files = new ArrayList<>();

		try {
			// 4. 요청 정보를 파싱하고 개별 객체의 목록을 반환
			List<FileItem> items = uploader.parseRequest(req);

			// 5. 목록에 담긴 데이터 사용
			for (FileItem item : items) {
				if (item.isFormField()) { // form-data인 경우 (File이 아닌 일반 데이터인 경우)
					if (item.getFieldName().equals("title")) {
						upload.setTitle(item.getString("utf-8"));
					} else if (item.getFieldName().equals("uploadNo")) {
						String sUploadNo = item.getString("utf-8");
						upload.setUploadNo(Integer.parseInt(sUploadNo));
					} else if (item.getFieldName().equals("content")) {
						upload.setContent(item.getString("utf-8"));
					}
				} else { // file인 경우
					String fileName = item.getName(); // 파일 이름 가져오기
					if (fileName != null && fileName.length() > 0) { // 내용이 있는 경우
						if (fileName.contains("\\")) { // iexplore 경우
							// C:\AAA\BBB\CCC.png -> CCC.png
							fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						}
						// String uniqueFileName = Util.makeUniqueFileName(path, fileName);//고유한 파일이름
						// 만들기
						String uniqueFileName = Util.makeUniqueFileName(fileName);// 고유한 파일이름 만들기
						item.write(new File(path, uniqueFileName)); // 파일 저장
						item.delete(); // 임시 파일 삭제

						UploadFile uploadFile = new UploadFile();
						uploadFile.setUserFileName(fileName);
						uploadFile.setSavedFileName(uniqueFileName);

						files.add(uploadFile);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		upload.setFiles(files); // 파일 목록을 Upload 객체에 저장
		// 여기까지 실행되면 파일은 디스크에 저장되었고, 데이터는 Upload, UploadFile 객체에 나뉘어서 저장된 상태
		
		// 6. 서비스 객체에 데이터 처리 요청 (UploadService)
		UploadService uploadService = new UploadService();
		uploadService.updateUpload(upload);
		
		// 7. 목록으로 이동
		resp.sendRedirect("list.action");

	}

}
