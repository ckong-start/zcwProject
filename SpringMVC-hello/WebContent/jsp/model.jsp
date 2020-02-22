<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		request中输出mapKey1:${ requestScope.mapKey1 }<br/>
		request中输出mapKey2:${ requestScope.mapKey2 }<br/>
		<hr/>
		session中输出mapKey1:${ sessionScope.mapKey1 }<br/>
		session中输出mapKey2:${ sessionScope.mapKey2 }<br/>
		session中输出mapKey2:${ sessionScope.mapKey3 }<br/>
		<hr/>
		request中输出modelKey1:${ requestScope.modelKey1 }<br/>
		request中输出modelKey2:${ requestScope.modelKey2 }<br/>
		<hr/>
		request中输出modelMapKey1:${ requestScope.modelMapKey1 }<br/>
		request中输出modelMapKey2:${ requestScope.modelMapKey2 }<br/>
		<hr/>
		request中输出modelAndViewKey1:${ requestScope.modelAndViewKey1 }<br/>
		request中输出modelAndViewKey2:${ requestScope.modelAndViewKey2 }<br/>
		<hr/>
	</body>
</html>