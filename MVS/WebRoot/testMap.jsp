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

<title>MapTest</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href='assets/stylesheets/bootstrap/bootstrap.css' media='all'
	rel='stylesheet' type='text/css' />
<link href='assets/stylesheets/bootstrap/bootstrap-responsive.css'
	media='all' rel='stylesheet' type='text/css' />
<link href='assets/stylesheets/jquery_ui/jquery-ui-1.10.0.custom.css'
	media='all' rel='stylesheet' type='text/css' />
<link href='assets/stylesheets/jquery_ui/jquery.ui.1.10.0.ie.css'
	media='all' rel='stylesheet' type='text/css' />
<!-- 主题样式-->
<link href='assets/stylesheets/light-theme.css'
	id='color-settings-body-color' media='all' rel='stylesheet'
	type='text/css' />

<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>

<script src="http://webapi.amap.com/js/marker.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body class='contrast-red fixed-navigation'>
	<header>
		<div class='navbar'>
			<div class='navbar-inner'>
				<div class='container-fluid'>
					<a class='brand' href='index.jsp'> <span class='hidden-phone'>厂车管理后台</span>
					</a> <a id="toggle_nav" class='toggle-nav btn pull-left' href='#'>
						<i class='icon-reorder'></i>
					</a>
					<ul class='nav pull-right'>
						<li class='dropdown dark user-menu'><a
							class='dropdown-toggle' data-toggle='dropdown' href='#'><span
								class='user-name hidden-phone'>咸鱼的梦想</span> <b class='caret'></b>
						</a>
							<ul class='dropdown-menu'>
								<li><a href='javascript:;'> <i class='icon-user'></i>
										信息修改
								</a></li>
								<li><a href='javascript:;'> <i class='icon-cog'></i> 设置
								</a></li>
								<li class='divider'></li>
								<li><a href='javascript:;'> <i class='icon-signout'></i>
										登出
								</a></li>
							</ul></li>
					</ul>

				</div>
			</div>
		</div>
	</header>

	<div id='wrapper' style="z-index:100 !important;">
		<div id='main-nav-bg'></div>
		<nav class='main-nav-fixed' id='main-nav'>
			<div class='navigation'>
				<ul class='nav nav-stacked'>
					<li class=''><a href='index.html'> <i
							class='icon-dashboard'></i> <span>主页</span>
					</a></li>
					<li class=''><a class='dropdown-collapse' href='#'> <i
							class='icon-edit'></i> <span>Forms</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked'>
							<li class=''><a href='form_styles.html'> <i
									class='icon-caret-right'></i> <span>Form styles and
										features</span>
							</a></li>
							<li class=''><a href='form_components.html'> <i
									class='icon-caret-right'></i> <span>Form components</span>
							</a></li>
							<li class=''><a href='validations.html'> <i
									class='icon-caret-right'></i> <span>Validations</span>
							</a></li>
							<li class=''><a href='wizard.html'> <i
									class='icon-caret-right'></i> <span>Wizard</span>
							</a></li>
						</ul></li>

					<li class=''><a id="test_load" href='javascript:;'> <i
							class='icon-star'></i> <span>异步加载</span></a></li>
					<li class='active'><a href='index2.jsp'> <i
							class='icon-table'></i> <span>跳转</span>
					</a></li>
				</ul>
			</div>
		</nav>
		<section id='content'>
			<div id='container' style="margin-left:255px;"></div>
			<div id="info">
				<h1>
					<h1>
			</div>
			<div id="myPageTop"
				style="position: absolute; top:175px; right:100px;">

				<div id="panel"></div>
			</div>
			<div id="result"></div>
			<div>
				<ul id="routes" style="position: absolute;">
					<li><button>çº¿è·¯1</button></li>
					<li><button>çº¿è·¯2</button></li>
					<li><button>çº¿è·¯3</button></li>
				</ul>
			</div>
		</section>
	</div>
	<script src='assets/javascripts/jquery/jquery.min.js'
		type='text/javascript'></script>
	<script
		src='assets/javascripts/plugins/mobile_events/jquery.mobile-events.min.js'
		type='text/javascript'></script>
	<script src='assets/javascripts/jquery_ui/jquery-ui.min.js'
		type='text/javascript'></script>
	<script src='assets/javascripts/bootstrap/bootstrap.min.js'
		type='text/javascript'></script>
	<script src='assets/javascripts/plugins/flot/excanvas.js'
		type='text/javascript'></script>

	<!-- 主题文件 勿删-->
	<script src='assets/javascripts/nav.js' type='text/javascript'></script>
	<script src='assets/javascripts/tables.js' type='text/javascript'></script>
	<script src='assets/javascripts/theme.js' type='text/javascript'></script>

	<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript">
		$("#test_load").click(function() {
			$("#content").load("index.jsp");
		});

		//调节地图大小
		var turn = false;
		$("#toggle_nav").click(function() {
			if (turn) {
				$("#container").css("marginLeft", "255px");
				turn = false;
			} else {
				$("#container").css("marginLeft", "50px");
				turn = true;
			}
		});
	</script>
</body>
</html>

