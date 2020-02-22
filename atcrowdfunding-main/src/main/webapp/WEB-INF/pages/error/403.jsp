<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="/WEB-INF/pages/include/base_css.jsp"%>
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

.tree-closed {
	height: 40px;
}

.tree-expanded {
	height: auto;
}
</style>
</head>

<body>
	<c:set var="pageTitle" value="众筹平台 - 权限不足"></c:set>
	<!-- 登录成功的静态包含 -->
	<%@ include file="/WEB-INF/pages/include/admin_loginbar.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<!--侧边菜单栏 -->
			<%@include file="/WEB-INF/pages/include/admin_sidebar.jsp"%>
		
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">错误页面</h1>
				<span>${ requestScope.errorMsg }</span>
			</div>
		</div>
	</div>
	<c:set scope="page" var="pageName" value="错误页面"></c:set>
	<%@ include file="/WEB-INF/pages/include/base_js.jsp"%>
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
		});
	</script>
</body>
</html>
