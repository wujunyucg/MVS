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

<title>My JSP 'mynavbar.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/map/layout.css">
<link rel="stylesheet" type="text/css" href="css/j-css/j-theme.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">

<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
<script type="text/javascript" src="scripts/j-scripts/j-theme.js"></script>

<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/js/marker.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<style type="text/css">
	ul{
		list-style-type:none;
		width:90%;
		margin:0px auto;
	}
	#addroute li{
		margin-top:10px;
	}
	#addroute li input{
		width:80%;
		float:right;
	}
	#addroute li span{
		width:80%;
		float:right;
	}
	#addroute-number{
		width:80%;
		float:right;
	}
	.button-left{
		float:left;
		width:30%;
	}
	.button-right{
		float:right;
		width:30%;
	}
</style>
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
					class="j-btn-active list-group-item btn-menu btn_text create-admin">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					管理人员 <span style="float:right;"class="glyphicon glyphicon-menu-down" aria-hidden="true"></span>
				</button>
				<div class="list-group j-child-menu" style="display:none;">
					<button type="button" class="list-group-item">增加</button>
					<button type="button" class="list-group-item">查询</button>
					<button type="button" class="list-group-item">显示</button>
				</div>
				<button type="button" style="display:none;"
					class="list-group-item btn-menu btn_icon create-admin">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text create-user">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
					管理线路
				</button>
				<div id="manager-satation" class="list-group j-child-menu" style="display:none;">
					<button type="button" class="list-group-item" onclick="addroute()">增加</button>
					<button type="button" class="list-group-item" onclick="map.clearMap();showAllRoute(hhj_satation)">显示</button>
				</div>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon create-user">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
					管理站点
				</button>
				<div id="manager-satation" class="list-group j-child-menu" style="display:none;">
					<button type="button" class="list-group-item" onclick="addsatation()">增加</button>
					<button type="button" class="list-group-item" onclick="map.clearMap();markers(hhj_satation)">显示</button>
				</div>
				<button id="manage-admin" type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					管理车辆
				</button>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					管理班次
				</button>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</button>
			</div>
			<div class="panel panel-default j-no-radous">
				<div class="panel-body">一些注释讲解之类的，这下面太空了</div>
			</div>
		</div>
		<div id="content">
			<div id='container' style="margin-left:255px;margin-top:50px;width:80%"></div>
			
			<div id="info">
				<h1>
					<h1>
			</div>
			<div id="myPageTop"
				style="position: absolute; top:175px; right:100px;">
				<div id="panel"></div>
			</div>
			<div id="addroute" style="position:absolute;margin-left:55px;margin-top:105px;background-color:#fff;width:180px;display:none">
				<ul>
					<li>路线<select size="1" id="addroute-number"></select></li>
					<li id="addroute-start">起点<input type="text" value=""/></li>
					<li id="addroute-end">终点<input type="text" value=""/></li>
					<li >用时<span id="addroute-time">111</span></li>
					<li >距离<span id="addroute-distance">111</span></li>
					<li id="addroute-sbm"><button class="button-left">确认</button></li>
					<li id="addroute-quit"><button class="button-right">取消</button></li>
				</ul>
			</div>
			<div id="result" style="position:absolute;margin-left:55px;margin-top:105px;background-color:#fff;width:180px;display:none">		
					<span id="routenumber",align="center" style="width:90%;margin:0px 10px;text-align:center">线路</span></br>	
					<span id="start-end" style="width:90%;margin:0px 10px;">起点---终点</span>
					<table style="width:90%;margin:0px 10px;">
					<tr >
						<td>时间</td>
						<td>距离</td>
					</tr>
					<tr id="time-distance">
						<td>155</td>
						<td>155</td>
					</tr>
				</table>
			</div>
			<div>
        <ul  id="routes" style="position: absolute;display:none;">
            <li><button >线路1</button></li>
            <li><button >线路2</button></li>
            <li><button >线路3</button></li>
        </ul>
    </div>
	<div style="position: absolute;margin-left:355px;margin-top:50px;">
        <!--站点管理-->
        <ul id="satation" style="display:none;">
            <li>站点管理</li>
            <ul >
                <li><button onclick="addsatation()">添加站点</button></li>
                <li>删除站点</li>
                <li><button onclick="map.clearMap();markers(hhj_satation)">显示所有</button></li>
                <li>查询站点</li>
            </ul>
        </ul>
        <ul id="manage_route" style="display:none">
            <li>路线管理</li>
            <ul >
                <li>添加路线</li>
                <li>删除路线</li>
                <li>详细信息</li>
                <li>查询路线</li>
            </ul>
            <li><input id="lnglat" value=''/></li>
        </ul>
      </div>
      <div id="satation-search" style="position:absolute;margin-left:65px;margin-top:80px;width:180px" autoComplete='off'>
      	<input type="text" value="输入关键字进行查询" />
      </div>
      <div id="addsatation-info" style="position: absolute;margin-top:480px;display:none;">	 
         <ul id="info-satation" style="list-style-type:none;">
              <li>&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;&nbsp;名称&nbsp;<input type="text" value="" id="satation-name"/></li>
              <li>&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;&nbsp;地址&nbsp;<input type="text" value="" id="satation-address"/></li>
              <li>&nbsp;&nbsp;&nbsp;经纬度 &nbsp;<input type="text" id="satation-lng"/></li>
              <li>乘坐人数&nbsp;<input type="text" id="satation-people"/></li>
              <li>所属路线
              	<select size="1" style="margin-bottom:10px;" id="satation-route">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                 </select></li>
              <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp编号
                  <select size="1" style="margin-bottom:10px;" id="satation-number">
                     <option value="1">1</option>
                     <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                     <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                     <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                     <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                     <option value="15">15</option>
                    <option value="16">16</option>
                  </select>
              </li>
              <li style="float:left;margin-left:30%;width:20%"><button type="submit" id="sbm">确认</button></li>
              <li style="float:right;margin-right:30%;width:20%""><button type="reset" id="set">修改</button></li>
         </ul>                 
      </div>
      <div id="route-info">
      
      </div>
      </div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript">
		//调节地图大小
		var turn = false;
		$("#j_nav_toggle").click(function() {
			if (turn) {
				$("#container").css("margin-left", "255px");
				$("#container").css("width", "80%");
				turn = false;
			} else {
				$("#container").css("margin-left", "50px");
				$("#container").css("width", "95%");
				turn = true;
			}
		});
	</script>
</body>
</html>
