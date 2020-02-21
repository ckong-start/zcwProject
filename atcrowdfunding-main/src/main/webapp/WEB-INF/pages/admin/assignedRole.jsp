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
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<c:set var="pageTitle" value="众筹平台 - 用户维护"></c:set>
			<!-- 静态包含 侧边框状态栏 -->
			<%@ include file="/WEB-INF/pages/include/admin_sidebar.jsp"%>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<!-- 静态包含 登录成功的状态栏 -->
			<%@ include file="/WEB-INF/pages/include/admin_loginbar.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">数据列表</a></li>
					<li class="active">分配角色</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-body">
						<form role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputPassword1">未分配角色列表</label><br> <select id="unAssignedRolesList"
									class="form-control" multiple size="10"
									style="width: 200px; overflow-y: auto;">
									<c:forEach items="${ unAssignedRoles }" var="role">
										<option value="${ role.id }">${ role.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<ul>
									<!-- 分配角色的按钮 -->
									<li id="assignedRoleBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
									<br>
									<!-- 取消角色的按钮 -->
									<li id="unAssignedRoleBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
										style="margin-top: 20px;"></li>
								</ul>
							</div>
							<div class="form-group" style="margin-left: 40px;">
								<label for="exampleInputPassword1">已分配角色列表</label><br> <select id="assignedRolesList"
									class="form-control" multiple size="10"
									style="width: 200px; overflow-y: auto;">
									<c:forEach items="${ assignedRoles }" var="role">
										<option value="${ role.id }">${ role.name }</option>
									</c:forEach>
								</select>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">帮助</h4>
				</div>
				<div class="modal-body">
					<div class="bs-callout bs-callout-info">
						<h4>测试标题1</h4>
						<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
					</div>
					<div class="bs-callout bs-callout-info">
						<h4>测试标题2</h4>
						<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:set var="pageName" value="用户维护"></c:set>
	<%@ include file="/WEB-INF/pages/include/base_js.jsp"%>
	<script type="text/javascript">
		//分配角色
		$("#assignedRoleBtn").click(function(){
			var $selectedOption = $("#unAssignedRolesList :selected");
			if($selectedOption.length == 0){
				layer.msg("请选择一个角色");
				return;
			}
			//获取未分配的角色列表中选中的角色集合  和  管理员id
			var adminId = "${ param.id }";//EL表达式给服务器解析，比浏览器优先解析
			var idsArr = new Array();
			//遍历每个选中的角色，this代表正在遍历的option
			$selectedOption.each(function(){
				idsArr.push(this.value);
			});
			var idsStr = idsArr.join(",");
			$.ajax({
				type:"POST",
				url:"${PATH}/admin/assignedRolesToAdmin",
				data:{"ids":idsStr, "adminId":adminId},
				success:function(result){
					if(result == "ok"){
						//分配角色成功
						layer.msg("分配角色成功");
						$selectedOption.appendTo("#assignedRolesList");
					}
				}
			});
		});
		
		//取消分配角色
		$("#unAssignedRoleBtn").click(function(){
			var $selectedOption = $("#assignedRolesList :selected");
			if($selectedOption.size() == 0){
				layer.msg("请选择一个角色");
				return;
			}
			//获取必要的参数
			var adminId = "${ param.id }";
			var idsArr = new Array();
			$selectedOption.each(function(){
				idsArr.push(this.value);
			});
			var idsStr = idsArr.join();
			$.ajax({
				type:"POST",
				url:"${PATH}/admin/unAssignedRolesToAdmin",
				data:{"ids":idsStr, "adminId":adminId},
				success:function(result){
					if(result == "ok"){
						layer.msg("取消成功");
						$selectedOption.appendTo("#unAssignedRolesList");
					}
				}
			});
		});
	
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
