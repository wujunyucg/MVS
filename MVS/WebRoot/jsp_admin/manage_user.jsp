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
	 <script src="layer/layer.js"></script>
	 <style>
	 .td1{width:100px;}
	 </style>

<script type="text/javascript" language="javascript">
$.ajaxSetup ({ 
    cache: false //关闭AJAX相应的缓存 
}); 

function  layer1(number,password,admin,type ){
	layer.open({
	  area: ['420px', '340px'],
	  content: '<div><table class="table table-hover table-bordered" style="width:100%;">'
	  +'<tr><td class="td1">管理员账号</td><td>'+number+'</td></tr>'
	  +'<tr><td class="td1">管理员密码</td><td>'+password+'</td></tr>'
	  +'<tr><td class="td1">管理员角色</td><td>'+admin+'</td></tr>'
	  +'<tr><td class="td1">管理员类型</td><td>'+type+'</td></tr>'
	  +'<table></div>',
	  scrollbar: false
	});
}

function  layer2(number,password,admin,type ){
	layer.open({
  type: 1,
  closeBtn: 0, 
  area: ['420px', '340px'],
  content: '</br><table class="table table-hover table-bordered" style="width:100%;">'
	  +'<tr><td class="td1">管理员账号</td><td></td></tr>'
	  +'<tr><td class="td1">管理员密码</td><td></td></tr>'
	  +'<tr><td class="td1">管理员角色</td><td><input ></td></tr>'
	  +'<tr><td class="td1">管理员类型</td><td>'+type+'</td></tr>'
	  +'<table>',
	  btn: ['修改', '取消']
  ,yes: function(index){
    layer.close(index);
    layer.msg('修改成功',{
    });
    },
  scrollbar: false
	});
}
</script>
  </head>
  
  <body style = "text-align: center;">
    <h1>管理用户</h1>
     <c:if test="${user_map != null }">
    <div style="text-align: center;margin-right: auto;margin-left: auto;"> 
    <table class="table table-hover table-bordered" style="width:98%;margin-right: auto;margin-left: auto;color:#000">
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
	       <c:forEach items="${user_map}" var="user" varStatus="status" >
          <tr>
            <td></td>
            <td>${status.index}</td>
            <td>${user.key.getNumber()}</td>
            <td>${user.value}</td>
            <td><a onclick="javascript:layer1('${user.key.getNumber()}','${user.key.getPassword()}','${user.value}','${user.key.getType()}')">查看详情</a></td>
            <td><a onclick="javascript:layer2()">修改</a></td>
            <td><a >删除</a></td>
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
