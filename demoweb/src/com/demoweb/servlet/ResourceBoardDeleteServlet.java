package com.demoweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.demoweb.service.UploadService;

//import com.demoweb.vo.Member;

@WebServlet("/rboard/delete.action")
public class ResourceBoardDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 2. 삭제 대상 uploadNo 읽기
		String sUploadNo = req.getParameter("uploadNo");
		int uploadNo = -1;
		try {
			uploadNo = Integer.parseInt(sUploadNo);
		} catch (Exception ex) { // 잘못된 자료번호인 경우
			resp.sendRedirect("list.action"); // 다시 목록으로 이동
			return;
		}

		// 3. 서비스 객체에 삭제 요청 (UploadService)
		UploadService uploadService = new UploadService();
		uploadService.delete(uploadNo);

		// 4. 목록으로 이동
		resp.sendRedirect("list.action");
	}

}
