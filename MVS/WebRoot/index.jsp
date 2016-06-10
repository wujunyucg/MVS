<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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


<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/index.css">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a id="control_left_menu" class="navbar-brand" href="#"
					data-toggle="collapse" href="#left_menu" aria-expanded="false"
					aria-controls="left_menu"> <span
					class="glyphicon glyphicon-list"></span></a> <a class="navbar-brand"
					href="#">厂车管理</a>
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
		</div>
	</nav>
	<!-- 导航栏结束 -->
	<!-- 
		<div id="left_menu" class="" style="margin-top:60px;">
			<div class="row">
				<div class="col-sm-3 col-md-2 sidebar ">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">用户管理</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-sidebar">
								<li><a href="#">增加用户</a></li>
								<li><a href="#">删除用户</a></li>
								<li><a href="#">修改用户</a></li>
								<li><a href="#">查找用户</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-2 sidebar ">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h3 class="panel-title">角色管理</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-sidebar">
								<li><a href="#">创建角色</a></li>
								<li><a href="#">删除角色</a></li>
								<li><a href="#">修改角色</a></li>
								<li><a href="#">查找角色</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-2 sidebar ">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">用户管理</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-sidebar">
								<li><a href="#">增加用户</a></li>
								<li id="10"><a href="#">删除用户</a></li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	-->
	<div class="main-body">
		<div class="left-menu">
			<div class="container">

				<ul>
					<li class="dropdown1"><a href="#" data-toggle="dropdown1">First
							Menu <i class="icon-arrow"></i>
					</a>
						<ul class="dropdown1-menu">
							<li><a href="#">Home</a></li>
							<li><a href="#">About Us</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="#">Contact</a></li>
						</ul></li>
					<li class="dropdown1"><a href="#" data-toggle="dropdown1">Second
							Menu <i class="icon-arrow"></i>
					</a>
						<ul class="dropdown1-menu">
							<li><a href="#">Home</a></li>
							<li><a href="#">About Us</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="#">Contact</a></li>
						</ul></li>
					<li class="dropdown1"><a href="#" data-toggle="dropdown1">Third
							Menu <i class="icon-arrow"></i>
					</a>
						<ul class="dropdown1-menu">
							<li><a href="#">Home</a></li>
							<li><a href="#">About Us</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="#">Contact</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div id="main_page" class="main-page">main page</div>
	</div>

	<!-- 模态框 -->
	<div id="load_modal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<h3 class="modal-content">正在加载，请稍后...</h3>
		</div>
	</div>

	<script src='scripts/jquery.js'></script>
	<script src='scripts/bootstrap.min.js'></script>
	<script type="text/javascript">
		$(function() {

			/*
			$("#control_left_menu").click(function() {
				//$("#left_menu").collapse('toggle');
				$("#left_menu").toggle();
			});

			$("li").each(function(index, elm) {
				$(elm).click(function() {
					$("#load_modal").modal(function() {
						backdrop: static;
					})
					$("#load_modal").modal('show');

					$.ajax({
						url : "",
						type : "POST",
						data : "",
						success : function() {
							//alert("yes");
							$("#load_modal").modal('hide');
							$("#left_menu").hide();
							$("#main_page").load("user.jsp");
						},
						error : function() {
							alert("no")
							$("#main_page").load("user.jsp");
						}
					});
				});
			});*/
			
			/*隐藏左侧菜单*/
			$("#control_left_menu").click(function() {
				//$("#left_menu").collapse('toggle');
				$(".left-menu").toggle();
			});
		});

		
		

		/*下面是下拉菜单的响应事件*/
		var dropdown = document.querySelectorAll('.dropdown1');
		var dropdownArray = Array.prototype.slice.call(dropdown, 0);
		dropdownArray
				.forEach(function(el) {
					var button = el.querySelector('a[data-toggle="dropdown1"]'), menu = el
							.querySelector('.dropdown1-menu'), arrow = button
							.querySelector('i.icon-arrow');

					button.onclick = function(event) {
						if (!menu.hasClass('show')) {
							menu.classList.add('show');
							menu.classList.remove('hide');
							arrow.classList.add('open');
							arrow.classList.remove('close');
							event.preventDefault();
						} else {
							menu.classList.remove('show');
							menu.classList.add('hide');
							arrow.classList.remove('open');
							arrow.classList.add('close');
							event.preventDefault();
						}
					};
				})

		Element.prototype.hasClass = function(className) {
			return this.className
					&& new RegExp("(^|\\s)" + className + "(\\s|$)")
							.test(this.className);
		};
	</script>
</body>
</html>
