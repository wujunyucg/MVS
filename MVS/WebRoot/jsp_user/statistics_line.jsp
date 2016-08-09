<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    
    <title>My JSP 'statistics_line.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/create_user.css">
	
    <link href="css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="scripts/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
	          <div id="lineadvice">
	          </div>
	         
	      </div>
	   </div>
	   <div class="panel panel-default" id="line_advice">
	      <div class="panel-heading">
	         <h4 class="panel-title">
	            <a data-toggle="collapse" data-parent="" id="lines_advice"
	               href="#collapseTwo">
	               线路优化建议
	            </a>
	         </h4>
	      </div>
	      <div id="collapseTwo" class="panel-collapse collapse">
	         <div class="panel-body">
	         	<div id="lineadvicedata">
	            </div>
	         </div>
	      </div>
	   </div>
	   <div class="panel panel-default" id="line_data">
	      <div class="panel-heading">
	         <h4 class="panel-title">
	            <a data-toggle="collapse" data-parent="" id="collapse3"
	               href="#collapseThree">
	               线路人员查询
	            </a>
	         </h4>
	      </div>
	      <div id="collapseThree" class="panel-collapse collapse">
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

	<!-- Modal -->
	<div class="modal fade" id="notFindData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">数据查询</h4>
	      </div>
	      <div class="modal-body">
	      	<div class="alert alert-danger" role="alert">未查找到相应数据信息</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
			
	<script type="text/javascript">
		var json_linereclist;
		var json_linelist;
		var showed = "0";
		
		var linenames = "";
		var linerates = "";
		
		$("#expstatic1").hide();
		$("#line_data").hide();
		
	//	$('.dropdown-toggle').dropdown();
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
		};
		
		var barChartData1 = {
			labels : [],
			datasets : [{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : []
			},{
				fillColor : "rgba(244,164,96,0.5)",
				strokeColor : "rgba(244,164,96,0.8)",
				highlightFill : "rgba(244,164,96,0.75)",
				highlightStroke : "rgba(244,164,96,1)",
				data : []
			}]
		};
		
		var barChartData2 = {
			labels : [],
			datasets : [{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : []
			}]
		};
		var barChartData3 = {
			labels : [],
			datasets : [{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : []
			}]
		};
		var barChartData4 = {
			labels : [],
			datasets : [{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : []
			}]
		};
		
		
		
		

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
				$("#collapseOne").collapse("show");
				$.ajax({
					url : "servlet/StaAnaLineServlet",
					type : "POST",
					data : {
						type : "1",
						s_method : s_method,
						s_date : s_date,
					},
					success : function(json_list) {
						if(json_list != "no"&&json_list != null && json_list !=""){
						//alert(json_list);
							linenames = "";
							linerates = "";
							$("#expstatic1").show("1000");
							var datac = "";
							var tab = "";
							$("#chartdata").text("");
							$("#chartdata").html("");
							datac += "<h4 style='float:left;'>下图为当前查询中各条路线中乘坐率（%）柱状图</h4>";
							datac += " <form action='servlet/ExportStaffStati?type=2' method='post' id='statexp'><button type='button' ";
							datac += "class='btn btn-primary' style='float:right;' onclick='javascript:exportstatistics()' id='expstatic1'>导出统计报表</button></form>";
							datac += "<div class='demo-chat'>";
							datac += "<canvas id='canvas' style=' height: 60%;width: 97%;margin: 0;padding: 0;display: block;'></canvas></div>";
							$("#chartdata").html(datac);	
							$("#linetabledate").text("");
							$("#linetabledate").html("");
							var arr = json_list.toString().split("&");
							json_linelist = arr[0];	
							json_linereclist = arr[1];
							var json_advice = arr[2];
							var obj_linerecords = eval(json_linereclist);
							var obj_lines = eval(json_linelist); 
							tab += "<table class='table table-bordered'>";
	      					tab += "<thead><tr><th>#</th><th>路线名称</th>";
	         				tab += "<th>所经站点</th><th>总人数</th><th>乘坐率</th><th>查看乘车人员名单</th><th>导出乘车人员名单</th></tr></thead><tbody>";
							for(var i=0;i<obj_linerecords.length;i++){
								if(obj_lines[i].linelist == undefined){
									alert("1");
									continue;
								}
								linenames += obj_lines[i].linelist.name + ",";
								linerates += obj_linerecords[i].linereclist.rate + ",";
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
								tab += "<td>";
								tab += "<form action='servlet/ExportStaffStati?type=1&linerecId="+obj_linerecords[i].linereclist.linerecordId+"' id='"+obj_linerecords[i].linereclist.linerecordId+"' method='post'></form>";
								tab += "<a onclick=javascript:expstaff('"+obj_linerecords[i].linereclist.linerecordId+"')>导出乘车人员名单</a></td>";
								
							}
							var temp = linenames.substring(linenames.length-1,linenames.length);
							if(temp == ","){
								linenames = linenames.substring(0,linenames.length-1);
							}
							temp = linerates.substring(linerates.length-1,linerates.length);
							if(temp == ","){
								linerates = linerates.substring(0,linerates.length-1);
							}
							tab += "</tbody></table>";
							$("#lineadvice").html("");
							$("#lineadvice").text("");	
							var obj_names = eval(json_advice); 
							var adv =  "<div class='panel panel-default'><div class='panel-body' style='width: 97%;'><p class='text-info'>建议优化线路：" + obj_names[0].name;
							for(var i=1;i<obj_names.length;i++){
								adv += "，" + obj_names[i].name;
							}
							adv += "（详细线路优化建议请查看——路线优化建议）</p>";
							adv += "</div></div>";
							var ctx = document.getElementById("canvas").getContext("2d");
							window.myBar = new Chart(ctx).Bar(barChartData, {
							barDatasetSpacing : 20,
							});
							if(s_method == "1"){
								$("#line_data").show("1000");
								$("#linetabledate").html(tab);	
								$("#lineadvice").html(adv);	
							}else{
								$("#line_data").hide("1000");
								$("#lineadvice").html(adv);	
							}
 						}else{
 							$("#chartdata").text("");
							$("#chartdata").html("未查询到相应数据信息");
							$("#lineadvice").text("");
							$("#lineadvice").html("");
							$("#linetabledate").text("");
							$("#linetabledate").html("");
							$("#stafftabledate").text("");
							$("#stafftabledate").html("");
							$("#expstatic1").hide("1000");
							$("#line_data").hide("1000");
							
 							$("#notFindData").modal("show");
 							
 							
 						}
					}
				});
			}
		});
		
		function getlinestaff(linerecId, lineName){
			$.ajax({
					url : "servlet/StaAnaLineServlet",
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
							tab += "<form action='servlet/ExportStaffStati?type=1&linerecId="+linerecId+"' method='post'>";
							tab += "<button type='submit' class='btn btn-success'  style='float:right;' >导出当前路线人员名单</button></form>";
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
						}else{
						}
					}
				});
		}
		
		function gobackline(){
	 		$("#stafftabledate").hide("1000");
			$("#linetabledate").show("1000");
		}
		
		$("#lines_advice").click(function(){
			if(showed == "0"){
				$.ajax({
					url : "servlet/StaAnaLineServlet",
					type : "POST",
					data : {
						type : "3",
					},
					success : function(json_list) {
						var datac = "";
						var tab = "";
						datac += "<div class='page-header'><h2>详细路线统计分析与优化建议 <small>针对当前正在使用的所有线路</small></h2></div>"
						datac += "<div>";
						datac += "<h4 >各条路线——乘坐率（%）和路程数（5km）柱状图</h4>";
						datac += "<div id='legend1' style=' float:right;margin-right:30px;padding: 0;display: block;'></div>"
						datac += "<div class='demo-chat'><canvas id='canvas_1' style=' height: 330px;width: 97%;margin: 0;padding: 0;display: block;'></canvas></div></div>";
						datac += "<div><hr/><h4 >左图为各条路线中乘坐率（%）较低路线 ， 右图为各条路线中路程数（5km）较高路线</h4>";
						datac += "<div class='demo-chat'><canvas id='canvas_2' style=' float:left; height: 200px;width: 45%;margin: 0;padding: 0;display: block;'></canvas></div>";
						datac += "<div class='demo-chat'><canvas id='canvas_3' style=' float:right;  height: 200px;width: 45%;margin-right:30px;padding: 0;display: block;'></canvas></div></div>";
						datac += "<div style='clear:both' id='advice1'></div>"
						datac += "<div><br/><hr/><h4 >各条路线以乘坐率和路程数为判断标准，所得到由优至劣的各条路线综合评价排序后的柱状图</h4>";
						datac += "<div class='demo-chat'><canvas id='canvas_4' style=' height: 330px;width: 97%;margin: 0;padding: 0;display: block;'></canvas></div></div>";
						datac += "<div style='clear:both'id='advice2'></div>"
						$("#lineadvicedata").html(datac);	
						$("#linetabledate").text("");
						$("#linetabledate").html("");
						var arr = json_list.toString().split("&");
						json_linelist = arr[0];	
						json_linereclist = arr[1];
						var json_advice1 = arr[2];
						var json_advice2 = arr[3];
						var json_advice = arr[4];
						var obj_linerecords = eval(json_linereclist);
						var obj_lines = eval(json_linelist); 
						var obj_ad1 = eval(json_advice1); 
						var obj_ad2 = eval(json_advice2); 
						var obj_ad = eval(json_advice); 
						for(var i=0;i<obj_linerecords.length;i++){
							barChartData1.labels.push(obj_lines[i].linelist.name);
							barChartData1.datasets[0].data.push(obj_linerecords[i].linereclist.rate);
							barChartData1.datasets[1].data.push(obj_linerecords[i].linereclist.staffIds);
							barChartData4.labels.push(obj_ad[i].linereclist.date);
							barChartData4.datasets[0].data.push(obj_ad[i].linereclist.staffIds);
						}
						for(var i=0;i<obj_ad1.length;i++){
							barChartData2.labels.push(obj_ad1[i].linelist1.name);
							barChartData2.datasets[0].data.push(obj_ad1[i].linelist1.rate);
						}
						for(var i=0;i<obj_ad2.length;i++){
							barChartData3.labels.push(obj_ad2[i].linelist2.name);
							barChartData3.datasets[0].data.push(obj_ad2[i].linelist2.siteId);
						}
						var line_legend = '<span style=\"background-color:rgba(151,187,205,0.7);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;乘坐率';
						line_legend +='&nbsp;&nbsp;&nbsp;';
						line_legend += '<span style=\"background-color:rgba(244,164,96,0.7);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;路程';
						var ctx1 = document.getElementById("canvas_1").getContext("2d");
						window.myBar1 = new Chart(ctx1).Bar(barChartData1, {
						barDatasetSpacing : 3,
						legendTemplate : line_legend,
						});
						var legend_1 = document.getElementById("legend1");
					    legend_1.innerHTML = myBar1.generateLegend();
						var ctx2 = document.getElementById("canvas_2").getContext("2d");
						window.myBar2 = new Chart(ctx2).Bar(barChartData2, {
						barDatasetSpacing : 20,
						barValueSpacing : 25,// 柱状块与x值所形成的线之间的距离
						});
						var ctx3 = document.getElementById("canvas_3").getContext("2d");
						window.myBar3 = new Chart(ctx3).Bar(barChartData3, {
						barDatasetSpacing : 20,
						barValueSpacing : 25,// 柱状块与x值所形成的线之间的距离
						});
 						var ctx4 = document.getElementById("canvas_4").getContext("2d");
						window.myBar4 = new Chart(ctx4).Bar(barChartData4, {
						barDatasetSpacing : 20,
						 scaleBeginAtZero: false, // y轴标记是否从0开始
						});
						var ad1 = "<br/><p class='text-info'><span class='label label-primary'>建议（乘坐率）</span>&nbsp;单从线路乘坐率看，建议删除上图中乘坐率过低的线路；</p>";
						ad1 += "<p class='text-info'><span class='label label-primary'>建议（路程数）</span>&nbsp;单从线路路程数看，建议修改或删除上图中路程数较高的线路。（修改建议见下文）</p>";
						var ad2 = "<p class='text-info'><span class='label label-primary'>评价公式</span>&nbsp;如上综合线路乘坐率及路程数进行评价公式为：U/∑Ui-S/∑Si ，其中U为乘坐率，S为路程数。</p>";
						ad2 += "<p class='text-info'><span class='label label-primary'>路线建议</span>&nbsp;对乘坐率和路程数综合考虑，建议对上图后五条路线进行优化处理或删除评价过低路线，优化建议如下：</p>";
						ad2 += "<p class='text-info'><span class='label label-primary'>建议优化</span>&nbsp;①调整站点位置及站点人数，以控制路线总路程和乘坐率；</p>";
						ad2 += "<p class='text-info' style='margin-left:67px;'>②控制站点人数，使路线所经站点数尽可能接近；</p>";
						ad2 += "<p class='text-info' style='margin-left:67px;'>③调整路线所经过站点，使其乘坐率尽可能高；</p>";
						ad2 += "<p class='text-info' style='margin-left:67px;'>④保证一个站点只有一条路线经过，尽量避免多条路线重复经过一个站点；</p>";
						ad2 += "<p class='text-info' style='margin-left:67px;'>⑤可对站点和线路进行智能生成操作，以控制线路生成时最低乘坐率和最大路程数。</p>";
						$("#advice1").html(ad1);
						$("#advice2").html(ad2);
					}
				});
			}
			showed = "1";
		});
		
		function expstaff(linerecId){
			$("#"+linerecId).submit();
		}
		
		function exportstatistics(){
			var u = "servlet/ExportStaffStati?type=2&linenames="+linenames+"&linerates="+linerates;
			$("#statexp").attr("action",u);
			$("#statexp").submit();
		}
		
//		$("#collapse1").click(function(){
//			$("#collapseOne").hide("1000");
		
//		});

	</script>
  </body>
</html>
