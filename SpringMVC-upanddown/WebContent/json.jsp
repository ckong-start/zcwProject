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
				$("#sendJson").click(function(){
//		 			contentType表示提交的数据类型：
					// 	application/x-www-form-urlencoded
					// 是把提交的数据以name=value&name=value的形式进行拼接，然后url编码 发送给服务器。
					// application/json 表示发送的数据是JSON格式
					// 发送是JSON字符串 {'key':value,'key':value}
					var personJson = {
							'id':100,
							'name':"qwe123"
					};
				// json数据有两种格式，一种是json对象，一种是json字符串,此时的personJson是Object类型
				//	若要将json转化为字符串，需要用到JSON.stringify(json)方法。
					$.ajax({
						url:"${pageContext.request.contextPath}/addPerson",
						data:JSON.stringify(personJson),
						type:"POST",
						success:function(data){
							alert("返回的数据：" + data);
						},
						dataType:"json",
						contentType:"application/json"
					});
				});
			});
		</script>
	</head>
	<body>
		<button id="sendJson">发送请求</button>
	</body>
</html>