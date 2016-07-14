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

	<link rel="stylesheet" type="text/css" href="css/create_user.css">
	<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
	<script src='scripts/jquery.js'></script>
	<script src='scripts/bootstrap.min.js'></script>

</head>

<body>

<br/>
	<!--  -->
	<div class="mypage2">
		<table class="table table-hover table-bordered " style="width:98%;">
			<thead>
				<tr>
					<th style="width:60px;">序号</th>
					<th>车牌号</th>
					<th>品牌</th>
					<th>司机</th>
					<th>排班编号</th>
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
							<td>${car.getArrangeId()}</td>
							<td style="width:100px;"><a class="car_detail" id="${car.getCarId()}" href="javascript:;" 
							data-toggle="modal" data-target="#myModal" onclick="javascript:cardetail('${pageSize*(page_index-1)+status.index+1}',
							'${car.getLicensePlate()}', '${car.getBrand()}', '${car.getRegistrationDate()}', '${car.getInsuranceDate()}', 
							'${car.getDrivingLicense()}', '${car.getLicense()}', '${car.getArrangeId()}', '${car.getDriver()}', 
							'${car.getNumber()}')">
							查看详情</a></td>
							<td style="width:100px;"><a class="car_modify" id="${car.getCarId()}" href="javascript:;" 
							data-toggle="modal" data-target="#myModal_C" onclick="javascript:carmodify('${pageSize*(page_index-1)+status.index+1}',${car.getCarId()}, 
							'${car.getLicensePlate()}', '${car.getBrand()}', '${car.getRegistrationDate()}', '${car.getInsuranceDate()}', 
							'${car.getDrivingLicense()}', '${car.getLicense()}', '${car.getArrangeId()}', '${car.getDriver()}', 
							'${car.getNumber()}')">修改</a></td>    
							<td style="width:100px;"><a class="car_delete" href="javascript:;"  data-toggle="modal" data-target="#myModal_D"
							 onclick="javascript:car_delete('${car.getLicensePlate()}')">删除</a></td>
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
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">序号</span>
						<div class="form-control" id="carId" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">车牌</span>
						<div class="form-control" id="licensePlate" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">品牌</span>
						<div class="form-control" id="brand" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">注册日期</span>
						<div class="form-control" id="registrationDate" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">保险日期</span>
						<div class="form-control" id="insuranceDate" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">驾驶证</span>
						<div class="form-control" id="drivingLicense" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">行驶证</span>
						<div class="form-control" id="license" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">班次</span>
						<div class="form-control" id="arrangeId" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">司机</span>
						<div class="form-control" id="driver" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">座位数</span>
						<div class="form-control" id="number" aria-describedby="sizing-addon2"></div></div><br/>
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
						<div id="judgeNull"  class="alert alert-danger" role="alert" style="display:none;">数据信息不可为空</div>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">序号</span>
						<div class="form-control" id="carId1" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">车牌</span>
						<input type="text" class="form-control" id="licensePlate1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">品牌</span>
						<input type="text" class="form-control" id="brand1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">注册日期</span>
						<input type="text" class="form-control" id="registrationDate1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">保险日期</span>
						<input type="text" class="form-control" id="insuranceDate1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">驾驶证</span>
						<input type="text" class="form-control" id="drivingLicense1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">行驶证</span>
						<input type="text" class="form-control" id="license1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">班次</span>
						<input type="text" class="form-control" id="arrangeId1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">司机</span>
						<input type="text" class="form-control" id="driver1" aria-describedby="sizing-addon2"></div><br/>
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">座位数</span>
						<input type="text" class="form-control" id="number1" aria-describedby="sizing-addon2"></div><br/>
					</div>
				<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button id="s_modify" type="button" class="btn btn-primary">确认修改</button>
			</div></div>
			
				<div id="page_ms" class="page_m"">	
					<div class="modal-body">已成功修改该车辆数据信息</div>
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
						<div class="input-group"><span class="input-group-addon" id="sizing-addon2">车牌</span>
						<div class="form-control" id="licensePlate_D" aria-describedby="sizing-addon2"></div></div><br/>
						<div class="alert alert-danger" role="alert">是否确认删除当前车辆？</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="su_delete">确认删除</button>
					</div>
				</div>
				<div id="page_ds" class="page_ds">
					<div class="modal-body">已成功删除该车辆数据信息</div>
					<div class="modal-footer"><button type="button" class="btn btn-primary"  data-dismiss="modal" id="succdel"
					onclick="javascript:;" >确认并刷新</button></div>
					</div>
			</div>
		</div>
	</div>
</div>

	<div  class="pagenumber"  style="margin:0 auto;">
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
	
	<script type="text/javascript">
		
		var carId="";
		var licPla;
		
		function  cardetail(carid,licp,bra,reg,ins,dril,lic,arr,dri,num){
			$("#carId").html(carid);
			$("#licensePlate").html(licp);
			$("#brand").html(bra);
			$("#registrationDate").html(reg);
			$("#insuranceDate").html(ins);
			$("#drivingLicense").html(dril);
			$("#license").html(lic);
			$("#arrangeId").html(arr);
			$("#driver").html(dri);
			$("#number").html(num);  
		}
		
		function carmodify(index, carid,licp,bra,reg,ins,dril,lic,arr,dri,num){
			$("#page_ms").hide();
			$("#page_m").show();
			carId = carid;
			$("#carId1").html(index);
			$("#licensePlate1").val(licp);
			$("#brand1").val(bra);
			$("#registrationDate1").val(reg);
			$("#insuranceDate1").val(ins);
			$("#drivingLicense1").val(dril);
			$("#license1").val(lic);
			$("#arrangeId1").val(arr);
			$("#driver1").val(dri);
			$("#number1").val(num);  
		}
		
		$("#succmod").click(function() {
				$(".modal-backdrop").hide();
				$("#s_modify").attr("data-dismiss", "modal");
				$("#succmod").attr("data-dismiss", "modal");
				$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=" + ${page_index});
		});
		
		$("#succdel").click(function() {
				$(".modal-backdrop").hide();
				$("#myModal_D").modal('hide');
				$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=" + ${page_index});
		});

		$("#s_modify").click(function() {
			var licensePlate1 = $("#licensePlate1").val();
			var brand1 = $("#brand1").val();
			var registrationDate1 = $("#registrationDate1").val();
			var insuranceDate1 = $("#insuranceDate1").val();
			var drivingLicense1 = $("#drivingLicense1").val();
			var license1 = $("#license1").val();
			var arrangeId1 = $("#arrangeId1").val();
			var driver1 = $("#driver1").val();
			var number1 = $("#number1").val();
			
			if(licensePlate1 != null && licensePlate1 != ""&&brand1 != null && brand1 != ""
			&&registrationDate1 != null && registrationDate1 != ""&&insuranceDate1 != null && insuranceDate1 != ""
			&&drivingLicense1 != null && drivingLicense1 != ""&&license1 != null && license1 != ""
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
					if ("yes" == re) {
						$("#s_modify").attr("data-dismiss", "modal");
						
						$("#page_m").hide("1000");
						$("#page_ms").show();
					} else {
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
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index="+page1);
		});
		$(".pagepre").click(function() {
			var page1 = $(this).attr('id');
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index="+page1);
		});
		$(".pagenex").click(function() {
			var page1 = $(this).attr('id');
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index="+page1);
		});
		
	</script>
	
</body>
</html>
