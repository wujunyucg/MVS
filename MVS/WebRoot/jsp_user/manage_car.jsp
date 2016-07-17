<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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

<title>My JSP 'manage_car.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="scripts/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
</head>

<body>
<br/>
		<h4 style="float:left;">查询条件: </h4>
		<div style="float:left;">&nbsp;</div>
	<div style="float:left;"  class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu_ca" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true" title="1">按车牌查询 <span class="caret"></span></button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu_ca">
			<li><a href="javascript:;"  onclick="javascript:sercon('1')">按车牌查询</a></li>
			<li><a href="javascript:;" onclick="javascript:sercon('2')">按品牌查询</a></li>
			<li><a href="javascript:;" onclick="javascript:sercon('3')">按司机查询</a></li>
			<li style="display: none;"><a href="javascript:;" onclick="javascript:sercon('4')" >按班次名称查询</a></li>
			<li><a href="javascript:;" onclick="javascript:sercon('5')">按座位数查询</a></li>
			<li><a href="javascript:;" onclick="javascript:sercon('6')">按驾驶证查询</a></li>
			<li><a href="javascript:;" onclick="javascript:sercon('7')">按行驶证查询</a></li>
		</ul>
	</div>
	
	&nbsp;<div style="float:left;">&nbsp;</div>
	 <input id="searchcon" class="form-control" style="width: 30%;float:left;" type="text" placeholder="请输入查询关键字">
	<div style="float:left;">&nbsp;</div>
	<button type="button" class="btn btn-primary" onclick="search_car()">执行查询</button>
	&nbsp;
	<button type="button" class="btn btn-primary" onclick="serall()">查询全部车辆</button>
	&nbsp;

	<div style="float:right;">
		<form action="servlet/ExportCarData" method="post">
			<button type="submit" class="btn btn-primary">导出全部数据</button>
		</form>
	</div>
	&nbsp;<div style="float:right;">&nbsp;</div>
	<div style="float:right;">
		<form action="servlet/ExportConCarData?condition=${condition}&sea_condition=${sea_condition}" method="post">
			<button type="submit" class="btn btn-primary">导出当前查询数据</button>
		</form>
	</div>	
	
	<br/><br/>
	<!--  -->
	<div class="mypage2">
		<table class="table table-hover table-bordered " style="width:98%;algin:center !important;" >
			<thead>
				<tr>
					<th style="width:60px;">序号</th>
					<th>车牌号</th>
					<th>品牌</th>
					<th>司机</th>
					<th>座位数</th>
					<th>排班名称</th>
					<th style="width:100px;">查看详情</th>
					<th style="width:100px;">维护数据</th>
					<th style="width:100px;">删除数据</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list != null }">
					<c:forEach items="${list}" var="car" varStatus="status">
						<tr>
							<th style="width:60px;" class="row_index">${pageSize*(page_index-1)+status.index+1}</th>
							<td>${car.getLicensePlate()}</td>
							<td>${car.getBrand()}</td>
							<td>${car.getDriver()}</td>
							<td>${car.getNumber()}</td>
							<td>${arrangeName.get(status.index)}</td>
							<td style="width:100px;"><a class="car_detail" id="${car.getCarId()}" href="" 
							data-toggle="modal" data-target="#myModal" onclick="javascript:cardetail('${pageSize*(page_index-1)+status.index+1}',
							'${car.getLicensePlate()}', '${car.getBrand()}', '${car.getRegistrationDate()}', '${car.getInsuranceDate()}', 
							'${car.getDrivingLicense()}', '${car.getLicense()}', '${car.getArrangeId()}', '${car.getDriver()}', 
							'${car.getNumber()}', '${arrangeName.get(status.index)}');">
							查看详情</a></td>
							<td style="width:100px;"><a class="car_modify" id="${car.getCarId()}" href="" 
							data-toggle="modal" data-target="#myModal_C" onclick="javascript:carmodify('${pageSize*(page_index-1)+status.index+1}',${car.getCarId()}, 
							'${car.getLicensePlate()}', '${car.getBrand()}', '${car.getRegistrationDate()}', '${car.getInsuranceDate()}', 
							'${car.getDrivingLicense()}', '${car.getLicense()}', '${car.getArrangeId()}', '${car.getDriver()}', 
							'${car.getNumber()}', '${arrangeName.get(status.index)}')">修改数据</a></td>    
							<td style="width:100px;"><a class="car_delete" href=""  data-toggle="modal" data-target="#myModal_D"
							 onclick="javascript:car_delete('${car.getLicensePlate()}')">删除车辆</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

	<!-- 详情的模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog"><div class="modal-content"><div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"aria-label="Close">
				<span aria-hidden="true">&times;</span></button><h4 class="modal-title">车辆信息详情</h4></div>
					<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">序号</span>
							<div class="form-control" id="carId" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div><div class="col-md-6">
							<div></div></div>
					</div><div class="row">
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">车牌</span>
							<div class="form-control" id="licensePlate" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div><div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">品牌</span>
							<div class="form-control" id="brand" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div>
					</div><div class="row">
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">班次名称</span>
							<div class="form-control" id="arrangeId" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div>
						<div class="col-md-4">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">座位数</span>
							<div class="form-control" id="number" aria-describedby="sizing-addon2" readonly></div>
							<span class="input-group-addon">个</span></div><br/>
						</div>
					</div><div class="row">
						<div class="col-md-8">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">行驶证</span>
							<div class="form-control" id="license" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div>	<div class="col-md-6"></div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">司机</span>
							<div class="form-control" id="driver" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div><div class="col-md-6"></div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">驾驶证</span>
							<div class="form-control" id="drivingLicense" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div><div class="col-md-6"></div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">注册日期</span>
							<div class="form-control" id="registrationDate" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div><div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">保险日期</span>
							<div class="form-control" id="insuranceDate" aria-describedby="sizing-addon2" readonly></div></div><br/>
						</div>
					</div>
					<p class="text-muted">（上述行驶证指证芯编号；驾驶证指驾驶证的证号，即持证人身份证号）</p>
					</div>
				<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div></div>
		</div>
	</div>
	
	<!-- 修改的模态框 -->
	<div class="modal fade" id="myModal_C" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog"><div class="modal-content"><div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span></button><h4 class="modal-title">车辆信息修改</h4></div>
				<div id="page_m" class="page_m">
					<div class="modal-body">
						<div id="judgeNull"  class="alert alert-danger" role="alert" style="display:none;">您尚有未填写的数据信息   或   相应的车牌、行驶证车辆已存在  或  座位数不是整数</div>
						<div class="row">
						<div class="col-md-4">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">序号</span>
							<input class="form-control" type="text" id="carId1" readonly></div><br/>
						</div><div class="col-md-6">
							<div></div></div>
					</div><div class="row">
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">车牌</span>
							<input type="text" class="form-control" id="licensePlate1" aria-describedby="sizing-addon2"></div><br/>
						</div><div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">品牌</span>
							<input type="text" class="form-control" id="brand1" aria-describedby="sizing-addon2"></div><br/>
						</div>
					</div><div class="row">
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">班次名称</span>
							<input type="text" class="form-control" id="arrangeId1" aria-describedby="sizing-addon2" readonly></div><br/>
						</div>
						<div class="col-md-4">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">座位数</span>
							<input type="text" class="form-control" id="number1" aria-describedby="sizing-addon2">
							<span class="input-group-addon">个</span></div><br/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">行驶证</span>
							<input type="text" class="form-control" id="license1" aria-describedby="sizing-addon2"></div><br/>
						</div><div class="col-md-6"></div></div>
					<div class="row">
						<div class="col-md-8">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">司机</span>
							<div class="input-group-btn">
						<button type="button" id="Judge_driver" title="1" class="btn btn-default dropdown-toggle"data-toggle="dropdown" aria-haspopup="true" 
						style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;" aria-expanded="false">
							未安排司机 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="javascript:;" onclick="javascript:notDriver()">未安排司机</a></li>
							<li><a href="javascript:;" onclick="javascript:doDriver()">已安排司机</a></li>
						</ul>
					</div>
							<input type="text" class="form-control" id="driver1" aria-describedby="sizing-addon2"></div><br/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">驾驶证</span>
							<input type="text" class="form-control" id="drivingLicense1" aria-describedby="sizing-addon2"></div><br/>
						</div><div class="col-md-6"></div></div>
					
					<div class="row">
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">注册日期</span>
									<div style="float:left;" class="input-group date form_date"
										data-date="" data-date-format="yyyy-mm-dd">
										<input size="50" id="registrationDate1" class="form-control"
											type="text" placeholder="请选择新辆的保险日期"
											style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"
											readonly> <span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div><br/>
						</div>
						<div class="col-md-6">
							<div class="input-group"><span class="input-group-addon" id="sizing-addon2">保险日期</span>
									<div style="float:left;" class="input-group date form_date"
										data-date="" data-date-format="yyyy-mm-dd">
										<input size="50" id="insuranceDate1" class="form-control"
											type="text" placeholder="请选择新辆的保险日期"
											style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"
											readonly> <span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div></div>
						</div>
					</div>
					<p class="text-muted">（上述行驶证指证芯编号；驾驶证指驾驶证的证号，即持证人身份证号）</p>
					<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal"  onclick="javascript:hidehunt();">取消</button>
				<button id="s_modify" type="button" class="btn btn-primary">确认修改</button>
			</div>
					</div>
				<div id="page_ms" class="page_ms"">	
					<div class="modal-body">
						<div class="alert alert-success" role="alert">已成功修改该车辆数据信息</div>
					</div>
					<div class="modal-footer"><button type="button" class="btn btn-primary"  data-dismiss="modal" id="succmod"
					onclick="javascript:;" >确认并刷新</button></div>
				</div>	
			</div>	
		</div>
	</div>

	<!-- Modal --><div id="test">
	<div class="modal fade" id="myModal_D" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">删除车辆</h4>
				</div>
				<div id="page_d" class="page_d">
					<div class="modal-body">
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2" >车牌</span>
						<div class="form-control" id="licensePlate_D" aria-describedby="sizing-addon2" readonly></div></div><br/>
						<div class="alert alert-danger" role="alert">是否确认删除当前车辆？</div>
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
					<div class="modal-footer"><button type="button" class="btn btn-primary"  data-dismiss="modal" id="succdel"
					onclick="javascript:;" >确认并刷新</button></div>
					</div>
			</div>
		</div>
	</div>
</div>

 
  
	<div  class="pagenumber"">
		<nav>
		<ul class="pagination">
			<c:if test="${page_index == 1 }">
				<li class="disabled"><a aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
			</c:if>
			<c:if test="${page_index > 1 }">
				<li><a href="javascript:;" class="pagepre" id="${page_index-1}" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span></a></li>
			</c:if>
			<c:if test="${allpage != null }">
					<c:forEach var="i" begin='1' end='${allpage}' varStatus="status">		
					<c:choose>			
						<c:when test="${page_index == status.index }">
							<li class="active"><a href="javascript:;" class="pagechoice" id="${status.index}" title="${status.index}">${status.index}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:;" class="pagechoice" id="${status.index}" title="${status.index}">${status.index}</a></li>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</c:if>
			<c:if test="${page_index == allpage }">
				<li class="disabled"><a aria-label="Next"><span aria-hidden="true">&raquo;</span>
      </a></li>
			</c:if>
			<c:if test="${page_index < allpage }">
				<li><a href="javascript:;" class="pagenex" id="${page_index+1}" aria-label="Next">
				<span aria-hidden="true">&raquo;</span></a></li>
			</c:if>
		</ul>
		</nav>
	</div>

	<!-- Not Find Data -->
	<div class="modal fade" id="notFindData" tabindex="-1" role="dialog"aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">数据错误</h4>
				</div>
				<div class="modal-body">当前查询为未找到任何满足条件的车辆数据！</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"  id="dataNotExit" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
	</div>
	<b id="allpageb">${allpage}</b>
	<b id="sea_condition">${sea_condition}</b>
	<script type="text/javascript">
		$("#load_modal").modal('hide');//隐藏加载框
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		
		var carId="";
		var licPla="";
		var condition=${condition};
 		var pagenum= $("#allpageb").text();
 		var sea_condition= $("#sea_condition").text();
 		var jud_driv="";
 		var arraId="";
 		
 		var dirvel="";
 		var driverr="";
 		
 		$("#allpageb").hide();
 		$("#sea_condition").hide();
 		
 		if(pagenum == "0"||pagenum == null){
 			$("#notFindData").modal('show');
 		}
 		else if(condition != "0" && sea_condition != "0"){
 		
 			$("#searchcon").val(sea_condition);
 			if(condition == "1"){
				$("#dropdownMenu_ca").attr("title", "1");
				$("#dropdownMenu_ca").html("按车牌查询 <span class='caret'></span>");
			}
			else if(condition == "2"){
				$("#dropdownMenu_ca").attr("title", "2");
				$("#dropdownMenu_ca").html("按品牌查询 <span class='caret'></span>");
			}
			else if(condition == "3"){
				$("#dropdownMenu_ca").attr("title", "3");
				$("#dropdownMenu_ca").html("按司机查询 <span class='caret'></span>");
			}
			else if(condition == "4"){
				$("#dropdownMenu_ca").attr("title", "4");
				$("#dropdownMenu_ca").html("按班次查询 <span class='caret'></span>");
			}
			else if(condition == "5"){
				$("#dropdownMenu_ca").attr("title", "5");
				$("#dropdownMenu_ca").html("按座位查询 <span class='caret'></span>");
			}
			else if(condition == "6"){
				$("#dropdownMenu_ca").attr("title", "6");
				$("#dropdownMenu_ca").html("按驾驶证查询 <span class='caret'></span>");
			}
			else if(condition == "7"){
				$("#dropdownMenu_ca").attr("title", "7");
				$("#dropdownMenu_ca").html("按行驶证查询 <span class='caret'></span>");
			}
 		}
 		
 		
 		
 		
 		$("#dataNotExit").click(function() {
 			$(".modal-backdrop").hide();
				$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=1&condition=0&sea_condition=0");
		});
 		
 		function hidehunt(){
 			$("#judgeNull").hide();
 		}
 		
		function sercon(choice){
			if(choice == "1"){
				$("#dropdownMenu_ca").attr("title", "1");
				$("#dropdownMenu_ca").html("按车牌查询 <span class='caret'></span>");
			}
			else if(choice == "2"){
				$("#dropdownMenu_ca").attr("title", "2");
				$("#dropdownMenu_ca").html("按品牌查询 <span class='caret'></span>");
			}
			else if(choice == "3"){
				$("#dropdownMenu_ca").attr("title", "3");
				$("#dropdownMenu_ca").html("按司机查询 <span class='caret'></span>");
			}
			else if(choice == "4"){
				$("#dropdownMenu_ca").attr("title", "4");
				$("#dropdownMenu_ca").html("按班次查询 <span class='caret'></span>");
			}
			else if(choice == "5"){
				$("#dropdownMenu_ca").attr("title", "5");
				$("#dropdownMenu_ca").html("按座位查询 <span class='caret'></span>");
			}
			else if(choice == "6"){
				$("#dropdownMenu_ca").attr("title", "6");
				$("#dropdownMenu_ca").html("按驾驶证查询 <span class='caret'></span>");
			}
			else if(choice == "7"){
				$("#dropdownMenu_ca").attr("title", "7");
				$("#dropdownMenu_ca").html("按行驶证查询 <span class='caret'></span>");
			}
		}
		
		function search_car(){
			sea_condition=$("#searchcon").val();
			condition=$("#dropdownMenu_ca").attr("title");
			if(sea_condition == ""){
				sea_condition = 0;
				condition = 0;
			}
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=1&condition="+condition+"&sea_condition="+sea_condition);
			
		}
		
		function serall(){
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=1&condition=0&sea_condition=0");
		}
		
		function  cardetail(carid,licp,bra,reg,ins,dril,lic,arr,dri,num,arrN){
			$("#carId").html(carid);
			$("#licensePlate").html(licp);
			$("#brand").html(bra);
			$("#registrationDate").html(reg);
			$("#insuranceDate").html(ins);
			
			if(dril!="0"){
				$("#drivingLicense").html(dril);
			}else{
				$("#drivingLicense").html("未安排司机，无驾驶证");
			}
			
			$("#license").html(lic);
			
			$("#arrangeId").html(arrN);
			
			$("#driver").html(dri);
			$("#number").html(num);  
		}
		
		function notDriver() {
			jud_driv = "1";
			$("#driver1").val("");
			$("#drivingLicense1").val("");
			$("#Judge_driver").attr("title", "1");
			$("#Judge_driver").html("未安排司机 <span class='caret'></span>");
			$("#driver1").attr("disabled", "true");
			$("#driver1").attr("placeholder", "未安排相应司机,不需填写");
			$("#drivingLicense1").attr("disabled", "true");
			$("#drivingLicense1").attr("placeholder", "未安排相应司机,不需填写");
			
		}

		function doDriver() {
			jud_driv = "2";
			$("#Judge_driver").attr("title", "2");
			$("#Judge_driver").html("已安排司机 <span class='caret'></span>");
			$("#driver1").attr("placeholder", "请输入司机姓名");
			document.getElementById("driver1").disabled = false;
			$("#drivingLicense1").attr("placeholder", "请填写驾驶证号码");
			document.getElementById("drivingLicense1").disabled = false;
		}
		
		function carmodify(index, carid,licp,bra,reg,ins,dril,lic,arr,dri,num,arrN){
			arraId = arr;
			if(dril==0){		
				jud_driv = "1";
				$("#Judge_driver").attr("title", "1");
				$("#Judge_driver").html("未安排司机 <span class='caret'></span>");
				$("#driver1").attr("disabled", "true");
				$("#driver1").attr("placeholder", "未安排相应司机,不需填写");
				$("#drivingLicense1").attr("disabled", "true");
				$("#drivingLicense1").attr("placeholder", "未安排相应司机,不需填写");
			}else{
				jud_driv = "2";
				$("#Judge_driver").attr("title", "2");
				$("#Judge_driver").html("已安排司机 <span class='caret'></span>");
				$("#driver1").attr("placeholder", "请输入司机姓名");
				$("#driver1").val(dri);
				document.getElementById("driver1").disabled = false;
				$("#drivingLicense1").attr("placeholder", "请填写驾驶证号码");
				document.getElementById("drivingLicense1").disabled = false;
				$("#drivingLicense1").val(dril);
			}
			
			
			$("#judgeNull").hide();
			$("#page_ms").hide();
			$("#page_m").show();
			carId = carid;
			$("#carId1").val(index);
			$("#licensePlate1").val(licp);
			$("#brand1").val(bra);
			$("#registrationDate1").val(reg);
			$("#insuranceDate1").val(ins);
			
			$("#license1").val(lic);
			$("#arrangeId1").val(arrN);
			
			$("#number1").val(num);  
		}
		
		$("#succmod").click(function() {
				$(".modal-backdrop").hide();
				$("#myModal_C").modal('hide');
				$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=" + ${page_index}+"&condition="+condition+"&sea_condition="+sea_condition);
		});
		
		$("#succdel").click(function() {
				$(".modal-backdrop").hide();
				$("#myModal_D").modal('hide');
				$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=" + ${page_index}+"&condition="+condition+"&sea_condition="+sea_condition);
		});

		$("#s_modify").click(function() {
			var licensePlate1 = $("#licensePlate1").val();
			var brand1 = $("#brand1").val();
			var registrationDate1 = $("#registrationDate1").val();
			var insuranceDate1 = $("#insuranceDate1").val();
			var drivingLicense1 = ";"
			var license1 = $("#license1").val();
			var arrangeId1 = arraId;
			var driver1 = "";
			var number1 = $("#number1").val();
			
			if(jud_driv == "1"){
				driver1 = "未安排司机";
				drivingLicense1 = "0";
			}else{
				driver1 = $("#driver1").val();
				drivingLicense1 = $("#drivingLicense1").val();
			}
			
			
			if(licensePlate1 != null && licensePlate1 != ""&&brand1 != null && brand1 != ""
			&&registrationDate1 != null && registrationDate1 != ""&&insuranceDate1 != null && insuranceDate1 != ""
			&&drivingLicense1 != null &&drivingLicense1 != ""&&license1 != null && license1 != ""
			&&arrangeId1 != null && arrangeId1 != ""&&driver1 != null && driver1 != ""
			&&number1 != null && number1 != ""
			){
				$("#judgeNull").hide();
				$.ajax({
					url : "servlet/ManageCarServlet",
					type : "POST",
					data : {
						type : "2",
						carId : carId ,
						licensePlate : licensePlate1 ,
						brand : brand1,
						registrationDate : registrationDate1,
						insuranceDate : insuranceDate1 ,
						drivingLicense : drivingLicense1 ,
						license : license1 ,
						arrangeId : arrangeId1,
						driver : driver1,
						number : number1 ,
					},
					success : function(re) {
						$("#judgeNull").hide();
					if ("yes" == re) {
						$("#page_m").hide("1000");
						$("#page_ms").show();
					} else {
						$("#judgeNull").show();
					}
				}
				});
			}else{
				$("#judgeNull").show();
			}
		});
		
		function  car_delete(licp){
			$("#page_ds").hide();
			$("#page_d").show();
			licPla = licp;
			$("#licensePlate_D").html(licp);
		}
		
		$("#su_delete").click(function() {
			$.ajax({
						url : "servlet/ManageCarServlet",
						type : "POST",
						data : {
							type : "3",
							licensePlate : licPla,
						},
						success : function(re) {
						if ("yes" == re) {
							$("#page_d").hide("1000");
							$("#page_ds").show();
						} else {
						}
					}
				});
		});
		
		$(".pagechoice").click(function() {
			var page1 = $(this).attr("title");
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index="+page1+"&condition="+condition+"&sea_condition="+sea_condition);
		});
		$(".pagepre").click(function() {
			var page1 = $(this).attr('id');
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index="+page1+"&condition="+condition+"&sea_condition="+sea_condition);
		});
		$(".pagenex").click(function() {
			var page1 = $(this).attr('id');
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index="+page1+"&condition="+condition+"&sea_condition="+sea_condition);
		});
		
		$('.dropdown-toggle').dropdown()
	</script>
	
</body>
</html>
