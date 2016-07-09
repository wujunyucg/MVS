<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'admin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.bg {
	background-color: #00ff00;
}
</style>
</head>

<body>
	<button class="btn">Click Me</button>
	<button id="save">save</button>

	<script src='scripts/jquery.js'></script>

	<script type="text/javascript">
		$(function() {
			//添加属性
			$(".btn").click(function() {
				$(this).toggleClass("bg");
			});
			
			//检测有没有选中
			$("#save").click(function(){
				alert($(".btn").hasClass("bg"));
			});
		});
	</script>
</body>
</html>
