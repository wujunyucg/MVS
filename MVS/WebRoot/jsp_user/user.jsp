<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	if (null == session.getAttribute("user")) {
		response.sendRedirect(path+"/login.jsp");
		return;
	}
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



<!-- map- -->
	<link rel="stylesheet"
		href="http://cache.amap.com/lbs/static/main1119.css" />
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	<script src="http://webapi.amap.com/js/marker.js"></script>
	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>




<link rel="stylesheet" type="text/css" href="css/j-css/j-theme.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
<script type="text/javascript" src="scripts/j-scripts/j-theme.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top"  id="upHAInfor" style="margin-left:230px;height:60px;">
	<div>
		 <a id="j_nav_toggle" href="javascript:;"> <img alt="菜单栏搜索" src="images/caidan.png"
			style="float:left;width:40px;">
		</a> </div>
		<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="javascript:;" id="a_person_info">设置</a></li>
			<li><a href="#">Help</a></li>
			<li class="dropdown dropdown-toggle "><a href="#"
				class="dropdown-toggle" id="dropdownMenu_user"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					咸鱼的梦想 <span class="caret"></span>&nbsp;&nbsp;
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
			<div class="upPowerName">
				<ul class="">
					<li><a class="supManBac" style="text-decoration: none;">
					<span class="glyphicon glyphicon-unchecked" aria-hidden="true"></span><span class="opMenu">普通管理员后台</span></a></li>
				</ul>
			</div>
			<div class="list-group">
				<c:if test="${admin1!=null||admin2!=null}">
					<button type="button" class="list-group-item btn-menu btn_text">
						<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
						<span class="opMenu">员工管理</span> <span style="float:right;"
							class="glyphicon glyphicon-menu-down opMenu" aria-hidden="true"></span>
					</button>
					<div class="list-group j-child-menu" style="display:none;">
						<c:if test="${admin2!=null}">
							<button type="button" class="list-group-item opMenu" id="manange_staff">数据维护</button>
						</c:if>
						<c:if test="${admin1!=null}">
							<button type="button" class="list-group-item opMenu" id="synch_staff">数据同步</button>
						</c:if>
					</div>
					<button type="button" style="display:none;"
						class="list-group-item btn-menu btn_icon opMenu1">
						<span class="glyphicon glyphicon-thumbs-up opMenu" aria-hidden="true"></span>
					</button>
				</c:if>
				<c:if test="${admin9!=null}">
					<button type="button" class="list-group-item btn-menu btn_text">
						<span class="glyphicon glyphicon-copyright-mark" aria-hidden="true"></span>
						<span class="opMenu">车辆管理 </span><span style="float:right;"
							class="glyphicon glyphicon-menu-down opMenu" aria-hidden="true"></span>
					</button>
					<div class="list-group j-child-menu" style="display:none;">
						<c:if test="${admin10!=null}">
							<button type="button" class="list-group-item opMenu" id="cre_car">
								新建车辆数据</button>
						</c:if>
						<button type="button" class="list-group-item opMenu" id="man_car">
							数据维护与查询</button>
					</div>
					<button type="button" style="display:none;"
						class="list-group-item btn-menu btn_icon opMenu1">
						<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					</button>
				</c:if>
				<c:if test="${admin7!=null}">
					<button type="button" class="list-group-item  btn-menu btn_text"
						id="map_site">
						<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
						<span class="opMenu">站点管理</span><span style="float:right;"
							class="glyphicon glyphicon-menu-down opMenu" aria-hidden="true"></span>
					</button>
					<div class="list-group j-child-menu" style="display:none;">
						<c:if test="${admin2!=null}">
							<button type="button" class="list-group-item opMenu" id="map_site1"
								>站点维护</button>
						</c:if>
						<c:if test="${admin1!=null}">
							<button type="button" class="list-group-item  opMenu" id="show_site">数据统计</button>
						</c:if>
					</div>
					<button id="manage-admin" type="button" style="display:none;"
						class="list-group-item  btn-menu btn_icon opMenu1">
						<span class="glyphicon glyphicon-eye-open opMenu" aria-hidden="true"></span>
					</button>
				</c:if>
				<c:if test="${admin3!=null}">
					<button type="button" class="list-group-item  btn-menu btn_text"
						id="map_line">
						<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
						<span class="opMenu">线路管理</span><span style="float:right;"
							class="glyphicon glyphicon-menu-down opMenu" aria-hidden="true"></span>
					</button>
					<div class="list-group j-child-menu" style="display:none;">
						<button type="button" class="list-group-item opMenu" id="man_line">线路维护</button>
						<button type="button" class="list-group-item opMenu" id="statics_line">数据统计</button>
					</div>
					<button type="button" style="display:none;"
						class="list-group-item  btn-menu btn_icon opMenu1">
						<span class="glyphicon glyphicon-magnet opMenu" aria-hidden="true"></span>
					</button>
				</c:if>

				<c:if test="${admin5!=null}">
					<button type="button" class="list-group-item  btn-menu btn_text ">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<span class="opMenu">班次管理</span> <span style="float:right;"
							class="glyphicon glyphicon-menu-down opMenu" aria-hidden="true"></span>
					</button>
					<div class="list-group j-child-menu" style="display:none;">
						<c:if test="${admin6!=null}">
							<button type="button" class="list-group-item add-arrange opMenu">新增班次</button>
						</c:if>
						<button type="button" class="list-group-item manage-arrange opMenu">数据维护</button>
					</div>
					<button type="button" style="display:none;"
						class="list-group-item  btn-menu btn_icon opMenu1">
						<span class="glyphicon glyphicon-user " aria-hidden="true"></span>
					</button>
				</c:if>
			</div>
			<div class="panel panel-default j-no-radous opMenu">
				<div class="panel-body opMenu">一些注释讲解之类的，这下面太空了</div>
			</div>
		</div>
		
		<div id="content">
			<h1>欢迎您登录咸鱼的梦想厂车管理后台</h1>
		</div>
		
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="w-modal" tabindex="-1" role="dialog"
		aria-labelledby="w-modal-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="w-modal-close2"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="w-modal-label">
						<p id="w-modal-p1"></p>
					</h4>
				</div>
				<div class="modal-body">
					<div id="w-modal-div"></div>
					<p id="w-modal-p2"></p>
					<p id="w-modal-p3"></p>
				</div>
				<div class="modal-footer">
					<form action="servlet/ShowSiteServlet?type=3" method="post">
						<button type="submit" id="w-modal-export" class="btn btn-primary"
							style="display:none">导出报表</button>


						<button type="button" id="w-modal-close" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="w-modal-but"
							onclick="javascript:update()">提交更改</button>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
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
		
		$(".add-arrange").click(function(){
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=path%>/servlet/CreateArrServlet?multi=yes");
		});
		
		$("#cre_car").click(function() {
			$("#content").load("<%=request.getContextPath()%>/jsp_user/create_car.jsp");
		});
		
		$("#man_car").click(function() {
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=1&condition=0&sea_condition=0");
		});

		$("#manange_staff").click(function(){
				$("#content").load("<%=basePath%>servlet/ManageStaffServlet");
			});
		$("#synch_staff").click(function(){
				$("#content").load("<%=basePath%>servlet/ManageSynchServlet");
		});
		
		/*点击个人信息*/
		$("#a_person_info").click(function(){
			$("#content").load("<%=path%>/person_info.jsp");
		});
		
		$("#show_site").click(function(){
				$("#content").load("<%=basePath%>jsp_user/show_site.jsp");
		});
		
		$("#man_line").click(function(){
				$("#content").load("<%=basePath%>/servlet/ManageLineServlet?type=1");
		});
		
		$("#statics_line").click(function(){
				$("#content").load("<%=request.getContextPath()%>/jsp_user/statistics_line.jsp");
		});
		$("#map_site1").click(function(){
				$("#content").load("<%=basePath%>servlet/MapSiteServlet");
				
		});
	</script>
</body>
</html>
