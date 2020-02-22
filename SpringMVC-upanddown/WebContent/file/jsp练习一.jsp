<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- 		练习一：输出n*m的表格（n是行数，m是列数） -->
		<table>
			<% for (int n = 1; n <= 3; n++) { %>
			<tr>
				<% for (int m = 1; m <= 3; m++) { %>
					<td><%="行是：" + n + " , 列是：" + m %></td>
				<% } %>
			</tr>
			<% } %>
		</table>
		
	</body>
</html>