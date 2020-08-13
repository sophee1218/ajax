package com.ajax.test.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class test
{
	public static void main(String[] args)
	{
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
	}
}