<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>create admin</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link type="text/css" rel="stylesheet" href="css/create_admin.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>

<script type="text/javascript">
	function myload() {

	}
</script>
</head>

<body>
	<h1>create admin</h1>
	<!-- 第一个页面获取角色名称 -->
	<div id="page1" class="mypage first">
		<div class="input-group input-group-lg">
			<span class="input-group-addon" id="sizing-addon1">请输入角色名称：</span> <input
				id="admintext" type="text" class="form-control" placeholder="name"
				aria-describedby="sizing-addon1">
		</div>
		<button type="button" class="btn btn-default" id="btn_next1"
			disabled="disabled">下一步</button>
	</div>

	<!-- 第二个页面选择权限 -->
	<div id="page2" class="mypage " style="display: none;">
		<button id="btn_select_all" type="button" class="btn btn-default">全选</button>

		<p class="bg-success text-primary">人员管理</p>
		<button type="button" class="btn btn-default btn-lg">增加</button>
		<button type="button" class="btn btn-default btn-lg">删除</button>
		<button type="button" class="btn btn-default btn-lg">修改</button>
		<button type="button" class="btn btn-default btn-lg">查询</button>

		<p class="bg-info text-primary">线路管理</p>
		<button type="button" class="btn btn-default btn-lg">增加</button>
		<button type="button" class="btn btn-default btn-lg">删除</button>
		<button type="button" class="btn btn-default btn-lg">修改</button>
		<button type="button" class="btn btn-default btn-lg">查询</button>

		<p class="bg-success text-primary">排班管理</p>
		<button type="button" class="btn btn-default btn-lg">增加</button>
		<button type="button" class="btn btn-default btn-lg">删除</button>
		<button type="button" class="btn btn-default btn-lg">修改</button>
		<button type="button" class="btn btn-default btn-lg">查询</button>
		<button type="button" class="btn btn-default btn-lg">数据导出</button>

		<p class="bg-info text-primary">站点管理</p>
		<button type="button" class="btn btn-default btn-lg">增加</button>
		<button type="button" class="btn btn-default btn-lg">删除</button>
		<button type="button" class="btn btn-default btn-lg">修改</button>
		<button type="button" class="btn btn-default btn-lg">查询</button>
		<button type="button" class="btn btn-default btn-lg">数据导出</button>

		<p class="bg-success text-primary">车辆管理</p>
		<button type="button" class="btn btn-default btn-lg">增加</button>
		<button type="button" class="btn btn-default btn-lg">删除</button>
		<button type="button" class="btn btn-default btn-lg">修改</button>
		<button type="button" class="btn btn-default btn-lg">查询</button>
		<button type="button" class="btn btn-default btn-lg">数据导出</button>

		<p class="text-primary">
			<button type="button" class="btn btn-default" id="btn_pre2">上一步</button>
			<button type="button" class="btn btn-default" id="btn_next2">下一步</button>
		</p>
	</div>

	<!-- 第三个页面显示此角色的信息和权限 -->
	<div id="page3" class="mypage " style="display: none;">
		page3
		<button id="btn_pre3">pre</button>
		<button id="btn_next3">next</button>
	</div>


	<script type="text/javascript">
		$(function() {

			//控制页面的切换
			$("#btn_next1").click(function() {
				$("#page1").hide();
				$("#page2").show();
			});

			$("#btn_next2").click(function() {
				$("#page2").hide();
				$("#page3").show();
			});

			$("#btn_pre2").click(function() {
				$("#page2").hide();
				$("#page1").show();
			});

			$("#btn_pre3").click(function() {
				$("#page3").hide();
				$("#page2").show();
			});

			//当文本框文字改变监听
			$("#admintext").keyup(function() {
				if ($(this).val() == "") {
					$("#btn_next1").attr("disabled", true);
				} else {
					$("#btn_next1").attr("disabled", false);
				}
			});

			//若选中此权限按钮，按钮变为绿色
			$(".btn-lg").click(function() {
				$(this).toggleClass("btn-success");
			});

			//全选的操作
			var t = true;
			$("#btn_select_all").click(function() {
				if (t) {
					$(".btn-lg").each(function() {
						$(this).addClass("btn-success");
					})
					$("#btn_select_all").text("取消全选");
					t = false;
				} else {
					$(".btn-lg").each(function() {
						$(this).removeClass("btn-success");
					})
					$("#btn_select_all").text("全选");
					t = true;
				}
			});
		});
	</script>
</body>
</html>
