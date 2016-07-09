<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">

  </head>
  
  <body style = "text-align: center;">
    <h1>manage zdd e</h1>
     <c:if test="${user_list != null }">
    <div style="text-align: center;margin-right: auto;margin-left: auto;"> 
    <table class="table table-hover table-bordered" style="width:800px;margin-right: auto;margin-left: auto;color:#000">
	  <thead>
	    <tr>
	   	  <th>#</th>
	      <th>#</th>
	      <th>管理员账号</th>
	      <th>管理员角色</th>
	      <th>查看详情</th>
	      <th>修改</th>
	      <th>删除</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	       <c:forEach items="${user_list}" var="user" begin="0" end="10">
          <tr>
            <td></td>
            <td>0</td>
            <td>${user.getNumber()}</td>
            <td></td>
            <td></td>
            <td></td>
            <td>1</td>
          </tr>
           </c:forEach>
	    </tr>
	  </tbody>
	</table>
    </div>
    </c:if>
  </body>
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
</html>
