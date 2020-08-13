package com.ajax.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ajax.test.service.UserService;
import com.ajax.test.service.impl.UserServiceImpl;
import com.google.gson.Gson;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idx = request.getRequestURI().lastIndexOf("/") + 1;
		String cmd = request.getRequestURI().substring(idx);
		PrintWriter pw = response.getWriter();
		if("checkid".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			Map<String,String> rMap = userService.checkId(uiId);
			pw.println(gson.toJson(rMap));
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idx = request.getRequestURI().lastIndexOf("/") + 1;
		String cmd = request.getRequestURI().substring(idx);

		if ("login".equals(cmd))
		{
			BufferedReader br = request.getReader();
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null)
			{
				sb.append(str);
			}
			Map<String, String> pMap = gson.fromJson(sb.toString(), Map.class);
			Map<String, Object> rMap = userService.doLogin(pMap);
			if ("ok".equals(rMap.get("result")))
			{
				HttpSession session = request.getSession();
				session.setAttribute("id", pMap.get("id"));
			}

			response.getWriter().append(gson.toJson(rMap));
		} else if ("logout".equals(cmd))
		{
			HttpSession session = request.getSession();
			session.invalidate();
			Map<String, String> rMap = new HashMap<>();
			rMap.put("msg", "로그아웃 되었습니다.");
			response.getWriter().append(gson.toJson(rMap));
		} else if ("join".equals(cmd))
		{

			String uiId = request.getParameter("UI_ID");
			if (uiId == null || uiId.trim().length() < 4)
			{
				throw new ServletException("올바르지 않은 아이디에욧!!");
			}
			String uiPwd = request.getParameter("UI_PWD");
			String uiName = request.getParameter("UI_NAME");
			int uiAge = Integer.parseInt(request.getParameter("UI_AGE"));
			String uiBirth = request.getParameter("UI_BIRTH");
			uiBirth = uiBirth.replace("-", "");
			String uiPhone = request.getParameter("UI_PHONE");
			String uiEmail = request.getParameter("UI_EMAIL");
			String uiNickname = request.getParameter("UI_NICKNAME");

			Map<String, Object> user = new HashMap<>();
			user.put("UI_ID", uiId);
			user.put("UI_PWD", uiPwd);
			user.put("UI_NAME", uiName);
			user.put("UI_AGE", uiAge);
			user.put("UI_BIRTH", uiBirth);
			user.put("UI_PHONE", uiPhone);
			user.put("UI_EMAIL", uiEmail);
			user.put("UI_NICKNAME", uiNickname);

			Map<String, Object> rMap = userService.joinUserInfo(user);
			request.setAttribute("rMap", rMap);
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg");
			rd.forward(request, response);

		}
	}

}
