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
		$("#submit_btn").click(function() {
			var name = $("#name").val();
			var pass = $("#pass").val();
			var type = "";
			var validCode = $("#valid").val();

			if (name != "" && pass != "" && validCode != "" && type != "") {
				$("#load_modal").modal('show');
				$.ajax({
					url : "servlet/LoginServlet",
					type : "POST",
					data : {
						username : name,
						password : pass,
						type:type,
						valid:validCode
					},
					success : function(re) {
						$("#load_modal").modal('hide');
						if (re == "yes") {
							window.location.href = "index.jsp";
						} else {
							$("#modal_error").modal('show');
						}
					},
					error : function() {
						alert("服务器故障");
					}
				});

				return false;//记得添加
			}
		});
	});
</script>
</head>
<body>
	<div id="login">
		<h1>厂车管理后台</h1>
		<form id="form_id">
			<input id="name" name="username" type="text" placeholder="用户名" /> <input
				name="password" id="pass" type="password" placeholder="密码" /> <input
				id="valid" name="validtion" type="text" placeholder="验证码,不区分大小写" />
			<div>
				<img id="validationCode" alt="验证码图片" title="验证码图片"
					src="<%=path%>/validationCodeServlet.png"
					onclick="refreshCode(this)" /> <label class="radio-inline">
					<input type="radio" name="inlineRadioOptions" id="inlineRadio1"
					value="1"> 超级管理员
				</label> <label class="radio-inline"> <input type="radio"
					name="inlineRadioOptions" id="inlineRadio2" value="1">
					普通管理员
				</label> <label class="radio-inline"> <input type="radio"
					name="inlineRadioOptions" id="inlineRadio3" value="3"> 员工
				</label>
			</div>
			<input id="submit_btn" type="submit" value="登    录"
				style="margin-top:10px;" />
		</form>
	</div>

	<!-- 加载的模态框 -->
	<div id="load_modal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<h3 class="modal-content">
				<span id="modal_text">正在验证，请稍后...</span>
			</h3>
		</div>
	</div>
	<!-- 登陆失败的提示框 -->
	<div id="modal_error" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<h3 class="modal-content">
				用户名或密码错误</span>
			</h3>
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
