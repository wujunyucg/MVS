<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
		response.sendRedirect(path+"/login.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>person-info</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<div style="float:left;">
		<div class="panel panel-default">
			<div class="panel-heading" style="width: 150px;">个人设置</div>
			<ul class="list-group">
				<li class="list-group-item"><a id="a_per_username"
					href="javascript:;">用户名</a></li>
				<li class="list-group-item"><a id="a_per_pass"
					href="javascript:;">密码</a></li>
				<li class="list-group-item"><a id="a_per_power"
					href="javascript:;">权限</a></li>
			</ul>
		</div>
	</div>
	<div style="margin-left:180px;">
		<div class="panel panel-default per-info-div" id="per_name_div">
			<div class="panel-heading">修改用户名(用于登录)</div>
			<div class="form-group" style="padding:10px;">
				<label for="exampleInputEmail1">用户名</label> <input type="text"
					class="form-control" id="per_input_name" placeholder="username"
					value="${user.getNumber()}">
			</div>
			<div class="form-group" style="padding:10px;">
				<label for="exampleInputEmail1">密码</label> <input type="password"
					class="form-control" id="input_name_pass"
					placeholder="password" value="">
			</div>
			<button id="per_btn_name" type="button" class="btn btn-primary"
				style="margin:10px;">确认修改</button>
		</div>
		<div class="panel panel-default  per-info-div" id="per_pass_div"
			style="display:none;">
			<div class="panel-heading">修改密码</div>
			<div class="form-group" style="padding:10px;">
				<label for="exampleInputEmail1">旧密码</label> <input type="password"
					class="form-control" id="input_old_pass" placeholder="password"
					value="">
			</div>
			<div class="form-group" style="padding:10px;">
				<label for="exampleInputEmail1">新密码</label> <input type="password"
					class="form-control" id="input_new_pass"
					placeholder="new password" value="">
			</div>
			<div class="form-group" style="padding:10px;">
				<label for="exampleInputEmail1">确认新密码</label> <input type="password"
					class="form-control" id="input_new_pass_again"
					placeholder="new password again" value="">
			</div>
			<button id="per_btn_pass" type="button" class="btn btn-primary"
				style="margin:10px;">确认修改</button>
		</div>

		<div class="panel panel-default per-info-div" id="per_power_div"
			style="display:none;">
			<div class="panel-heading">查看权限</div>

		</div>
	</div>

	<script type="text/javascript">
		$("#a_per_username").click(function() {
			$(".per-info-div").hide();
			$("#per_name_div").slideDown("1000");

		});
		$("#a_per_pass").click(function() {
			$(".per-info-div").hide();
			$("#per_pass_div").slideDown("1000");

		});
		$("#a_per_power").click(function() {
			$(".per-info-div").hide();
			$("#per_power_div").slideDown("1000");

		});
		
		/*修改用户名*/
		$("#per_btn_name").click(function() {
			var n = $("#per_input_name").val();
			var pass = $("#input_name_pass").val();
			if(""==n||pass==""){
				return ;
			}
			$.ajax({
				url : "servlet/UserInfoServlet",
				type : "POST",
				data : {
					type : "1",
					name : n,
					pass: pass
				},
				success : function(re) {
					if(re=="yes"){
						alert("修改成功")
					}else if(re=="same"){
						alert("没有修改，因为一样")
					}else if(re=="error"){
						alert("密码错误")
					}else{
						alert("修改失败")
					}
				},
				error : function() {
					alert("服务器故障")
				}
			});
		});
		
		$("#a_per_power").click(function() {
			$(".per-info-div").hide();
			$("#per_power_div").slideDown("1000");

		});

		/*修改密码*/
		$("#per_btn_pass").click(function() {
			var old_pass = $("#input_old_pass").val();
			var new_pass = $("#input_new_pass").val();
			var nnew_pass = $("#input_new_pass_again").val();
			if(""==old_pass||""==new_pass||""==nnew_pass){
				return ;
			}else if(new_pass!=nnew_pass){
				alert("密码不一致")
			}
			$.ajax({
				url : "servlet/UserInfoServlet",
				type : "POST",
				data : {
					type : "2",
					oldPass : old_pass,
					newPass : new_pass
				},
				success : function(re) {
					if(re=="yes"){
						alert("yes")
					}else if(re=="notpass"){
						alert("notpass")
					}else{
						alert("no")
					}
				},
				error : function() {
					alert("服务器故障")
				}
			});
		});
	</script>
</body>
</html>
