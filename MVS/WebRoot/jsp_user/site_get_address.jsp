<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'site_get_address' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript" src="js/mapinterface.js"></script>
	<script type="text/javascript" src="js/testroute.js"></script>
	<script>
	$(window).load(function(){
		 <c:forEach items="${buff_site_list}" var="site" varStatus="status" >
		 	satationSuit(${site.getLongitude()},${site.getLatitude()});
		 	var s=document.getElementById('return_satationinfo').innerHTML;
		 	
		 </c:c:forEach>

		});
		
	</script>
  </head>
  
  <body>
    <span id="return_satationinfo"></span>
  </body>
</html>
