 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
<style type="text/css">
	#addsatation-info ul{
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
<style type="text/css">
		.table{
			width: 100%;
			border-collapse:collapse; 
			border-spacing:0; 
		}
		.fixedThead{
			display: block;
			width: 100%;
		}
		.scrollTbody{
			display: block;
			height: 110px;
			overflow: auto;
			width: 100%;
		}
		.table td,.table th {
			width: 200px;
			border-bottom: none;
			border-left: none;
			border-right: 1px solid #CCC;
			border-top: 1px solid #DDD;
			padding: 2px 3px 3px 4px;
		word-break:break-all; word-wrap:break-word;
			font-size:10px
		}
		.table tr{
			border-left: 1px solid #EB8;
			border-bottom: 1px solid #B74;
			
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
			</div>
			<div id="myPageTop"
				style="position: absolute; top:175px; right:100px;">
			</div>
      <div id="satation-search" class="form-inline" style="position:absolute;margin-left:65px;margin-top:80px;" autoComplete='off'>
      	<input type="text" id="tipinput" placeholder="查询已有站点" />
      	 <button id="checkbut"  style="height: 25px;font-size:10px" class="btn btn-primary" onclick="checksite()">查询</button>
      	
      </div>
       <div id="addsatation-info" style="position: absolute;margin-top:480px;display:none;">	 
         <ul id="info-satation" style="list-style-type:none;">
              <li>&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;&nbsp;名称&nbsp;<input type="text" value="" id="satation-name"/></li>
              <li>&nbsp;&nbsp;&nbsp;&nbsp;经纬度 <input type="text" readonly="readonly" id="satation-lng"/></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址<input type="text" readonly="readonly" id="satation-address"/></li>
              <li>乘坐人数&nbsp;<input type="text" readonly="readonly" id="satation-people"/></li>
              <li>所属路线
              <input type="text" value=""id="satation-route" readonly="readonly"/>
              </li>
              <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编号
              <input type="text" value="" id="satation-number" readonly="readonly"/>
              </li>
               <li>停留时间
              <input type="text" value="" id="satation-delay" />
              </li>
              <li style="float:left;margin-left:30%;width:20%"><button type="submit" id="sbm">确认</button></li>
              <li style="float:right;margin-right:30%;width:20%""><button type="reset" id="set">取消</button></li>
         </ul>                 
      </div>
      
       <div id="name"  class="form-inline" style="position:absolute;display:none;top:190px;right:165px" > <input type="" class="form-control" id="stressname" >&nbsp;&nbsp;&nbsp;
<button  class="btn btn-primary" onclick="javascript:p_s($('#stressname').val(),hhj_ctn)">确认</button>&nbsp;&nbsp;&nbsp;
<button onclick="disnone()"  class="btn btn-primary">隐藏</button> 
</div>
     <div style="position:absolute;right:160px;top:190px;">
    

 <div class="dropdown" style="position:absolute;top:0px">
  <button id="dLabel" style="width:160px" class="btn btn-primary" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   添加站点
    <span class="caret"></span>
  </button>

  <ul class="dropdown-menu" aria-labelledby="dLabel">
  <li><a onclick="console.log(hhj_ctn);addclicksite(hhj_ctn)">点击选点</a></li>
     <li> <a onclick="showinput()"> 搜索选点</a></li>
      <li> <a onclick="javascript:autosite()"> 自动生成</a></li>
  </ul>
 
			
			
</div>


<div  style="position:absolute;top:40px"> <button  style="width:160px" class="btn btn-primary" onclick="disp();">显示/隐藏所有信息</button></div>
<div  style="position:absolute;top:80px"><button  style="width:160px" class="btn btn-primary" onclick="showall()">显示所有站点</button></div>
<div  style="position:absolute;top:120px"><button  style="width:160px" class="btn btn-primary" onclick="showsite()">显示未分配线路站点</button></div>
 <div  style="position:absolute;top:160px"><button  style="width:160px" class="btn btn-primary" onclick="showallstaff()">显示所有员工</button></div>  
    <div  style="position:absolute;top:200px"><button  style="width:160px" class="btn btn-primary" onclick="showstaff()">显示未分配站点员工</button></div>    
    <div  style="position:absolute;top:240px"> <select id="listselect" style="width:160px"  class="btn btn-primary form-control" >
         <option value="-1">选择线路查看</option>
           <c:if test="${site_line_list!=null }">
       <c:forEach items="${site_line_list}" var="line" varStatus="status">
       <option value="${line.getLineId()}">${line.getName()}</option>
       </c:forEach>
       </c:if>
      </select> </div>    
  </div>  
      <div id="route-info">
      
      </div>
      <div id='panel'></div>
      </div>
      
     <div id="collasped" class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"  style="position:fixed;bottom:0px;right:0px;width: 85%"> 
  <div class="panel panel-default">
    <a id="updown" class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree" >
    <div class="panel-heading" role="tab" id="headingThree" style="background-color:#000000;">
        <img id="updownimg" src="images/up.png">
    </div>
     </a>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body" style="height: 200px">
      <c:if test="${site_list!=null }">
     <form action="servlet/ExportSiteServlet" method="post">
			<button type="submit" class="btn btn-primary">导出全部数据</button>
			
		</form>
     	<table class="table  table-hover table-bordered " id="site_table">
		<thead class="fixedThead">
			<tr><th>#</th><th>站点名称</th>
			<th>站点地址</th><th>站点人数</th><th>站点所属线路</th><th>站点所属线路次序</th>
			</tr>
		</thead>
		<tbody class="scrollTbody">
	 <c:forEach items="${site_list}" var="site" varStatus="status" >
	<tr id="${status.index}">
	<td>${status.index+1}</td>
	<td>${site.getName()}</td>
	<td>${site.getAddress()}</td>
	<td>${site.getPeoNum()}</td>
	<td>${site.getLineName()}</td>
		<td>${site.getOrder()}</td>
	</tr>
	</c:forEach>
		</tbody>
	</table>
	</c:if>
      </div>
    </div>
  </div>
</div>
 
 </div> 
	<div id="load_modal" class="modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<b>正在加载，请稍后...</b>
				</div>
				<div class="modal-footer">
				 <button type="button" id="w-modal-but2"  class="btn btn-default" 
              style="display:none" >确定
            </button>
				 <button type="button" id="w-modal-but"  class="btn btn-default" 
              style="display:none" >确定
            </button>
            <button type="button" id="w-modal-close"  class="btn btn-default" 
               data-dismiss="modal" style="display:none" >取消
            </button>
         </div>
			</div>
			
		</div>
	</div>
	<span id="return_satationinfo"></span>
	<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
		<script type="text/javascript" src="js/mapinterface.js"></script>
	<!-- <script type="text/javascript" src="js/testroute.js"></script> -->
	<script type="text/javascript">
	$(function(){
	$('#listselect').change(function () {
		var id = $(this).val();
		if(id==-1)
			map.clearMap();
		else
		$.ajax({ 
		type:"post",
		url: "servlet/ManageSiteServlet",
		data:{
				type:6,
				lineid:id
		}, 
		error: function(request) {
           // document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			map.clearMap();
			var list = eval('(' + request + ')');
			var lsitelist = list.lsitelist;
		
	    	for(var i=0;i<lsitelist.length;i++){		
					satationsmarker(lsitelist[i]);
			}
      }});
	});
	
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
});
		//调节地图大小
		var sitelist,stafflist;
 
            var hhj_ctn;
		$(window).load(function(){
			 hhj_ctn=document.getElementById('addsatation-info').innerHTML;
			 console.log(hhj_ctn);
		var site='${json_site_list}';
		var list = eval('(' + site + ')');
		 sitelist = list.sitelist;
			map.setCity('成都');
		for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
					satationsmarker(sitelist[i]);
				}
			}
			
		var staff='${json_staff_list}';
		 list = eval('(' + staff + ')');
		 stafflist = list.stafflist;
		 
		 for(var i=0;i<stafflist.length;i++){
		 	//console.log(stafflist[i]);
					staffmarker(stafflist[i]);
					
			}
		});
		function showall(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
					satationsmarker(sitelist[i]);
				}
			}
		}
		function showsite(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId==null||sitelist[i].lineId==""){
					satationsmarker(sitelist[i]);
				}
			}
		}
		function showallstaff(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<stafflist.length;i++){
					staffmarker(stafflist[i]);
			}
		}
		function showstaff(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<stafflist.length;i++){
				if(stafflist[i].siteId<0)
					staffmarker(stafflist[i]);
			}
		}
		var turn = false;
		$("#j_nav_toggle").click(function() {
			if (turn) {
				$("#container").css("margin-left", "15%");
				$("#container").css("width", "85%");
				$("#collasped").css("margin-left", "15%");
				$("#collasped").css("width", "85%");
				turn = false;
			} else {
				$("#container").css("margin-left", "4%");
				$("#container").css("width", "96%");
				$("#collasped").css("margin-left", "4%");
				$("#collasped").css("width", "96%");
				turn = true;
			}
		});
		var up=true;
		$("#updown").click(function() {
			if(up){
				$("#updownimg").attr("src","images/down.png");
				up=false;
			}
			else{
				$("#updownimg").attr("src","images/up.png");
				up=true;
			}
		});
		
		var n=0;
var json="{\"slist\":[";
var s="-1",arr=new Array();
	var num;
		function autosite(){
		$(".modal-body").html("请选择是全部生成(清除以往数据)或为未匹配站点员工生成站点");
		$("#w-modal-but2").css("display","inline");
		$("#w-modal-but").css("display","inline");
		$("#w-modal-close").css("display","inline");
		$("#w-modal-but").html("全部生成");
		$("#w-modal-but2").html("部分生成");
		$("#w-modal-close").html("取消");
		$("#w-modal-but").attr("onclick","kmeans();");
		$("#w-modal-but2").attr("onclick","kmeans2();");
		$("#load_modal").modal('show');
		}
		
		
		function kmeans(){
		$(".modal-body").html("正在处理中......");
		$("#w-modal-but2").css("display","none");
		$("#w-modal-but").css("display","none");
		$("#w-modal-close").css("display","none");
		$.ajax({ 
		type:"post",
		url: "servlet/ManageSiteServlet",
		data:{
				type:0,
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
		
			var list = eval('(' + request + ')');
			var sitelist2 = list.bsitelist;
			json="{\"slist\":[";
			arr=new Array();
			n=0;
	    	nextkmeans(sitelist2);
      }});
     
	}
	
	function kmeans2(){
		$(".modal-body").html("正在处理中......");
		$("#w-modal-but2").css("display","none");
		$("#w-modal-but").css("display","none");
		$("#w-modal-close").css("display","none");
		
		$.ajax({ 
		type:"post",
		url: "servlet/ManageSiteServlet",
		data:{
				type:7,
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
		
			var list = eval('(' + request + ')');
			var sitelist2 = list.bsitelist;
			json="{\"slist\":[";
			arr=new Array();
			n=0;
	    	nextkmeans(sitelist2);
      }});
     
	}
	
	
	
	
	function nextkmeans(sitelist2){
		 num=sitelist2.length;
		 
		for(var i= 0; i<sitelist2.length ;i++){
		console.log(num);
		 	satationSuit2(sitelist2[i].longitude,sitelist2[i].latitude,sitelist2[i].bufftag);
	}
}

function satationSuit2(lng,lat,sta){
	//var name=place.name;
	
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:10,
		//panel:'panel'
	});
	console.log([lng,lat]);
	satation_search.searchNearBy("街|路|道",[lng,lat],300,function(status,result){
	
		var dd=result.poiList.pois[0].name+","+result.poiList.pois[0].location+'';//+result.poiList.pois[0].address;
		console.log(result.poiList.pois[0].name);
		console.log(sta);
		if(result.poiList.pois[0]==null){
			arr[0]="未匹配到合适街道";
			arr[1]=lng;
			arr[2]=lat;
		}
		else 
			arr=dd.split(",");
		
		if(n==0){
		 		json=json+"{\"address\":\""+arr[0]+"\",\"long\":"+arr[1]+",\"lati\":"+arr[2]+",\"index\":"+sta+"}";
		 	}
		 	else
		 	{
		 	
		 		json=json+",{\"address\":\""+arr[0]+"\",\"long\":"+arr[1]+",\"lati\":"+arr[2]+",\"index\":"+sta+"}";
		 	}
		 n++;
		 console.log(n);
		if(num==n){
			json=json+']}';
		
			
		$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageSiteServlet", 
		contenttype :"application/x-www-form-urlencoded;charset=utf-8",
		data:{
				type:1,
				jsonlist:json
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			$(".modal-body").html("生成站点完毕");
			$("#w-modal-close").css("display","inline");
			$("#w-modal-close").html("确定");
			var list = eval('(' + request + ')');
		 	sitelist = list.nsitelist;
		 	stafflist=list.stafflist;
			var tab='<thead class="fixedThead"><tr><th>#</th><th>站点名称</th><th>站点地址</th><th>站点人数</th><th>站点所属线路</th><th>站点所属线路次序</th></tr></thead><tbody class="scrollTbody">';
			for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
				satationsmarker(sitelist[i]);
				tab=tab+'<tr id="'+i+'"><td>'+(i+1)+'</td><td>'+sitelist[i].name+'</td><td>'+sitelist[i].address+'</td><td>'+sitelist[i].peoNum+'</td><td>'+sitelist[i].lineName+'</td><td>'+sitelist[i].order+'</td></tr>';
				}
			}
			tab=tab+'</tbody>';
			$("#site_table").html(tab);
			$("#site_table td").click(function() {
 			$("tr").each(function() {
 				$(this).css('background-color','white');
 			})
 				map.clearMap();
             var  tr=$(this).parent().attr("id");
               satationsmarker(sitelist[tr]);
               moveTocenter([sitelist[tr].longitude,sitelist[tr].latitude]);
               $("#"+tr).css('background-color','red');
        
           
            });
	    
      }
      });
		}
			
	});
}
$("#site_table td").click(function() {
 			$("tr").each(function() {
 				$(this).css('background-color','white');
 			})
 				map.clearMap();
             var  tr=$(this).parent().attr("id");
               satationsmarker(sitelist[tr]);
               map.setCenter([sitelist[tr].longitude,sitelist[tr].latitude]);
               $("#"+tr).css('background-color','#CCFFFF');
        
           
            });
  function showinput(){
  	$("#name").css("display","inline");
  }
  function disnone(){
  $("#name").css("display","none");
  }
  
  function checksite(){
  		//alert($("#tipinput").val());
  		$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageSiteServlet", 
		contenttype :"application/x-www-form-urlencoded;charset=utf-8",
		data:{
				type:5,
				sitename:$("#tipinput").val()
		}, 
		error: function(request) {
           // document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
         success:function(request){
        // alert(request);
         map.clearMap();
         	var list = eval('(' + request + ')');
		 	var csitelist = list.csitelist;
		 	for(var i=0;i<csitelist.length;i++){
					satationsmarker(csitelist[i]);
			}
         }
         });
  }
  var dis=true;
  function disp(){
  	if(dis){
  		map.clearMap();
  		dis=false;
  	}
  	else{
  		for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
					satationsmarker(sitelist[i]);
				}
			}
		for(var i=0;i<stafflist.length;i++){
					staffmarker(stafflist[i]);
		}
		dis=true;
  	}
  }
	</script>
</body>
</html>
