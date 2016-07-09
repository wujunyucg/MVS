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

	<ol class="breadcrumb">
		<li><h1>创建角色</h1></li>
	</ol>
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
		<button type="button" class="btn btn-default btn-lg">增加人员</button>
		<button type="button" class="btn btn-default btn-lg">删除人员</button>
		<button type="button" class="btn btn-default btn-lg">修改人员</button>
		<button type="button" class="btn btn-default btn-lg">查询人员</button>

		<p class="bg-info text-primary">线路管理</p>
		<button type="button" class="btn btn-default btn-lg">增加线路</button>
		<button type="button" class="btn btn-default btn-lg">删除线路</button>
		<button type="button" class="btn btn-default btn-lg">修改线路</button>
		<button type="button" class="btn btn-default btn-lg">查询线路</button>

		<p class="bg-success text-primary">排班管理</p>
		<button type="button" class="btn btn-default btn-lg">增加班次</button>
		<button type="button" class="btn btn-default btn-lg">删除班次</button>
		<button type="button" class="btn btn-default btn-lg">修改班次</button>
		<button type="button" class="btn btn-default btn-lg">查询班次</button>
		<button type="button" class="btn btn-default btn-lg">班次数据导出</button>

		<p class="bg-info text-primary">站点管理</p>
		<button type="button" class="btn btn-default btn-lg">增加站点</button>
		<button type="button" class="btn btn-default btn-lg">删除站点</button>
		<button type="button" class="btn btn-default btn-lg">修改站点</button>
		<button type="button" class="btn btn-default btn-lg">查询站点</button>
		<button type="button" class="btn btn-default btn-lg">站点数据导出</button>

		<p class="bg-success text-primary">车辆管理</p>
		<button type="button" class="btn btn-default btn-lg">新车入厂</button>
		<button type="button" class="btn btn-default btn-lg">删除车辆</button>
		<button type="button" class="btn btn-default btn-lg">更新车辆信息</button>
		<button type="button" class="btn btn-default btn-lg">查询车辆信息</button>
		<button type="button" class="btn btn-default btn-lg">车辆数据导出</button>

		<p class="text-primary">
			<button type="button" class="btn btn-default" id="btn_pre2">上一步</button>
			<button type="button" class="btn btn-default" id="btn_next2">下一步</button>
		</p>
	</div>

	<!-- 第三个页面显示此角色的信息和权限 -->
	<div id="page3" class="mypage " style="display: none;">
		<div class="alert alert-info" role="alert">下面是您创建的角色信息，若有错误，请返回上一步修改！否则请点击完成</div>

		<div id="show_name" class="alert alert-success" role="alert"></div>
		<div id="show_power" class="alert alert-success" role="alert"></div>
		<button id="btn_pre3"type="button" class="btn btn-default">上一步</button>
		<button id="btn_finish"type="button" class="btn btn-default">完成</button>
	</div>


	<script type="text/javascript">
		var powerIds = "";
		$(function() {

			//控制页面的切换
			$("#btn_next1").click(function() {
				$("#page1").hide();
				$("#page2").show();
			});

			$("#btn_next2").click(function() {
				$("#page2").hide();
				$("#page3").show();

				//加载数据到第三页
				$("#show_name").text("角色名称：" + $("#admintext").val());

				var powers = "<h2>拥有的权限：</h2><br/>";
				var cnt = 0;
				$(".btn-lg").each(function(index) {
					if ($(this).hasClass("btn-success")) {
						powerIds += index + ",";
						powers += ($(this).text() + "  &nbsp;&nbsp");
						cnt++;
					}
					if (cnt % 4 == 0) {
						powers += "<br/>";
					}
				});
				if (0 == cnt) {
					$("#show_power").text("此角色难道没有权限？抱歉，您不能完成此次角色创建");
					$("#btn_finish").attr("disabled",true);
				} else {
				$("#btn_finish").attr("disabled",false);
					$("#show_power").html(powers);
					alert(powerIds)
				}
			});

			$("#btn_pre2").click(function() {
				$("#page2").hide();
				$("#page1").show();
			});

			$("#btn_pre3").click(function() {
				$("#page3").hide();
				$("#page2").show();
				
				powerIds = "";//必须清空
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
