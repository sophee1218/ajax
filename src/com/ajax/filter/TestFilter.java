package com.ajax.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class TestFilter implements Filter {
    public TestFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();
		int idx = url.lastIndexOf(".");
		if(idx!=-1) {
			String ext = url.substring(idx+1,url.length());
			if("html".equals(ext)) {
				chain.doFilter(request, response);
				return;
			}
		}
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String excludePatterns = fConfig.getInitParameter("excludes");
		System.out.println(excludePatterns);
	}

}
