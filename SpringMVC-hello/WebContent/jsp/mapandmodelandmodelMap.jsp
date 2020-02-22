<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		map保存到request中的数据mapk1：${ requestScope.mapK1 }<br/>
		model保存到request中的数据modelk1：${ requestScope.modelK1 }<br/>
		modelMap保存到request中的数据modelMapk1：${ requestScope.modelMapK1 }<br/>
		
		<hr/>
		modelAndView保存到request中的数据modelAndViewKey1：${ modelAndViewKey1 }<br/>
		modelAndView保存到request中的数据modelAndViewKey2：${ modelAndViewKey2 }<br/>
	</body>
</html>