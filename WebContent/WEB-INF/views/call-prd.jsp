<%@page import="java.sql.*"%>
<%@page import="com.ajax.test.servlet.InitServlet"%>
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
Connection con = InitServlet.getConnection();

String sql = "{call prd_incress_sal(?,?)}";
CallableStatement cs = null;
try
{
	con.prepareCall(sql);
	cs.setInt(1, 1);
	cs.setDouble(2, 1.2);
	int result = cs.executeUpdate();
	out.println("결과 : " + result);
	out.println("1번 사원이 20% 인상 되었습니다");
} catch (SQLException e)
{
	e.printStackTrace();
} finally
{
	try
	{
		cs.close();
		con.close();

	} catch (SQLException e)
	{
		e.printStackTrace();
	}
}
%>
</body>
</html>