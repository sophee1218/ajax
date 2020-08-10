<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ajax index 화면
	<br>
	<c:if test="${sessionScope.id == null }">
		<a href="/views/login"><button>로그인</button></a>
	</c:if>
	<c:if test="${sessionScope.id != null }">
		<button onclick="doLogout()">로그아웃</button>
	</c:if>
<script>
function doLogout(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST','/user/logout');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res = JSON.parse(xhr.responseText);
				alert(res.msg);
				location.href='/';
			}
		}
	}
	xhr.send();
}
</script>
</body>
</html>