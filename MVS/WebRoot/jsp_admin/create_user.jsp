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
    
    <title>My JSP 'create_admin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
	<script src='scripts/jquery.js'></script>
	<script src='scripts/bootstrap.min.js'></script>
	<link rel="stylesheet" type="text/css" href="css/create_user.css">

	<script type="text/javascript">
		function myload() {

		}
	</script>
  </head>
  
  <body>
  
  	<ol class="breadcrumb"><li><h4>创建用户</h4></li></ol>
  
  	<!-- 第一个页面创建用户属性 -->
  	 <div class="mypage1">
  	 	
  		<div class="row">
  		<div class="col-lg-6">
  		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">用户帐号：</span> <input
				id="numbertext" type="text" class="form-control" placeholder="请输入帐号(必填)"
				aria-describedby="sizing-addon2">		
		</div>
		</div>
		<div class="col-lg-6">
			<h6>
				<div id="judgeUserNumber"  class="label label-danger" role="alert" style="display:none;">此用户账号已存在</div>
				<div id="judgeUN"  class="label label-danger" role="alert" style="display:none;">请填写用户账号</div>
			</h6>
		</div>
		</div>
		<br/>
		<div class="row">
		<div class="col-lg-6">
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">用户密码：</span> <input
				id="passwordtext" type="password" class="form-control" placeholder="默认密码为123456"
				aria-describedby="sizing-addon2">
		</div>
		</div>
		<div class="col-lg-6">
			<h6>
				<div id="judgePL"  class="label label-danger" role="alert" style="display:none;">密码长度不能小于6个字符</div>
			</h6>
		</div>
		</div>
		
	<br/>
		<div class="row">
		<div class="col-lg-6">
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">用户角色：</span>
			<div class="form-control" id="admincho" aria-describedby="sizing-addon2">请从下列中选取一个角色(必选)</div>
		</div>
		</div>
		<div class="col-lg-6">

		</div>
		</div>
	<br/>
	<div class="panel panel-default">
	  <div class="panel-heading">
	 		<tr><table>
						<th style="width:210px">管理角色</th>
						<th>角色权限 （说明： A:增加   D:删除   C:更新   F:查看   E:导出）</th>
			</table></tr>
	</div>

	<div class="table-responsive"  style="max-height:400px;overflow-y:auto">	
		<table class="table">	
			<c:if test="${list != null }">
				<c:forEach items="${list}" var="admin">
					<tr class="admin_id" id="${admin.getAdminId()}" title="${admin.getName()}">
						<th class="adminnam" style="width:220px">${admin.getName()}</th>
						<th>${admin.getPowerId()}</th>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	<br/>
	</div>
	</div>
			<button id="btn_next" type="button" class="btn btn-primary" data-toggle="modal" 
			data-target="#myModal" disabled="disabled" style="align:right;">下一步</button>
	</div>
	<br/>
	
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">创建用户</h4>
	      </div>
	      <div id="page2" class="mypage">
	      	<div class="modal-body">
		      	<div class="alert alert-info" role="alert">下面是您创建的用户信息，若核实无误，请点击-确认创建</div>
		        <div id="show_number" class="alert alert-success" role="alert"></div>
				<div id="show_password" class="alert alert-success" role="alert"></div>
				<div id="show_power" class="alert alert-success" role="alert"></div>
		      </div>
		      <div class="modal-footer">
		       	<button id="btn_pre" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button id="btn_finish" type="button" class="btn btn-primary">确认创建</button>
		      </div>
		  </div>
		  
		   <div id="page3" class="mypage3">
	      	<div class="modal-body">
		      	<div class="alert alert-success" role="alert">已成功创建了一个用户</div>
		      </div>
		      <div class="modal-footer">
		       	<button id="btn_ne" type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
		      </div>
		  </div>
		  
	    </div>
	  </div>
	</div>
	
	
	
	
	<script type="text/javascript">
		var usernumber = "";
		var userpassword = "";
		var adminid = "";
		var adminname = "";
		var numkey = 0;
		var powkey = 0;
		var paskey = 1;
		$(function() {

			//控制页面的切换
			
			$("#btn_ne").click(function() {
				$(".modal-backdrop").hide();
				$("#content").load("<%=request.getContextPath()%>/servlet/CreateUserServlet");
			});
				
			$("#btn_next").click(function() {
			
				$("#page2").show();
				$("#page3").hide();
				
				usernumber = $("#numbertext").val();
				userpassword = $("#passwordtext").val();		
				
				if(userpassword == null || userpassword == "")
					userpassword = "123456";
				
				$(".admin_id").each(function() {
					if ($(this).hasClass("btn-success")) {
						adminid = $(this).attr("id");
						adminname = $(this).attr("title");
					}
				});
				
				$("#show_number").text("用户帐号：" + usernumber);
				$("#show_password").text("用户密码：" + userpassword);		
				$("#show_power").text("用户角色：" + adminname);
			});

			//当文本框文字改变监听
			$("#numbertext").keyup(function() {
				if ($(this).val() == "" || $(this).val() == null) {
					$("#judgeUserNumber").hide();
					numkey = 0; 
					$("#judgeUN").show();
					$("#btn_next").attr("disabled", true);
				} else {
					$("#judgeUN").hide();
					//异步检测名字是否重复
					usernumber = $(this).val();
					$.ajax({
						url : "servlet/CreUserNSServlet",
						type : "POST",
						data : {
							type : "1",
							userNumber : usernumber,
						},
						success : function(re) {
							if (re == "yes") {
								$("#judgeUserNumber").hide();
								numkey = 1;
								if(numkey == 1 && powkey == 1 && paskey == 1){
									$("#btn_next").attr("disabled", false); }
								else{
									$("#btn_next").attr("disabled", true); }
							} else {
								$("#judgeUserNumber").show();
								numkey = 0;
								$("#btn_next").attr("disabled", true);
							}
						}
					});
				}
			});

			$("#passwordtext").keyup(function() {
				if ($(this).val() != "" &&  $(this).val() != null) {
					if($(this).val().length < 6){
						paskey = 0;
						$("#judgePL").show();
					}
					else{
						$("#judgePL").hide();
						paskey = 1;
					}
				}
				else{
					$("#judgePL").hide();
					paskey = 1;
				}
				if(numkey == 1 && powkey == 1 && paskey == 1){
					$("#btn_next").attr("disabled", false); }
				else{
					$("#btn_next").attr("disabled", true); }
			});


			var t = -1;
			$(".admin_id").click(function() {			
				
				if(t == -1){
					$(this).toggleClass("btn-success");
					t = this;
				}
				else{
					$(t).toggleClass("btn-success");
					$(this).toggleClass("btn-success");
					t = this;
				}
				$("#admincho").html($(this).attr('title'));  
				powkey = 1;
				if(numkey == 1 && powkey == 1){
					$("#btn_next").attr("disabled", false); }
			});

			//点击完成后保存用户数据到数据库
			$("#btn_finish").click(function() {
				//$("#btn_finish").attr("data-dismiss", "modal");
				if(adminid != null && adminid != ""){
					$.ajax({
						url : "servlet/CreUserNSServlet",
						type : "POST",
						data : {
							type : "2",
							userNumber : usernumber,
							userPassword : userpassword,
							adminId : adminid,
						},
						success : function(re) {
						if ("yes" == re) {
							$("#page2").hide("1000");
							$("#page3").show();
							
						}
					}
				});
				}
			});
			
			
			
		});
	</script>
	
	
  </body>
</html>
