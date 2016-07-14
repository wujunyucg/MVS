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
    
    <title>My JSP 'maintenance_staff.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/j-css/j-theme.css">
	<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
	<script src='scripts/jquery.js'></script>
	<script src='scripts/bootstrap.min.js'></script>
	<script type="text/javascript" src="scripts/j-scripts/j-theme.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <c:if test="${user_list != null }">
    <div style="text-align: center;margin-right: auto;margin-left: auto;"> 
    <table id ="usertab" class="table table-hover table-bordered" style="text-align: center; width:98%;margin-right: auto;margin-left: auto;color:#000">
	  <thead>
	    <tr>
	   	  <th><input name="" type="checkbox" id ="checkall" value="" onclick="javascript:checkall();"/>全选\不选</th>
	      <th>#</th>
	      <th>员工工号</th>
	      <th>员工姓名</th>
	      <th>员工部门</th>
	      <th>员工组别</th>
	      <th>查看详情</th>
	      <th>修改</th>
	      <th>删除</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	       <c:forEach items="${staff_list}" var="staff" varStatus="status" begin="${(satff_page-1)*satff_page_num}" end="${satff_page*user_page_num-1}">
          <tr id="tr${status.index}">
            <td><input name="deletecheck" type="checkbox" id="${user.getUserId()}" value="${status.index}" /></td>
            <td >${status.index+1}</td>
            <td id="tr${status.index}0">${user.getNumber()}</td>
             <c:forEach items="${user_admin_list}" var="admin" varStatus="status1" >
           <c:if test="${user.getAdminId() == admin.getAdminId() }"> <td id="tr${status.index}1"> ${admin.getName()}</td>
           <td></td>
           <td></td>
            <td id="tr${status.index}2"><a data-toggle="modal"  data-target="#myModal" onclick="javascript:layer1('${user.getNumber()}','${user.getPassword()}', '${admin.getName()}','${user.getType()}')">查看详情</a></td>
            <td id="tr${status.index}3"><a data-toggle="modal"  data-target="#myModal" onclick="javascript:layer2('${user.getUserId()}','${user.getNumber()}','${user.getPassword()}','${admin.getName()}','${user.getType()}',${status.index},$(this).parent().parent().attr('id'))">修改</a></td>
           </c:if>
            </c:forEach>
            <td><a onclick="javascript:deleteone('${user.getUserId()}','${status.index}')"  data-toggle="modal"  data-target="#myModal">删除</a></td>
          </tr>
           </c:forEach>
	    </tr>
	  </tbody>
	</table>
	<button type="button" class="btn btn-danger" id="deleteall" onclick="javascript:deleteall();" data-toggle="modal"  data-target="#myModal" style="float:left">删除</button>
	<nav>
  <ul class="pagination">
    <li class="disabled" id= "pre_li">
      <a   aria-label="Previous" id= "pre_li_a" onclick="">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach var="i" begin="${user_page}" end="${user_page+4<user_page_all?user_page+4:user_page_all }">
    <c:if test="${i == user_page }">
    <li id="li${i}" class="active" ><a id="li_a${i}" onclick="">${i}</a></li>
    </c:if>
     <c:if test="${i != user_page }">
    <li id="li${i}" class=""><a id="li_a${i}" onclick="javascript:pagination(${i})">${i}</a></li>
    </c:if>
    </c:forEach>
     <c:if test="${user_page_all==1 }">
    <li class="disabled" id= "next_li">
   
      <a  aria-label="Next" id= "next_li_a" onclock="">
        <span aria-hidden="true">&raquo;</span>
      </a>
     
    </li>
     </c:if>
      <c:if test="${user_page_all !=1 }">
    <li id= "next_li">
   
      <a  aria-label="Next" id= "next_li_a" onclock="javascript:pagination(2)">
        <span aria-hidden="true">&raquo;</span>
      </a>
     
    </li>
     </c:if>
  </ul>
</nav>
    </div>
    </c:if>
    
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              <p id= "p1"></p>
            </h4>
         </div>
         <div class="modal-body">
           <div id = "modalDiv"></div>
           <p id="p2"></p>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="modalBut" onclick="javascript:update()">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
  </body>
</html>
