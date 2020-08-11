package com.ajax.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/views/*")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String prefix="/WEB-INF";
	private static final String suffix=".jsp";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = prefix + request.getRequestURI() + suffix;
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
