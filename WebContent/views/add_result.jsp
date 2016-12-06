<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8S">
<title>结果页面</title>
</head>
<body bgcolor="ffffc0">
<h1>算术计算器</h1>
	<table>
		<tbody>
			<tr>
				<td>第一个数:</td>
				<td>${param.num1 }</td>
			</tr>
			<tr>
				<td>第二个数:</td>
				<td>${param.num2 }</td>
			</tr>
			<tr>
				<td>结果:</td>
				<!-- 使用EL表达式显示结果 -->
				<td>${requestScope.result}</td>
			</tr>
		</tbody>
	</table>
	<button onclick = "history.go(-1);">返回</button>
</body>
</html>