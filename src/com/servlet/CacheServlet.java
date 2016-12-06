package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CacheServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			PrintWriter out = response.getWriter();
			long now = System.currentTimeMillis();
			out.println("doGet:" + now); 
			System.out.println("doGet:" + now);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected long getLastModified(HttpServletRequest request) {
		long now = System.currentTimeMillis();
		System.out.println("getLastModified:" + now);
		return now;
	}
}
