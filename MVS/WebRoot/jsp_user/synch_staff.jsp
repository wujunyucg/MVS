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
    
    <title>My JSP 'synch_staff.jsp' starting page</title>
    
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
  
  <div>
  <h1>同步记录</h1>
     <c:if test="${synch_list != null }">
     
    <div style="text-align: center;margin-right: auto;margin-left: auto;"> 
    <table  id ="usertab" class="table table-hover table-bordered" style="text-align: center; width:98%;margin-right: auto;margin-left: auto;color:#000,float:right">
	  <thead>
	    <tr>
	      <th>#</th>
	      <th>同步者</th>
	      <th>同步时间</th>
	    </tr>
	  </thead>
	  <tbody>
	       <c:forEach items="${synch_list}" var="synch" varStatus="status" >
            <td >${(synch_page-1)*synch_page_num+status.index+1}</td>
            <td >${synch.getName()}</td>            
           <td>${synch.getTime()}</td>
         </tr>
          </c:forEach>
	  </tbody>
	</table>
<span>共${synch_page_all}页</span>
	<nav>
  <ul class="pagination">
   <c:if test="${1==synch_page }">
    <li class="disabled" id= "pre_li">
      <a   aria-label="Previous" id= "pre_li_a" onclick="">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <c:if test="${1 !=synch_page }">
    <li  id= "pre_li">
      <a   aria-label="Previous" id= "pre_li_a"  onclick="javascript:pagination(${staff_page-1})">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <c:forEach var="i" begin="${synch_begin_page}" end="${synch_begin_page+4<synch_page_all?synch_begin_page+4:synch_page_all }">
    <c:if test="${i == synch_page }">
    <li id="li${i}" class="active" ><a id="li_a${i}" onclick="">${i}</a></li>
    </c:if>
     <c:if test="${i != synch_page}">
    <li id="li${i}" class=""><a id="li_a${i}" onclick="javascript:pagination(${i})">${i}</a></li>
    </c:if>
    </c:forEach>
     <c:if test="${synch_page_all==synch_page}">
    <li class="disabled" id= "next_li">
   
      <a  aria-label="Next" id= "next_li_a" onclock="">
        <span aria-hidden="true">&raquo;</span>
      </a>
     
    </li>
     </c:if>
      <c:if test="${synch_page_all !=synch_page}">
    <li id= "next_li">
   
      <a  aria-label="Next" id= "next_li_a"  onclick="javascript:pagination(${synch_page+1})">
        <span aria-hidden="true">&raquo;</span>
      </a>
     
    </li>
     </c:if>
  </ul>
</nav>
    </div>
    </c:if>
    
  </div>
  </body>
  <script>

  
  function pagination(page){
          $("#content").load("<%=basePath%>servlet/ManageSynchServlet?synch_page="+page);
             
}
  </script>
</html>
  