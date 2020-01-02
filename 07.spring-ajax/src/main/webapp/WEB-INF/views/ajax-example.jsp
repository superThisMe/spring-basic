<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
	<button id="sync-request">동기 방식 요청</button>
	&nbsp;&nbsp;
	<button id="async-request">비동기 방식 요청 (XMLHttpRequest 직접 사용)</button>
	&nbsp;&nbsp;
	<button id="async-request2">비동기 방식 요청2 (JQuery 사용)</button>
	<hr>
	<div><%=new Date().toString()%></div>
	<div id="container">
		<div id="message"><%=new Date().toString()%></div>
	</div>
</body>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function() {
		
		$('#sync-request').on('click', function(event) {
			location.href = "get-time";
		});

		var proxy = new XMLHttpRequest();

		$('#async-request').on('click', function(event) {

			proxy.open("GET", "get-time", true) // 'GET' : 요청방식, 'get-time' : 요청 경로, true : 비동기 여부
			proxy.onreadystatechange = processResult; // 비동기 응답이 도착했을 때 호출될 function
			proxy.send(null); // 비동기 요청 시작

		});

		$('#async-request2').on('click', function(event) {

			$.ajax({ // JQuery의 ajax 요청 helper function
				"url": "get-time", 
				"method": "GET",
				"async": true,
				"success": function(data, status, xhr) {
					$('#message').text(data);
				}, 
				"error": function(xhr, status, err) {
					alert("error: " + err.message);
				}
			});
			
		});

		function processResult() {
			alert(proxy.readyState);
			if (proxy.readyState == 4) {
				if (proxy.status == 200) {
					var result = proxy.responseText;
					$('#message').text(result);
				}
			}

		};

	});
</script>

</html>





