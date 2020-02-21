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

	<%@ include file="/WEB-INF/pages/include/base_css.jsp" %>
		
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
		<!-- 静态包含 登录成功的状态栏 -->
		<c:set var="pageTitle" value="众筹平台 - 许可维护"></c:set>
     	<%@ include file="/WEB-INF/pages/include/admin_loginbar.jsp" %>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
      	<%@ include file="/WEB-INF/pages/include/admin_sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="panel panel-default">
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <!-- ztree的容器 -->
			  <div class="panel-body">
                  <ul id="treeDemo" class="ztree"></ul>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
	<!-- 新增菜单的模态框 -->
	<div class="modal fade" id="addMenuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加菜单</h4>
	      </div>
	      <div class="modal-body">
	        <form id="addForm">
	        	<!-- 当前新增菜单的pid -->
	        	<input type="hidden" name="pid" value=""/>
	          <div class="form-group">
	            <label for="message-text" class="control-label">菜单名:</label>
	            <input name="name" class="form-control" id="message-text"></input>
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">菜单图标:</label>
	            <input name="icon" class="form-control" id="message-text"></input>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="addMenuBtn" type="button" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>   
	<!--修改菜单的模态框 -->
	<div class="modal fade" id="updateMenuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">修改菜单</h4>
	      </div>
	      <div class="modal-body">
	        <form id="updateForm">
	        	<!-- 当前修改菜单的id -->
	        	<input type="hidden" name="id" value=""/>
	          <div class="form-group">
	            <label for="message-text" class="control-label">菜单名:</label>
	            <input name="name" class="form-control" id="message-text"></input>
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">菜单图标:</label>
	            <input name="icon" class="form-control" id="message-text"></input>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="updateMenuBtn" type="button" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>   
	
	<c:set var="pageName" value="菜单维护"></c:set>
	<%@ include file="/WEB-INF/pages/include/base_js.jsp" %>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
            
          	//ztree解析时使用的配置参数
			var setting = {         
			    	    data: {
			    	        simpleData: {
			    	            enable: true,
			    	            pIdKey:"pid"
			    	         },
			    	         key: {
			    	        	 url:"xUrl",/* 取消节点点击跳转的配置设置 */
			    	        	 children:"nodes"/* 将treeNode的nodes属性当作节点名称 */
			    	         }
			    	    },
						view: {
				        	 addDiyDom: addDiyDom, //自定义生成的ztree节点的标签
				        	 removeHoverDom: removeHoverDom, //自定义鼠标离开节点时的操作
				        	 addHoverDom: addHoverDom //自定义鼠标悬停时的标签
				         }
			};
          	
			//鼠标在每个节点上悬停离开时会自动使用该节点调用一次该方法
			function removeHoverDom(treeId, treeNode){
				//移除按钮组
				$("#"+treeNode.tId+"_Group_Btn").remove();
			};
			//鼠标在每个节点上悬停时会自动使用该节点调用一次该方法
			function addHoverDom(treeId, treeNode){
				//treeNode是根据menu生成的对象，包含menu对象的所有属性
				/* var treeNodeId = treeNode.tId;
				if($("#"+treeNodeId+"_Group_Btn").length > 0)return;
				$("#" + treeNodeId + "_a").after('<span id="'+treeNodeId+'_Group_Btn"><span class="glyphicon glyphicon-remove"></span></span>'); */
				var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
				aObj.attr("href", "javascript:;");
        	    aObj.prop("target","");
				if (treeNode.editNameFlag || $("#"+treeNode.tId+"_Group_Btn").length>0) return;
				var s = '<span id="'+treeNode.tId+'_Group_Btn">';
				if ( treeNode.level == 0 ) {//根节点
					//只能添加
					s += '<a onclick="addChildMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:;" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
				} else if ( treeNode.level == 1 ) {//枝节点
					//修改按钮
					s += '<a onclick="updateMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:;" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
					if (!treeNode.hasOwnProperty('nodes') || treeNode.nodes.length == 0) {
						//如果枝节点没有子节点 多添加一个删除按钮
						s += '<a onclick="delMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:;" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
					}
					//添加按钮
					s += '<a onclick="addChildMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:;" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
				} else if ( treeNode.level == 2 ) {//叶子节点
					//修改按钮
					s += '<a onclick="updateMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:;" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
					//删除按钮
					s += '<a  onclick="delMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:;">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
				}

				s += '</span>';
				aObj.after(s);
			};
			
          	//按钮组添加绑定单击事件
          	function addChildMenu(id){
          		//id值为pid
          		$("#addMenuModal input[name='pid']").val(id);
          		$("#addMenuModal").modal("toggle");
          	}
          	//添加菜单的模态框提交按钮绑定事件
          	$("#addMenuModal #addMenuBtn").click(function(){
          		$.ajax({
          			type:"POST",
          			url:"${PATH}/menu/addMenu",
          			data:$("#addMenuModal form").serialize(),
          			success:function(result){
          				if(result == "ok"){
          					$("#addMenuModal").modal("toggle");
          					layer.msg("添加成功");
          					//初始化ztree
          					initMenusTree();
          				}
          			}
          		});
          		$('#addForm')[0].reset();//重置表单
          	});
          	
          	//按钮组修改绑定单击事件
          	function updateMenu(id){
          		$.ajax({
          			type:"GET",
          			url:"${PATH}/menu/getMenuById",
          			data:{"id":id},
          			success:function(menu){
		          		$("#updateMenuModal input[name='id']").val(id);
          				$("#updateMenuModal input[name='name']").val(menu.name);
          				$("#updateMenuModal input[name='icon']").val(menu.icon);
		          		$("#updateMenuModal").modal("toggle");
          			}
          		});
          	}
          	//为修改的模态框的提交按钮绑定单击事件
          	$("#updateMenuModal #updateMenuBtn").click(function(){
          		$.ajax({
          			type:"POST",
          			url:"${PATH}/menu/updateMenu",
          			data:$("#updateMenuModal form").serialize(),
          			success:function(result){
          				if(result == "ok"){
          					$("#updateMenuModal").modal("toggle");
          					layer.msg("修改成功");
          					initMenusTree();
          				}
          			}
          		});
          		$('#updateForm')[0].reset();//重置表单
          	});
          	
          	//按钮组删除绑定单击事件
			function delMenu(id){
          		layer.confirm("是否删除？", {title:"删除确认"}, function(idx){
          			layer.close(idx);
          			$.ajax({
              			type:"GET",
              			url:"${PATH}/menu/delMenu",
              			data:{"id":id},
              			success:function(result){
              				if(result == "ok"){
              					layer.msg("删除成功");
              					initMenusTree();
              				}else{
              					layer.msg(result);
              				}
              			}
              		});
          		});
          	}
          	
          	//ztree每次生成一个节点就会使用该节点调用一次当前方法
          	//参数1：代表ztree容器的id	参数2：当前的ztree节点对象
          	function addDiyDom(treeId, treeNode){
          		//console.log("treeId:" + treeId);
          		console.log(treeNode);
          		//获取正在显示的节点的id
          		var treeNodeId = treeNode.tId;
          		//修改当前节点的图标:   每个节点都是由menus数据生成的，包含了menu的所有的属性值，ztree中显示图标的标签id为  treeNodeId+"_ico" 
          		var treeNodeIcon = treeNode.icon;
          		$("#" + treeNodeId + "_ico").remove();//移除之前显示图标的标签
          		$("#" + treeNodeId + "_span").before('<span title="" treenode_ico="" class="'+treeNode.icon+'" style="background:url(glyphicon glyphicon-user) 0 0 no-repeat;"></span>');
          	};
          	
            //ajax请求的菜单节点json数组
            var zNodes;
			//初始化ztree
			initMenusTree();
            function initMenusTree(){
				//发送Ajax请求获取ztree节点数据
				$.ajax({
					type:"GET",
					url:"${PATH}/menu/getMenus",
					success:function(menus){
						if(menus == "权限不足"){
							layer.msg(menus);
						}else{
							console.log(menus);
							zNodes = menus;
							//zNodes数据没有最外层的父节点，zNodes是一个js的数组，zNodes中的现有的最外层的几个节点的pid属性值都为0
							zNodes.push({"id":0, "name":"系统权限菜单", "icon":"glyphicon glyphicon-list"});
							//等待异步加载数据结束后生成ztree树显示到ztree容器中
							var ztreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
							//自动展开菜单树
							ztreeObj.expandAll(true);
						}
					}
				});
			}
            
        </script>
  </body>
</html>
