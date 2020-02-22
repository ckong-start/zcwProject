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
	<c:set var="pageTitle" value="众筹平台 - 用户维护"></c:set>
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
						<form method="get" action="${ PATH }/admin/user.html" class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<!-- 添加name属性值 -->
									<input name="condition" value="${ param.condition }" class="form-control has-success" type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<!-- form表单中，收集有name属性值的表单数据，以键值对的方式提交给ip地址xxx的 -->
							<button type="submit" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchDelAdminsBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button id="addAdminModalBtn" type="button" class="btn btn-primary"
							style="float: right;" ><!-- onclick="window.location.href='add.html'" -->
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
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 一个集合使用一个表格显示数据，一个对象使用一行显示，一个对象的一个属性值使用一个单元格显示 
              							varStatus :   遍历的状态对象(foreach标签遍历开始时会创建一个状态对象，以后的遍历中会更新此对象的状态值)
		              						count: 从1开始，计算已经遍历过的元素数量
		              						index: 从0开始，表示正在遍历元素的索引
		              						isFirst: 当前遍历的元素是否是第一个
		              						current： 代表当前正在遍历的元素
              						-->
									<c:forEach items="${ requestScope.pageInfo.list }" var="admin" varStatus="vs">
										<tr>
											<td>${ vs.count }</td>
											<!-- 给复选框添加id属性，携带admin的id -->
											<td><input id="${ admin.id }" type="checkbox"></td>
											<td>${ admin.loginacct }</td>
											<td>${ admin.username }</td>
											<td>${ admin.email }</td>
											<td>
												<!-- 更新权限按钮 -->
												<button onclick="window.location='${PATH}/admin/assignedRolePage?id=${ admin.id }'" type="button" class="btn btn-success btn-xs">
													<i class=" glyphicon glyphicon-check"></i>
												</button>
												<!-- 编辑信息按钮 -->
												<button type="button" id="${ admin.id }" class="btn btn-primary btn-xs updateBtn">
													<i class=" glyphicon glyphicon-pencil"></i>
												</button>
												<!-- 删除按钮 -->
												<button type="button" adminId="${ admin.id }" class="btn btn-danger btn-xs deleteBtn">
													<i class=" glyphicon glyphicon-remove"></i>
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<ul class="pagination">
											<!-- 分页开始 -->
												<c:choose>
													<c:when test="${ requestScope.pageInfo.hasPreviousPage }">
														<!-- 有上一页 -->
														<li><a href="${ PATH }/admin/user.html?pageNum=${ pageInfo.pageNum - 1 }&condition=${ param.condition }">上一页</a></li>
													</c:when>
													<c:otherwise>
														<!-- 没有上一页 -->
														<li class="disabled"><a href="javascript:void(0);">上一页</a></li>
													</c:otherwise>
												</c:choose>
												<c:forEach items="${ requestScope.pageInfo.navigatepageNums }" var="index">
													<c:choose>
														<c:when test="${ pageInfo.pageNum == index }">
															<li class="active"><a href="javascript:void(0);"><span id="currentPageNum">${ index }</span><span class="sr-only">(current)</span></a></li>
														</c:when>
														<c:otherwise>
															<!-- 此处的地址是浏览器解析的 -->
															<li><a href="${ PATH }/admin/user.html?pageNum=${ index }&condition=${ param.condition }">${ index }</a></li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:choose>
													<c:when test="${ requestScope.pageInfo.hasNextPage }">
														<li><a href="${ PATH }/admin/user.html?pageNum=${ pageInfo.pageNum + 1 }&condition=${ param.condition }">下一页</a></li>
													</c:when>
													<c:otherwise>
														<!-- 没有下一页 -->
														<li class="disabled"><a href="javascript:void(0);">下一页</a></li>
													</c:otherwise>
												</c:choose>
											<!-- 分页结束 -->
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
	<!-- 新增管理员的模态框开始 -->
	<div class="modal fade" id="addAdminModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">新增管理员</h4>
	      </div>
	      <div class="modal-body"><span id="errorMsg" style="color:red;"></span>
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">登录账号</label>
	            <input name="loginacct" type="text" class="form-control">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">用户名称</label>
	            <input name="username" type="text" class="form-control"/>
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">用户密码</label>
	            <input name="userpswd" type="text" class="form-control"/>
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">邮箱地址</label>
	            <input name="email" type="text" class="form-control">
	          </div>
	          <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="addAdminBtn" type="button" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 新增管理员模态框结束 -->
	<!-- 修改管理员信息的模态框开始 -->
	<div class="modal fade" id="updateAdminModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">修改管理员</h4>
	      </div>
	      <div class="modal-body"><span id="errorMsg" style="color:red;"></span>
	        <form>
	        	<input type="hidden" name="id" />
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">登录账号</label>
	            <input name="loginacct" type="text" class="form-control">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">用户名称</label>
	            <input name="username" type="text" class="form-control"/>
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">邮箱地址</label>
	            <input name="email" type="text" class="form-control">
	          </div>
	          <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="updateAdminBtn" type="button" class="btn btn-primary">更新</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 修改管理员模态框结束 -->
	
	<!-- 提取当前页面高亮显示的字符串 存到域中-->
	<c:set scope="page" var="pageName" value="用户维护"></c:set>
	<%@ include file="/WEB-INF/pages/include/base_js.jsp"%>
	
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					/* toggleClass表示有这个Class就去掉，没有就加上 */
					$(this).toggleClass("tree-closed");
					/* hasClass表示是否有Class属性 */
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
			
		});
	/* 	$("tbody .btn-success").click(function() {
			window.location.href = "assignRole.html";
		});
		$("tbody .btn-primary").click(function() {
			window.location.href = "edit.html";
		}); */
		
	
		
		//单个删除绑定单击事件
		$("tbody .deleteBtn").click(function(){
			//获取要删除管理员的name与id
			var adminName = $(this).parents("tr").children("td:eq(3)").text();
			var adminId = $(this).attr("adminId");
			layer.confirm("是否要删除【" + adminName + "】?", {title:"删除确认"}, function(idx){
				layer.close(idx);
				window.location = "${ PATH }/admin/deleteAdmin?id=" + adminId;
				layer.msg("删除成功");/* 可以使用{time:2000} 延时2秒自动关闭 */
			});
			
		});
		
		//全选全不选功能
		$("thead :checkbox").click(function(){
			//当thead复选框点击时，tbody中复选框的状态和它一致
			$("tbody :checkbox").prop("checked", $("thead :checkbox").prop("checked"));
		});
		//给子复选框绑定单击事件
		$("tbody :checkbox").click(function(){
			//被选中的子复选框的个数
			//alert($("tbody :checkbox:checked").size());
			//alert($("tbody :checkbox:checked").length);
			$("thead :checkbox").prop("checked", $("tbody :checkbox").length == $("tbody :checkbox:checked").size());
		});
		
		//批量删除绑定单击事件
		$("#batchDelAdminsBtn").click(function(){
			if($("tbody :checkbox:checked").size() > 0){
				layer.confirm("是否删除？", {title:"删除确认"}, function(idx){
					layer.close(idx);
					//获取选中的复选框
					var $checkedAdmins = $("tbody :checkbox:checked");//jquery对象的本质是dom对象的集合
					//查询要删除的管理员s的id集合
					/* for(var i = 0; i < $checkedAdmins.length; i++){
						alert($checkedAdmins[i]);
					} 此方法也可以遍历jquery对象数组*/
					var idStr = "";
					//使用jQuery自身的api来遍历集合
					//第一个参数是要遍历的集合	第二个参数是集合遍历时自动会使用正在遍历的对象调用的函数
					$.each($checkedAdmins, function(){
						//this代表正在遍历的元素【dom对象】	alert(this.id);
						idStr += "id=" + this.id + "&";
					});
					//使用字符串截取，将最后个多余的&去除
					idStr = idStr.substr(0, idStr.length - 1);//alert(idStr);
					//提交删除请求
					window.location = "${PATH}/admin/batchDelAdmins?" + idStr;
					layer.msg("删除成功");
				});
			}else{
				layer.msg("请选择要删除的管理员");
			}
			
		});
		
		//为新增按钮绑定单击事件，点击时弹出模态框
		$("#addAdminModalBtn").click(function(){
			$("#addAdminModal").modal("toggle");
		});
		//给添加管理员的模态框提交按钮添加单击事件
		$("#addAdminModal #addAdminBtn").click(function(){
			$.ajax({
				type:"POST",
				url:"${PATH}/admin/addAdmin",
				/* 通过id查询将模态框的数据表单序列化，提交给后台 */
				data:$("#addAdminModal form").serialize(),
				success:function(result){
					if(result == "ok"){
						layer.msg("添加成功");
						//关闭模态框，页面跳转到最后一页
						$("#addAdminModal").modal("toggle");
						window.location = "${PATH}/admin/user.html?pageNum=" + <%=session.getAttribute("pages")%>; 
					}else {
						$("#addAdminModal #errorMsg").text(result);
					}
				}
			});
		});

		
		//为管理员修改绑定单击事件
		$("tbody .updateBtn").click(function(){
			//查询管理员信息并设置到模态框中回显后然后显示模态框
			var adminId = this.id;
			//上下两种方式都可以
			//var adminId = $(this).prop("id");
			//1.发送ajax请求获取指定id的管理员信息
			$.ajax({
				type:"GET",
				url:"${PATH}/admin/getAdmin",
				data:{id:adminId},
				success:function(admin){
					//2.将要修改的信息回显到模态框中
					$("#updateAdminModal input[name='id']").val(admin.id);
					$("#updateAdminModal input[name='loginacct']").val(admin.loginacct);
					$("#updateAdminModal input[name='username']").val(admin.username);
					$("#updateAdminModal input[name='email']").val(admin.email);
					//3.弹出模态框
					$("#updateAdminModal").modal("toggle");
				}
			});
		});
		//给更新的模态框更新按钮绑定单击事件,点击时发送更新请求
		$("#updateAdminModal #updateAdminBtn").click(function(){
			var pageNum = $("tfoot #currentPageNum").text();
			var condition = $("input[name='condition']").val();
			//异步发送更新的角色的数据 到服务器
			$.ajax({
				type:"POST",
				url:"${PATH}/admin/updateAdmin",
				data:$("#updateAdminModal form").serialize(),
				success:function(result){
					if(result == "ok"){
						layer.msg("更新成功");
						$("#updateAdminModal").modal("toggle");
						//更新数据后刷新页面
						window.location = "${PATH}/admin/user.html?pageNum=" + pageNum + "&condition=" + condition;
						//再发送一个请求，将网页重定向刷新页面
						//window.location = "${PATH}/admin/reflushAdmin";
					}else{
						$("#updateAdminModal #errorMsg").text(result);
					}
				}
			});
		});
		
	</script>
</body>
</html>
