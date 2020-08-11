package com.ajax.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajax.test.common.Conn;
import com.ajax.test.dao.UserInfoDAO;

public class UserInfoDAOImpl implements UserInfoDAO
{

	@Override
	public int insertUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try
		{
			con = Conn.open();
			String sql = "insert into user_info(\r\n" + "UI_NUM,\r\n" + "UI_NAME,\r\n" + "UI_AGE,\r\n" + "UI_BIRTH,\r\n"
					+ "UI_ID,\r\n" + "UI_PASSWORD,\r\n" + "UI_PHONE,\r\n" + "UI_EMAIL,\r\n" + "UI_CREDAT,\r\n"
					+ "UI_NICKNAME)\r\n" + "values(seq_ui_num,?,?,?,?,?,?,?,sysdate,?)";

			ps = con.prepareStatement(sql);
			ps.setObject(1, ui.get("UI_NAME"));
			ps.setInt(2, (int) ui.get("UI_AGE"));
			ps.setObject(3, ui.get("UI_BIRTH"));
			ps.setObject(4, ui.get("UI_ID"));
			ps.setObject(5, ui.get("UI_PASSWORD"));
			ps.setObject(6, ui.get("UI_PHONE"));
			ps.setObject(7, ui.get("UI_EMAIL"));
			ps.setObject(8, ui.get("UI_NICKNAME"));

			result = ps.executeUpdate();
			con.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			Conn.close(ps, con);
		}
		return result;
	}

	@Override
	public int updatetUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try
		{
			con = Conn.open();
			String sql = "update user_info set(\r\n" + "UI_NAME = ?,\r\n" + "UI_AGE = ?,\r\n" + "UI_BIRTH = ?,\r\n"
					+ "UI_PASSWORD = ?,\r\n" + "UI_PHONE = ?,\r\n" + "UI_EMAIL = ?,\r\n" + "UI_NICKNAME = ?,\r\n"
					+ "where ui_num = ?\r\n" + ")";

			ps = con.prepareStatement(sql);

			ps.setObject(1, ui.get("UI_NAME"));
			ps.setInt(2, (int) ui.get("UI_AGE"));
			ps.setObject(3, ui.get("UI_BIRTH"));
			ps.setObject(4, ui.get("UI_PASSWORD"));
			ps.setObject(5, ui.get("UI_PHONE"));
			ps.setObject(6, ui.get("UI_EMAIL"));
			ps.setObject(7, ui.get("UI_NICKNAME"));
			ps.setObject(8, ui.get("ui_num"));

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			Conn.close(ps, con);
		}
		return result;

	}

	@Override
	public int deleteUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try
		{
			con = Conn.open();
			String sql = "delete from user_info where ui_num=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1, "ui_num");
			result = ps.executeUpdate();

			result = ps.executeUpdate();
			con.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			Conn.close(ps, con);
		}
		return result;

	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			con = Conn.open();
			String sql = "select ui_num,ui_name,ui_age,ui_birth,ui_id,ui_password,ui_phone,ui_email,ui_credat,ui_nickname from user_info where ui_num = ?;";

			ps = con.prepareStatement(sql);
			ps.setObject(1, ui.get("ui_num"));
			rs = ps.executeQuery();

			if (rs.next())
			{
				Map<String, Object> ui_map = new HashMap<>();
				ui_map.put("ui_num", rs.getObject("ui_num"));
				ui_map.put("ui_name", rs.getObject("ui_name"));
				ui_map.put("ui_age", rs.getObject("ui_age"));
				ui_map.put("ui_birth", rs.getObject("ui_birth"));
				ui_map.put("ui_id", rs.getObject("ui_id"));
				ui_map.put("ui_password", rs.getObject("ui_password"));
				ui_map.put("ui_phone", rs.getObject("ui_phone"));
				ui_map.put("ui_email", rs.getObject("ui_email"));
				ui_map.put("ui_credat", rs.getObject("ui_credat"));
				ui_map.put("ui_nickname", rs.getObject("ui_nickname"));

				return ui_map;

			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			Conn.close(rs, ps, con);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> selectUserInfoList(Map<String, Object> ui)
	{
		List<Map<String, Object>> uiList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			con = Conn.open();
			String sql = "select ui_num,ui_name,ui_age,ui_birth,ui_id,ui_password,ui_phone,ui_email,ui_credat,ui_nickname from user_info";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next())
			{
				Map<String, Object> ui_map = new HashMap<>();
				ui_map.put("ui_num", rs.getObject("ui_num"));
				ui_map.put("ui_name", rs.getObject("ui_name"));
				ui_map.put("ui_age", rs.getObject("ui_age"));
				ui_map.put("ui_birth", rs.getObject("ui_birth"));
				ui_map.put("ui_id", rs.getObject("ui_id"));
				ui_map.put("ui_password", rs.getObject("ui_password"));
				ui_map.put("ui_phone", rs.getObject("ui_phone"));
				ui_map.put("ui_email", rs.getObject("ui_email"));
				ui_map.put("ui_credat", rs.getObject("ui_credat"));
				ui_map.put("ui_nickname", rs.getObject("ui_nickname"));
				uiList.add(ui_map);

			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			Conn.close(rs, ps, con);
		}
		return uiList;
	}

	public static void main(String[] args)
	{
		UserInfoDAO userInfoDao = new UserInfoDAOImpl();
		Map<String, Object> ui = new HashMap<>();
		ui.put("UI_NAME", "신송희");
		ui.put("UI_age", 30);
		ui.put("UI_birth", "911218");
		ui.put("UI_id", "test");
		ui.put("UI_password", "test");
		ui.put("UI_phone", "01087117645");
		ui.put("UI_email", "test");
		ui.put("UI_nickname", "송송");
		userInfoDao.insertUserInfo(ui);
		
	}
}
