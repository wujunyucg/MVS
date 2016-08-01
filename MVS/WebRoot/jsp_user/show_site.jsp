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
<script src='chartjs//js/Chart.js'></script>
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap-datetimepicker.min.js'></script>
<script src='scripts/bootstrap.min.js'></script>
	<script type="text/javascript" src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
  </head>
  
  <body>
<div style="width: 300px;margin-top:5%;" class="input-group date form_date" data-date="" >
						<input size="50" id="res_date" class="form-control" type="text" placeholder="请选择日期"  
						style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
</div>


			<div class="demo-chat" style="position:relative;width:80%;overflow :auto;left:5%;top:10px;height:600px;">
				<canvas id="canvas" height="580"  ></canvas>
			</div>
		

  </body>
  <script>
  var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
var arr=["January","February","March","April","May","June","July"];
	var barChartData = {
		labels : ["January","February","March","April","May","June","July"],
		datasets : [
			
			{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
			}
			
		]

	}
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
		barDatasetSpacing : 20,
		});
	}
	
	$('.form_date').datetimepicker({
			language : 'zh-CN',
			format: 'yyyy-mm-dd',
			startDate:'2015-01-01',
			weekStart : 1,
			todayBtn : 1,
			autoclose : false,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
		});	
  </script>
</html>
