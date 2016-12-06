package com.servlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileAccessServlet extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
//			FileInputStream fis = new FileInputStream("it315.properties");
			InputStream fis = getClass().getResourceAsStream("/it315.properties");
			Properties props = new Properties();
			props.load(fis);
			out.println("database=" + props.getProperty("database"));
			out.println("username=" + props.getProperty("username"));
			out.println("password=" + props.getProperty("password"));
			//fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
