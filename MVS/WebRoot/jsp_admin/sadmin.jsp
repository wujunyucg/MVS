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
<script src="layer/layer.js"></script>
<script type="text/javascript" language="javascript">
$.ajaxSetup ({ 
    cache: false //关闭AJAX相应的缓存 
}); 
</script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">超级管理员后台</a>
			<button id="nav_toggle"type="button" class="btn btn-default" aria-label="Left Align"
			style="margin-top:8px;color:#fff;background-color:#000;border:0;">
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

	<div id="main">
		<div id="left-menu">
			<div class="list-group">
				<button type="button"
					class="list-group-item btn-menu btn_text create-admin">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					创建角色</button>
				<button type="button"style="display:none;"
					class="list-group-item btn-menu btn_icon create-admin">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text create-user">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
					创建用户</button>
					<button  type="button"style="display:none;"
					class="list-group-item  btn-menu btn_icon create-user">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
					</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
					管理角色</button>
					<button id="manage-admin" type="button"style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
					</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					管理用户</button>
					<button type="button"style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					</button>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">一些注释讲解之类的，这下面太空了</div>
			</div>
		</div>
		<div id="content">content</div>
	</div>


	<script type="text/javascript">
		$(function() {
			$(".create-admin").click(function(){
			
				$("#content").load("jsp_admin/create_admin.jsp");
			});
			
			$(".create-user").click(function(){
			
				$("#content").load("<%=request.getContextPath()%>/servlet/CreateUserServlet");
			});
			
			$(".manage-admin").click(function(){
			
				$("#content").load("<%=path%>/servlet/ManageAdminServlet");
			});
			
			$(".manage-user").click(function(){
			
				$("#content").load("<%=basePath%>/servlet/ManageUserServlet?random" + Math.random());
			});
			
			var turn = true;
			$("#nav_toggle").click(function(){
				if(turn){
					$("#left-menu").css("width","60px");
					$("#content").css("marginLeft","80px");
					//
					$(".btn_text").hide();
					$(".btn_icon").show();
					turn = false;
				}else{
					$("#left-menu").css("width","250px");
					$("#content").css("marginLeft","260px");
					$(".btn_text").show();
					$(".btn_icon").hide();
					turn = true;
				}
			});
		});
	</script>
</body>
</html>
