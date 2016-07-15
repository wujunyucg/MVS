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
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <style>
	 .td1{width:100px;}
	 </style>
  </head>
  
  <body onload="searchcontent()">
  <h1>员工维护</h1>
  <div style="text-align: center;margin-right: auto;margin-left: auto;">
   
  <div class="form-inline" >
  
  <div class="form-group">
    <div class="input-group">
  <div class="input-group-addon">搜索类型</div>
    <select class="form-control" id="type">
  <option value="1" >员工工号</option>
   <option value="2">员工姓名</option>
  <option value="3">员工部门</option>
  <option value="4">员工组别</option>
  <option value="5">所属班次</option>
  <option value="6">所属线路</option>
  <option value="7">所属站点</option>
</select>
    
    </div>
  </div>
   <div class="form-group">
    <div class="input-group">
   <div class="input-group-addon">关键词</div>
      <input type="text" class="form-control"  id="content1">
    
    </div>
  </div>
  <button type="submit" class="btn btn-primary" onclick="javascript:search();">搜索</button>
</div>
<br>
  </div>
     <c:if test="${staff_list != null }">
     
    <div style="text-align: center;margin-right: auto;margin-left: auto;"> 
    <table  id ="usertab" class="table table-hover table-bordered" style="text-align: center; width:98%;margin-right: auto;margin-left: auto;color:#000,float:right">
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
	       <c:forEach items="${staff_list}" var="staff" varStatus="status" >
          <tr id="tr${(staff_page-1)*staff_page_num+status.index+1}">
            <td><input name="deletecheck" type="checkbox" value="${staff.getStaffId()}" /></td>
            <td >${(staff_page-1)*staff_page_num+status.index+1}</td>
            <td >${staff.getNumber()}</td>            
           <td>${staff.getName()}</td>
           <td>${staff.getDepartment()}</td>
            <td>${staff.getGroup()}</td>
            <td ><a data-toggle="modal"  data-target="#w-modal" onclick="javascript:layer1('${staff.getNumber()}','${staff.getName()}', '${staff.getDepartment()}','${staff.getGroup()}','${staff.getArrangeId()}','${staff.getLineId()}','${staff.getSiteId()}','${staff.getAddress()}')">查看详情</a></td>
            <td ><a data-toggle="modal"  data-target="#w-modal" onclick="javascript:layer2('${staff.getStaffId()}','${staff.getNumber()}','${staff.getName()}', '${staff.getDepartment()}','${staff.getGroup()}','${staff.getArrangeId()}','${staff.getLineId()}','${staff.getSiteId()}','${staff.getAddress()}')">修改</a></td>
            <td><a onclick="javascript:deleteone('${staff.getStaffId()}')"  data-toggle="modal"  data-target="#w-modal">删除</a></td>
          </tr>
          </c:forEach>
	  </tbody>
	</table>
	<button type="button" class="btn btn-danger" id="deleteall" onclick="javascript:deleteall();" data-toggle="modal"  data-target="#w-modal" style="float:left">删除</button>
<span>共${staff_page_all}页</span>
	<nav>
  <ul class="pagination">
   <c:if test="${1==staff_page }">
    <li class="disabled" id= "pre_li">
      <a   aria-label="Previous" id= "pre_li_a" onclick="">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <c:if test="${1 !=staff_page }">
    <li  id= "pre_li">
      <a   aria-label="Previous" id= "pre_li_a"  onclick="javascript:pagination(${staff_page-1})">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <c:forEach var="i" begin="${staff_begin_page}" end="${staff_begin_page+4<staff_page_all?staff_begin_page+4:staff_page_all }">
    <c:if test="${i == staff_page }">
    <li id="li${i}" class="active" ><a id="li_a${i}" onclick="">${i}</a></li>
    </c:if>
     <c:if test="${i != staff_page}">
    <li id="li${i}" class=""><a id="li_a${i}" onclick="javascript:pagination(${i})">${i}</a></li>
    </c:if>
    </c:forEach>
     <c:if test="${staff_page_all==staff_page}">
    <li class="disabled" id= "next_li">
   
      <a  aria-label="Next" id= "next_li_a" onclock="">
        <span aria-hidden="true">&raquo;</span>
      </a>
     
    </li>
     </c:if>
      <c:if test="${staff_page_all !=staff_page}">
    <li id= "next_li">
   
      <a  aria-label="Next" id= "next_li_a"  onclick="javascript:pagination(${staff_page+1})">
        <span aria-hidden="true">&raquo;</span>
      </a>
     
    </li>
     </c:if>
  </ul>
</nav>
    </div>
    </c:if>
    

  </body>
   
  <script>
<c:choose>
       <c:when test="${staff_type == null}">
 			 function searchcontent(){}
               </c:when>
               <c:otherwise>
                  function searchcontent(){
  				$("#type").val("${staff_type}"); 
				$("#content1").val("${staff_search_content}"); 
  	}
               </c:otherwise>
           </c:choose>
$(window).load(searchcontent());
  function  layer1(number,name,department,group,arrange,line,site ,address){
  $("#w-modal-close").attr("onclick","");
	  var tab='<table class="table table-hover table-bordered" style="width:100%;">'
	  +'<tr><td class="td1">员工工号</td><td>'+number+'</td></tr>'
	  +'<tr><td class="td1">员工姓名</td><td>'+name+'</td></tr>'
	  +'<tr><td class="td1">员工部门</td><td>'+department+'</td></tr>'
	  +'<tr><td class="td1">员工组别</td><td>'+group+'</td></tr>'
	  +'<tr><td class="td1">所属班次</td><td>'+arrange+'</td></tr>'
	  +'<tr><td class="td1">所属线路</td><td>'+line+'</td></tr>'
	  +'<tr><td class="td1">所属站点</td><td>'+site+'</td></tr>'
	  +'<tr><td class="td1">所属站点</td><td>'+address+'</td></tr>'
	  +'<table>';
	   document.getElementById("w-modal-p1"). innerHTML = '查看详情';
	  document.getElementById("w-modal-div"). innerHTML = tab;
	  document.getElementById("w-modal-but"). style.display="none"; 
}

function  layer2(staffid,number,name,department,group,arrange,line,site,address ){
 document.getElementById("w-modal-p2"). innerHTML = '';
  $("#w-modal-but").html("提交更改"); 

 document.getElementById("w-modal-but"). style.display="inline "; 
  var tab= '<form id="updateuser">'
  	  +'<table class="table table-hover table-bordered" style="width:100%;">'
  	  +'<input type="text" id= "staffid" name="staffid" value="'+staffid+'" style="display:none;"/>'
	  +'<tr><td class="td1">员工工号</td><td ><input type="text"  name="number" value="'+number+'" /></td></tr>'
	  +'<tr><td class="td1" >员工姓名</td><td><input type="text"  name="name" value="'+name+'"/></td></tr>'
	  +'<tr><td class="td1">员工部门</td><td  ><input type="text"  name="department" value="'+department+'"/>'
	/*  +' <select name="admin" id="useradmin" >';
	  <c:forEach items="${user_admin_list}" var="admin1" varStatus="status" >
	  	if(admin != '${admin1.getName()}')
  		 tab=tab+'<option >${admin1.getName()}</option>';
  		 else
   			tab=tab+'<option selected="selected">${admin1.getName()}</option>';
		</c:forEach>
		tab=tab+'</select>'*/
	  +'</td></tr>'
	   +'<tr><td class="td1" >员工组别</td><td ><input type="text"  name="group" value="'+group+'"/></td></tr>'
	    +'<tr><td class="td1" >所属班次</td><td ><input type="text"  name="arrange" value="'+arrange+'"/></td></tr>'
	     +'<tr><td class="td1" >所属线路</td><td ><input type="text"  name="line" value="'+line+'"/></td></tr>'
	      +'<tr><td class="td1" >所属站点</td><td ><input type="text"  name="site" value="'+site+'"/></td></tr>'
	       +'<tr><td class="td1" >员工地址</td><td ><input type="text"  name="address" value="'+address+'"/></td></tr>'
	  +'<table> </form>';
	 
	   document.getElementById("w-modal-p1"). innerHTML = '修改';
      document.getElementById("w-modal-div"). innerHTML = tab;
     // alert($("#useradmin").html())
}
function pagination(page){
		<c:choose>
               <c:when test="${staff_type == null}">
                  $("#content").load("<%=basePath%>servlet/ManageStaffServlet?staff_page="+page);
               </c:when>
               <c:otherwise>
                   $("#content").load("<%=basePath%>servlet/ManageStaffServlet?staff_page="+page+"&staff_type="+${staff_type});
               </c:otherwise>
           </c:choose>
}
function update(){
	$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/UpdateStaffServlet", 
		data:$('#updateuser').serialize(), 
		error: function(request) {
            document.getElementById("w-modal-p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
		if(request == 1){
			document.getElementById("w-modal-p2"). innerHTML = '修改成功';
			$("#w-modal-close").attr("onclick","javascript:pagination("+${staff_page}+")");
           	$("#w-modal-close2").attr("onclick","javascript:pagination("+${staff_page}+")");
		}
        else{
        	document.getElementById("w-modal-p2"). innerHTML = '修改失败，请重新修改';
        }
	    
      }});
}
function deleteonesure(staffid)
{
	$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/DeleteStaffServlet", 
		data:{
						onlyOne : "1",
						staffId :staffid,
			},
		error: function(request) {
         },
		success: function(request){
		if(request == 1){
		$("#w-modal-but").attr("disabled",true);
		$("#w-modal-p2").html("删除成功");
      		$("#w-modal-close").attr("onclick","javascript:pagination("+${staff_page}+")");
      		$("#w-modal-close2").attr("onclick","javascript:pagination("+${staff_page}+")");
		}	
		else{
		$("#w-modal-but").attr("disabled",true);
		$("#w-modal-p2").html("删除成功");
			$("#w-modal-close").attr("onclick","javascript:pagination("+${staff_page-1}+")");
			$("#w-modal-close2").attr("onclick","javascript:pagination("+${staff_page-1}+")");
		}    
      }});
}
function deleteone(staffid){
			$("#w-modal-p2").html("");
			$("#w-modal-but").attr("disabled",false);
			$("#w-modal-p1").html("删除确认");
			$("#w-modal-but").html("确认删除");
			$("#w-modal-div").html("确认删除此管理员吗？");
			$("#w-modal-but").attr("onclick","javascript:deleteonesure("+staffid+")");
	
}

function deleteallsure(){
	var arr_id=[];
	$("input[name='deletecheck']").each(function(){
		if($(this).is(':checked')){
        	arr_id.push($(this).val());
		}
	});
	$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/DeleteStaffServlet", 
		data:{
						onlyOne : "0",
						ids : arr_id.toString()
			},
		traditional:true,
		error: function(request) {
          alert('修改失败，请重新修改');
         },
		success: function(request){
		if(request == 1){
		$("#w-modal-but").attr("disabled",true);
		$("#w-modal-p2").html("删除成功");
			$("#w-modal-close").attr("onclick","javascript:pagination("+${staff_page}+")");
      		$("#w-modal-close2").attr("onclick","javascript:pagination("+${staff_page}+")");
		}
        else{
        	$("#w-modal-but").attr("disabled",true);
		$("#w-modal-p2").html("删除成功");
        	 $("#w-modal-close").attr("onclick","javascript:pagination("+${staff_page-1}+")");
			$("#w-modal-close2").attr("onclick","javascript:pagination("+${staff_page-1}+")");
        }
	    
      }});
}

function deleteall(){
		var flag=0;
		$("#w-modal-but").attr("disabled",false);
		$("#w-modal-p2").html("");
		$("input[name='deletecheck']").each(function(){
		if($(this).is(':checked')){
        	flag=1;
		}
	});
		if(flag == 0){
			$("#w-modal-but").attr("disabled",true);
			$("#w-modal-p2").html("没有选择管理员");
		}
			$("#w-modal-p1").html("删除确认");
			$("#w-modal-but").html("确认删除");
			$("#w-modal-div").html("确认删除选择的所有管理员吗？");
			$("#w-modal-but").attr("onclick","javascript:deleteallsure()");
	
}

function checkall(){
	$("input[name='deletecheck']").prop("checked",$("#checkall").prop("checked"));
}


function search(){
	var type = $("#type").val();
	var content = $("#content1").val();
	if(content == "")
		alert("未输入内容");
	else
		$("#content").load("<%=basePath%>servlet/SearchStaffServlet?content="+content+"&type="+type);
}

  </script>
</html>
