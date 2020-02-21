<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/script/jquery-1.7.2.js"></script>
		<script type="text/javascript">
			$(function(){
				$("button").click(function(){
					
				});
			});
		</script>
	</head>
	<body>
		这是文件上传页面<br/><br/>
		<form action="${ pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data">
			用户名：<input type="text" name="username" /><br/>
			头像：<input type="file" name="photo" /><br/>
			<input type="submit" name="提交" />
		</form>
		<hr/>
		<a href="${ pageContext.request.contextPath }/download" target="_self">下载</a>
	</body>
</html>