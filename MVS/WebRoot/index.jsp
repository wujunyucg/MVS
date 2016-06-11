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

<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>

<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/js/marker.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script src="scripts/map/map.js"></script>
	<script src="scripts/map/map2.js"></script>


<script type="text/javascript">
	/*页面加载时分配权限*/
	function jimoOnLoad() {
		/*先隐藏所有权限*/
		$(".dropdown1-menu li a").each(function(index, elm) {
			//$(this).removeAttr('onclick');
			$(this).hide();
		});

		var power = "${power}";//获得权限
		//alert(power)
		var pa = power.split(",");//解析成数组
		//alert(pa)
		for(var i=0;i<pa.length;i++){
			//alert(pa[i])
			$("#"+pa[i]).show();
		}
	}
</script>
</head>

<body onload="jimoOnLoad()">

	<nav class="navbar navbar-inverse navbar-fixed-top">
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
	</nav>

	<div class="myleft-menu">
		<ul>
			<li class="dropdown1"><a href="#" data-toggle="dropdown1">
					管理用户<i class="icon-arrow"></i>
			</a>
				<ul class="dropdown1-menu">
					<li><a id="1" href="javascript:;">增加</a></li>
					<li><a id="2" href="#">删除</a></li>
					<li><a id="3" href="#">修改</a></li>
					<li><a id="4" href="#">查找</a></li>
				</ul></li>
			<li class="dropdown1"><a href="#" data-toggle="dropdown1">Second
					Menu <i class="icon-arrow"></i>
			</a>
				<ul class="dropdown1-menu">
					<li><a id="5" href="#">Home</a></li>
					<li><a id="6" href="#">About Us</a></li>
					<li><a id="7" href="#">Services</a></li>
					<li><a id="8" href="#">Contact</a></li>
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
	<div id="main_page" class="main-page"></div>

	<!-- 模态框 -->
	<div id="loadpage_modal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<h3 class="modal-content">正在加载，请稍后...</h3>
		</div>
	</div>


	<script type="text/javascript">
		$(function() {
			/*控制导航栏菜单的下拉*/
			$('#dropdownMenu_user').dropdown('toggle');

			/*隐藏左侧菜单*/
			$("#control_left_menu").click(function() {
				//$("#left_menu").collapse('toggle');
				$(".myleft-menu").fadeToggle("fast", "linear");
			});

			/*监听下拉菜单选项的点击事件*/
			$(".dropdown1-menu li a").each(function(index, elm) {

				$(elm).click(function() {
					//alert(index)
					$("#loadpage_modal").modal('show');
					$.ajax({
						url : "",
						type : "POST",
						data : "",
						success : function() {
							//alert("yes");
							$("#loadpage_modal").modal('hide');
							//$("#left_menu").hide();
							//$("#main_page").load("login.jsp");
							$("#main_page").load("map.jsp");
							$(".myleft-menu").fadeToggle("fast", "linear");
						},
						error : function() {
							//alert("no")
							$("#main_page").load("user.jsp");
						}
					});
				});
			});
		});

		/*下面是下拉菜单的响应事件*/
		var dropdown1 = document.querySelectorAll('.dropdown1');
		var dropdownArray = Array.prototype.slice.call(dropdown1, 0);
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
