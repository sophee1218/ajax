package com.ajax.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajax.test.dao.UserInfoDAO;
import com.ajax.test.servlet.InitServlet;

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
			con = InitServlet.getConnection();
			String sql = "insert into user_info(UI_NUM, UI_NAME, UI_AGE, UI_BIRTH, UI_ID, UI_PWD, UI_PHONE, UI_EMAIL, ui_CREDAT, "
					+ " UI_NICKNAME) values(seq_UI_NUM.nextval,?,?,?,?,?,?,?,sysdate,?)";

			ps = con.prepareStatement(sql);
			ps.setObject(1, ui.get("UI_NAME"));
			ps.setInt(2, (int) ui.get("UI_AGE"));
			ps.setObject(3, ui.get("UI_BIRTH"));
			ps.setObject(4, ui.get("UI_ID"));
			ps.setObject(5, ui.get("UI_PWD"));
			ps.setObject(6, ui.get("UI_PHONE"));
			ps.setObject(7, ui.get("UI_EMAIL"));
			ps.setObject(8, ui.get("UI_NICKNAME"));
			result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			InitServlet.close(ps, con);
		}
		return 0;
	}

	@Override
	public int updatetUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try
		{
			con = InitServlet.getConnection();
			String sql = "update user_info set(UI_NAME = ?, UI_AGE = ?, UI_BIRTH = ?, UI_PWD = ?, UI_PHONE = ?, UI_EMAIL = ?, UI_NICKNAME = ?,"
					+ " where UI_NUM = ?)";

			ps = con.prepareStatement(sql);

			ps.setObject(1, ui.get("UI_NAME"));
			ps.setInt(2, (int) ui.get("UI_AGE"));
			ps.setObject(3, ui.get("UI_BIRTH"));
			ps.setObject(4, ui.get("UI_PWD"));
			ps.setObject(5, ui.get("UI_PHONE"));
			ps.setObject(6, ui.get("UI_EMAIL"));
			ps.setObject(7, ui.get("UI_NICKNAME"));
			ps.setObject(8, ui.get("UI_NUM"));
			result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			InitServlet.close(ps, con);
		}
		return 0;

	}

	@Override
	public int deleteUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try
		{
			con = InitServlet.getConnection();
			String sql = "delete from user_info where UI_NUM=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1, ui.get("UI_NUM"));
			result = ps.executeUpdate();
			con.commit();
			return result;
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			InitServlet.close(ps, con);
		}
		return 0;

	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> ui)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> ui_map = new HashMap<>();
		try
		{
			con = InitServlet.getConnection();
			String sql = "select UI_NUM,UI_NAME,UI_AGE,UI_BIRTH,UI_ID,UI_PWD,UI_PHONE,UI_EMAIL,ui_CREDAT,UI_NICKNAME from user_info where UI_NUM = ?";

			ps = con.prepareStatement(sql);
			ps.setObject(1, ui.get("UI_NUM"));
			rs = ps.executeQuery();
			if (rs.next())
			{
				ui_map.put("UI_NUM", rs.getObject("UI_NUM"));
				ui_map.put("UI_NAME", rs.getObject("UI_NAME"));
				ui_map.put("UI_AGE", rs.getObject("UI_AGE"));
				ui_map.put("UI_BIRTH", rs.getObject("UI_BIRTH"));
				ui_map.put("UI_ID", rs.getObject("UI_ID"));
				ui_map.put("UI_PWD", rs.getObject("UI_PWD"));
				ui_map.put("UI_PHONE", rs.getObject("UI_PHONE"));
				ui_map.put("UI_EMAIL", rs.getObject("UI_EMAIL"));
				ui_map.put("ui_CREDAT", rs.getObject("ui_CREDAT"));
				ui_map.put("UI_NICKNAME", rs.getObject("UI_NICKNAME"));
				
				return ui_map;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			InitServlet.close(rs, ps, con);
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
			con = InitServlet.getConnection();
			String sql = "select UI_NUM,UI_NAME,UI_AGE,UI_BIRTH,UI_ID,UI_PWD,UI_PHONE,UI_EMAIL,ui_CREDAT,UI_NICKNAME from user_info";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				Map<String, Object> ui_map = new HashMap<>();
				ui_map.put("UI_NUM", rs.getObject("UI_NUM"));
				ui_map.put("UI_NAME", rs.getObject("UI_NAME"));
				ui_map.put("UI_AGE", rs.getObject("UI_AGE"));
				ui_map.put("UI_BIRTH", rs.getObject("UI_BIRTH"));
				ui_map.put("UI_ID", rs.getObject("UI_ID"));
				ui_map.put("UI_PWD", rs.getObject("UI_PWD"));
				ui_map.put("UI_PHONE", rs.getObject("UI_PHONE"));
				ui_map.put("UI_EMAIL", rs.getObject("UI_EMAIL"));
				ui_map.put("ui_CREDAT", rs.getObject("ui_CREDAT"));
				ui_map.put("UI_NICKNAME", rs.getObject("UI_NICKNAME"));
				uiList.add(ui_map);
			}
			return uiList;
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			InitServlet.close(rs, ps, con);
		}
		return null;
	}

	@Override
	public Map<String, Object> selectUserInfoByUiId(String uiId)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try
		{
			con = InitServlet.getConnection();
			String sql = "select * from user_info where UI_ID = ?";

			ps = con.prepareStatement(sql);
			ps.setObject(1, uiId);
			rs = ps.executeQuery();

			Map<String, Object> ui_map = new HashMap<>();
			if (rs.next())
			{
				ui_map.put("UI_NUM", rs.getObject("UI_NUM"));
				ui_map.put("UI_NAME", rs.getObject("UI_NAME"));
				ui_map.put("UI_AGE", rs.getObject("UI_AGE"));
				ui_map.put("UI_BIRTH", rs.getObject("UI_BIRTH"));
				ui_map.put("UI_ID", rs.getObject("UI_ID"));
				ui_map.put("UI_PWD", rs.getObject("UI_PWD"));
				ui_map.put("UI_PHONE", rs.getObject("UI_PHONE"));
				ui_map.put("UI_EMAIL", rs.getObject("UI_EMAIL"));
				ui_map.put("UI_CREDAT", rs.getObject("UI_CREDAT"));
				ui_map.put("UI_NICKNAME", rs.getObject("UI_NICKNAME"));

				return ui_map;

			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			InitServlet.close(rs, ps, con);
		}
		return null;
	}

	public static void main(String[] args)
	{
		UserInfoDAO userInfoDao = new UserInfoDAOImpl();
		Map<String, Object> ui = new HashMap<>();
//		ui.put("UI_NAME", "신송희");
//		ui.put("UI_AGE", 30);
//		ui.put("UI_BIRTH", "911218");
//		ui.put("UI_ID", "test");
//		ui.put("UI_PWD", "test");
//		ui.put("UI_PHONE", "01087117645");
//		ui.put("UI_EMAIL", "test");
//		ui.put("UI_NICKNAME", "송송");
//		ui.put("UI_NUM", 1);

		System.out.println(userInfoDao.selectUserInfoList(ui));
	}
}
