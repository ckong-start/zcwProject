<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 	<script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="script/docs.min.js"></script>
    <script src="script/back-to-top.js"></script>
    <script src="layer/layer.js"></script>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
    
    <script type="text/javascript">
		$(function(){
		    <!-- 给退出绑定单击事件，并添加确认操作 -->
			$("#logoutA").click(function(){
				layer.confirm("你确定要退出吗？", {title:"确认退出"}, function(idx){
					layer.close(idx);
		        	//修改地址栏地址，浏览器会自动向新地址发起请求
		        	window.location = "${PATH}/admin-logout";
				})
			});
			//由于很多标签都是在ul li a的标签下，所以不能单纯的用这个方式查询，改为用class查询更准确
			//attr():获取或者设置自定义属性的值  ，prop() 获取或者设置标签的自带属性 ， css()获取或者设置标签的css样式
			var $item_a = $(".list-group li a:contains('${pageName}')");
			/* 将菜单高亮显示 */
			$item_a.css("color", "orange");
			/* 将菜单父菜单展开 */
			$item_a.parents("ul:hidden").toggle();
			/* 将菜单的class隐藏的属性去掉 */
			$item_a.parents(".list-group-item").removeClass("tree-closed");
		});
	</script>