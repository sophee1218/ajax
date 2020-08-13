<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
var isChecked = false;
function checkValue(formObj){
	if(!isChecked){
		alert('중복확인을 눌러주세요');
		return false;
	}
}


function checkValue(formObj){
	var UI_ID = document.querySelector('#UI_ID');
	if(UI_ID.value.trim().length<4)
		{
			alert('아이디를 확인해주세요');
			UI_ID.focus();
			return false;
		}
	
	var UI_PWD = document.querySelector('#UI_PWD');
	if(UI_PWD.value.trim().length<4)
		{
			alert('비밀번호를 확인해주세요');
			UI_PWD.focus();
			return false;
		}
	
	var UI_NAME = document.querySelector('#UI_NAME');
	if(UI_NAME.value.trim().length<2)
		{
			alert('이름을 확인해주세요');
			UI_NAME.focus();
			return false;
		}
	
	var UI_AGE = document.querySelector('#UI_AGE');
	if(UI_AGE.value<1 || UI_AGE.value>150)
		{
			alert('나이를 확인해주세요');
			UI_AGE.focus();
			return false;
		}
	
	var UI_BIRTH = document.querySelector('#UI_BIRTH');
	if(!UI_BIRTH.value)
		{
		alert('생일을 확인해주세요');
		UI_BIRTH.focus();
			return false;
		}
	
	var UI_NICKNAME = document.querySelector('#UI_NICKNAME');
	if(UI_NICKNAME.value.trim().length<4)
		{
			alert('닉네임을 확인해주세요');
			UI_NICKNAME.focus();
			return false;
		}
	
}
function checkId(){
	var id = document.querySelector('#UI_ID').value;
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/user/checkid?ui_id=' + id);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res = JSON.parse(xhr.responseText);
				alert(xhr.msg);
				if(res.result=='true'){
					isChecked = true;
				}
			}
		}
	}
	xhr.send();
}
</script>
<form action="/user/join" method="post" onsubmit="return checkValue()">
	ID : <input type="text" name="UI_ID" id="UI_ID"> 
	<button type="button" onclick="checkId()">중복확인</button><br>
	PWD : <input type="password" name="UI_PWD" id="UI_PWD"> <br>
	Name : <input type="text" name="UI_NAME" id="UI_NAME"> <br>
	Age : <input type="number" name="UI_AGE"id="UI_AGE"> <br>
	Birth : <input type="date" name="UI_BIRTH" id="UI_BIRTH"> <br>
	Phone : <input type="text" name="UI_PHONE" id="UI_PHONE"> <br>
	Email : <input type="text" name="UI_EMAIL" id="UI_EMAIL"> <br>
	NickName : <input type="text" name="UI_NICKNAME" id="UI_NICKNAME"> <br>
	<button>회원가입</button>
</form>
</body>
</html>