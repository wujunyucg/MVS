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

<title>user help</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="javascript:;">管理员帮助文档</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">员工管理 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" class="guide" id="1a">数据维护</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="javascript:;" class="guide" id="2a">数据同步</a></li>
					</ul></li>
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">车辆管理 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" class="guide" id="3a">新建车辆数据</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="javascript:;" class="guide" id="4a">数据维护与查询</a></li>
					</ul></li>
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">站点管理 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" class="guide" id="5a">站点维护</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="javascript:;" class="guide" id="6a">数据统计</a></li>
					</ul></li>
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">线路管理 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" class="guide" id="7a">线路维护</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="javascript:;" class="guide" id="8a">统计分析</a></li>
					</ul></li>
				<li class="dropdown"><a href="javascript:;"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false">排班管理 <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" class="guide" id="9a">新增班次</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="javascript:;" class="guide" id="aa">数据维护</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div style="margin:60px 180px;">
		<div class="panel panel-default per-info-div" id="1">
			<div class="panel-heading">
				<b>员工管理-数据维护</b>
			</div>
			<div class="panel-footer">系统支持关键字查询，管理员可以按照员工工号、员工姓名、员工部门、
				员工组别、所属班次、所属线路和所属站点， 从员工数据中筛选想要的数据。下图显示了按照员工部门为人事部来查询员工的情况。
				选中想要操作的员工，管理员可以查看该员工的详细信息，修改员工信息以及删除该员工信息。</div>
		</div>
		<div class="panel panel-default  per-info-div" id="2">
			<div class="panel-heading">
				<b>员工管理-数据同步</b>
			</div>
			<div class="panel-footer">
				管理员可通过操作使员工数据与HR数据同步，同步方式分为即时同步和定时同步，并可对定时同步的周期进行修改，管理员也可以通过手动输入和文件上传实现同步。</div>
		</div>
		<div class="panel panel-default per-info-div" id="3">
			<div class="panel-heading">
				<b>车辆管理-新建车辆数据</b>
			</div>
			<div class="panel-footer">
				点击新建车辆数据，依次输入车辆的车牌号、品牌、座位数、行驶证、注册日期、保险日期、司机和驾驶证信息，点击下一步，按提示完成车辆数据的驶输入</div>
		</div>
		<div class="panel panel-default per-info-div" id="4">
			<div class="panel-heading">
				<b>车辆管理-数据维护与查询</b>
			</div>
			<div class="panel-footer">
				点击数据维护和查询，系统支持关键字查询，管理员可以按照车牌、品牌、司机、座位数、驾驶证和行驶证进行查询，
				从车辆数据中筛选想要的数据。也可以点击查询全部车辆，显示所有车辆信息。<br>
				选中想要操作的车辆数据，可查看详情、修改或删除数据。<br>
				界面右上角可选择“导出当前查询数据”和“导出全部数据”按钮，进行数据导出，数据将以excel形式下载
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="5">
			<div class="panel-heading">
				<b>站点管理-站点维护</b>
			</div>
			<div class="panel-footer">
				添加站点 添加站点可以通过三种途径:点击添加、搜索添加和自动生成。 <br /> <strong>1.点击添加</strong><br>
				在右侧功能栏点击添加站点-点击添加后，管理员可以在地图上直接操作，选中某处，弹出提示框，输入该站点名称，乘坐人数，编号和停留时间(经纬度和地址信息自动识别，所属线路在线路排好后会自动显示)，点击确定，即可成功添加该站点。
				<br /> <br /> <strong>2. 搜索添加</strong><br>
				点击添加站点-搜索添加，在搜索框内输入地址，即可定位到此位置设置站点，同样，需要输入该站点名称，乘坐人数，编号和停留时间(经纬度和地址信息自动识别，所属线路在线路拍好后会自动显示)，点击确定，即可成功添加该站点。
				<br /> <br /> <strong>3. 自动生成</strong><br>
				自动生成站点包括部分生成和全部生成。点击自动生成-部分生成，即可为未安排站点的员工安排站点，点击自动生成-全部生成，系统会自动清空以前所有站点（但可恢复），并为所有员工自动安排站点。选择自动生成后，地图上会显示所有站点的位置。
				<br /> <br /> <strong>4. 基于地图的信息查看</strong><br>
				点击地图界面右侧功能按钮“显示/隐藏所有信息”，可在地图上显示所有的员工位置（黄色标记）和所有站点位置（蓝色标记），再次点击按钮将隐藏所有信息。同样，点击“显示所有站点”、“显示未分配线路站点”“显示所有员工”“显示未分配站点员工”，可直观的在地图上查看对应的信息。
				点击“选择线路查看”下拉线路列表，可以选择相应路线查看其站点信息 <br /> <br /> <strong>5.
					站点数据维护</strong><br />
				地图下面有个上拉按钮，点击上拉后可以查看所有站点信息，选中某个站点，可以查看该站点详情，地图也将自动定位至该站点，在地图上右键点击站点。可对其进行移动、修改和删除操作。同时，上拉界面设计导出数据按钮，支持以excel形式导出所有站点数据。
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="6">
			<div class="panel-heading">
				<b>站点管理-数据统计</b>
			</div>
			<div class="panel-footer">
				点击数据统计，可按天、周、月查看相应时间各站点乘车人数，以柱状图形式直观显示。其中，以天统计页面，
				点击某个站点对应的柱形图，可查看该站点乘车人员名单。</div>
		</div>
		<div class="panel panel-default per-info-div" id="7">
			<div class="panel-heading">
				<b>线路管理-线路维护</b>
			</div>
			<div class="panel-footer">
				<strong>1.智能生成路线</strong><br>
				管理员可通过一键点击智能生成厂车路线，智能生成线路功能包括对未排站点进行规划和对所有站点进行规划。需要注意的是：如果点击对所有站点进行规划，那么以前的所有线路数据都会自动清空（但可恢复）。
				点击自动生成后，需要设置自动生成路线的条件：包括最低乘车率和最大路程数，最大路程数有三项选择：无限制---每条线路都满足最低乘坐率要求，自定义——手动输入每条路线的最长千米数，单位为千米，车厂距最远站点路程的3/2——厂车行驶总路程不超过车厂距最远站点路程的3/2。
				自动生成线路后，线路上所有站点变为绿色图标且不可编辑 <br /> <br /> <strong>2.
					显示全部路线 </strong><br> 点击该按钮，可以在地图上看到所有线路的分布情况。 <br /> <br /> <strong>3.显示/隐藏站点</strong><br>
				点击一次会显示所有站点，再点击一次会隐藏所有站点 <br /> <br /> <strong>4.生成员工路线</strong><br>
				在手动创建或智能生成线路后，点击该按纽，线路信息将导入员工信息和站点信息内，在此操作后，查看员工信息即可查看其乘坐线路，点击站点即可查看其所属线路<br />
				<br /> <strong>5.线路数据维护</strong><br>
				地图下面有个上拉按钮，点击上拉后可以查看所有线路信息，选中某条线路，可以进行查看详情、修改或删除，同时左上方设计导出数据按钮，支持以excel形式导出所有线路数据
				<br /> <br /> <strong>6.手动创建路线</strong><br>
				手动创建路线同样分为对未排站点进行规划和对所有站点进行规划，如果选择对未排站点进行规划，地图上将显示所有未排站点（蓝色图标），如果选择对所有站点进行规划，将自动清空所有历史线路，并在地图上显示所有站点（蓝色图标）。右键点击地图上的蓝色站点图标（蓝色即为未排站点），共有四个选项，设为起点、路径径点、取消设置和查看路线，管理员可手动依次点击所需站点生成线路，选中的站点图标将变为红色，点击查看路线，当前路线的所有站点显示为黄色<br />
				<br />
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="8">
			<div class="panel-heading">
				<b>线路管理-统计分析</b>
			</div>
			<div class="panel-footer">管理员可选择查询周期——天、周、月，在选中相应时间后，下方会以柱状图形式直观显示各线路乘坐率情况，点击统计图右上方，可以excel形式导出统计数据。
				根据已有信息，统计图下方会显示建议优化的线路名称，点击“线路优化建议”，可查看详细的数据分析情况和线路优化建议。其中以天统计页面，下方还设置有“线路人员”查询按钮，可以提供对某一线路乘车人员名单的查询和导出服务
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="9">
			<div class="panel-heading">
				<b>班次管理-新增班次</b>
			</div>
			<div class="panel-footer">
				<strong>1. 新增月班次</strong><br />
				点击新增班次开始操作，输入班次月份，并手动设置每日班次数，依次设置班次的发车时间、线路名称并选择车辆，点击“完成”完成该班次的创建。需要注意的是，创建成功的班次将在该月的每一天生效
				<br /> <br /> <strong>2. 新增临时班次</strong><br />
				此外，在用工高峰期，可点击“加班车”，手动输入日期、时间、班次名称，线路和车辆，生成追加班次，当天有效，有效缓解高峰期车辆不够的情况
			</div>
		</div>
		<div class="panel panel-default per-info-div" id="a">
			<div class="panel-heading">
				<b>班次管理-数据维护</b>
			</div>
			<div class="panel-footer">
				可以点击“按月查看”，查看某月份排班数据， 也可点击“全部班次”查看全部班次的详细信息，选中班次可进行修改或删除操作。<br />
				界面右上方也可选择导入或导出，点击导入可以通过excel导入排班数据，也可以点击导出，导出排班数据
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(".guide").click(function() {
			var id = $(this).attr("id");
			id = id.substr(0, 1);
			$("body,html").animate({
				scrollTop : $("#" + id).offset().top - 60
			});
		});
	</script>
</body>
</html>
