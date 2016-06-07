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

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/login.css"/>

</head>
<body>
	<div id="login">
		<h1>厂车管理后台</h1>
		<form action="" method="post">
			<input type="text" placeholder="用户名" /> 
			<input type="password"	placeholder="密码" /> 
			<input type="submit" value="登    录" />
		</form>
	</div>

	<script src='scripts/jquery.js'></script>
	
	<script type="text/javascript">
	
	</script>
</body>
</html>
