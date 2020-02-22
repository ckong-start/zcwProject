<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		requestScope的key1：${ requestScope.requestKey1 }<br/>
		requestScope的key2：${ requestScope.requestKey2 }<br/>
		<hr/>
		sessionScope的key1：${ sessionScope.sessionKey1 }<br/>
		sessionScope的key2：${ sessionScope.sessionKey2 }<br/>
		<hr/>
		servletContext的key1：${ servletContextKey1 }<br/>
		servletContext的key2：${ applicationScope.servletContextKey2 }<br/>
		<hr/>
	</body>
</html>