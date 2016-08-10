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

<title>My JSP 'admin_help.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<body>
	<h1>超级管理员后台</h1>
	<hr>
	<div style="float:left;">
		<div class="panel panel-default">
			<div class="panel-heading" style="width: 150px;">帮助目录</div>
			<ul class="list-group">
				<li class="list-group-item"><a class="guide" id="1a"
					href="javascript:;">导航栏</a></li>
				<li class="list-group-item"><a class="guide" id="2a"
					href="javascript:;">创建角色</a></li>
				<li class="list-group-item"><a class="guide" id="3a"
					href="javascript:;">管理角色</a></li>
					<li class="list-group-item"><a class="guide" id="4a"
					href="javascript:;">创建用户</a></li>
					<li class="list-group-item"><a class="guide" id="5a"
					href="javascript:;">管理用户</a></li>
					<li class="list-group-item"><a class="guide" id="6a"
					href="javascript:;">数据备份</a></li>
			</ul>
		</div>
	</div>
	<div style="margin-left:180px;">
		<div class="panel panel-default per-info-div" id="1">
			<div class="panel-heading"><b>导航栏</b></div>
			<div class="panel-footer">
				超级管理员登陆后可以看到系统主界面，主界面左侧导航条，右侧是对应内容。
				包括创建角色、管理角色、创建用户、管理用户和数据备份。
			</div>
		</div>
		<div class="panel panel-default  per-info-div" id="2">
			<div class="panel-heading"><b>创建角色</b></div>
			<div class="panel-footer">
				超级管理员首先需要对厂车管理员的角色进行创建，包括角色名称以及该角色的权限设定。
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="3">
			<div class="panel-heading"><b>管理角色</b></div>
			<div class="panel-footer">
				系统为超级管理员提供了简便的操作帮助超级管理员管理角色，超级管理员可以轻松查询已有角色信息，
				修改角色（包括对角色的权限进行修改）以及删除角色。
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="4">
			<div class="panel-heading"><b>创建用户</b></div>
			<div class="panel-footer">
				在已经创建有角色的前提下，超级管理员可为每个角色设置一个或多个用户，
				用户信息包括用户账号、用户密码（默认初始密码123456）和用户角色。
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="5">
			<div class="panel-heading"><b>管理用户</b></div>
			<div class="panel-footer">
				超级管理员可以对已创建的用户进行维护，包括查询用户信息，修改用户以及删除用户。
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="6">
			<div class="panel-heading"><b>数据备份</b></div>
			<div class="panel-footer">
				超级管理员可对数据库的数据进行备份和恢复。并可查询备份或恢复数据记录。
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(".guide").click(function(){
			var id = $(this).attr("id");
			id = id.substr(0, 1);
			$("body,html").animate({
				scrollTop:$("#"+id).offset().top-60
			});
		});
	</script>
</body>
</html>
