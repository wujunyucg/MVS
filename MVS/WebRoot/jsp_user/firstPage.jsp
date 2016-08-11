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
	<div style="height:145px;margin-left:-10px;background-image: url(icons/firpage${backPicture }.jpg);background-size:100%;">
		<br/><br/><br/>
		<h3 style="color:#ffffff;font-weight:bold;">&nbsp;&nbsp;欢迎进入咸鱼的梦想厂车管理后台</h3>
		<h4 style="color:#ffffff;">&nbsp;&nbsp;&nbsp;Welcome Administrator</h4>
	</div>
	
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
  			<ul class="list-group">
			  <li class="list-group-item">
			  	<div style="width:210px;float:left;"><p style="font-weight:bold;float:left;">员工数据现有数量： </p>${totalNumber[0] }</div>
			  	<div style="width:120px;float:left;"><p style="font-weight:bold;float:left;">所占总数据比重：</p></div>
			  	<div class="progress" style="margin-bottom: 0px;">
			  		<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: ${totalRate[0]}">
			  		</div>
				</div>
			  </li>
			  <li class="list-group-item">
			  	<div style="width:210px;float:left;"><p style="font-weight:bold;float:left;">车辆数据现有数量： </p>${totalNumber[1] }</div>
			  	<div style="width:120px;float:left;"><p style="font-weight:bold;float:left;">所占总数据比重：</p></div>
			  	<div class="progress" style="margin-bottom: 0px;">
			  		<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: ${totalRate[1]}">
			  		</div>
				</div>
			 </li>
			  <li class="list-group-item">
			  	<div style="width:210px;float:left;"><p style="font-weight:bold;float:left;">站点数据现有数量： </p>${totalNumber[2] }</div>
			  	<div style="width:120px;float:left;"><p style="font-weight:bold;float:left;">所占总数据比重：</p></div>
			  	<div class="progress" style="margin-bottom: 0px;">
			  		<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: ${totalRate[2]}">
			  		</div>
				</div>
				</li>
			  <li class="list-group-item">
			  	<div style="width:210px;float:left;"><p style="font-weight:bold;float:left;">线路数据现有数量： </p>${totalNumber[3] }</div>
			  	<div style="width:120px;float:left;"><p style="font-weight:bold;float:left;">所占总数据比重：</p></div>
			  	<div class="progress" style="margin-bottom: 0px;">
			  		<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: ${totalRate[3]}">
			  		</div>
				</div>
				</li>
			  <li class="list-group-item">
			  	<div style="width:210px;float:left;"><p style="font-weight:bold;float:left;">排班数据现有数量： </p>${totalNumber[4] }</div>
			  	<div style="width:120px;float:left;"><p style="font-weight:bold;float:left;">所占总数据比重：</p></div>
			  	<div class="progress" style="margin-bottom: 0px;">
			  		<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: ${totalRate[4]}">
			  		</div>
				</div>
				</li>
			</ul>
  		</div>
  		
  		
  		<div class="panel panel-default" style="margin-left:-10px;">
  			<div class="panel-heading">基本操作流程</div>
  				<div class="alert alert-info" role="alert" style="margin-top:5px;">下图为系统的基本操作流程，详细操作请点击右上角help查询。</div>
  				<p style="text-align:center;"><img src="icons/operapro.png" style="width:60%;"></p>
  		</div>
 		
	<script language=JavaScript>
	
		
	
	
		document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
		setInterval("document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
	</script>

  </body>
</html>
