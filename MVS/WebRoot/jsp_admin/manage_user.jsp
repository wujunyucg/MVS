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

var tr;
 $(function(){
 $("#usertab td").click(function() {
               tr=$(this).parent().attr("id");
            });
     });
     
function  layer1(number,password,admin,type ){
	  var tab='<table class="table table-hover table-bordered" style="width:100%;">'
	  +'<tr><td class="td1">管理员账号</td><td>'+number+'</td></tr>'
	  +'<tr><td class="td1">管理员密码</td><td>'+password+'</td></tr>'
	  +'<tr><td class="td1">管理员角色</td><td>'+admin+'</td></tr>'
	  +'<tr><td class="td1">管理员类型</td><td>'+type+'</td></tr>'
	  +'<table>';
	   document.getElementById("p1"). innerHTML = '查看详情';
	  document.getElementById("modalDiv"). innerHTML = tab;
	  document.getElementById("modalBut"). style.display="none"; 
}

function  layer2(userid,number,password,admin,type,staus){
 document.getElementById("p2"). innerHTML = '';
 document.getElementById("modalBut"). style.display="inline "; 
  var tab= '<form id="updateuser">'
  	  +'<table class="table table-hover table-bordered" style="width:100%;">'
  	  +'<input type="text" name="userid" value="'+userid+'" style="display:none;"/>'
	  +'<tr><td class="td1">管理员账号</td><td ><input type="text" id="username" name="number" value="'+number+'" /></td></tr>'
	  +'<tr><td class="td1">管理员密码</td><td><input type="text" name="password" value="'+password+'"/></td></tr>'
	  +'<tr><td class="td1">管理员角色</td><td  >'
	  +' <select name="admin" >';
	  <c:forEach items="${user_admin_list}" var="admin1" varStatus="status" >
	  	if(admin != '${admin1.getName()}')
  		 tab=tab+'<option >${admin1.getName()}</option>';
  		 else
   			tab=tab+'<option id="useradmin" selected="selected">${admin1.getName()}</option>';
		</c:forEach>
		tab=tab+'</select>'
	  +'</td></tr>'
	   +'<tr><td class="td1">管理员类型</td><td>'+type+'</td></tr>'
	  +'<input type="text" name="type" value="'+type+'" style="display:none;"/>'
	  +'<input type="text" name="staus" value="'+staus+'" style="display:none;"/>'
	  +'<table> </form>';
	 
	   document.getElementById("p1"). innerHTML = '修改';
      document.getElementById("modalDiv"). innerHTML = tab;
     // alert($("#useradmin").html())
}
function update(){
	$.ajax({ 
		type:"post",
		url: "<%=basePath%>/servlet/UpdateUserServlet", 
		data:$('#updateuser').serialize(), 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
		if(request == 1){
			document.getElementById("p2"). innerHTML = '修改成功';
			var name =$("#username").val();
			var ad= $("#useradmin").html();	
	   	 	$("#"+tr+"0").html(name);		
           	$("#"+tr+'1').html(ad);
           	
		}
        else{
        	document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
        }
	    
      }});
}
</script>
  </head>
  
  <body style = "text-align: center;">
  

    <h1>管理用户</h1>
     <c:if test="${user_list != null }">
    <div style="text-align: center;margin-right: auto;margin-left: auto;"> 
    <table id ="usertab" class="table table-hover table-bordered" style="width:98%;margin-right: auto;margin-left: auto;color:#000">
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
	       <c:forEach items="${user_list}" var="user" varStatus="status" >
          <tr id="tr${status.index}">
            <td></td>
            <td >${status.index}</td>
            <td id="tr${status.index}0">${user.getNumber()}</td>
             <c:forEach items="${user_admin_list}" var="admin" varStatus="status1" >
           <c:if test="${user.getAdminId() == admin.getAdminId() }"> <td id="tr${status.index}1"> ${admin.getName()}</td>
            <td><a data-toggle="modal"  data-target="#myModal" onclick="javascript:layer1('${user.getNumber()}','${user.getPassword()}', '${admin.getName()}','${user.getType()}')">查看详情</a></td>
            <td><a data-toggle="modal"  data-target="#myModal" onclick="javascript:layer2('${user.getUserId()}','${user.getNumber()}','${user.getPassword()}','${admin.getName()}','${user.getType()}',${status.index})">修改</a></td>
           </c:if>
            </c:forEach>
            <td><a >删除</a></td>
          </tr>
           </c:forEach>
	    </tr>
	  </tbody>
	</table>
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
  
  <script src="scripts/jquery.min.js"></script>
  <script src="scripts/bootstrap.min.js"></script>
 
</html>
