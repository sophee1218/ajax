<%@page import="java.sql.SQLException"%>
<%@page import="com.ajax.test.common.*"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection conn = null;
CallableStatement sc = null;
String sql = "{call increase_salary(?,?)}";
conn = Conn.getConnection();
try
{
	sc = conn.prepareCall(sql);
	sc.setString(1, "dragon");
	sc.setFloat(2, 1.05f);
	int result = sc.executeUpdate();
	if (result == 1)
	{
		System.out.println("dragon의 급여를 0.05 인상했습니다.");
	}
} catch (SQLException e)
{
	e.printStackTrace();
} finally
{
	try
	{
		sc.close();
		conn.close();
	} catch (SQLException e)
	{
		e.printStackTrace();
	}
}
%>
</body>
</html>