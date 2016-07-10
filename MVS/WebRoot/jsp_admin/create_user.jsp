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
	
	<link rel="stylesheet" type="text/css" href="css/create_user.css">
	<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
   <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src='scripts/jquery.js'></script>
	<script src='scripts/bootstrap.min.js'></script>

	<script type="text/javascript">
		function myload() {

		}
	</script>
  </head>
  
  <body>
  
  	<ol class="breadcrumb"><li><h4>创建用户</h4></li></ol>
  
  	<div id="result" class="123" role="alert"
			style="display:none;">已成功创建了一个用户</div>
		
  	<!-- 第一个页面创建用户属性 -->
  	 <div class="mypage1">
  	 	
  		<div class="row">
  		<div class="col-lg-6">
  		<div class="input-group input-group-lg">
			<span class="input-group-addon" id="sizing-addon1">用户帐号：</span> <input
				id="numbertext" type="text" class="form-control" placeholder="请输入帐号(必填)"
				aria-describedby="sizing-addon1">		
		</div>
		</div>
		<div class="col-lg-6">
		<div id="judgeUserNumber"  class="alert alert-danger" role="alert" style="display:none;">此角色名已存在</div>
		</div>
		</div>
		<br/>
		<div class="row">
		<div class="col-lg-6">
		<div class="input-group input-group-lg">
			<span class="input-group-addon" id="sizing-addon1">用户密码：</span> <input
				id="passwordtext" type="password" class="form-control" placeholder="默认密码为123456"
				aria-describedby="sizing-addon1">
		</div>
		</div>
		<div class="col-lg-6">

		</div>
		</div>
		
	<br/>
		<div class="row">
		<div class="col-lg-6">
		<div class="input-group input-group-lg">
			<span class="input-group-addon" id="sizing-addon1">管理角色：</span>
			<div class="form-control" id="admincho" aria-describedby="sizing-addon1">请从下列中选取一个权限(必选)</div>
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

	<div class="table-responsive"  style="height:220px;overflow-y:auto">	
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
			<button id="btn_next" type="button" class="btn btn-default" 
			disabled="disabled" style="align:left">下一步</button>
	</div>
	<br/>
	
	<div id="page2" class="mypage " style="display: none;">
		<div class="alert alert-info" role="alert">下面是您创建的角色信息，若有错误，请返回上一步修改！否则请点击完成</div>

		<div id="show_number" class="alert alert-success" role="alert"></div>
		<div id="show_password" class="alert alert-success" role="alert"></div>
		<div id="show_power" class="alert alert-success" role="alert"></div>
		<button id="btn_pre" type="button" class="btn btn-default">上一步</button>
		<button id="btn_finish" type="button" class="btn btn-default">确认创建</button>
	</div>
	
	
	
	<script type="text/javascript">
		var usernumber = "";
		var userpassword = "";
		var adminid = "";
		var numkey = 0;
		var powkey = 0;
		$(function() {

			//控制页面的切换
			$("#btn_next").click(function() {
			
				$(".mypage1").hide();
				$("#page2").show();
				
				usernumber = $("#numbertext").val();
				userpassword = $("#passwordtext").val();		
				
				if(userpassword == null || userpassword == "")
					userpassword = "123456";
				
				$(".admin_id").each(function() {
					if ($(this).hasClass("btn-success")) {
						adminid = $(this).attr("id");
					}
				});
				
				$("#show_number").text("用户帐号：" + usernumber);
				$("#show_password").text("用户密码：" + userpassword);

				
				$("#show_power").text("用户角色：" + adminid);
				
				var powers = "<h2>拥有的权限：</h2><br/>";
				
			});
			
			$("#btn_pre").click(function() {
			
				$("#page2").hide();
				$(".mypage1").show();
			});
			//当文本框文字改变监听
			$("#numbertext").keyup(function() {
				if($(this).val() == "" || $(this).val() == null){
					numkey = 0; 
					$("#btn_next").attr("disabled", true);}	
				if ($(this).val() == "") {
					$("#judgeUserNumber").hide();
					$("#btn_next").attr("disabled", true);
				} else {
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
								if(numkey == 1 && powkey == 1){
									$("#btn_next").attr("disabled", false); }
							} else {
								$("#judgeUserNumber").show();
								$("#btn_next").attr("disabled", true);
							}
						}
					});
				}
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
						$("#result").show();
						if ("yes" == re) {
							$("#page2").hide("1000");
						} else {
							$("#result").removeClass("alert-success");
							$("#result").addClass("alert-danger");
							$("#result").text("抱歉，未知原因创建失败");
						}
					}
					});
				}else{
					
				}
			});
		});
	</script>
	
	
  </body>
</html>
