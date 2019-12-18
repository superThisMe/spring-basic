<%@ page language="java" 
		 contentType="text/html; charset=utf-8"
    	 pageEncoding="utf-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>result page</title>
</head>
<body>

<h1>This is result page</h1>

<h2>1. [${ requestScope.myPerson.name }] [${ myPerson.phone }] [${ myPerson.email }] [${ myPerson.age }]</h2>

<h2>2. [${ person.name }] [${ person.phone }] [${ person.email }] [${ person.age }]</h2>

<h2>3. [${ requestScope.myPerson2.name }] [${ myPerson2.phone }] [${ myPerson2.email }] [${ myPerson2.age }]</h2>

</body>
</html>