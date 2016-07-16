<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
  <!-- Nav tabs -->
  <ul class="nav  nav-pills" role="tablist" style="height: 60px;">  
    <li role="presentation" class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
      即时同步 <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
       <li role="presentation" ><a href="" onclick="javascript:immed_manual()" data-toggle="modal"  data-target="#w-modal" aria-controls="" role="tab" >手动输入同步</a></li>
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
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="immed_manual"  style = "text-align: center; ">
	
  </div>

</div>

  </body>
  <script>
  
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
  }
  
  function immed_excel(){
  	//$("#w-modal-but").attr("disabled",true);
  	$("#w-modal-but").html("同步");
  	$("#w-modal-p3").html("");
  	$("#w-modal-p2").html("");
  	$("#w-modal-but").attr("onclick","");	
  	var content='<form id="immedadd" enctype="multipart/form-data"> <div class="form-group"> <label for="exampleInputFile">File input</label><input id="upload" type="file" name="staff_excel"><p class="help-block">选择您想导入的EXCEL文件</p>  </div>'
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
                
                
            }
           // $(this).resetForm(); // 提交后重置表单
        });
       
  }
  </script>
</html>
  