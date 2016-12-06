<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 浏览器访问地址：http://192.168.1.67:8080/Servlet/views/add.jsp?name="wang" -->
<%@page import="java.io.PrintWriter;"%> 
<%
	String path = request.getContextPath(); // "/Servlet" 
	String uri = request.getRequestURI();   //  "/Servlet/views/add.jsp"
	StringBuffer url = request.getRequestURL();  // "http://192.168.1.67:8080/Servlet/views/add.jsp "
	String scheme = request.getScheme();  // "http"
	String serverName = request.getServerName();  // "192.168.1.67 " 	 
	int serverPort = request.getServerPort();   // "8080"
	String serverpath = request.getServletPath();  // "/views/add.jsp"
	String queryString = request.getQueryString(); // "name="wang""
	String param1 = request.getParameter("name");  // "wang"
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";      // "http://192.168.1.67:8080/Servlet/"
	//http://192.168.1.67:8080/Servlet/views/add.jsp?name="wang"
	String allPath =  request.getRequestURL() + "?" +request.getQueryString();  
	PrintWriter writer =response.getWriter();  
	//writer.write(path);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算器</title>
</head>
<body bgcolor="#ffffc0">
	<script type="text/javascript">
		var projectName='<%=url%>';
		//alert(projectName);
	</script>
	<form id = "calcForm" method = "post" action = "add.action">
		<table>
			<tr>
				<td>第一个数</td>
				<td><input type = "text" name = "num1" /></td>
			</tr>
			<tr>
				<td>第二个数</td>
				<td><input type = "text" name = "num2" /></td>
			</tr>
			<tr>
				<td><input type = "submit" value = "加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>