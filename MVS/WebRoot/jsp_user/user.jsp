<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>j-frame</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/j-css/j-theme.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
<script type="text/javascript" src="scripts/j-scripts/j-theme.js"></script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">超级管理员后台</a>
		<button id="j_nav_toggle" type="button" class="btn btn-default"
			aria-label="Left Align"
			style="margin-top:13px;color:#fff;background-color:#101010;border:0;">
			<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
		</button>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">设置</a></li>
			<li><a href="#">Help</a></li>
			<li class="dropdown dropdown-toggle "><a href="#"
				class="dropdown-toggle" id="dropdownMenu_user"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					咸鱼的梦想<span class="caret"></span>
			</a>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu_user">
					<li><a href="#">个人信息</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">退出</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>
	<div id="j-main">
		<div id="j-left-menu">
			<div class="list-group">
				<button type="button"
					class="j-btn-active list-group-item btn-menu btn_text">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					人员管理 <span style="float:right;"
						class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
				</button>
				<div class="list-group j-child-menu" style="display:none;">
					<button type="button" class="list-group-item">数据维护</button>
					<button type="button" class="list-group-item">数据同步</button>
				</div>
				<button type="button" style="display:none;"
					class="list-group-item btn-menu btn_icon">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button type="button" class="list-group-item  btn-menu btn_text">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
					线路管理
				</button>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
				</button>
				<button type="button" class="list-group-item  btn-menu btn_text">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
					站点管理
				</button>
				<button id="manage-admin" type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
				</button>


				<button type="button"
					class="j-btn-active list-group-item btn-menu btn_text">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					车辆管理 <span style="float:right;"
						class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
					</button>
					<div class="list-group j-child-menu" style="display:none;">
						<button type="button" class="list-group-item" id="cre_car">
							新建车辆数据
							</button>
							<button type="button" class="list-group-item" id="man_car">
								数据维护与查询
								</button>
					</div>
					<button type="button" style="display:none;"
						class="list-group-item btn-menu btn_icon">
						<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
						</button>

						<button type="button" class="list-group-item  btn-menu btn_text">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							班次管理 <span style="float:right;"
								class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
						</button>
						<div class="list-group j-child-menu" style="display:none;">
							<button type="button" class="list-group-item manage-arrange">数据维护</button>
						</div>
						<button type="button" style="display:none;"
							class="list-group-item  btn-menu btn_icon">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						</button>
			</div>
			<div class="panel panel-default j-no-radous">
				<div class="panel-body">一些注释讲解之类的，这下面太空了</div>
			</div>
		</div>
		<div id="content">
			<h1>欢迎您登录咸鱼的梦想厂车管理后台</h1>
		</div>
	</div>

	<!-- jimo -->
	<div id="load_modal" class="modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<b>正在加载，请稍后...</b>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		//异步加载代码
		
		$(".manage-arrange").click(function(){
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=path%>/servlet/ManageArrangeServlet");
		});
		
		$("#cre_car").click(function() {
			$("#content").load("<%=request.getContextPath()%>/jsp_user/create_car.jsp");
		});
		
		$("#man_car").click(function() {
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=1&condition=0&sea_condition=0");
		});

	</script>
</body>
</html>
