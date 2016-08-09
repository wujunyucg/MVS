<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
	if (null == session.getAttribute("user")) {
		response.sendRedirect(path+"/jsp_user/user.jsp");
		return;
	}
%>
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
  <c:if test="${admin1!=null}">
  <div style="font-size:25px">
  <!-- Nav tabs -->
  <ul class="nav  nav-pills" role="tablist" style="height: 40px;font-size:25px">  
  
    <li role="presentation" class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
      即时同步 <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
       <li role="presentation" ><a href="" onclick="javascript:immed_manual()" data-toggle="modal"  data-target="#w-modal" aria-controls="" role="tab" >手动输入同步</a>
       </li>
   	 <li role="presentation"><a href="" onclick="javascript:immed_excel()" data-toggle="modal"  data-target="#w-modal" aria-controls="" role="tab" >文件上传同步</a></li>
    </ul>
    
     <li role="presentation" class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#"  role="button" aria-haspopup="true" aria-expanded="false">
      定时同步 <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
      <li role="presentation" ><a href="" onclick="javascript:time_manual()"  data-toggle="modal"  data-target="#w-modal" aria-controls="" role="tab">手动输入同步</a></li>
   	 <li role="presentation"><a href="" onclick="javascript:time_excel()" data-toggle="modal"  data-target="#w-modal" aria-controls="" role="tab" >文件上传同步</a></li>
    </ul>

	 <li role="presentation" ><a href="" aria-controls="" role="button" data-toggle="modal" data-target="#w-modal" onclick="javascript:changetime()">修改周期</a></li>
  </ul>
  </div>
<hr style="height:3px;border:none;border-top:3px double blue;" />
  </c:if>
  <h1>员工维护</h1>
  <div style="text-align: center;margin-right: auto;margin-left: auto;">
  	<c:if test="${admin0!=null}">
   	<button type="button" class="btn btn-danger" id="deleteall" onclick="javascript:deleteall();" data-toggle="modal"  data-target="#w-modal" style="float:left">删除</button>
   	</c:if>
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
	    <c:if test="${admin0!=null}">
	   	  <th><input name="" type="checkbox" id ="checkall" value="" onclick="javascript:checkall();"/>全选\不选</th>
	   	  </c:if>
	      <th>#</th>
	      <th>员工工号</th>
	      <th>员工姓名</th>
	      <th>员工部门</th>
	      <th>员工组别</th>
	      <th>查看详情</th>
	      <c:if test="${admin0!=null}">
	      <th>修改</th>
	      <th>删除</th>
	      </c:if>
	    </tr>
	  </thead>
	  <tbody>
	       <c:forEach items="${staff_list}" var="staff" varStatus="status" >
          <tr id="tr${(staff_page-1)*staff_page_num+status.index+1}">
          <c:if test="${admin0!=null}">
            <td><input name="deletecheck" type="checkbox" value="${staff.getStaffId()}" /></td>
            </c:if>
            <td >${(staff_page-1)*staff_page_num+status.index+1}</td>
            <td >${staff.getNumber()}</td>            
           <td>${staff.getName()}</td>
           <td>${staff.getDepartment()}</td>
            <td>${staff.getGroup()}</td>
            <td ><a data-toggle="modal" href="javascript:;" data-target="#w-modal" onclick="javascript:layer1('${staff.getNumber()}','${staff.getName()}', '${staff.getDepartment()}','${staff.getGroup()}','${staff.getArrangeId()}','${staff.getLineId()}','${staff.getSiteId()}','${staff.getAddress()}')">查看详情</a></td>
            <c:if test="${admin0!=null}">
            <td ><a data-toggle="modal" href="javascript:;" data-target="#w-modal" onclick="javascript:layer2('${staff.getStaffId()}','${staff.getNumber()}','${staff.getName()}', '${staff.getDepartment()}','${staff.getGroup()}','${staff.getArrangeId()}','${staff.getLineId()}','${staff.getSiteId()}','${staff.getAddress()}')">修改</a></td>
            <td><a onclick="javascript:deleteone('${staff.getStaffId()}')"   href="javascript:;" data-toggle="modal"  data-target="#w-modal">删除</a></td>
            </c:if>
          </tr>
          </c:forEach>
	  </tbody>
	</table>

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
    <c:forEach var="i" begin="${staff_begin_page-10>0?staff_begin_page-10:1}" end="${staff_begin_page+10<staff_page_all?staff_begin_page+10:staff_page_all }">
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
 $("#w-modal-div").css("overflow","");
$("#w-modal-div").css("height","");
$(window).load(searchcontent());
  function  layer1(number,name,department,group,arrangeid,lineid,siteid ,address){
  		$("#w-modal-close").attr("onclick","");
	$("#w-modal-export").css("display","none");
  		$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageStaffServlet", 
		data:{
			type:1,
			siteid:siteid,
			lineid:lineid,
			arrid:arrangeid
		}, 
		error: function(request) {
            document.getElementById("w-modal-p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			//alert(request);
			 var list = eval('(' + request + ')');
			 var site=list.site;
			 var line=list.line;
			var arr=list.arrange;
			var tab='<table class="table table-hover table-bordered" style="width:100%;">'
	  +'<tr><td class="td1">员工工号</td><td>'+number+'</td></tr>'
	  +'<tr><td class="td1">员工姓名</td><td>'+name+'</td></tr>'
	  +'<tr><td class="td1">员工部门</td><td>'+department+'</td></tr>'
	  +'<tr><td class="td1">员工组别</td><td>'+group+'</td></tr>';
	  if(arr==undefined)
	  	tab=tab+'<tr><td class="td1">所属班次</td><td>'+'无'+'</td></tr>';
	  else
	  	tab=tab+'<tr><td class="td1">所属班次</td><td>'+arr.name+'</td></tr>';
	 if(line==undefined)
	  	tab=tab+'<tr><td class="td1">所属线路</td><td>'+'无'+'</td></tr>';
	  else
	  	tab=tab+'<tr><td class="td1">所属线路</td><td>'+line.name+'</td></tr>';
	   if(site==undefined)
	  	tab=tab+'<tr><td class="td1">所属站点</td><td>'+'无'+'</td></tr>';
	  else
	  	tab=tab+'<tr><td class="td1">所属站点</td><td>'+site.name+'</td></tr>';
	  
	 tab=tab +'<tr><td class="td1">员工地址</td><td>'+address+'</td></tr>'
	  +'</table>';
	   document.getElementById("w-modal-p1"). innerHTML = '查看详情';
	  document.getElementById("w-modal-div"). innerHTML = tab;
	  document.getElementById("w-modal-but"). style.display="none"; 
	    
      }});
    
	  
}

function  layer2(staffid,number,name,department,group,arrangeid,lineid,siteid,address ){
 document.getElementById("w-modal-p2"). innerHTML = '';
  $("#w-modal-but").html("提交更改"); 
$("#w-modal-export").css("display","none");
 document.getElementById("w-modal-but"). style.display="inline "; 
 $.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageStaffServlet", 
		data:{
			type:2,
			siteid:siteid,
			lineid:lineid,
			arrid:arrangeid
		}, 
		error: function(request) {
            document.getElementById("w-modal-p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			//alert(request);
			 var list = eval('(' + request + ')');
			 var site=list.site;
			 var line=list.line;
			var arr=list.arrange;
			var allsite=list.allsite;
			 var allline=list.allline;
			var allarr=list.allarrange;
			var tab='<form id="updateuser">'
			+'<table class="table table-hover table-bordered" style="width:100%;">'
	  +'<tr><td class="td1">员工工号</td><td>'+number+'</td></tr>'
	  +'<tr><td class="td1">员工姓名</td><td>'+name+'</td></tr>'
	  +'<tr><td class="td1">员工部门</td><td>'+department+'</td></tr>'
	  +'<tr><td class="td1">员工组别</td><td>'+group+'</td></tr>';
	  	tab=tab+'<tr><td class="td1">所属班次</td><td>';
	  	tab=tab+'<select name="arrange">';
	  if(arr==undefined)
	 	tab=tab+'<option selected="selected" value="-1">无</option>';
	  else{
	  		tab=tab+'<option value="-1">无</option>';
	  		for(var i=0;i<allarr.length;i++){
	  			if(allarr[i].arrangeId == arr.arrangeId)
	  				tab=tab+'<option selected="selected" value="'+allarr[i].arrangeId+'">'+allarr[i].name+'</option>';
	  			else
	  				tab=tab+'<option  value="'+allarr[i].arrangeId+'">'+allarr[i].name+'</option>';
	  		}
	  }
	  	tab=tab+'</select></td></tr><tr><td class="td1">所属线路</td><td><select name="line">';
	  
	 if(line==undefined)
	  	tab=tab+'<option selected="selected" value="-1">无</option>';
	   else{
	  		tab=tab+'<option value="-1">无</option>';
	  		for(var i=0;i<allline.length;i++){
	  			if(allline[i].lineId == line.lineId)
	  				tab=tab+'<option selected="selected" value="'+allline[i].lineId+'">'+allline[i].name+'</option>';
	  			else
	  				tab=tab+'<option  value="'+allline[i].lineId+'">'+allline[i].name+'</option>';
	  		}
	  }
	  	tab=tab+'</select></td></tr><tr><td class="td1">所属站点</td><td><select name="site">';
	   if(site==undefined)
	  		tab=tab+'<option selected="selected" value="-1">无</option>';
	   else{
	  		tab=tab+'<option value="-1">无</option>';
	  		for(var i=0;i<allsite.length;i++){
	  			if(allsite[i].siteId == site.siteId)
	  				tab=tab+'<option selected="selected" value="'+allsite[i].siteId+'">'+allsite[i].name+'</option>';
	  			else
	  				tab=tab+'<option  value="'+allsite[i].siteId+'">'+allsite[i].name+'</option>';
	  		}
	  }
	  tab=tab+'</select></td></tr>';
	 tab=tab +'<tr><td class="td1">员工地址</td><td>'+address+'</td></tr>'
	  +'</table></form>';
	   document.getElementById("w-modal-p1"). innerHTML = '修改';
      document.getElementById("w-modal-div"). innerHTML = tab;
	  document.getElementById("w-modal-but"). style.display="inline";
	  $("#w-modal-but").attr("onclick","update();")
	    
      }});
	 
	  
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



  function changetime(){
  	$("#w-modal-but").attr("disabled",false);
  	$("#w-modal-but").html("修改周期");
  	$("#w-modal-p3").html("");
  	$("#w-modal-p2").html("");
  	var content='<form id="immedadd"><div class="form-group form-inline"><select id="quartz_type" name ="time" class="form-control">'
  			+'<option value = "day">天</option>'
  			+'<option value = "week">周</option>'
  	 		+'<option value = "month">月</option>'
  	 		+'</select>'
  	+'<input name = "type" value="5" style="display:none;"></form>';
  
  $("#w-modal-div").html(content);
  $("#quartz_type").val("${quartz_type}");
  $("#w-modal-but").attr("onclick","javascript:change();");	
  }
  function immed_manual(){
  	$("#w-modal-but").attr("disabled",true);
  	$("#w-modal-but").html("同步");
  	$("#w-modal-p3").html("");
  	$("#w-modal-p2").html("");
  	var content='<form id="immedadd"><div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工工号：</label><input type="text" class="form-control" id="staff_number"  name = "staff_number" placeholder="员工工号" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工姓名：</label><input type="text" class="form-control" name = "staff_name" placeholder="员工姓名" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工部门：</label><input type="text" class="form-control" name = "staff_department" placeholder="员工部门" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工组别：</label><input type="text" class="form-control" name = "staff_group" placeholder="员工组别" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工地址：</label><input type="text" class="form-control" name = "staff_address" placeholder="员工地址" style="width: 300px"></div>'
  				+'<input name = "type" value="1" style="display:none;"></form>';
  $("#w-modal-div").html(content);
  $(function(){
  $(".form-control").blur(function(){
  		if($(this).val()==""){	
  			$("#w-modal-p2").html("请输入完整内容");
  			$("#w-modal-p2").css("color","red");
  			$("#w-modal-p2").css("font-size","20px");
  		}
  		else{
  			$("#w-modal-p2").html("");
  		}
  		if($(this).attr("id")=="staff_number" && $(this).val()!=""){
  			$.ajax({ 
				type:"post",
				url: "<%=basePath%>servlet/SynchStaffServlet", 
				data:{
								type : "0",
								number :$(this).val() ,			
					},
				error: function(request) {
		          alert('修改失败，请重新修改');
		         },
				success: function(request){
				if(request == 1){
					$("#w-modal-p3").html("员工工号已有，请详细审核信息");
  					$("#w-modal-p3").css("color","red");
  					$("#w-modal-p3").css("font-size","20px");
  					
				}
				 else if(request == 2){
		        	$("#w-modal-p3").html("员工工号已在定时同步计划中，请详细审核信息");
  					$("#w-modal-p3").css("color","red");
  					$("#w-modal-p3").css("font-size","20px");
  					
		        }
		        else if(request == 3){
		        	 $("#w-modal-p3").html("员工工号可用");
  					$("#w-modal-p3").css("color","blue");
  					$("#w-modal-p3").css("font-size","20px");
  					
		        }
			    
		      }});
  		}
  		var flag = 0;
  		$(".form-control").each(function() {
  		
  		if($(this).val()=="")
  			flag = 1;
  		});
  		if(flag == 0 &&  $("#w-modal-p3").html()=="员工工号可用"){
  			$("#w-modal-but").attr("disabled",false);
  			$("#w-modal-but").attr("onclick","javascript:addone();");
  		}
  			
			
	});
	});
  }
  function addone(){
  		$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/SynchStaffServlet", 
		data:$('#immedadd').serialize(), 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '同步失败，请重新同步';
         },
		success: function(request){
		if(request == 1){
			$("#w-modal-p2").html('同步成功') ;
			$("#w-modal-p2").css("color","blue"); 
			$("#w-modal-but").attr("disabled",true); 	
		}
        else{
        	document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
        }
	    
      }});
       pagination(${staff_page});
  }
  
  function immed_excel(){
  	//$("#w-modal-but").attr("disabled",true);
  	$("#w-modal-but").html("同步");
  	$("#w-modal-p3").html("");
  	$("#w-modal-p2").html("");
  	$("#w-modal-but").attr("onclick","");	
  	var content='<form id="immedadd" enctype="multipart/form-data"> <div class="form-group"> <label for="exampleInputFile">File input</label><input id="upload" type="file" name="staff_excel"><p class="help-block">选择您想导入的EXCEL文件</p>'
  				+'<a href="/MVS/download/2014112155.xlsx">模板下载</a></div>'
  				+'<input name = "type" value="2" style="display:none;"></form>';
  	$("#w-modal-div").html(content);
  	$("#w-modal-but").attr("onclick","javascript:addexcel();");	
  	 
  }
  $("#w-modal-but").click(function(){
  	 	if($("#upload").val()==""){
  	 	$("#w-modal-p2").html("请输入文件");
  		$("#w-modal-p2").css("color","red");
  		$("#w-modal-p2").css("font-size","20px");
  		return false;
  	 	}
  	 	else{
  	 		 $("#w-modal-p2").html("");
  	 	}
  	 		
  	 });
  	 
  function addexcel(){
 $("#w-modal-p2").html("正在处理数据中，请稍候");
  		$("#w-modal-p2").css("color","red");
  		$("#w-modal-p2").css("font-size","20px");
  	 $("#immedadd").ajaxSubmit({
            type: 'post', 
            url: '<%=basePath%>servlet/SynchStaffServlet?type=2',
            success: function(data) { 
                if(data==1){
  					$("#w-modal-p2").html("文件有错，请审核后提交");
  					$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 2){
                	$("#w-modal-p2").html("文件上传失败");
                	$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 3){
                	$("#w-modal-p2").html("表格中没有数据，请审核后提交");
                	$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 4){
                	$("#w-modal-p2").html("有员工工号已经重复，请查核后提交");
                	$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 5){
                	$("#w-modal-p2").html("同步成功");
                	$("#w-modal-p2").css("color","blue");
                	$("#w-modal-p2").css("font-size","20px");
                	$("#w-modal-but").attr("disabled",true);
                }
                else if(data == 6){
                	$("#w-modal-p2").html("文件中有未填项，请审核后提交");
                	$("#w-modal-p2").css("color","red");
                	$("#w-modal-p2").css("font-size","20px");
                	$("#w-modal-but").attr("disabled",true);
                }
                else if(data == 7){
                	$("#w-modal-p2").html("有员工工号和定时同步计划相同，请审核后提交");
                	$("#w-modal-p2").css("color","red");
                	$("#w-modal-p2").css("font-size","20px");
                	$("#w-modal-but").attr("disabled",true);
                }
                
                
            }
           // $(this).resetForm(); // 提交后重置表单
        });
        pagination(${staff_page});
  }
  
  function time_manual(){
  	$("#w-modal-but").attr("disabled",true);
  	$("#w-modal-but").html("同步");
  	$("#w-modal-p3").html("");
  	$("#w-modal-p2").html("");
  	var content='<form id="immedadd"><div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工工号：</label><input type="text" class="form-control" id="staff_number"  name = "staff_number" placeholder="员工工号" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工姓名：</label><input type="text" class="form-control" name = "staff_name" placeholder="员工姓名" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工部门：</label><input type="text" class="form-control" name = "staff_department" placeholder="员工部门" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工组别：</label><input type="text" class="form-control" name = "staff_group" placeholder="员工组别" style="width: 300px"></div>'
  				+'<div class="form-group form-inline"><label for="exampleInputName2" style="font-size: 15px">员工地址：</label><input type="text" class="form-control" name = "staff_address" placeholder="员工地址" style="width: 300px"></div>'
  				+'<input name = "type" value="3" style="display:none;"></form>';
  $("#w-modal-div").html(content);
  $(function(){
  $(".form-control").blur(function(){
  		if($(this).val()==""){	
  			$("#w-modal-p2").html("请输入完整内容");
  			$("#w-modal-p2").css("color","red");
  			$("#w-modal-p2").css("font-size","20px");
  		}
  		else{
  			$("#w-modal-p2").html("");
  		}
  		if($(this).attr("id")=="staff_number" && $(this).val()!=""){
  			$.ajax({ 
				type:"post",
				url: "<%=basePath%>servlet/SynchStaffServlet", 
				data:{
								type : "0",
								number :$(this).val() ,			
					},
				error: function(request) {
		          alert('修改失败，请重新修改');
		         },
				success: function(request){
				if(request == 1){
					$("#w-modal-p3").html("员工工号已有，请详细审核信息");
  					$("#w-modal-p3").css("color","red");
  					$("#w-modal-p3").css("font-size","20px");
  					
				}
		        else if(request == 2){
		        	 $("#w-modal-p3").html("员工工号可用");
  					$("#w-modal-p3").css("color","blue");
  					$("#w-modal-p3").css("font-size","20px");
  					
		        }
			    
		      }});
  		}
  		var flag = 0;
  		$(".form-control").each(function() {
  		
  		if($(this).val()=="")
  			flag = 1;
  		});
  		if(flag == 0 &&  $("#w-modal-p3").html()=="员工工号可用"){
  			$("#w-modal-but").attr("disabled",false);
  			$("#w-modal-but").attr("onclick","javascript:addone_time();");
  		}			
		});
	});
  }
  
  function addone_time(){
  	$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/SynchStaffServlet", 
		data:$('#immedadd').serialize(), 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '同步失败，请重新同步';
         },
		success: function(request){
		if(request == 1){
			$("#w-modal-p2").html('记录已上传,到达同步时间即可同步') ;
			$("#w-modal-p2").css("color","blue"); 
			$("#w-modal-but").attr("disabled",true); 	
		}
        else{
        	document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
        }
	    
      }});

  }
  
  function time_excel(){
  	//$("#w-modal-but").attr("disabled",true);
  	$("#w-modal-but").html("同步");
  	$("#w-modal-p3").html("");
  	$("#w-modal-p2").html("");
  	$("#w-modal-but").attr("onclick","");	
  	var content='<form id="immedadd" enctype="multipart/form-data"> <div class="form-group"> <label for="exampleInputFile">File input</label><input id="upload" type="file" name="staff_excel"><p class="help-block">选择您想导入的EXCEL文件</p>'
  				+'<a href="/MVS/download/2014112155.xlsx">模板下载</a></div>'
  				+'<input name = "type" value="2" style="display:none;"></form>';
  	$("#w-modal-div").html(content);
  	$("#w-modal-but").attr("onclick","javascript:addexcel_time();");	
  	 
  }
  $("#w-modal-but").click(function(){
  	 	if($("#upload").val()==""){
  	 	$("#w-modal-p2").html("请输入文件");
  		$("#w-modal-p2").css("color","red");
  		$("#w-modal-p2").css("font-size","20px");
  		return false;
  	 	}
  	 	else{
  	 		$("#w-modal-p2").html("");
  	 	}
  	 		
  	 });

  function addexcel_time(){
  	 $("#immedadd").ajaxSubmit({
            type: 'post', 
            url: '<%=basePath%>servlet/SynchStaffServlet?type=4',
            success: function(data) { 
                if(data==1){
  					$("#w-modal-p2").html("文件有错，请审核后提交");
  					$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 2){
                	$("#w-modal-p2").html("文件上传失败");
                	$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 3){
                	$("#w-modal-p2").html("表格中没有数据，请审核后提交");
                	$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 4){
                	$("#w-modal-p2").html("有员工工号已经重复，请查核后提交");
                	$("#w-modal-p2").css("color","red");
  					$("#w-modal-p2").css("font-size","20px");
                }
                else if(data == 5){
                	$("#w-modal-p2").html("记录已上传,到达同步时间即可同步");
                	$("#w-modal-p2").css("color","blue");
                	$("#w-modal-p2").css("font-size","20px");
                	$("#w-modal-but").attr("disabled",true);
                }
                else if(data == 6){
                	$("#w-modal-p2").html("文件中有未填项，请审核后提交");
                	$("#w-modal-p2").css("color","red");
                	$("#w-modal-p2").css("font-size","20px");
                	$("#w-modal-but").attr("disabled",true);
                }
                else if(data == 7){
                	$("#w-modal-p2").html("有员工工号和定时同步计划相同，请审核后提交");
                	$("#w-modal-p2").css("color","red");
                	$("#w-modal-p2").css("font-size","20px");
                	$("#w-modal-but").attr("disabled",true);
                }
                
            }
           // $(this).resetForm(); // 提交后重置表单
        });
  }
  
  
  function change(){
  	$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/SynchStaffServlet", 
		data:$('#immedadd').serialize(), 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '同步失败，请重新同步';
         },
		success: function(request){
		if(request == 1){
			$("#w-modal-p2").html('周期已更改，详情请看介绍') ;
			$("#w-modal-p2").css("color","blue"); 
			$("#w-modal-but").attr("disabled",true); 	
		}
        else{
        	document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
        }
	    
      }});
      pagination(${staff_page});
  }
 
  </script>
</html>
