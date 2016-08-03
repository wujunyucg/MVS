<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'statistics_line.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src='scripts/jquery.min.js'></script>
	<link rel="stylesheet" type="text/css" href="css/create_user.css">


	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="scripts/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script src='scripts/bootstrap.min.js'></script>
	
	<script src='chartjs//js/Chart.js'></script>
  </head>
  
  <body>
  
  	<br/>
	<div class="row">
		<div class="col-lg-2"><div class="input-group">
				<span class="input-group-addon" id="sizing-addon2">查询周期</span>
				<div class="input-group-btn">
					<button type="button" id="search_c" title="1" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						天 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="javascript:;" onclick="javascript:byday()">天</a></li>
						<li><a href="javascript:;" onclick="javascript:byweek()">周</a></li>
						<li><a href="javascript:;" onclick="javascript:bymonth()">月</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
			<input size="50" id="search_date" class="form-control" type="text" readonly>
			<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		</div>
		</div>
	<button type="button" class="btn btn-default" id="sure_sta">确认</button>
	</div>
	
	
	<br/>
	
	
	<div class="panel-group" id="accordion">
	   <div class="panel panel-default">
	      <div class="panel-heading">
	         <h4 class="panel-title">
	            <a data-toggle="collapse" id="collapse1" 
	               href="#collapseOne">
	               统计柱状图
	            </a>
	         </h4>
	      </div>
	      <div id="collapseOne" class="panel-collapse collapse in">
	         <div class="panel-body" id="chartdata">
	        	 未查找到相应数据信息
	         </div>
	      </div>
	   </div>
	   <div class="panel panel-info" id="line_data">
	      <div class="panel-heading">
	         <h4 class="panel-title">
	            <a data-toggle="collapse" data-parent="" id="collapse2"
	               href="#collapseTwo">
	               线路人员查询
	            </a>
	         </h4>
	      </div>
	      <div id="collapseTwo" class="panel-collapse collapse">
	         <div class="panel-body">
	         	<div id="linetabledate">
	            	未查找到相应数据信息
	            </div>
	            <div id="stafftabledate">
	            	
	            </div>
	         </div>
	      </div>
	   </div>
	</div>

			
	<script type="text/javascript">

		var json_linereclist;
		var json_linelist;
		
		$("#line_data").hide();
		
		$('.dropdown-toggle').dropdown();
	 	$(".form_date").datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
			
		var barChartData = {
			labels : [],
			datasets : [{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : []
			}]
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

		$("#sure_sta").click(function(){
			var s_method = $("#search_c").attr("title");
			var s_date = $("#search_date").val();
			if(s_date == "" || s_date == null){
			}
			else{
				barChartData.labels = [];
				barChartData.datasets = [{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : []
				}]
				$.ajax({
					url : "servlet/StaAnaLineServlet",
					type : "POST",
					data : {
						type : "1",
						s_method : s_method,
						s_date : s_date,
					},
					success : function(json_list) {
						var datac = "";
						var tab = "";
						$("#chartdata").text("");
						$("#chartdata").html("");
						datac += "<div class='demo-chat'>";
						datac += "<canvas id='canvas' style=' height: 60%;width: 97%;margin: 0;padding: 0;display: block;'></canvas></div>";
						$("#chartdata").html(datac);	
						$("#linetabledate").text("");
						$("#linetabledate").html("");
						var arr = json_list.toString().split("&");
						json_linereclist = arr[1];
						json_linelist = arr[0];	
						var obj_linerecords = eval(json_linereclist);
						var obj_lines = eval(json_linelist); 
						tab += "<table class='table table-bordered'>";
      					tab += "<thead><tr><th>#</th><th>路线名称</th>";
         				tab += "<th>所经站点</th><th>总人数</th><th>乘坐率</th><th>查看乘车人员名单</th><th>导出乘车人员名单</th></tr></thead><tbody>";
						for(var i=0;i<obj_linerecords.length;i++){
							barChartData.labels.push(obj_lines[i].linelist.name);
							barChartData.datasets[0].data.push(obj_linerecords[i].linereclist.rate);
							
							var index = i+1;
							if(obj_lines[i].linelist.rate < 0){
								tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
								tab += "<td class='text-success'>"+obj_lines[i].linelist.name+"</td>";
								tab += "<td class='text-success'>"+obj_lines[i].linelist.siteId+"</td>";
								tab += "<td class='text-success'>"+obj_linerecords[i].linereclist.num+"</td>";
								tab += "<td class='text-success'>"+(obj_linerecords[i].linereclist.rate*(100)).toFixed(3)+"%</td>";
							}else{
								tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
								tab += "<td>"+obj_lines[i].linelist.name+"</td>";
								tab += "<td>"+obj_lines[i].linelist.siteId+"</td>";
								tab += "<td>"+obj_linerecords[i].linereclist.num+"</td>";
								tab += "<td>"+(obj_linerecords[i].linereclist.rate*100).toFixed(3);+"%</td>";
							}
							tab += "<td><a onclick=javascript:getlinestaff('"+obj_linerecords[i].linereclist.linerecordId+"','"+obj_lines[i].linelist.name+"')>查看乘车人员名单</a></td>";
							tab += "<td><a onclick=javascript:exportlinestaff('"+obj_linerecords[i].linereclist.linerecordId+"')>导出乘车人员名单</a></td>";
							
						}
						tab += "</tbody></table>";
						var ctx = document.getElementById("canvas").getContext("2d");
						window.myBar = new Chart(ctx).Bar(barChartData, {
						barDatasetSpacing : 20,
						});
						if(s_method == "1"){
							$("#line_data").show("1000");
							$("#linetabledate").html(tab);	
						}else{
							$("#line_data").hide("1000");
						}
 						
					}
				});
			}
		});
		
		function getlinestaff(linerecId, lineName){
			$.ajax({
					url : "servlet/StatisticLineServlet",
					type : "POST",
					data : {
						type : "2",
						linerecId : linerecId,
					},
					success : function(json_list) {
						if(json_list != "no"){
							var tab = "";
							$("#stafftabledate").text("");
							$("#stafftabledate").html("");
							var arr = json_list;
							var obj_staffs = eval(arr); 
							tab += "<div class='page-header'><h3>路线："+lineName+"——乘车人员名单如下：";
							tab += "<button type='button' class='btn btn-success'  onclick=javascript:gobackline() style='float:right;' >导出当前路线人员名单</button>";
							tab += "<button type='button' class='btn btn-primary'  onclick=javascript:gobackline() style='float:right;margin-right:30px;' >返回</button>&nbsp;&nbsp;</h3></div>";
							tab += "<table class='table table-bordered'>";
	      					tab += "<thead><tr><th>#</th><th>员工姓名</th>";
	         				tab += "<th>员工工号</th><th>部门</th><th>组别</th></tr></thead><tbody>";
							for(var i=0;i<obj_staffs.length;i++){
								var index = i+1;
								tab += "<tr><th style='width:60px;' class='row_index'>"+index+"</th>" ;
								tab += "<td>"+obj_staffs[i].stafflist.name+"</td>";
								tab += "<td>"+obj_staffs[i].stafflist.number+"</td>";
								tab += "<td>"+obj_staffs[i].stafflist.department+"</td>";
								tab += "<td>"+obj_staffs[i].stafflist.group+"</td>";
							}
							tab += "</tbody></table>";
	 						$("#stafftabledate").html(tab);	
	 						$("#linetabledate").hide("1000");
	 						$("#stafftabledate").show("1000");
						}
					}
				});
		}
		
		function gobackline(){
	 		$("#stafftabledate").hide("1000");
			$("#linetabledate").show("1000");
		}
		
	</script>
  </body>
</html>
