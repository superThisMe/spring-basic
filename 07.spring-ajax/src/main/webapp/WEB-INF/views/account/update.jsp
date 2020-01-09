<%@page import="com.demoweb.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>사용자등록</title>
	<link rel="Stylesheet" href="/ajax/resources/styles/default.css" />
	<link rel="Stylesheet" href="/ajax/resources/styles/input.css" />

</head>
<body>

	<div id="pageContainer">

		<jsp:include page="/WEB-INF/views/modules/header.jsp" />	
	
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>

		        
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" 
		                    	   style="width:230px" />
		                    <button id="search-button">검색</button>
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<input type="text" id="email" name="email" 
		                		   style="width:280px" readonly="readonly" />		                		   
		                </td>
		            </tr>
		            <tr>
		                <th>등록일자</th>
		                <td>
		                	<input type="text" id="regDate" name="regDate" 
		                		style="width:280px" readonly="readonly" />
		                </td>
		            </tr>
		            <tr>
		                <th>사용자구분</th>
		                <td>
		                	<input type="text" id="userType" name="userType" 
		                		   style="width:280px" readonly="readonly" />
		                </td>
		            </tr>
		            <tr>
		                <th>활성화여부</th>
		                <td>
		                	<input type="text" id="active" name="active" 
		                		   style="width:280px" readonly="readonly" />
		                </td>
		            </tr>
		                       		            
		        </table>
		        <div class="buttons">
		        	
		        </div>
		        
		    </div>
		</div>   	
	
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
	$(function() {

		$('#search-button').on('click', function(event) {

			var memberId = $('#memberId').val();
			if (!memberId) { // !memberId : memberId == null or memberId.length == 0
				alert('검색할 아이디를 입력하세요');
				$('#memberId').focus();
			}
			
			$.ajax({
				"url": "search-member",
				"method": "get",
				"async": true,
				//"data": "memberId=" + memberId + "&datax=100",
				"data": { "memberId": memberId, "datax": 100 },				
				"success": function(resp, status, xhr) {
					if (resp.startsWith("fail to")){
						alert('검색 실패');
					}
					
					var member = resp.split("|");// 'a|b|c' -> ['a', 'b', 'c']
					$('#email').val(member[1]);
					$('#userType').val(member[2]);
					$('#regDate').val(member[3]);
					$('#active').val(member[4]);
				},
				"error": function(xhr, status, err) {
					alert(err);
				}
			});
			
		});
		
	});
	</script>

</body>
</html>














