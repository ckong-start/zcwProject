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

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>

<body>
	<c:set var="pageTitle" value="众筹平台 - 角色维护"></c:set>
	<!-- 静态包含 登录成功的状态栏 -->
	<%@ include file="/WEB-INF/pages/include/admin_loginbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<!-- 静态包含 侧边框状态栏 -->
			<%@ include file="/WEB-INF/pages/include/admin_sidebar.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<!-- 给查询条件添加id属性 -->
									<input id="queryCondition" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<!-- 给查询按钮添加id属性 -->
							<button id="queryConditionBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="delRoleBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button id="addRoleBtn" type="button" class="btn btn-primary"
							style="float: right;">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox"></th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 遍历角色的地方 -->
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<ul class="pagination">
												<!-- 分页的地方 -->
											</ul>
										</td>
									</tr>

								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 角色信息的模态框开始 -->
	<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel"></h4>
	      </div>
	      <div class="modal-body"><span id="errorMsg" style="color:red;"></span>
	        <form id="modalForm">
	        	<!--重置表单-->
            	<input type="reset" hidden/>
            	<!-- 隐藏域发送id -->
	        	<input type="hidden" name="id" />
	          <div class="form-group">
	            <label for="message-text" class="control-label">角色名称</label>
	            <input name="name" type="text" class="form-control"/>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="roleModalBtn"  type="button" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 角色分配权限的模态框 -->
	<div class="modal fade" id="roleToAssignPermissionsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">分配权限</h4>
	      </div>
	         <div class="modal-body">
        		<!-- 提供ztree树容器 -->
        		<ul id="treeDemo" class="ztree"></ul>
			</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="roleToAssignPermissionsModalBtn"  type="button" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 角色信息的模态框结束 -->
	<c:set scope="page" var="pageName" value="角色维护"></c:set>
	<%@ include file="/WEB-INF/pages/include/base_js.jsp"%>
	<script type="text/javascript">
	
		//定义一个全局变量来存放总页码
		var pages;
		//定义一个全局变量存放搜索的条件
		var condition;
	
		//页面加载完成后，先加载第一页的角色数据
		initTableRoles(1);
	
		//页面加载完成后，发送Ajax请求获取分页的角色数据，提取成为一个方法
		function initTableRoles(pageNum, condition){
			$.ajax({
				type:"GET",
				url:"${PATH}/role/getRoles",
				data:{"pageNum":pageNum, "condition":condition},
				success:function(pageInfo){
					//此处可以获取到pageInfo对象，就可以拿到总页码，也就可以将总页码存入上面的定义的pages变量中，这样就可以做到在几次请求中获取一个变量的方式
					pages = pageInfo.pages;
					//1.分页数据
					initTBodyRole(pageInfo);
					//2.生成分页导航栏(将分页添加到tfoot里面的class属性是pagination的地ul中)
					initTFootNavUI(pageInfo);
				}
			});	
		}
		//解析分页导航栏显示到tfoot中方法的抽取
		function initTFootNavUI(pageInfo){
			var $navUI = $("tfoot .pagination");
			$navUI.empty();//清空之前的内容
			//2.1生成上一页
			//console.log(pageInfo);
			if(pageInfo.isFirstPage){//如果是第一页就禁用
				$navUI.append('<li class="disabled"><a href="javascript:void(0);">上一页</a></li>');
			}else{
				$navUI.append('<li><a class="navA" pageNum="' + (pageInfo.pageNum - 1) + '" href="#">上一页</a></li>');
			}
			//2.2生成中间页,navigatepageNums为分页页码的数组
			$.each(pageInfo.navigatepageNums, function(){
				//判断是否为当前页
				if(this == pageInfo.pageNum){
					$navUI.append('<li class="active"><a href="javascript:void(0);"><span class="currentPageNum">' + this + '</span><span class="sr-only">(current)</span></a></li>');
				}else{
					$navUI.append('<li><a class="navA" pageNum="' + this + '" href="#">' + this + '</a></li>');
				}
			});
			//2.3生成下一页
			if(pageInfo.isLastPage){//如果是最后一页就禁用
				$navUI.append('<li class="disabled"><a href="javascript:void(0);">下一页</a></li>');
			}else{
				$navUI.append('<li><a class="navA" pageNum="' + (pageInfo.pageNum + 1) + '" href="#">下一页</a></li>');
			}
			//分页导航栏的单击事件
			$("tfoot .pagination .navA").click(function(){
				var pageNum = $(this).attr("pageNum");
				//获取搜索的条件
				var condition = $("#queryCondition").val();
				initTableRoles(pageNum, condition);
				return false;//取消a标签的默认行为
			});
		}
			
		//解析分页数据列表显示到tbody中方法的抽取
		function initTBodyRole(pageInfo){
			//解析pageInfo生成tbody内的role列表
			//找到要插入数据的tbody
			var $tbody = $("tbody");
			$tbody.empty();//清空之前的数据
			//遍历list集合,function是遍历的每个元素都调用的方法（其中js中提供了“\”用来折行,即一行没有写完将下一行的进行拼接）
			/* 当前遍历第一页的数据
				list: Array(3)
					0: {id: 1, name: "PM - 项目经理"}
					1: {id: 2, name: "SE - 软件工程师"}
					2: {id: 3, name: "PG - 程序员"}
			*/
			$.each(pageInfo.list, function(i){//i表示遍历元素的索引值，从0开始计算
				//this	代表list集合中的一个role的json对象，即{id: 3, name: "PG - 程序员"}
				$tbody.append('<tr>\
						<td>' + (i + 1) + '</td>\
						<td><input id="'+this.id+'" type="checkbox"></td>\
						<td>' + this.name + '</td>\
						<td>\
							<button onclick="assignPermissionsToRole('+this.id+')" type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>\
							<button type="button" id="' + this.id + '" name="' + this.name + '" class="btn btn-primary btn-xs updateRoleBtn"><i class=" glyphicon glyphicon-pencil"></i></button>\
							<button type="button" id="' + this.id + '" name="' + this.name + '" class="btn btn-danger btn-xs delRoleBtn"><i class=" glyphicon glyphicon-remove"></i></button>\
						</td>\
					</tr>');
			});
		}
		
		//为单个删除绑定单击事件,另外说明：绑定事件的标签是异步请求生成的，但是绑定事件是在js的主线线程中执行的，异步请求还没有完成，事件绑定就已经执行
		//解决方案1：将事件绑定写在异步请求标签生成之后
		//解决方案2：使用动态委派绑定事件	祖先元素.delegate("后代元素", "事件类型列表", 事件处理函数){}	无论现有的标签还是后加入的标签都会绑定上事件
		$("tbody").delegate(".delRoleBtn", "click", function(){
			//获取删除角色的id和name属性
			var roleId = this.id;
			var roleName = this.name;
			//找到要删除角色的行，将其行移除
			var $tr = $(this).parents("tr");
			layer.confirm("确定要删除【"+roleName+"】", {title:"删除确认"}, function(idx){
				$.ajax({
					type:"GET",
					url:"${PATH}/role/delRole",
					data:{"id":roleId},
					success:function(result){
						layer.close(idx);
						if(result == "ok"){
							$tr.remove();
							layer.msg("删除成功");
							//删除数据后，判断一下是否还存在其他的数据，如果没有则返回上一页
							var trLen = $("tbody tr").length;
							if(trLen < 1){
								var pageNum = $("tfoot .currentPageNum").text();
								initTableRoles(pageNum);
							}
						}else{
							layer.msg(result);
						}
					}
				});
			});
		});

		//为新增添加单击事件，弹出模态框
		$("#addRoleBtn").click(function(){
			//此处将form表单的id去除是为了防止上一步操作是修改，还携带id属性值
			$("form input[name='id']").val("");
			$("#exampleModalLabel").text("新增角色");
			$("#roleModal").modal("show");
		});
		
		//给修改按钮绑定单击事件，使用动态委派
		$("tbody").delegate(".updateRoleBtn", "click", function(){
			//$('#modalForm')[0].reset();//重置表单
			$("#exampleModalLabel").text("修改角色");
			var roleId = this.id;
			//发送Ajax请求，将id传给后台
			$.ajax({
				type:"GET",
				url:"${PATH}/role/getRole",
				data:{id:roleId},
				success:function(role){
					//给模态框回显数据
					$("#roleModal input[name='id']").val(roleId);
					$("#roleModal input[name='name']").val(role.name);
					$("#roleModal").modal("show");
				}
			});
		});
		
		//为修改与新增角色使用的模态框的提交按钮添加单击事件
		$("#roleModal #roleModalBtn").click(function(){
			//获取pageNum和condition
			var pageNum = $("tfoot .pagination .currentPageNum").text();
			condition = $("#queryCondition").val();
			//定义要发送请求的变量
			var url;
			//var data;
			var resultStr;                   
			var page;
			var msg;
			//假如信息没有填写完整，则给出提示
			if($("form input[name='name']").val() != ""){
				//判断roleId是否为空，分配是修改还是新增
				if($("form input[name='id']").val() == ""){
					//为添加角色发送Ajax请求赋值变量
					url = "${PATH}/role/addRole";
					//data = {"name":roleName};
					resultStr = "add";
					page = pages + 1;
					msg = "添加成功";
				}else{
					//为修改角色发送Ajax请求的变量赋值
					url = "${PATH}/role/updateRole";
					//data = {"id":roleId, "name":roleName};
					resultStr = "update";
					page = pageNum;
					msg = "修改成功";
				}
				$.ajax({
					type:"POST",
					url:url,
					data:$("#roleModal form").serialize(),
					success:function(result){
						if(result == resultStr){
							$("#roleModal").modal("toggle");
							layer.msg(msg);
							initTableRoles(page, condition);
						}
					}
				});
				$('#modalForm input[type="reset"]').click();//重置表单
			}else{
				layer.msg("请填入完整的信息");
			}
		});
		
		//给查询按钮绑定单击事件
		$("form #queryConditionBtn").click(function(){
			//获取输入的条件
			condition = $("#queryCondition").val();
			initTableRoles(1, condition);
			
		});
		
		//全选与全不选
		$("thead input").click(function(){
			$("tbody input:checkbox").prop("checked", $("thead input:checkbox").prop("checked"));
		});
		$("tbody").delegate(":checkbox", "click", function(){
			$("thead input:checkbox").prop("checked", $("tbody input:checked").length == $("tbody input:checkbox").size());
		});
		
		//为批量删除绑定单击事件
		$("button#delRoleBtn").click(function(){
			//创建id的数组
			var idsArr = new Array();
			//如果没有选择角色给出提示
			if($("tbody :checkbox:checked").size() < 1){
				layer.msg("请选择要删除的角色");
			}else{
				//调用each函数，遍历选择的角色信息
				$("tbody :checkbox:checked").each(function(){
					idsArr.push(this.id);
				});
				//[1,2,3]
        		// ids=1&ids=2&ids=3   后台会自动使用List接受
        		// ids=1,2,3  后台也会自动使用List集合接受
        		//准备批量删除的参数id字符串   1,2,3
        		var idsStr = idsArr.join();
        		console.log(idsStr);
        		$.ajax({
        			type:"GET",
        			url:"${PATH}/role/batchDelRoles",
        			data:{"ids":idsStr},
        			success:function(result){
        				if(result == "ok"){
        					layer.msg("删除成功");
        					//刷新当前页
        					var pageNum = $("tfoot .pagination .currentPageNum").text();
        					initTableRoles(pageNum, condition);
        					$("thead :checkbox").prop("checked",false);
        				}
        			}
        		});
			}
		});
		
		var ztreeObj;//ztree树对象
		var role_perId;
		//角色分配权限：点击分配权限按钮时，弹出模态框，将所有的权限以菜单树的形式显示到模态框中，
		//并区分已分配的权限和未分配的权限；显示权限树时  未分配的默认不选中，已分配的默认勾选
		//发送两次ajax请求：先请求所有的权限 ，当相应成功后再请求获取已分配的权限id集合
		function assignPermissionsToRole(id){
			role_perId = id;
			//1.发送请求获取所有的权限信息
			$.ajax({
				type:"GET",
				url:"${PATH}/role/getPermissions",
				success:function(permissions){
					//2.发送第二个请求获取已分配的权限id集合
					$.ajax({
						type:"GET",
						url:"${PATH}/role/getAssignedPermissionids",
						data:{"id":id},
						success:function(assignedPermissionids){
							//使用ztree将permissions显示到ztree树种[
	        				//1、引入ztreecss和js文件2、提供ztree容器 3、将permissions数据生成ztree树设置到ztree容器中]
							var setting = {
									check: {
               							enable: true //使用复选框
               						},
               						data: {
               							key: {
               								name: "title"
               							},
               							simpleData: {
               								enable: true,
               								pIdKey: "pid"
               							}
               						},
               						view:{
               							addDiyDom: addDiyDom
               						}
							}
							//修改图标
							function addDiyDom(treeId, treeNode){
								$("#" + treeNode.tId + "_ico").remove();
								$("#" + treeNode.tId + "_span").before("<span class='"+treeNode.icon+"'></span>");
							}
							//判断哪些权限需要设置选中
							//遍历permissions，如果permission是已分配的，给他添加checked属性值=true
							$.each(permissions, function(){
								//如果assignedPermissionids集合中包含正在遍历的permission的id，则默认选中
								if(assignedPermissionids.indexOf(this.id) >= 0){
									//js对象：给对象动态分配属性
									this.checked = true;
								}
							});
							//调用ztree的初始化方法  根据setting将permissions生成ztree树 显示到ztree容器中
							ztreeObj = $.fn.zTree.init($("#treeDemo"), setting, permissions);
							//显示树的模态框
							ztreeObj.expandAll(true);
							$("#roleToAssignPermissionsModal").modal("toggle");
						}
					});
				}
			});
		}
		//给分配权限的模态框提交按钮绑定单击事件
		$("#roleToAssignPermissionsModalBtn").click(function(){
			//当前角色的id
			//alert(role_perId);
			//获取ztree树中被选中的权限的id集合
			var checkedNodes = ztreeObj.getCheckedNodes(true);
			var idsArr = new Array();
			$.each(checkedNodes, function(){
				idsArr.push(this.id);
			});
			var idsStr = idsArr.join();
			$.ajax({
				type:"POST",
				url:"${PATH}/role/assignPermissionsToRole",
				data:{"roleId":role_perId, "ids":idsStr},
				success:function(result){
					if(result == "ok"){
						$("#roleToAssignPermissionsModal").modal("toggle");
						layer.msg("角色分配权限成功");
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

		$("tbody .btn-success").click(function() {
			window.location.href = "assignPermission.html";
		});
		
	</script>
</body>
</html>
