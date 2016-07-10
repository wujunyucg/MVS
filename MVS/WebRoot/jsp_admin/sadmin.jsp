<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//不能直接通过链接访问
	if (null == session.getAttribute("user")) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>car</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" tye="text/css" href="css/sadmin.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">超级管理员后台</a>
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

	<div id="main">
		<div id=left-menu>
			<div class="list-group">
				<button id="create-admin" type="button"
					class="list-group-item btn-menu">创建角色</button>
				<button id="create-user"type="button" class="list-group-item  btn-menu">创建用户</button>
				<button id="manage-admin"type="button" class="list-group-item  btn-menu">管理角色</button>
				<button id="manage-user"type="button" class="list-group-item  btn-menu">管理用户</button>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">一些注释讲解之类的，这下面太空了</div>
			</div>
		</div>
		<div id="content">content</div>
	</div>


	<script type="text/javascript">
		$(function() {
			$("#create-admin").click(function(){
			
				$("#content").load("jsp_admin/create_admin.jsp");
			});
			
			$("#create-user").click(function(){
			
				$("#content").load("<%=request.getContextPath()%>/servlet/CreateUserServlet");
			});
			
			$("#manage-admin").click(function(){
			
				$("#content").load("jsp_admin/manage_admin.jsp");
			});
			
			$("#manage-user").click(function(){
			
				$("#content").load("<%=path%>rvlet/ManageUserServlet");
			});
		});
	</script>
</body>
</html>
