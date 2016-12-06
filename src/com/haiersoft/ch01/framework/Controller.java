package com.haiersoft.ch01.framework;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet{
	//声明由控制器Controller维护的Action映射，其中保存所有的Action实例
	private HashMap actionMap;
	/**
	 * Servlet初始化方法
	 */
	public void init() {
		//初始化actionMap
		actionMap = new HashMap();
		//将Action对象放入到actionMap中
		actionMap.put("add", new AddAction());
		
	}
	/**
	 * 根据path判断由哪个action执行操作
	 */
	public Action determinActionByPath(String path) {
		//如： 从http://localhost:8080/ch01/add.action 中得到add
		String actionName = path.substring(path.lastIndexOf("/") + 1,path.length() - 7);
		Action ret = (Action) actionMap.get(actionName);
		return ret;
	}
	/**
	 * 处理页面以get方式提交的申请
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//得到path,诸如：http://localhost:8080/ch01/ShowBaby.action
		String path = request.getServletPath();
		//找出Action
		Action action = this.determinActionByPath(path);
		//执行操作
		String resultView = action.execute(request, response);
		//控制页面转向
		if (!"null".equals(request)) {
			//				request.getRequestDispatcher(resultView).forward(request, response);
			response.sendRedirect(resultView);
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		//执行doGet方法
		try {
			this.doGet(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
