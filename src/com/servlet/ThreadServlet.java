package com.servlet;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ThreadServlet extends HttpServlet implements SingleThreadModel{
	
	private int count = 0;
	public void service(HttpServletRequest request, HttpServletResponse response) {
		count++;
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("这是第" + count + "次访问" + "线程名称为:" + Thread.currentThread().getName());
	}
}
