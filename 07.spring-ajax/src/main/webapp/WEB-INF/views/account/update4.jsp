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
				<form autocomplete="off">
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
		        </form>
		    </div>
		</div>   	
	
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
	$(function() {

		$('#search-button').on('click', function(event) {

			event.preventDefault();
			//event.stopPropagation();
			
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
		// end of search event handler

		////////////////////////////////////
		
		function getTop() {
			var	t =	document.getElementById("memberId");
	
			var	topPos = 2 + t.offsetHeight;//현재 요소의 높이
			while(t.tagName.toLowerCase() != "body" && 
				  t.tagName.toLowerCase() != "html") {
				topPos += t.offsetTop;//offsetTop : 상위 요소와의 y축 거리
				t = t.offsetParent;	//상위 요소를 현재 요소에 대입
			}
			return topPos;
		}
	  
		function getLeft() {
			var	t =	document.getElementById("memberId");
	
			var	leftPos	= 0;
			while(t.tagName.toLowerCase() != "body" && 
				  t.tagName.toLowerCase() != "html") {
				leftPos += t.offsetLeft;//t와 상위 요소 사이의 x축 거리
				t = t.offsetParent;//t의 부모 요소
			}
			return leftPos;
		}
		
		var outerBox = $('<div></div>');
		outerBox.css({
			"border": "solid 1px",
			"background-color": "white",
			"width": "230px",
			"display": "none",
			"position": "absolute",
			"left": getLeft(),
			"top": getTop()
		});
		$('body').append(outerBox);
		
		$('body').on('click', function(event) {
			outerBox.css('display', 'none');
		})

		$('#memberId').on('keyup', function(event) {
			//console.log( $(this).val() );
			var id = $(this).val();

			if (id.length == 0) {
				return;
			}
			
			$.ajax({
				"url": "get-suggestions",
				"method": "get",
				"async": true,
				"data": { "id" : id },
				"success": function(resp, status, xhr) {
					//eval : 문자열 -> javascript code
					eval("var list = " + resp);

					if (list.length == 0) {
						outerBox.css("display", "none");
						return;
					}

					outerBox.empty();
					
					for (var i = 0; i < list.length; i++) {
						var innerBox = $('<div></div>');
						innerBox.text(list[i]);
						innerBox.css({
							"padding": "5px"
						});
						innerBox.hover(function(event) {
							$(this).css('background-color', 'lightgray');
						}, function(event) {
							$(this).css('background-color', 'white');
						});
						innerBox.on('click', function(event) {							
							$('#memberId').val($(this).text())
							outerBox.css('display', 'none');
						});
						outerBox.append(innerBox);
					}
					
					outerBox.css("display", "block");
					
				},
				"error": function(xhr, status, err) {
					console.log(err);
				}
			});
			
		});
		
	});
	</script>

</body>
</html>














