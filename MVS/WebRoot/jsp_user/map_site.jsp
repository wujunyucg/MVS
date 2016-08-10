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
<%
	if (null == session.getAttribute("user")) {
		response.sendRedirect(path+"/jsp_user/user.jsp");
		return;
	}
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
	<!-- map- -->
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
 
	

		<div id='container' style="margin-top:50px;"></div>
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
<div id="ternamediv"  class="form-inline" style="position:absolute;display:none;top:470px;right:165px" > <input type="" class="form-control" id="tername" placeholder="现在未有终点，请输入">&nbsp;&nbsp;&nbsp;
<button  class="btn btn-primary" onclick="javascript:p_s1($('#tername').val(),hhj_ctn)">确认</button>&nbsp;&nbsp;&nbsp;
<button onclick="$('#ternamediv').css('display','none')"  class="btn btn-primary">隐藏</button> 
</div>
<button id="exitclick" onclick="$('#exitclick').css('display','none')" style="position:absolute;display:none;top:190px;right:165px" class="btn btn-primary">退出点击选点</button> 
     <div style="position:absolute;right:160px;top:190px;">
    

 <div class="dropdown" style="position:absolute;top:0px">
  <button id="dLabel" style="width:160px" class="btn btn-primary" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   添加站点
    <span class="caret"></span>
  </button>

  <ul class="dropdown-menu" aria-labelledby="dLabel">
  <li><a onclick="addclicksite(hhj_ctn);$('#exitclick').css('display','inline')">点击选点</a></li>
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
    
      <div  style="position:absolute;top:280px"><button  style="width:160px" class="btn btn-primary" onclick="terminal()">管理终点</button></div>    
  </div>  
   
  
  
      <div id="route-info">
      
      </div>
      <span id="return_satationinfo"></span>
      <div id='panel'></div>
<span id="addroute-number"></span>
      
     <div id="collasped" class="panel-group accordionLS" id="accordion" role="tablist" aria-multiselectable="true"  style="position:fixed;bottom:-21px;left:230px;;right:0px;z-index:800;"> 
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
 

	<div id="ww-load_modal" class="modal fade"  tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body" id="ww-modal-boay">
					<b>正在加载，请稍后...</b>
				</div>
				<div class="modal-footer">
				 <button type="button" id="ww-modal-but2"  class="btn btn-default" 
              style="display:none" >确定
            </button>
				 <button type="button" id="ww-modal-but"  class="btn btn-default" 
              style="display:none" >确定
            </button>
            <button type="button" id="ww-modal-close"  class="btn btn-default" 
               data-dismiss="modal" style="display:none" >取消
            </button>
         </div>
			</div>
			
		</div>
	      </div>
	

<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
  	<script type="text/javascript" src="js/mapinterface.js"></script>  



	<script type="text/javascript">
	








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
  
  
	var sitelist,stafflist;
 
            var hhj_ctn;
$(function(){
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
		$("#ww-modal-body").html("请选择是全部生成(清除以往数据)或为未匹配站点员工生成站点");
		$("#ww-modal-but2").css("display","inline");
		$("#ww-modal-but").css("display","inline");
		$("#ww-modal-close").css("display","inline");
		$("#ww-modal-but").html("全部生成");
		$("#ww-modal-but2").html("部分生成");
		$("#ww-modal-close").html("取消");
		$("#ww-modal-but").attr("onclick","kmeans();");
		$("#ww-modal-but2").attr("onclick","kmeans2();");
		$("#ww-load_modal").modal('show');
		}
		
		
		function kmeans(){
		$("#ww-modal-body").html("正在处理中......");
		$("#ww-modal-but2").css("display","none");
		$("#ww-modal-but").css("display","none");
		$("#ww-modal-close").css("display","none");
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
		$("#ww-modal-body").html("正在处理中......");
		$("#ww-modal-but2").css("display","none");
		$("#ww-modal-but").css("display","none");
		$("#ww-modal-close").css("display","none");
		
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
			$("#ww-modal-body").html("生成站点完毕");
			$("#ww-modal-close").css("display","inline");
			$("#ww-modal-close").html("确定");
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
		
		function terminal(){
	
			$.ajax({ 
			type:"post",
			url: "<%=basePath%>servlet/ManageSiteServlet", 
			contenttype :"application/x-www-form-urlencoded;charset=utf-8",
			data:{
					type:8,
			}, 
			error: function(request) {
	           // document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
	         },
	         success:function(request){
	         map.clearMap();
	         if(request=="0"){
	         	$("#ternamediv").css("display","inline");
	         }
	         else{
	       	  var list = eval('(' + request + ')');
			 	var site = list.terminal;		
			 
				satationsmarker(site);
	         }
	         	
	         }
	         });
		}
   function p_s1(name,ctn){
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:1,
		//panel:'panel'
	});
	satation_search.search(name,function(status,result){
		//for(var i=0;i<result.poiList.pois.length;i++){
			map.setCenter([result.poiList.pois[0].location.lng,result.poiList.pois[0].location.lat]);
			var satation={
					siteId:"",
					peoNum:0,
					lineName:"null",
					order:"",
					delay:0,
					latitude:result.poiList.pois[0].location.lat,
					longitude:result.poiList.pois[0].location.lng,
					address:name,
					name:"",
					bufftag:0
			};
			var marker=satationsmarker(satation);
			map.setCenter([result.poiList.pois[0].location.lng,result.poiList.pois[0].location.lat]);
			EditSatationter(satation,marker,ctn);
	});
}

function EditSatationter(data,marker,ctn){
	map.on('click',function(e){
		marker.hide();
	});
	document.getElementById('addsatation-info').innerHTML="";
	document.getElementById('addsatation-info').innerHTML=ctn;
	var s=[data.longitude,data.latitude];
	var ctn=document.getElementById('info-satation');
	//console.log(document.getElementById('satation-lng'));
	$('#satation-lng').val(s);
	$('#satation-name').val(data.name);
	$('#satation-address').val(data.address);
	$('#satation-route').val(data.lineName);
	$('#satation-number').val(data.order);
	$('#satation-people').val(data.peoNum);
	info(s,ctn);
	//var sbm=document.getElementById('sbm');
	var sbm=ctn.getElementsByTagName('button');	
	//console.log(sbm);
	//console.log(data);
	sbm[0].onclick=function(){
		//document.getElementById('result_satationinfo').innerHTML=data.name+","+data.address+","+data.longitude+""+data.latitude+","+data.lineId+","+","+data.siteId+","+data.peoNum+","+data.delay;
		
		data.order=$('#satation-number').val();
		data.name=document.getElementById('satation-name').value;
		data.address=document.getElementById('satation-address').value;
		data.peoNum=parseInt($('#satation-people').val());
		///console.log(data);
		//alert("增加成功");
		satationsmarker(data);
		var json=JSON.stringify(data);
		Addsitedatater(json.toString());
		marker.hide();
		map.on('click',function(e){});
		siteable=0;
		info2.close();
	};
	sbm[1].onclick=function(){
		info2.close();
		marker.hide();
	};

}

function Addsitedatater(Data){
		$.ajax({ 
			type:"post",
			url: "servlet/ManageSiteServlet",
			data:{
					type:9,
					json:Data
			}, 
			error: function(request) {
	            //document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
	         },
			success: function(request){
				$("#w-modal-close").css("display","inline");
				$(".modal-body").html("添加成功");
				$("#load_modal").modal('show');
				var list = eval('(' + request + ')');
			 	sitelist = list.sitelist;
				var tab='<thead class="fixedThead"><tr><th>#</th><th>站点名称</th><th>站点地址</th><th>站点人数</th><th>站点所属线路</th></tr></thead><tbody class="scrollTbody">';
				for(var i=0;i<sitelist.length;i++){
					satationsmarker(sitelist[i]);
					tab=tab+'<tr id="'+i+'"><td>'+(i+1)+'</td><td>'+sitelist[i].name+'</td><td>'+sitelist[i].address+'</td><td>'+sitelist[i].peoNum+'</td><td>'+sitelist[i].lineId+'</td></tr>';
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
	               map.setCenter([sitelist[tr].longitude,sitelist[tr].latitude]);
	               $("#"+tr).css('background-color','red');
	        
	           
	            });
		    
	      }});
	}
	</script>

	
  </body>
</html>
