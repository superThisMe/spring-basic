<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%-- JSTL의 함수를 제공하는 taglib --%>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자료업로드</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/modules/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">업로드 자료 정보</div>
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>${ upload.title }</td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>${ upload.uploader }</td>
		            </tr>
		            <tr>
		            	<th>조회수</th>
		            	<td>${ upload.readCount }</td>
		            </tr>
		            <tr>
		            	<th>등록일자</th>
		            	<td>${ upload.regDate }</td>
		            </tr>
		            <tr>
		                <th>첨부자료</th>
		                <td>
		                <c:forEach var="file" items="${ upload.files }">
		                <a href="download.action?fileno=${ file.uploadFileNo }">${ file.userFileName }</a> 
		                [${ file.downloadCount }]<br>
		                </c:forEach>
		                </td>
		            </tr>
		            <tr>
		                <th>자료설명</th>
<%-- 줄바꿈 문자열을 저장하고 있는 변수 만들기(EL) --%>	
<c:set var="enter" value="
" />
		                <%-- upload.content 문자열에서 \r\n을 <br>로 변경 --%>
		                <td>${ fn:replace(upload.content, enter, "<br>") }</td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<%-- 로그인한 사용자와 글의 작성자가 같으면 삭제, 수정 버튼 활성화 --%>
		        	<c:if test="${ loginuser.memberId eq upload.uploader }">
		        	<input type="button" id="update_button" value="편집" style="height:25px" />
		        	<input type="button" id="delete_button" value="삭제" style="height:25px" />
		        	</c:if>
		        	
		        	<input type="button" id="cancel_button" value="목록보기" style="height:25px" />
		        	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
		        	<script type="text/javascript">
		        	//브라우저가 html을 모두 읽고 처리할 준비가 되었을 때 호출할 함수 지정
		        	$(function() {//js의 main 함수 역할
		        		$("#cancel_button").on('click', function(event) {
		        			location.href = "list.action"; //주소창에 list.action을 입력하고 엔터
		        			//history.back(); // 브라우저의 이전 버튼을 클릭
		        		});
		        		
		        		$("#delete_button").on('click', function(event) {
		        			var ok = confirm("${upload.uploadNo}번 자료를 삭제할까요?");
		        			if (ok) {
			        			//<a 를 통한 요청이므로 주소 뒤에 ?key=value 형식을 써서 데이터 전송
			        			location.href = "delete.action?uploadNo=${ upload.uploadNo }";		        			
		        			}
		        		});
		        		
		        		$("#update_button").on('click', function(event) {
		        			location.href = "update.action?uploadNo=${ upload.uploadNo }";
		        		});
		        		
		        	});
		        	</script>
		        </div>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>