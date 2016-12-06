package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jms.client.InitialClientContext;


public class ConfigTestServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(ConfigTestServlet.class.getName());
	public void init(ServletConfig config) {
		
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println("html");
			out.println("Servlet名称为:" + getServletName() + "<br>");
			Enumeration enumeration = getInitParameterNames();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				String value = getInitParameter(key);
				out.println(key + "=" + value + "<br>");
			}
			ServletContext context = getServletContext();
			String path = context.getRealPath("/");
			out.println(path);
			out.println("<html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
