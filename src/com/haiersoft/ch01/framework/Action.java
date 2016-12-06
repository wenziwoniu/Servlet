package com.haiersoft.ch01.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//定义该接口的实现类必须实现的execute方法
	String execute(HttpServletRequest request,HttpServletResponse response);
}
