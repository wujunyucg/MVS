<%@page import="edu.swjtu.intelligent.PlanRoute"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    
    <title>My JSP 'map_arrange.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/map/layout.css">
	<link rel="stylesheet" type="text/css" href="css/j-css/map-theme.css">
	<link rel="stylesheet" type="text/css"
		href="css/bootstrap/bootstrap.min.css">

	<script src='scripts/jquery.js'></script>
	<script src='scripts/bootstrap.min.js'></script>
	<!-- map- -->
	<link rel="stylesheet"
		href="http://cache.amap.com/lbs/static/main1119.css" />
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
	<script src="http://webapi.amap.com/js/marker.js"></script>
	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>
	<script type="text/javascript"
		src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

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
				<button type="button" id="manager-satation-btn"
					class="list-group-item  btn-menu btn_text manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
					<span >管理站点</span>
				</button>
				<div id="manager-satation" class="list-group j-child-menu" style="display:none;">
					<button type="button" class="list-group-item" onclick="addsatation()">增加</button>
					<button type="button" class="list-group-item" onclick="Showallsatation()">显示</button>
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
			<div id='container' style="margin-left:15%;margin-top:50px;width:85%;height: 95%"></div>
		
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
      	<input type="text" id="tipinput" value="输入关键字进行查询" />
      	
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
      <div id='panel'></div>
      <span id="return_satationinfo"></span>
      <span id="delsatationnum"></span>
      </div>
	
	
		<div class="btn-group" style="position:fixed;top:150px;right:0px;z-index: 600;">
				<button type="button" class="btn btn-primary dropdown-toggle hc_button" style="width:130px;"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					智能生成路线&nbsp; <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a class="icreline" href="javascript:;" data-toggle="modal" onclick="javascript:icreLine(0)">对未排站点规划</a></li>
					<li><a  class="icreline" href="javascript:;" data-toggle="modal" onclick="javascript:icreLine(1)">对所有站点规划</a></li>
				</ul>
			</div>
			<div style="position:fixed;top:190px;right:0px;">
				<button type="button" id="getallline" class="btn btn-primary hc_button"  style="width:130px;" data-toggle="modal" title="${linelist.size()}">隐藏全部路线</button>
			</div>
			<div class="btn-group"  style="position:fixed;top:230px;right:0px;z-index: 500;">
				<button type="button" class="btn btn-primary dropdown-toggle hc_button"  style="width:130px;"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					显示/隐藏站点 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" >
					<li><a id="shownotsite" href="javascript:;" title="json_allsite">显示未排站点</a></li>
					<li><a id="showallsite" href="javascript:;" title="json_allsite">显示全部站点</a></li>
				</ul>
			</div>
			<div class="btn-group"  style="position:fixed;top:270px;right:0px;z-index: 400;">
				<button type="button" class="btn btn-primary dropdown-toggle hc_button"  style="width:130px;"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					手动创建路线&nbsp; <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a id="creNotSite" href="javascript:;" title="0">对未排站点规划</a></li>
					<li><a  id="creAllSite" href="javascript:;" title="1">对所有站点规划</a></li>
				</ul>
			</div>
			<div  style="position:fixed;top:310px;right:0px;">
				<button type="button"  style="width:130px;" id="surecreLsite" class="btn btn-success" data-toggle="modal">确认所选路线</button>
			</div>
			<div  style="position:fixed;top:350px;right:0px;">
				<button type="button"  style="width:130px;" id="outcreLsite" class="btn btn-danger" data-toggle="modal">退出路线创建</button>
			</div>
	
			
		  <div id="show_linetable" class="panel panel-group panel-default" id="accordion" style="position:fixed;bottom:0px;left:15%;right:0px;">
			<a id="updown" class="collapsed" role="button" data-toggle="collapse" href="#linetable" aria-expanded="true"
				aria-controls="linetable">
				<div class="panel-heading" role="tab" id="headingThree" style="background-color:#000000;">
					<img id="updownimg" src="images/up.png">
				</div>
			</a>
			<div id="linetable"  class="panel-collapse collapse">
			
	 			 <div class="panel-body " style="height: 200px; overflow:auto;">
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th>#</th><th>路线名称</th>
								<th>所经站点</th>
								<th style="width:100px;">车辆数</th>
								<th style="width:100px;">总人数</th>
								<th style="width:100px;">乘坐率</th>
								<th style="width:60px;" >查看</th
								><th style="width:60px;" >修改</th>
								<th style="width:60px;" >删除</th>
							</tr>
						</thead>
						<tbody  id="linetab_con">
						
							<c:if test="${linelist != null }">
								<c:forEach items="${linelist}" var="line" varStatus="status">
									<tr class="${line.getLineId()}">
										<th style="width:60px;" class="row_index">${status.index+1}</th>
										<c:choose>
											<c:when test="${line.getRate() < 0}">
												<td class="text-success">${line.getName()}</td>
												<td class="text-success">${line.getSiteId()}</td>
												<td style="width:100px;" class="text-success"></td>
												<td style="width:100px;" class="text-success">${line.getNum()}</td>
												<td style="width:100px;" class="text-success">
												<fmt:formatNumber type="number" value="${line.getRate() * (-100.0) }" maxFractionDigits="3"/>%</td>
											</c:when>
											<c:otherwise>   
												<td>${line.getName()}</td>
												<td>${line.getSiteId()}</td>
												<td style="width:100px;"></td>
												<td style="width:100px;">${line.getNum()}</td>
	   											<td style="width:100px;">
												<fmt:formatNumber type="number" value="${line.getRate() * (-100.0) }" maxFractionDigits="3"/>%</td>
	 										 </c:otherwise> 
 										 </c:choose>
										<td style="width:60px;">
										<a id="${line.getName()}" onclick="javascript:linedetail('${line.getLineId()}','${line.getName()}')" href="javascript:;" title="${status.index}">查看</a>
										</td>
										<td style="width:60px;"><a class="jimomo">修改</a></td>    
										<td style="width:60px;"  id="dline" onclick="javascript:linedelete('${line.getLineId()}',
										'${line.getName()}','${siteNames.get(status.index)}','${line.getNum()}',
										'${line.getRate()}')" href="javascript:;"  data-toggle="modal" 
										data-target="#de_line"><a>删除</a></td>
									</tr>
								</c:forEach>
							</c:if>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- 确认手动生成路线 -->   
		<div class="modal fade" id="h_creline" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">新建路线</h4></div>

					<div id="hcre_page1" class="mypage">
					
						<div class="modal-body">
							
							<div class="alert alert-info" role="alert">请填写线路名称：</div>
							
							<div class="row">
								<div class="col-lg-7"><div class="input-group">
										<span class="input-group-addon" id="sizing-addon2">线路名称</span>
										<input type="text" id="lin_nam" class="form-control" aria-describedby="sizing-addon2" placeholder="请输入创建路线名称">
								</div></div>
								<div class="col-lg-5">
									<h6>
										<div id="judgeLN1"  class="label label-danger" role="alert" style="display:none;">线路名称未填写</div>
										<div id="judgeLN2"  class="label label-danger" role="alert" style="display:none;">线路名称已存在或含非法字符</div>
									</h6>
								</div>
							</div>
							<br/>
							<li id="runsites" class="list-group-item list-group-item-success" title="" role="alert"></li>
							<li id="peonum" class="list-group-item list-group-item-success" title="" role="alert"></li>
							<li class="list-group-item list-group-item-success" role="alert">乘坐率：0%（未知）</li>
							<br/>
						</div>
						<div class="modal-footer">
							<button id="btn_pre" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button id="hsure_cre" type="button" class="btn btn-primary">确认创建</button>
						</div>
					</div>


					
					<div id="hcre_page2" class="mypage2">
						<div class="modal-body">
							<div id="result" class="alert alert-success" role="alert">已成功新建线路</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" >查看线路</button>
						</div>
					</div>
			</div>
		</div>
	</div>
	
	<!-- 手动生成路线 站点数为空 -->
		<div class="modal fade" id="linesiteNull" role="dialog" aria-labelledby="gridSystemModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content"><div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">路线创建</h4>
				</div>
					<div class="modal-body">
						<div class="alert alert-danger" role="alert">您尚未设置路线所经站点！</div>
					</div>
				<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
    </div>
    </div>

		<!-- 确认生成路线 -->
		<div class="modal fade" id="in_creline" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">新建车辆</h4></div>
					<div id="cre_page1" class="mypage">
						<div class="modal-body">
							<div class="alert alert-info" role="alert">请填写下列自动生成路线所需信息：</div>
							
							<div class="row">
								<div class="col-lg-6"><div class="input-group">
										<span class="input-group-addon" id="sizing-addon2">最低乘坐率</span>
										<input type="text" id="min_rec" class="form-control" aria-describedby="sizing-addon2" placeholder="请输入最低乘坐率">
										<span class="input-group-addon" id="sizing-addon2">%</span>
								</div></div>
								<div class="col-lg-5">
									<h6>
										<div id="judgeMinRec1"  class="label label-danger" role="alert" style="display:none;">乘坐率未填写</div>
										<div id="judgeMinRec2"  class="label label-danger" role="alert" style="display:none;">乘坐率不合法</div>
									</h6>
								</div>
							</div>
							<br/>
							<div class="row">
								<div class="col-lg-3" id="wridata"><div class="input-group">
										<span class="input-group-addon" id="sizing-addon2">最大路程数</span>
					
										<div class="input-group-btn">
											<button type="button" id="Judge_MAXLen" title="1" class="btn btn-default dropdown-toggle"data-toggle="dropdown" aria-haspopup="true" 
											 aria-expanded="false">
												无限制 <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<li><a href="javascript:;" onclick="javascript:notMAXLen()">无限制</a></li>
												<li><a href="javascript:;" onclick="javascript:doMAXLen()">自定义</a></li>
												<li><a href="javascript:;" onclick="javascript:mMAXLen()">车厂距最远站点路程的3/2</a></li>
											</ul>
										</div>
										<!-- /btn-group -->
											<input type="text" id="max_len" class="form-control" aria-describedby="sizing-addon2" placeholder="无限制" disabled="true" >
											<span class="input-group-addon" id="kil">千米</span>
										
								</div></div>
								<div class="col-lg-4">
									<h6>
										<div id="judgeMaxLen1"  class="label label-danger" role="alert" style="display:none;">最大路程数未填写</div>
										<div id="judgeMaxLen2"  class="label label-danger" role="alert" style="display:none;">最大路程数不合法</div>
									</h6>
								</div>
							</div>
							<br/>	<div class="alert alert-info" role="alert">
											相关数据说明如下：<br/>
											<b>最低乘坐率：</b>请填写以%单位格式数据；<br/>
											<b>最大路程数：</b><br/>
											无限制——每条路线都满足最低乘坐率要求；<br/>
											自定义——手动输入每条路线的最长千米数，且填写(千米)为单位数据；<br/>
											车厂距最远站点路程的3/2——所有站点中距离车场最远站点与车场距离的3/2最为最大路程数<br>
											<b>该智能生成路线会删除之前所有路线信息,重新规划路线</b>
										</div>
						</div>
						<div class="modal-footer">
							<button id="btn_pre" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button id="next_cre" type="button" class="btn btn-primary">下一步</button>
						</div>
					</div>
					
					<div id="cre_page2" class="mypage3">
						<div class="modal-body">
							<div class="alert alert-info" role="alert">请确认下列数据无误后点击-确认生成
							（点击确认生成会删除之前所有路线信息）：</div>
							<br/>
							<li id="smin_rec" class="list-group-item list-group-item-success" role="alert"></li>
							<li id="smax_len" class="list-group-item list-group-item-success" role="alert"></li>
						</div>
						<div class="modal-footer">
							<button id="pre_cre" type="button" class="btn btn-default">上一步</button>
							<button id="sure_cre" type="button" class="btn btn-primary">确认生成</button>
						</div>
					</div>
					
					<div id="cre_page3" class="mypage3">
						<div class="modal-body">
							<div id="result" class="alert alert-success" role="alert">已成功智能规划满足条件的所有线路</div>
						</div>
						<div class="modal-footer">
							<button id="sea_fin" type="button" class="btn btn-primary" data-dismiss="modal">查看线路</button>
						</div>
					</div>
					<div id="cre_page4" class="mypage4">
						<div class="modal-body">
							<div id="result" class="alert alert-danger" role="alert">受车辆数限制，所给最低乘坐率或最大路程数无法实现，规划线路失败！</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="goback_i">返回设置</button>
						</div>
					</div>
	
				</div>
			</div>
		</div>


		<!-- 删除路线 -->
		<div class="modal fade" id="de_line" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">删除路线</h4>
					</div>
					<div id="page_d" class="page_d">
						<div class="modal-body">
							<li id="de_name" class="list-group-item list-group-item-success" role="alert"></li>
							<li id="de_siteName" class="list-group-item list-group-item-success" role="alert"></li>
							<li id="de_Num" class="list-group-item list-group-item-success" role="alert"></li>
							<li id="de_Rate" class="list-group-item list-group-item-success" role="alert"></li>
							<br/>			
							<div class="alert alert-danger" role="alert">是否确认删除当前路线？</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-danger" id="su_delete">确认删除</button>
						</div>
					</div>
					<div id="page_ds" class="page_ds">
						<div class="modal-body">
							<div class="alert alert-success" role="alert">已成功删除该车辆数据信息</div>
						</div>
						<div class="modal-footer"><button type="button" class="btn btn-primary"  data-dismiss="modal"
						
						>确认并刷新</button></div>
						</div>
				</div>
			</div>
		</div>

		</div>


	<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
  		<script type="text/javascript" src="js/mapinterface.js"></script>  
	<script type="text/javascript" src="js/testroute.js"></script> 
	<script type="text/javascript">

		var max_length = "-1";
		var jud_r = 0;
		var jud_l = 1;
		var jud_ln1 = 0;
		var jud_ln2 = 0;
		var json_lines = ${json_linelist};
		var json_sites = ${json_sitelist};
		var json_allsite = ${json_allsite};
		var hcremet = 0;
		var choice_icre = 0; 
		var judgecreing = 0;
		
		$("#max_len").hide();
		$("#kil").hide();
		$("#getallline_json").hide();
		$("#cre_page4").hide();
		$("#surecreLsite").hide();
		$("#outcreLsite").hide();
		
		var obj_lt = eval(json_lines);
		for(var i=0;i<obj_lt.length;i++){
			linedetail(obj_lt[i].linelist.lineId,obj_lt[i].linelist.name);
		}
	
		var turn = true;
		var openMenus = new Array();//存放展开的子menu的div
		$("#j_nav_toggle").click(function(){
			if(turn){
				openMenus = [];//clear
				$(".j-child-menu").each(function(){
					if($(this).css("display")=="block"){
						openMenus.push($(this));//若展开的，则加入
					}
				});
				
				$(".j-child-menu").hide();
				$("#j-left-menu").css("width","4%");
				$("#content").css("marginLeft","4%");
				$(".btn_text").hide();
				$(".btn_icon").show();
				turn = false;
			}else{
				for(var i=0;i<openMenus.length;i++){
					openMenus[i].show();//显示出来
				}
				$("#j-left-menu").css("width","15%");
				$("#content").css("marginLeft","15%");
				$(".btn_text").show();
				$(".btn_icon").hide();
				turn = true;
			}
		});
		
		$(".btn_text").click(function(){
			
			$(this).next("div").slideToggle("1000");
		});
		//调节地图大小
		var turn = false;
		$("#j_nav_toggle").click(function() {
			if (turn) {
				$("#container").css("margin-left", "15%");
				$("#container").css("width", "85%");
				$("#show_linetable").css("left", "15%");
				turn = false;
			} else {
				$("#container").css("margin-left", "4%");
				$("#container").css("width", "96%");
				$("#show_linetable").css("left", "4%");
				turn = true;
			}
		});
		
		var up=true;
		$("#updown").click(function(){
			if(judgecreing == 0){
				if(up){
					$("#updownimg").attr("src","images/down.png");
					up=false;
				}
				else{
					$("#updownimg").attr("src","images/up.png");
					up=true;
				}
			}
		});

		$("#getallline").click(function (){
			var obj_linesss = eval(json_lines);
			var temp = "#"
			if($("#getallline").text() == "显示全部路线"){
				for(var i=0;i<obj_linesss.length;i++){
					if(	$(temp+obj_linesss[i].linelist.name).text() == "查看"){
						linedetail(obj_linesss[i].linelist.lineId,obj_linesss[i].linelist.name);
					}
				}
				$("#getallline").text("隐藏全部路线");
			}
			else if($("#getallline").text() == "隐藏全部路线"){
				for(var i=0;i<obj_linesss.length;i++){
					if(	$(temp+obj_linesss[i].linelist.name).text() == "隐藏"){
						linedetail(obj_linesss[i].linelist.lineId,obj_linesss[i].linelist.name);
					}
				}
			//	map.clearMap();
				$("#getallline").text("显示全部路线");
			}
		});
		
		
		$("#showallsite").click(function(){
			var allobj = eval(json_allsite);
			if($("#showallsite").text() == "显示全部站点"){
				if($("#shownotsite").text() == "隐藏未排站点"){
					hideroutesitesmk();
					$("#shownotsite").text("显示未排站点");
				}
				for(var i=0;i<allobj.length;i++){
					setroutesitesmk(allobj[i].allsite,1);
				}
				$("#showallsite").text("隐藏全部站点");
			}
			else if($("#showallsite").text() == "隐藏全部站点"){
				hideroutesitesmk();
				$("#showallsite").text("显示全部站点");
			}
		});
		
		$("#shownotsite").click(function(){
			var allobj = eval(json_allsite);
			if($("#shownotsite").text() == "显示未排站点"){
				 if($("#showallsite").text() == "隐藏全部站点"){
					hideroutesitesmk();
					$("#showallsite").text("显示全部站点");
				}
				for(var i=0;i<allobj.length;i++){
					setroutesitesmk(allobj[i].allsite,0);
				}
				$("#shownotsite").text("隐藏未排站点");
			}
			else if($("#shownotsite").text() == "隐藏未排站点"){
				hideroutesitesmk();
				$("#shownotsite").text("显示未排站点");
			}
		});
		
		
		$("#outcreLsite").click(function(){
			map.clearMap();
			judgecreing = 0;
			$("#shownotsite").text("显示未排站点");
			$("#shownotsite").text("显示未排站点");
			$("#updown").attr("href","#linetable");
			$("#getallline").text("隐藏全部路线");
			$("#getallline").click();  
			$("#getallline").click();  
			$(".hc_button").attr("disabled",false);
			$("#surecreLsite").hide("1000");	
			$("#outcreLsite").hide("1000");	
		});
		
		function linedetail(number,name){
			var obj = eval(json_sites);
			var l_na = "#" + name;
			var num = $(l_na).attr("title");
			if($(l_na).text() == "查看"){
				showroute(obj[num].sitelist, number,name);
				$(l_na).text("隐藏");
			}
			else if($(l_na).text() == "隐藏"){
				DelRoute(number);
				$(l_na).text("查看");
			}
			
		}


		function icreLine(cho){
			choice_icre = cho;
			document.getElementById("max_len").value="";
			document.getElementById("min_rec").value="";
			jud_l = 1;
			jud_r = 0;
			$("#judgeMaxLen2").hide();
			$("#judgeMaxLen1").hide();
			max_length = "-1";
			$("#max_len").hide();
			$("#kil").hide();
			$("#Judge_MAXLen").attr("title", "-1");
			$("#Judge_MAXLen").attr("style", "");
			$("#wridata").attr("class", "col-lg-3");
			$("#Judge_MAXLen").html("无限制 <span class='caret'></span>");
			$("#max_len").attr("disabled", "true");
			document.getElementById("max_len").value="";
			$("#max_len").attr("placeholder", "无限制");
			
			$("#judgeMinRec2").hide();
			$("#judgeMinRec1").hide();
			$("#judgeMaxLen2").hide();
			$("#judgeMaxLen1").hide();
			$("#cre_page1").show();
			$("#cre_page2").hide();
			$("#cre_page3").hide();
			$("#cre_page4").hide();
			$(".icreline").attr("data-target","#in_creline");
		}
		
		function notMAXLen(){
			jud_l = 1;
			$("#judgeMaxLen2").hide();
			$("#judgeMaxLen1").hide();
			max_length = "-1";
			$("#max_len").hide();
			$("#kil").hide();
			$("#Judge_MAXLen").attr("title", "-1");
			$("#Judge_MAXLen").attr("style", "");
			$("#wridata").attr("class", "col-lg-3");
			$("#Judge_MAXLen").html("无限制 <span class='caret'></span>");
			$("#max_len").attr("disabled", "true");
			document.getElementById("max_len").value="";
			$("#max_len").attr("placeholder", "无限制");
		}
		
		function doMAXLen(){
			jud_l = 0;
			max_length = "1";
			$("#max_len").show();
			$("#kil").show();
			$("#Judge_MAXLen").attr("title", "1");
			$("#wridata").attr("class", "col-lg-8");
			$("#Judge_MAXLen").attr("style", "-webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;");
			$("#Judge_MAXLen").html("自定义 <span class='caret'></span>");
			document.getElementById("max_len").disabled = false;
			$("#max_len").attr("placeholder", "请输入最大路程");
			
		}
		
		function mMAXLen(){
			jud_l = 1;
			max_length = "0";
			$("#judgeMaxLen2").hide();
			$("#judgeMaxLen1").hide();
			$("#max_len").hide();
			$("#kil").hide();
			$("#Judge_MAXLen").attr("title", "0");
			$("#wridata").attr("class", "col-lg-3");
			$("#Judge_MAXLen").attr("style", "");
			$("#Judge_MAXLen").html("车厂距最远站点路程的3/2 <span class='caret'></span>");
			$("#max_len").attr("disabled", "true");
			document.getElementById("max_len").value="";
			$("#max_len").attr("placeholder", "车厂距最远站点路程的3/2");
		}
		
		$("#pre_cre").click(function() {
			$("#cre_page1").show();
			$("#cre_page2").hide("1000");
			$("#cre_page3").hide();
			$("#cre_page4").hide();
		});
		
		$("#next_cre").click(function(){
			if(jud_r == 1&& jud_l == 1){
				$("#cre_page1").hide("1000");
				$("#cre_page2").show();
				$("#cre_page3").hide();
				$("#cre_page4").hide();
				var min_rec = $("#min_rec").val();
				var max_len = $("#max_len").val();
				$("#smin_rec").text("最低乘坐率：" + min_rec + "%");
				if(max_length == "1"){
					$("#smax_len").text("最大路程数：" + max_len + "千米");
				}
				else if(max_length == "-1"){
					$("#smax_len").text("最大路程数：无限制，每条路线皆满足乘坐率");
				}
				else if(max_length == "0"){
					$("#smax_len").text("最大路程数：车厂距最远站点路程的3/2");
				}
			}
			var min_rec = $("#min_rec").val();
			if(min_rec == ""||min_rec == null){
				$("#judgeMinRec1").show();
			}
			var max_len = max_length;
			if(max_len == "1"){
				max_len =  $("#max_len").val();
				if(max_len == ""||max_len == null){
					$("#judgeMaxLen1").show();
				}
			}
		});
		var fin = true;
		$("#sure_cre").click(function(){
			fin = false;
			var min_rec = $("#min_rec").val();
			var max_len = max_length;
			if(max_len == "1"){
				max_len =  $("#max_len").val();
			}
			if(min_rec!=null&&min_rec!=""&&max_len!=null&&max_len!=""){
				var choice = "0";
				if(choice_icre == 0){
					choice = "6";
				}else if(choice_icre == 1){
					choice = "2";
				}
				$.ajax({
						url : "servlet/ManageLineServlet",
						type : "POST",
						data : {
							type : choice,
							min_rec : min_rec,
							max_len : max_len,
						},
						success : function(json_list) {
							fin = true;
							$("#hcre_page1").hide("1000");
							$("#hcre_page2").show();
								
						if (json_list != "no") {
							$("#cre_page1").hide();
							$("#cre_page2").hide("1000");
							$("#cre_page3").show();
							$("#cre_page4").hide();
							
							var arr = json_list.toString().split("&");
							json_lines = arr[0];
							json_sites = arr[1];
							json_allsite = arr[2];
							$("#linetab_con").text("");
							$("#linetab_con").html("");
							var lines = json_lines;	
							var obj_lines = eval(lines);
							var tab = "";
							for(var i=0;i<obj_lines.length;i++){
								var index = i+1;
								if(obj_lines[i].linelist.rate < 0){
									tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
									tab += "<td class='text-success'>"+obj_lines[i].linelist.name+"</td>";
									tab += "<td class='text-success'>"+obj_lines[i].linelist.siteId+"</td>";
									tab += "<td class='text-success'>"+"</td>";
									tab += "<td class='text-success'>"+obj_lines[i].linelist.num+"</td>";
									tab += "<td class='text-success'>"+obj_lines[i].linelist.rate*(-100)+"%</td>";
								}else{
									tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
									tab += "<td>"+obj_lines[i].linelist.name+"</td>";
									tab += "<td>"+obj_lines[i].linelist.siteId+"</td>";
									tab += "<td>"+"</td>";
									tab += "<td>"+obj_lines[i].linelist.num+"</td>";
									tab += "<td>"+obj_lines[i].linelist.rate*100+"%</td>";
								}
								
								tab += "<td style='width:60px;'><a id='"+obj_lines[i].linelist.name;
								tab += "' onclick=javascript:linedetail('"+obj_lines[i].linelist.lineId+"','"+obj_lines[i].linelist.name+"') href='javascript:;' title='"+i+"'>查看</a></td>";
								
								tab += "<td style='width:60px;'><a class='jimomo'>修改</a></td>";
								
								tab += "<td style='width:60px;'  id='dline' onclick=javascript:linedelete('"+obj_lines[i].linelist.lineId+"',";
								tab += "'"+obj_lines[i].linelist.name+"','"+obj_lines[i].linelist.siteId+"','"+obj_lines[i].linelist.num+"',";
								tab += "'"+obj_lines[i].linelist.rate+"') href='javascript:;'  data-toggle='modal' data-target='#de_line'><a>删除</a></td>    ";
							
							}
							
	 						$("#linetab_con").html(tab);	
	 						$("#outcreLsite").click();  
						} else{
							$("#cre_page4").show();
							$("#cre_page1").hide();
							$("#cre_page2").hide("1000");
							$("#cre_page3").hide();
							//if(choice_icre == 1){
							
							//}
						}
					}
				});
			}
			else{
				if(min_rec==null||min_rec==""){
					$("#judgeMinRec1").show();
				}
				if(max_len==null||max_len==""){
					$("#judgeMaxLen1").show();
				}
			}
		});
		
		$("#min_rec").keyup(function() {
				var min_rec = $("#min_rec").val();
				if(min_rec != ""){
					$("#judgeMinRec1").hide();
					$.ajax({
						url : "servlet/ManageLineServlet",
						type : "POST",
						data : {
							type : "3",
							min_rec : min_rec,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeMinRec2").hide();
								jud_r = 1;
								
							} else {
								$("#judgeMinRec2").show();
								jud_r = 0;
							}
						}
					});
				}
				else{
					$("#judgeMinRec2").hide();
					$("#judgeMinRec1").show();
					jud_r = 0;
				}
			});
			
			$("#max_len").keyup(function() {
				var max_len = max_length;
				if(max_len == "1"){
					max_len =  $("#max_len").val();
				}
				if(max_len != ""){
					
					$("#judgeMaxLen1").hide();
					$.ajax({
						url : "servlet/ManageLineServlet",
						type : "POST",
						data : {
							type : "4",
							max_len : max_len,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeMaxLen2").hide();
								jud_l = 1;
							} else {
								$("#judgeMaxLen2").show();
								jud_l = 0;
							}
						}
					});
				}
				else{
					$("#judgeMaxLen2").hide();
					$("#judgeMaxLen1").show();
					jud_l = 0;
				}
			});
		
		
		$(".jimomo").click(function(){
 				var id = setInterval(function() {
 					if(true){
 						var pr = "${pr.getPro()}";
 						console.log("pr:"+pr+"fin:"+fin);
 					}
 				}, 500);
		});
		
		var lineId="";
		function linedelete(LineId,Name,siteNames,Num,Rate){
			lineId = LineId;
			$("#de_name").text("路线名称：" + Name);
			$("#de_siteName").text("所经站点：" + siteNames);
			if(Rate < 0){
				Rate = Rate * (-100.0);
			}
			else{
				Rate = Rate * 100.0;
			}
			$("#de_Num").text("路线人数：" + Num);
			$("#de_Rate").text("乘坐率：" + Math.round(Rate * 1000) / 1000 + "%");
			$("#page_d").show();
			$("#page_ds").hide();
			
		}
		
		$("#su_delete").click(function(){
			$.ajax({
						url : "servlet/ManageLineServlet",
						type : "POST",
						data : {
							type : "5",
							lineId : lineId,
						},
						success : function(json_list) {
							$("#page_d").hide("1000");
							$("#page_ds").show();
							var arr = json_list.toString().split("&");
							json_lines = arr[0];
							json_sites = arr[1];
							json_allsite = arr[2];
							$("#linetab_con").text("");
							$("#linetab_con").html("");
							var lines = json_lines;	
							var obj_lines = eval(lines);
							var tab = "";
							for(var i=0;i<obj_lines.length;i++){
							
								var index = i+1;
								if(obj_lines[i].linelist.rate < 0){
									tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
									tab += "<td class='text-success'>"+obj_lines[i].linelist.name+"</td>";
									tab += "<td class='text-success'>"+obj_lines[i].linelist.siteId+"</td>";
									tab += "<td class='text-success'>"+"</td>";
									tab += "<td class='text-success'>"+obj_lines[i].linelist.num+"</td>";
									tab += "<td class='text-success'>"+obj_lines[i].linelist.rate*(-100)+"%</td>";
								}else{
									tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
									tab += "<td>"+obj_lines[i].linelist.name+"</td>";
									tab += "<td>"+obj_lines[i].linelist.siteId+"</td>";
									tab += "<td>"+"</td>";
									tab += "<td>"+obj_lines[i].linelist.num+"</td>";
									tab += "<td>"+obj_lines[i].linelist.rate*100+"%</td>";
								}
								
								tab += "<td style='width:60px;'><a id='"+obj_lines[i].linelist.name;
								tab += "' onclick=javascript:linedetail('"+obj_lines[i].linelist.lineId+"','"+obj_lines[i].linelist.name+"') href='javascript:;' title='"+i+"'>查看</a></td>";
								
								tab += "<td style='width:60px;'><a class='jimomo'>修改</a></td>";
								
								tab += "<td style='width:60px;'  id='dline' onclick=javascript:linedelete('"+obj_lines[i].linelist.lineId+"',";
								tab += "'"+obj_lines[i].linelist.name+"','"+obj_lines[i].linelist.siteId+"','"+obj_lines[i].linelist.num+"',";
								tab += "'"+obj_lines[i].linelist.rate+"') href='javascript:;'  data-toggle='modal' data-target='#de_line'><a>删除</a></td>    ";
								
							}
	 						$("#linetab_con").html(tab);			
							$("#outcreLsite").click();  
						}
				});
		});

		$("#goback_i").click(function(){
			$("#cre_page1").show();
			$("#cre_page2").hide();
			$("#cre_page3").hide();
			$("#cre_page4").hide("1000");
		});
		
		$("#creNotSite").click(function(){
			$("#getallline").text("显示全部路线");
			map.clearMap();
			if(up==false){
				document.getElementById("updown").click();  
			}
			judgecreing = 1;
			$("#updown").attr("href","javascript:;");
			$(".hc_button").attr("disabled","true");
			$("#surecreLsite").show("1000");
			$("#outcreLsite").show("1000");
			creNotSite = 0;
			var allobj = eval(json_allsite);
			Routeshowsizt(allobj,0);
		});
		
		$("#creAllSite").click(function(){
			$("#getallline").text("显示全部路线");
			map.clearMap();
			if(up==false){
				document.getElementById("updown").click();  
			}
			judgecreing = 1;
			$("#updown").attr("href","javascript:;");
			$(".hc_button").attr("disabled","true");
			$("#surecreLsite").show("1000");
			$("#outcreLsite").show("1000");
			creNotSite = 1;
			var allobj = eval(json_allsite);
			Routeshowsizt(allobj,1);
		});
		
		
		$("#surecreLsite").click(function(){
			
		});
		
		$("#lin_nam").keyup(function() {
				var line_name = $("#lin_nam").val();
				if(line_name != ""){
					jud_ln1 = 1;
					$("#judgeLN1").hide();
					$.ajax({
						url : "servlet/ManageLineServlet",
						type : "POST",
						data : {
							type : "7",
							line_name : line_name,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeLN2").hide();
								jud_ln2 = 1;
							} else {
								$("#judgeLN2").show();
								jud_ln2 = 0;
							}
						}
					});
				}
				else{
					$("#judgeLN2").hide();
					$("#judgeLN1").show();
					jud_ln1 = 0;
				}
		});
		
		function h_creLine(newline){
			var site_ids = newline[0].siteId;
			var site_names = newline[0].name;
			var num = newline[0].peoNum;
			if(creNotSite == 1){
				num = "预计人数请创建后查看";
			}
			for(var i=1;i<newline.length;i++){
				site_ids += "," + newline[i].siteId; 
				site_names += "-" + newline[i].name;
				if(creNotSite == 0){
					num += newline[i].peoNum; 
				}
			}
			$("#runsites").attr("title",site_ids);
			$("#runsites").text("路径站点：" + site_names);
			if(creNotSite == 0){
				$("#peonum").attr("title",num);
				$("#peonum").text("预计人数："+num);
			}else{
				$("#peonum").attr("title","预计人数请创建后查看");
				$("#peonum").text("预计人数：请创建后查看");
			}
		}
			
		$("#hsure_cre").click(function(){
			var line_name = $("#lin_nam").val();
			var siteId = $("#runsites").attr("title");
			var peoNum = $("#peonum").attr("title");
			if(jud_ln1 == 1 && jud_ln2 == 1){
				$.ajax({
					url : "servlet/ManageLineServlet",
					type : "POST",
					data : {
						type : "8",
						line_name : line_name,
						siteId : siteId,
						peoNum : peoNum,
					},
					success : function(json_list) {
						$("#hcre_page1").hide("1000");
						$("#hcre_page2").show();
						var arr = json_list.toString().split("&");
						json_lines = arr[0];
						json_sites = arr[1];
						json_allsite = arr[2];
						$("#linetab_con").text("");
						$("#linetab_con").html("");
						
						var lines = json_lines;	
						var obj_lines = eval(lines);
						var tab = "";
						for(var i=0;i<obj_lines.length;i++){
							var index = i+1;
							if(obj_lines[i].linelist.rate < 0){
								tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
								tab += "<td class='text-success'>"+obj_lines[i].linelist.name+"</td>";
								tab += "<td class='text-success'>"+obj_lines[i].linelist.siteId+"</td>";
								tab += "<td class='text-success'>"+"</td>";
								tab += "<td class='text-success'>"+obj_lines[i].linelist.num+"</td>";
								tab += "<td class='text-success'>"+obj_lines[i].linelist.rate*(-100)+"%</td>";
							}else{
								tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
								tab += "<td>"+obj_lines[i].linelist.name+"</td>";
								tab += "<td>"+obj_lines[i].linelist.siteId+"</td>";
								tab += "<td>"+"</td>";
								tab += "<td>"+obj_lines[i].linelist.num+"</td>";
								tab += "<td>"+obj_lines[i].linelist.rate*100+"%</td>";
							}
							
							tab += "<td style='width:60px;'><a id='"+obj_lines[i].linelist.name;
							tab += "' onclick=javascript:linedetail('"+obj_lines[i].linelist.lineId+"','"+obj_lines[i].linelist.name+"') href='javascript:;' title='"+i+"'>查看</a></td>";
							tab += "<td style='width:60px;'><a class='jimomo'>修改</a></td>";
							
							tab += "<td style='width:60px;'  id='dline' onclick=javascript:linedelete('"+obj_lines[i].linelist.lineId+"',";
							tab += "'"+obj_lines[i].linelist.name+"','"+obj_lines[i].linelist.siteId+"','"+obj_lines[i].linelist.num+"',";
							tab += "'"+obj_lines[i].linelist.rate+"') href='javascript:;'  data-toggle='modal' data-target='#de_line'><a>删除</a></td>    ";
						
						}
						
 						$("#linetab_con").html(tab);		
 						$("#surecreLsite").hide("1000");	
						$("#outcreLsite").hide("1000");	
						map.clearMap();
						linedetail(obj_lines[obj_lines.length-1].linelist.lineId,obj_lines[obj_lines.length-1].linelist.name);
						judgecreing = 0;
						$("#updown").attr("href","#linetable");
						$("#getallline").text("显示全部路线");
						$(".hc_button").attr("disabled",false);
						$("#surecreLsite").hide("1000");	
						$("#outcreLsite").hide("1000");	
					}
				});
			}
			else{
				if(jud_ln1 == 0){
					$("#judgeLN1").show();
				}
				if(jud_ln2 == 0){
					$("#judgeLN2").show();
				}
			}
		});		
	</script>
<div>
 	
</div>
  </body>
</html>
