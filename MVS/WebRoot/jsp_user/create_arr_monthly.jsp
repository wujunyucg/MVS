<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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

<title>create</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/bootstrap/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<style type="text/css">
.tr_bg {
	background-color: #e5e5e5;
}

.select_bg {
	background-color: #159E5c;
}
</style>
</head>

<body>
	<br />
	<div id="crearr_msg_div" style="display:none;"
		class="alert alert-warning" role="alert">
		<b id="crearr_msg">请完善信息</b>
	</div>
	<div style="float:left !important;">
		<div class="input-group" style="width:360px;">
			<span class="input-group-addon" id="sizing-addon2">班次月份</span>
			<div style="float:left;" class="input-group date arr_date"
				data-date="" data-date-format="yyyy-mm">
				<input id="crearr_input_date" size="50" id="arr_date"
					class="form-control" type="text" placeholder="请选择班次月份"
					style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"
					readonly> <span class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
		<br />
		<div class="input-group" style="width:360px;">
			<span class="input-group-addon" id="sizing-addon2">每日班次数</span> <input
				type="text" id="month_input_num" class="form-control"
				placeholder="请输入每天班次数量" aria-describedby="sizing-addon2"> <span
				class="input-group-addon">次</span>
		</div>
		<br />
		<div id="arr_part" style="display:none;">
			<div id="" style="width:360px;margin:0 !important;" class="alert"
				role="alert">
				<h3 id="month_arr_num">第1个班次</h3>
				<p></p>
				<br />
				<form class="form-inline">
					<div class="form-group" style="width:330px;">
						<div class="input-group">
							<span class="input-group-addon" id="sizing-addon2">发车时间</span>
							<div class="input-group date arr_time" data-date=""
								data-date-format="hh:ii">
								<input id="crearr_input_time" size="50"
									class="form-control inputinput" type="text"
									placeholder="请选择厂车的发车时间"
									style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"
									readonly> <span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</div>
					</div>
				</form>
				<br />
				<form class="form-inline">
					<div class="form-group">
						<label class="sr-only" for="exampleInputAmount"></label>
						<div class="input-group">
							<div class="input-group-addon">线路名称</div>
							<input type="text" class="form-control inputinput"
								id="crearr_input_line" placeholder="线路名称" readonly>
							<div class="input-group-addon">
								<a href="javascript:;" id="cre_arr_choose_line">选择线路</a>
							</div>
						</div>
					</div>
				</form>
				<br />
				<form class="form-inline">
					<div class="form-group" style="width:350px;">
						<label class="sr-only" for="exampleInputAmount"></label>
						<div class="input-group">
							<div class="input-group-addon">选择车辆</div>
							<input type="text" class="form-control inputinput"
								id="crearr_input_car" placeholder="车牌号" readonly>
						</div>
					</div>
				</form>
				<br />
				<button id="crearr_btn_pre" type="button"
					style="width:100px;display:none;" class="btn btn-primary">上一次</button>
				<button id="crearr_btn_next" type="button"
					style="width:100px;float:right;margin-bottom:5px;"
					class="btn btn-primary">下一次</button>
			</div>
			<br />
		</div>
			 <br />
		<button id="crearr_btn_multi" type="button" style="width:360px;"
			class="btn btn-primary">按次排班</button>
	</div>

	<div style="margin-left:370px;display:none;" id="arr_line_div">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button disabled="disabled" id="crearr_btn_line" type="button"
					class="btn btn-primary">确定</button>
				请选择线路
			</div>
			<div class="table-responsive"
				style="max-height:700px;overflow-y:auto">
				<table class="table">
					<thead>
						<tr>
							<th>线路名称</th>
							<th>线路人数</th>
						</tr>
					</thead>
					<c:if test="${cre_arr_lines != null }">
						<c:forEach items="${cre_arr_lines}" var="line">
							<tr class="crearr_line_tr" id="${line.getLineId()}">
								<td class="">${line.getName()}
								</th>
								<td>${line.getNum()}
								</th>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<br />
			</div>
		</div>
	</div>

	<div style="margin-left:370px;display:none;" id="arr_car_div">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button disabled="disabled" id="crearr_btn_car" type="button"
					class="btn btn-primary">确定</button>
				已自动过滤掉不合适的车辆（座位数，保修日期），请选择车辆
			</div>
			<div class="table-responsive"
				style="max-height:700px;overflow-y:auto">
				<table class="table">
					<thead>
						<tr>
							<th>厂车座位数</th>
							<th>司机</th>
							<th>车牌号</th>
						</tr>
					</thead>
					<c:if test="${cre_arr_lines != null }">
						<c:forEach items="${cre_arr_cars}" var="car">
							<tr class="crearr_car_tr" id="${car.getCarId()}">
								<td>${car.getNumber()}</td>
								<td>${car.getDriver()}</td>
								<td>${car.getLicensePlate()}</td>
								<td style="display:none;">${car.getInsuranceDate()}</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<br />
			</div>
		</div>
	</div>
	<!-- modal -->
	<div class="modal fade" id="crearr_modal_submit" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新建车辆</h4>
				</div>
				<div class="modal-body">
					<div id="crearr_modal_re" class="alert alert-success" role="alert">正在处理，请稍后</div>
				</div>
				<div class="modal-footer">
					<button id="crearr_btn_goon" type="button" class="btn btn-default">继续安排</button>
					<button id="crearr_btn_check" type="button" class="btn btn-primary">查看数据</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end of modal -->
	<script type="text/javascript">
		$("#load_modal").modal('hide');//隐藏加载框

		/*时间控件*/
		$('.arr_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 3,
			minView : 3,
			forceParse : 0
		});
		$('.arr_time').datetimepicker({
			language : 'zh-CN',
			weekStart : 0,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			forceParse : 0
		});
		/*次数的验证*/
		$("#month_input_num").keyup(function(){
			var tt = $(this);
			var reg = new RegExp("^[0-9]*$");
			/*判断非零整数*/
			if(tt.val()!=""&&reg.test(tt.val())){
				if(tt.val()=="1"){
					$("#crearr_btn_next").text("完成");
				}else{
					$("#crearr_btn_next").text("下一步");
				}
				$("#arr_part").slideDown("500");
			}else{
				$("#arr_part").slideUp("500");
			}
		});
		
		//获取时间
		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = date.getFullYear() + seperator1 + month
					+ seperator1 + strDate;
			return currentdate;
		}
		/*日期选择时要进行重复验证*/
		$("#crearr_input_date").change(function(){
			$("#crearr_msg_div").hide();
			if($(this).val()<getNowFormatDate()){
				$("#crearr_msg").text("排班月份不能是以前");
				$("#crearr_msg_div").show();
				$(this).val("");
				return ;
			}
		});
		$("#crearr_input_time").change(function(){
			var ts = $(this);
		});
		
		var lineIds = new Array();
		var carIds = new Array();
		var goTime = "";
		/*控制线路的隐藏与显示*/
		$("#cre_arr_choose_line").click(
				function() {
					/*if ($("#crearr_input_time").val() == ""
							|| $("#crearr_input_date").val() == "") {
							$("#crearr_msg").text("必须完善时间和日期信息");
							$("#crearr_msg_div").show();
						return;
					}*/
					/*先加载已经选过的线路*/
					$(".crearr_line_tr").each(function(index){
						var tt = $(this);
						if(lineIds.indexOf(tt.attr("id"))!=-1){
							tt.addClass("select_bg");
						}
					});
					$("#arr_car_div").slideUp("1000");
					$("#arr_line_div").slideDown("1000");
				});

		/*控制表格行单击变色事件*/
		var lineNum = 0;//线路人数
		var lineId = 0;
		var carId = 0;
		$(".crearr_line_tr").click(function() {
			$(".crearr_line_tr").each(function() {
				$(this).removeClass("tr_bg");
			});
			$("#crearr_btn_line").attr("disabled", false);
			var tt = $(this);
			tt.addClass("tr_bg");
			$("#crearr_input_line").val(tt.children().first().text());
			lineNum = parseInt(tt.children().last().text());
			lineId = tt.attr("id");
		});
		/*线路选好之后*/
		$("#crearr_btn_line").click(
				function() {
					$("#arr_line_div").slideUp("1000");
					/*遍历车辆，去掉不合适的车辆*/
					$(".crearr_car_tr").each(
							function() {
								var tt = $(this);
								tt.show();
								var carNum = parseInt(tt.children().first()
										.text());
								if (carNum < lineNum) {
									tt.hide();
								}
								if(carIds.indexOf(tt.attr("id"))!=-1){
									tt.addClass("select_bg");
								}
							});
					$("#arr_car_div").slideDown("1000");
		});
		/*选择车辆*/
		$(".crearr_car_tr").click(function() {
			$(".crearr_car_tr").each(function() {
				var tt = $(this);
				tt.removeClass("tr_bg");
			});
			$("#crearr_btn_car").attr("disabled", false);
			var tt = $(this);
			tt.addClass("tr_bg");
			$("#crearr_input_car").val(tt.children().last().prev().text());
			carId = tt.attr("id");
		});
		$("#crearr_btn_car").click(function(){
			$("#arr_car_div").slideUp("1000");
		});
		
		/*下一步*/
		$("#crearr_btn_next").click(function(){
		
			/*显示隐藏的车辆*/
			$(".crearr_car_tr").each(function(){
				$(this).show();
			});
			var tt = $(this);
				
			var empty = false;
							if ($("#crearr_input_line").val() == ""
									|| $("#crearr_input_time").val() == ""
									|| $("#crearr_input_car").val() == "") {
								empty = true;
							} else {

							}
							if (!empty) {
								if (tt.text() == "下一步") {
									/*获取输入框的值*/
									goTime += $("#crearr_input_time").val()+",";
									lineIds.push(lineId);//加入
									carIds.push(carId);
									
									$("#crearr_btn_pre").show();
									$("#crearr_input_line").val("");
									$("#crearr_input_car").val("");
									$("#crearr_input_time").val("");
									$("#month_arr_num").text(
											"第" + (carIds.length + 1) + "次班次");
									
									if ($("#month_input_num").val() == carIds.length + 1) {
										tt.text("完成");
									}
								} else {
									goTime += $("#crearr_input_time").val();
									lineIds.push(lineId);//加入
									carIds.push(carId);
									var ads = "";
									var cds = "";
									for(var i=0;i<carIds.length-1;i++){
										ads+=lineIds[i]+",";
										cds+=carIds[i]+",";
									}
									ads+=lineIds[carIds.length-1];
									cds+=carIds[carIds.length-1];
									var d = $("#crearr_input_date").val();
									
									$("#crearr_msg").text("信息正在存储，请稍后");
									$("#crearr_msg_div").show();
									/*异步提交*/
									$.ajax({
										url : "servlet/CreateArrServlet",
										type : "POST",
										data : {
											type : "3",
											date : d,
											times : goTime,
											line_ids : ads,
											car_ids : cds
										},
										success : function(re) {
											if ("yes" == re) {
												$("#crearr_modal_re").text("班次安排成功");
											} else {
												$("#crearr_modal_re").text("班次安排失败");
											}
											$("#crearr_modal_submit").modal('show');
										},
										error : function() {
											alert("服务器错误")
										}
									});
								}
							} else {
								alert("请完善信息")
							}
						});

		/*最后提交保存*/
		$("#crearr_btn_fin").click(function() {
			var finish = true;
			$("input").each(function() {
				if ($(this).val() == "") {
					$("#crearr_msg").text("信息没有完善，请完善后提交");
					$("#crearr_msg_div").show();
					finish = false;
					return;
				}
			});
			if (finish) {
				$("#crearr_modal_re").text("正在处理，请稍后...");
				$("#crearr_modal_submit").modal('show');
				$("#crearr_msg_div").hide();
				var d = $("#crearr_input_date").val();
				var t = $("#crearr_input_time").val();
				var n = $("#crearr_input_name").val();
				/*异步提交*/
				$.ajax({
					url : "servlet/CreateArrServlet",
					type : "POST",
					data : {
						type : "2",
						date : d,
						time : t,
						name : n,
						line_id : lineId,
						car_id : carId
					},
					success : function(re) {
						if ("yes" == re) {
							$("#crearr_modal_re").text("班次安排成功");
						} else {
							$("#crearr_modal_re").text("班次安排失败");
						}
						$("#crearr_modal_submit").modal('show');
					},
					error : function() {
						alert("服务器错误")
					}
				});
			}
		});

		$("#crearr_btn_goon")
				.click(
						function() {
							$(".modal-backdrop").hide();
							$("#load_modal").modal('show');//显示加载框
							$("#content")
									.load("<%=request.getContextPath()%>/servlet/CreateArrServlet?multi=yes");
		});
		
		$("#crearr_btn_check").click(function(){
			var date = $("#crearr_input_date").val();
			$(".modal-backdrop").hide();
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageArrangeServlet?"+
				 "type=2"+"&date="+date);
		});
		
		/*按一次排班*/
		$("#crearr_btn_multi").click(function(){
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=path%>/servlet/CreateArrServlet");
		});
	</script>
</body>
</html>
