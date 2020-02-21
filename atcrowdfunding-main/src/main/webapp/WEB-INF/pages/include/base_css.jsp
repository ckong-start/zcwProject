<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入c标签库 -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- 使用base+相对路径  解决路径失效的问题：
    		当项目启动时，向全局上下文域中存入上下文路径
    			以后使用时直接通过EL从 域中获取
	     -->

<base href="${PATH }/static/">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/carousel.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="ztree/zTreeStyle.css">
	<%--
		<%@ include file="/WEB-INF/pages/include/base_css.jsp" %>
		<!-- 静态包含 登录成功的状态栏 -->
     	<%@ include file="/WEB-INF/pages/include/admin_loginbar.jsp" %>
     	<!-- 静态包含 侧边框状态栏 --> 	
     	<%@ include file="/WEB-INF/pages/include/admin_sidebar.jsp" %>
		<%@ include file="/WEB-INF/pages/include/base_js.jsp" %>
		<%@ include file="/WEB-INF/pages/include/base_footer.jsp" %>
	 --%>