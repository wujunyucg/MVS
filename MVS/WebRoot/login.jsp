<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>Login</title>

<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
<script type="text/javascript">
	//支持Enter键登录
	document.onkeydown = function(e) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			var obtnLogin = document.getElementById("submit_btn")
			obtnLogin.focus();
		}
	}

	$(function() {
		$("#submit_btn").click(function(){
			var name = $("#name").val();
			var pass = $("#pass").val();
			var type = $("input:radio[name='admin']:checked").val();
			var validCode = $("#valid").val();
			//alert(name+" "+pass+" "+type+" "+validCode)
			if (name != "" && pass != "" && validCode != "" && type != "") {
				$("#modal_text").text("正在验证，请稍后...");
				$("#msg_modal").modal('show');
				$.ajax({
					url : "servlet/LoginServlet",
					type : "POST",
					data : 
						$("#form_id").serialize()+"&type="
						+type+"&valid="
						+validCode
					,
					success : function(re) {
						$(".modal-backdrop").hide();
						$("#msg_modal").modal('hide');
						if (re == "valid") {
							$("#modal_text").text("验证码错误");
							$("#msg_modal").modal('show');
						}else if("admin"==re){
							window.location.href = "<%=path%>/jsp_admin/sadmin.jsp";
						}else if("user"==re){
							window.location.href = "<%=path%>/jsp_user/user.jsp";
						}else if("noadmin"==re){
							$("#modal_text").text("没有角色");
							$("#msg_modal").modal('show');
						}else{
							$("#modal_text").text("用户名或密码错误");
							$("#msg_modal").modal('show');
						}
					},
					error : function() {
						alert("服务器故障");
					}
				});
				return false;//记得添加
			}else{
				$("#modal_text").text("不能为空");
				$("#msg_modal").modal('show');
				return false;
			}
			});
		});
</script>
</head>
<body>
	<div class="bg" > </div>
	<div id="login">
		<div id="mask"> </div>
		<h1>厂车管理后台</h1>
		<form id="form_id">
			<input id="name" name="username" type="text" placeholder="用户名" /> <input
				name="password" id="pass" type="password" placeholder="密码" /> <input
				id="valid" name="validtion" type="text" placeholder="验证码,字母小写" />
			<div class="valitype">
				<img id="validationCode" alt="验证码图片" title="验证码图片"
					src="<%=path%>/validationCodeServlet.png"
					onclick="refreshCode(this)" /> <label> <input type="radio"
					name="admin" id="inlineRadio1" value="1"> 超级管理员
				</label> &nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio"
					name="admin" id="inlineRadio2" value="2" checked="checked">
					普通管理员</label>
			</div>
			<hr id="mhr"></hr>
			<input id="submit_btn" type="submit" value="登    录"
				style="margin-top:10px;" />
		</form>
	</div>

	<!-- 提示信息模态框 -->
	<div id="msg_modal" class="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<b id="modal_text">正在加载，请稍后...</b>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/** 
		 * 刷新验证码 
		 */
		function refreshCode(imgObj) {
			if (!imgObj) {
				imgObj = document.getElementById("validationCode");
			}
			var index = imgObj.src.indexOf("?");
			if (index != -1) {
				var url = imgObj.src.substring(0, index + 1);
				imgObj.src = url + Math.random();
			} else {
				imgObj.src = imgObj.src + "?" + Math.random();
			}
		}
	</script>
</body>
</html>
