package com.haiersoft.ch01.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAction implements Action {
	//业务逻辑对象
	private Calculator biz;
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		//获得页面输入
		double num1 = Double.parseDouble(request.getParameter("num1"));
		double num2 = Double.parseDouble(request.getParameter("num2"));
		//调用业务逻辑方法，获得返回值
		biz = new Calculator();
		double result = biz.add(num1, num2);
		//将结果保存在request中，以便在页面中得到该值
		request.setAttribute("result", result);
		return "add_result.jsp";
	}

}
