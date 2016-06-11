<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>map</title>


<link rel="stylesheet" language='text/css' href="css/map/layout.css"/>

<script src="scripts/jquery.js"></script>
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

</head>

<body>
	<!--头部-->
	<div id="header">
		<div id="log">
			<h1 style="text-indent:10px;">应急指挥系统</h1>
		</div>
		<!--导航菜单栏-->

	</div>
	<!--主体内容区-->
	<div id="main">

		<!--右侧地图-->
		<div id="container" class="right"></div>
		<div id="info">
			<h1>
				加载中...
				</h1>
		</div>

	</div>
	<div id="myPageTop" style="position: absolute; top:175px; right:100px;">
		<table>
			<tr>
				<td><label>按关键字搜索：</label></td>
				<td class="column2"><label>鼠标所在经纬度：</label></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
				</td>
				<td class="column2"><input type="text" readonly="true"
					id="lnglat"></td>
			</tr>
		</table>
		<div id="panel"></div>
	</div>
	<div id="result"></div>



</body>
</html>
