<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show_site.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap-datetimepicker.min.css">
<script src='chartjs/js/Chart.js'></script>
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap-datetimepicker.min.js'></script>
<script src='scripts/bootstrap.min.js'></script>

	<script type="text/javascript" src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<!-- -	<script type="text/javascript" src="chartjs/js/Chart-1.0.1-beta.4.js" charset="UTF-8"></script> -->
  </head>
  
  <body>
  <br>
  <div class="row">
  <form class="form-inline" action="servlet/ShowSiteServlet?type=2" method="post" >
<div class="form-group">
    <div class="input-group">
  <div class="input-group-addon">搜索类型</div>
    <select class="form-control" id="select">
  <option value="day" >天</option>
   <option value="week">周</option>
  <option value="month">月</option>
</select>
    </div>
 </div>
		<div class="col-lg-3">
			<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
			<input size="50" id="search_date" class="form-control" type="text" readonly>
			<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		</div>
		</div>
	<button type="button" class="btn btn-default" id="sure_sta" onclick="javascript:check()">确认</button>
	
			<button type="submit" id="export"class="btn btn-primary" style="display:none">导出报表</button>
			
		</form>
	</div>

		
			<div id="canvasp"class="demo-chat" style="position:relative;width:80%;overflow :auto;left:5%;top:10px;height:600px;">
				<canvas id="canvas" style="height:580px" ></canvas>
			</div>
	
	
	
  </body>
  <script>

	var chartBar = null;
	$('.form_date').datetimepicker({
			language : 'zh-CN',
			format: 'yyyy-mm-dd',
			startDate:'2015-01-01',
			endDate:'2015-07-31',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
		});	
	function check(){
	
   		$.ajax({ 
				type:"post",
				url: "<%=basePath%>servlet/ShowSiteServlet", 
				data:{
								type :$("#select").val(),
								date:$("#search_date").val() ,			
					},
				error: function(request) {
		          alert('修改失败，请重新修改');
		         },
				success: function(request){
				var list = eval('(' + request + ')');
				var site_list=list.site_list;
				var sr_list=list.sr_list;
			    var arr=new Array();
			  
			    for(var i=0;i<site_list.length;i++){
			    	arr[i]=site_list[i].name;
			    }
			     var datannum=new Array();
			     for(var i=0;i<sr_list.length;i++){
			    	datannum[i]=sr_list[i].num;
			    }
				var barChartData = {
				labels : arr,
				datasets : [
			
				{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : datannum
				}
				]
				}
				$("#canvas").remove();
				$("#canvasp").append('<canvas id="canvas" style="height:580px" ></canvas>');
				var ctx = document.getElementById("canvas").getContext("2d");
				chartBar = new Chart(ctx).Bar(barChartData, {
					barDatasetSpacing : 20,
				});
				$("#export").css("display","inline");
				
				
				
				
				
				if($("#select").val()=="day"){
				
				canvas.onclick = function(evt){
           		var activeBar = chartBar.getBarSingleAtEvent(evt);
            	//alert(activeBar.label)
            	var staffids=null,siteid,srid;
            	for(var i=0;i<site_list.length;i++){
            		if(site_list[i].name==activeBar.label){
            			staffids=sr_list[i].staffIds;
            			siteid=sr_list[i].siteId;
            			srid=sr_list[i].siterecordId;
            		}
            	}
            	
            	$.ajax({ 
				type:"post",
				url: "<%=basePath%>servlet/ShowSiteServlet", 
				contenttype :"application/x-www-form-urlencoded;charset=utf-8",
				data:{
						type:1,
						staffids:staffids,
						siteid:siteid,
						srid:srid
				}, 
				error: function(request) {
		           // document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
		         },
		         success:function(request){
		         	var list = eval('(' + request + ')');
		         	var staff_list = list.staff_list;
		         	var tab='<table class="table table-hover table-bordered" style="width:100%;">'
		         	+'<thead><tr><td>员工工号</td><td>员工姓名</td><td>员工部门</td><td>员工组别</td><tr></thead>'
		         	+'<tbody>';
		         	for(var i=0;i<staff_list.length;i++){
		         		tab=tab+'<tr><td>'+staff_list[i].number+'</td><td>'+staff_list[i].name+'</td><td>'+staff_list[i].department+'</td><td>'+staff_list[i].group+'</td><tr>';
		         	}
		         	tab=tab+'</tbody></table>';
				 	$("#w-modal-p1").html("员工信息");
				 	$("#w-modal-div").html(tab);
				 	$("#w-modal-but").css("display","none");
				 	$("#w-modal-div").css("overflow","auto");
				 	$("#w-modal-div").css("height","300px");
				 	$("#w-modal-export").css("display","inline");
				 	$("#w-modal").modal('show');
		         }
		         });
		      
            // => activeBars is an array of bars on the canvas that are at the same position as the click event.
            };
		   }		
		      }});
	}
	
	function byday(){
			$("#search_c").html("天 <span class='caret'></span>");
			$("#search_c").attr("title","1");
		}
		function byweek(){
			$("#search_c").html("周 <span class='caret'></span>");
			$("#search_c").attr("title","2");
		}
		function bymonth(){
			$("#search_c").html("月 <span class='caret'></span>");
			$("#search_c").attr("title","3");
		}
	
  </script>
</html>
		