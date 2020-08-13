<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" id="id"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="pwd"></td>
		</tr>
		<tr>
			<th colspan="2"><button onclick="doLogin()">로그인</button></th>
		</tr>
	</table>

	<script>
		function doLogin()
		{
			var id = document.querySelector('#id').value;
			var pwd = document.querySelector('#pwd').value;
			var params =
			{
				id : id,
				pwd : pwd
			}
			params.id = id;

			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/user/login');
			xhr.onreadystatechange = function()
			{
				if (xhr.readyState == 4)
				{
					if (xhr.status == 200)
					{
						var res = JSON.parse(xhr.responseText);
						alert(res.msg);
						if (res.result == 'ok')
						{
							location.href = '/';
						}
					}
				}
			}
			xhr.send(JSON.stringify(params));
		}
	</script>
</body>
</html>