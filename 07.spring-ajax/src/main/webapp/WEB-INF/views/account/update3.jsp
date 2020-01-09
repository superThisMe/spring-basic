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
				"url": "search-member3",
				"method": "get",
				"async": true,
				//"data": "memberId=" + memberId + "&datax=100",
				"data": { "memberId": memberId, "datax": 100 },	
				"dataType": "json", // dataType : 응답 컨텐츠의 종류 지정			
				"success": function(member, status, xhr) {
					if (member.error){
						alert(member.error);
						return;
					}
										
					$('#email').val(member.email);
					$('#userType').val(member.userType);
					
					//var d = new Date(member.regDate);
					//$('#regDate').val( (1900 + d.getYear()) + "-" + (d.getMonth() + 1) + "-" + d.getDate() );
					$('#regDate').val( member.regDate );
					
					$('#active').val(member.active ? '활성사용자' : '비활성사용자');
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














