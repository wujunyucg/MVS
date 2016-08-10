<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'firstPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<img src="icons/firstPage.jpg" style="width:100%;height:145px;margin-left:-10px;">
	<div class="panel panel-default" style="margin-left:-10px;">
  		<div class="panel-body">
			 <h4><span class="glyphicon glyphicon-time" style="float:left;"></span>
			 	<div style="float:left;">当前时间：</div>
			 	<div id="time" style="float:left;"></div>
			 </h4>
		</div>
	</div>
	<br/>
	<div class="panel panel-default" style="margin-left:-10px;">
  		<div class="panel-heading">数据数量展示</div>
 		<div class="panel-body">
			  <div class="progress-bar progress-bar-info" style="width: 20px;height:20px;">
			</div>
 		</div>
	</div>
	
	
	<script language=JavaScript>
		document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
		setInterval("document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
	</script>

  </body>
</html>
