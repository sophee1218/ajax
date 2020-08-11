package com.ajax.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/calc2")
public class Calc2Servlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private Gson g = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String op = request.getParameter("op");
		System.out.println(num1 + "," + num2+ "," +op);
		PrintWriter pw = response.getWriter();
		pw.print(num1+","+num2+","+op);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
