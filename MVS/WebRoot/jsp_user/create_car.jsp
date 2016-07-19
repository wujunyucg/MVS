<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'create_car.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<link rel="stylesheet" type="text/css" href="css/create_user.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
	<script src='scripts/jquery.min.js'></script>
	<script src='scripts/bootstrap.min.js'></script>


	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="scripts/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

  </head>
  
  <body>
	<br/>
	
	<div id="inf_hint" class="alert alert-info" role="alert">请填写下列车辆信息（以下可输入文本框皆为必填信息且包含日期信息，
	其中行驶证指证芯编号；驾驶证指驾驶证的证号，即持证人身份证号）
	</div>
	<div id="inf_error" class="alert alert-danger" role="alert">您尚有未填写的数据信息   或   相应的车牌、行驶证车辆已存在  或  座位数不为整数</div>
	
	<!-- 第一个页面新建车辆数据 -->
	<div id="create_car1"  style="float:left !important;width: 90%;">
		<div class="row">
			<div class="col-lg-5"><div class="input-group">
				<span class="input-group-addon" id="sizing-addon2">车牌</span>
				<input type="text" id="lic_plat" class="form-control" placeholder="请输入新增车辆的车牌号" aria-describedby="sizing-addon2">
			</div></div>
			<div class="col-lg-5">
				<h6>
					<div id="judgeLicPla"  class="label label-danger" role="alert" style="display:none;">具有此车牌的车辆已存在</div>
				</h6>
			</div>
		</div><!-- row -->
		<br/>
		<div class="row">
			<div class="col-lg-5"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">品牌</span>
					<input type="text" id="bran" class="form-control" placeholder="请输入新增车辆的品牌" aria-describedby="sizing-addon2">
			</div></div><div class="col-lg-6"></div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-4"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">座位数</span>
					<input type="text" id="numb" class="form-control" placeholder="请输入座位数" aria-describedby="sizing-addon2">
					<span class="input-group-addon">个</span>
			</div></div>
			<div class="col-lg-6">
				<h6>
					<div id="judgeNum"  class="label label-danger" role="alert" style="display:none;">请输入整数</div>
				</h6>
			</div>
		</div><!-- row -->
		<br/>
		<div class="row">
			<div class="col-lg-5"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">行驶证</span>
					<input type="text" id="lice" class="form-control" placeholder="请输入新增车辆的行驶证" aria-describedby="sizing-addon2">
			</div></div>
			<div class="col-lg-6">
				<h6>
					<div id="judgeLic"  class="label label-danger" role="alert" style="display:none;">具有此行驶证的车辆已存在</div>
				</h6>
			</div>
		</div><!-- row -->
		<br/>
		<div class="row">
			<div class="col-lg-4"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">注册日期</span>
					<div style="float:left;" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
						<input size="50" id="res_date" class="form-control" type="text" placeholder="请选择车辆的注册日期"  
						style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					</div><div class="col-lg-7"></div>
			</div></div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-4"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">保险日期</span>
					<div style="float:left;" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
						<input size="50" id="ins_date" class="form-control" type="text" placeholder="请选择新辆的保险日期"  
						style=" -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;"readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					</div>
			</div></div>
		</div><!-- row -->
		<br/>
		<div class="row">
			<div class="col-lg-5"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">司机</span>

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
					<!-- /btn-group -->

					<input type="text" id="driv" class="form-control" aria-describedby="sizing-addon2" placeholder="未安排相应司机,不需填写" disabled=true>
			</div></div><div class="col-lg-6"></div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-5"><div class="input-group">
					<span class="input-group-addon" id="sizing-addon2">驾驶证</span>
					<input type="text" id="dri_lice" class="form-control" placeholder="未安排相应司机,不需填写" aria-describedby="sizing-addon2"  disabled=true>
			</div></div><div class="col-lg-6"></div>
		</div><!-- row -->

	<br/>
			<button id="createcar" type="button" class="btn btn-primary" data-toggle="modal">下一步</button>
	</div><!-- create_car1 -->

	

	<!-- 确认新建车辆 -->
	<div class="modal fade" id="sure_cre" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"aria-label="Close">
				<span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">新建车辆</h4></div>
				<div id="cre_page1" class="mypage">
					<div class="modal-body">
						<div class="alert alert-info" role="alert">下面是您创建的车辆信息，若核实无误，请点击-确认创建</div>
						<ul class="list-group">
								<li id="show_lic" class="list-group-item list-group-item-success"></li>
								<li id="show_bra" class="list-group-item list-group-item-success"></li>
								<li id="show_num" class="list-group-item list-group-item-success" role="alert"></li>
 						 		<li id="show_lice" class="list-group-item list-group-item-success" role="alert"></li>
								<li id="show_driv" class="list-group-item list-group-item-success" role="alert"></li>
								<li id="show_dri" class="list-group-item list-group-item-success" role="alert"></li>
								<li id="show_res" class="list-group-item list-group-item-success" role="alert"></li>
								<li id="show_ins" class="list-group-item list-group-item-success" role="alert"></li>
						</ul>		
					</div>
					<div class="modal-footer">
						<button id="btn_pre" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="sure_fin" type="button" class="btn btn-primary">确认新建</button>
					</div>
				</div>
				
				<div id="cre_page2" class="mypage3">
					<div class="modal-body">
						<div id="result" class="alert alert-success" role="alert">已成功新建了一个车辆数据</div>
					</div>
					<div class="modal-footer">
						<button id="ret_fin" type="button" class="btn btn-default">继续新建</button>
						<button id="sea_fin" type="button" class="btn btn-primary">查看数据</button>
					</div>
				</div>

			</div>
		</div>
	</div>


	<script type="text/javascript">
	
		$("#inf_hint").show();
		$("#inf_error").hide();


		var lic_plat = "";
		var bran = "";
		var res_date = "";
		var ins_date = "";
		var dri_lice = "";
		var lice = "";
		var num = "";
		var driv = "";
		var jud_driv = "1";

		var judgepla = 1;
		var judgelic = 1;
		var judgenum = 1;

		$('.dropdown-toggle').dropdown()
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

		function notDriver() {
			jud_driv = "1";
			$("#Judge_driver").attr("title", "1");
			$("#Judge_driver").html("未安排司机 <span class='caret'></span>");
			$("#driv").attr("disabled", "true");
			$("#driv").attr("placeholder", "未安排相应司机,不需填写");
			$("#dri_lice").attr("disabled", "true");
			$("#dri_lice").attr("placeholder", "未安排相应司机,不需填写");
			
		}

		function doDriver() {
			jud_driv = "2";
			$("#Judge_driver").attr("title", "2");
			$("#Judge_driver").html("已安排司机 <span class='caret'></span>");
			$("#driv").attr("placeholder", "请输入司机姓名");
			document.getElementById("driv").disabled = false;
			$("#dri_lice").attr("placeholder", "请填写驾驶证号码");
			document.getElementById("dri_lice").disabled = false;
		}
		
		$("#createcar").click(function() {
				$("#cre_page1").hide();
				$("#cre_page2").hide();
				
				lic_plat = $("#lic_plat").val();
				bran = $("#bran").val();
				res_date = $("#res_date").val();
				ins_date = $("#ins_date").val();
				
				lice = $("#lice").val();
				numb = $("#numb").val();
				
				if(jud_driv == "1"){
					driv = "未安排司机";
					dri_lice = "0";
				}else{
					driv = $("#driv").val();
					dri_lice = $("#dri_lice").val();
				}
				
				if(judgenum==0||judgelic==0||judgepla==0||lic_plat==""||bran==""||res_date==""||ins_date==""||dri_lice==""||lice==""||numb==""||driv==""){
					$("#createcar").attr("data-target","");
					$("#inf_error").show();
				}else{
					$("#inf_hint").show();
					$("#inf_error").hide();
					$("#createcar").attr("data-target","#sure_cre");
					$("#cre_page1").show();
					$("#cre_page2").hide();
					
					$("#show_lic").text("车牌：" + lic_plat);
					$("#show_bra").text("品牌：" + bran);
					$("#show_res").text("注册日期：" + res_date);
					$("#show_ins").text("保险日期：" + ins_date);
					if(jud_driv == 1){
						$("#show_dri").text("驾驶证：未安排司机，无驾驶证");			
					}else{
						$("#show_dri").text("驾驶证：" + dri_lice);			
					}
					
					$("#show_lice").text("行驶证：" + lice);
					$("#show_num").text("座位数：" + numb + " 个");
					$("#show_driv").text("司机：" + driv);
				}
			});
			
			$("#sure_fin").click(function() {
				if(judgenum==0||judgepla==0||judgelic==0||lic_plat==""||bran==""||res_date==""||ins_date==""||dri_lice==""||lice==""||numb==""||driv==""){
				}else{
				$.ajax({
						url : "servlet/CreateCarServlet",
						type : "POST",
						data : {
							type : "1",
							lic_plat : lic_plat,
							bran : bran,
							res_date : res_date,
							ins_date : ins_date,
							dri_lice : dri_lice,
							lice : lice,
							numb : numb,
							driv : driv,
						},
						success : function(re) {
						if ("yes" == re) {
							//$("#sure_fin").attr("data-dismiss", "modal");
							$("#cre_page1").hide("1000");
							$("#cre_page2").show();
						} else {
						}
					}
				});
			}
		});
		
		$("#lic_plat").keyup(function() {
				lic_plat = $("#lic_plat").val();
				
				if(lic_plat != ""){
					$.ajax({
						url : "servlet/CreateCarServlet",
						type : "POST",
						data : {
							type : "2",
							type_s : "1" ,
							lic_plat : lic_plat,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeLicPla").hide();
								judgepla = 1;
							} else {
								$("#judgeLicPla").show();
								judgepla = 0;
							}
						}
					});
				}
			});
			
			$("#numb").keyup(function() {
				numb = $("#numb").val();
				
				if(numb != ""){
					$.ajax({
						url : "servlet/CreateCarServlet",
						type : "POST",
						data : {
							type : "3",
							numb : numb,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeNum").hide();
								judgenum = 1;
							} else {
								$("#judgeNum").show();
								judgenum = 0;
							}
						}
					});
				}
			});
			
			$("#lice").keyup(function() {
				lice = $("#lice").val();
				
				if(lice != ""){
					$.ajax({
						url : "servlet/CreateCarServlet",
						type : "POST",
						data : {
							type : "2",
							type_s : "2" ,
							lice : lice,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeLic").hide();
								judgelic = 1;
							} else {
								$("#judgeLic").show();
								judgelic = 0;
							}
						}
					});
				}
			});
		
		$("#ret_fin").click(function(){
			$(".modal-backdrop").hide();
			$("#content").load("<%=request.getContextPath()%>/jsp_user/create_car.jsp");
		});
		
		$("#sea_fin").click(function(){
			$(".modal-backdrop").hide();
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=request.getContextPath()%>/servlet/ManageCarServlet?type=1&page_index=1&condition=1&sea_condition="+lic_plat);
		});
		
	</script>      
		
</body>
</html>
