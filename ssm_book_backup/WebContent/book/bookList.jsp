<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>图书列表</title>
		<%
			String basePath = request.getScheme() 
								+ "://"
								+ request.getServerName()
								+ ":"
								+ request.getServerPort()
								+ request.getContextPath()
								+ "/";
		%>
		<base href="<%=basePath %>" />
		<style type="text/css">
			table {
				border: 1px blue solid;
				width: 700px;
				border-collapse: collapse;
			}
			td,th{
				border: 1px green solid;
			}
			div.menu {
				width: 700px;
				text-align: right;
			}
		</style>
	</head>
	<body>
		<center>
			<h2>图书列表管理页面</h2>
			<div class="menu"><a href="#">添加图书</a></div>
			<table>
				<tr bgcolor="#FF8888">
					<th>书名</th>
					<th>作者</th>
					<th>价格</th>
					<th>销量</th>
					<th>库存</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>C++编程思想</td>
					<td>刚哥</td>
					<td>99.8</td>
					<td>521</td>
					<td>23</td>
					<td><a href="#">删除</a>、<a href="#">修改</a></td>
				</tr>
				<tr>
					<td>Java编程思想</td>
					<td>国哥</td>
					<td>99.8</td>
					<td>5121</td>
					<td>123</td>
					<td><a href="#">删除</a>、<a href="#">修改</a></td>
				</tr>
				<tr>
					<td>JavaScript编程宝典</td>
					<td>国哥</td>
					<td>99.8</td>
					<td>5121</td>
					<td>123</td>
					<td><a href="#">删除</a>、<a href="#">修改</a></td>
				</tr>
				<tr>
					<td>C++编程思想</td>
					<td>刚哥</td>
					<td>99.8</td>
					<td>521</td>
					<td>23</td>
					<td><a href="#">删除</a>、<a href="#">修改</a></td>
				</tr>
				<tr>
					<td>Java编程思想</td>
					<td>国哥</td>
					<td>99.8</td>
					<td>5121</td>
					<td>123</td>
					<td><a href="#">删除</a>、<a href="#">修改</a></td>
				</tr>
				<tr>
					<td>JavaScript编程宝典</td>
					<td>国哥</td>
					<td>99.8</td>
					<td>5121</td>
					<td>123</td>
					<td><a href="#">删除</a>、<a href="#">修改</a></td>
				</tr>
			</table>
		</center>
	</body>
</html>