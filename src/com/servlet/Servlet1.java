package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servlet1 extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("Servlet1");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<font size=30 color=red>servlet1</font><br>");
			out.println("<marquee>" + new Date() + "</marquee>");
			out.println("</html>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
