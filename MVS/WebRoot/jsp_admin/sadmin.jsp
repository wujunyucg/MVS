<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//不能直接通过链接访问
	if (null == session.getAttribute("user")) {
		response.sendRedirect(path+"/login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>car</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<link rel="stylesheet" type="text/css" href="css/j-css/j-theme.css">

<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
<script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
<script type="text/javascript" language="javascript">
$.ajaxSetup ({ 
    cache: false //关闭AJAX相应的缓存 
}); 
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top"  id="upHAInfor" style="margin-left:230px;height:60px;">
	<div>
		 <a id="j_nav_toggle" href="javascript:;" type="button" > <img alt="菜单栏搜索" src="images/caidan.png"
			style="float:left;width:40px;">
		</a> </div>
		<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=path%>/jsp_admin/admin_help.jsp" target="_blank">Help</a></li>
			<li class="dropdown dropdown-toggle "><a href="#"
				class="dropdown-toggle" id="dropdownMenu_user"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					咸鱼的梦想 <span class="caret"></span>&nbsp;&nbsp;
			</a>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu_user">
					<li><a href="javascript:;" id="a_person_info">设置</a></li>
					<li role="separator" class="divider"></li>
					<li><a id="fin_exit"href="javascript:;">退出</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>


		<div id="j-left-menu">
			<div class="upPowerName">
				<ul class="">
					<li><a class="supManBac" style="text-decoration: none;">
					<span class="glyphicon glyphicon-unchecked" aria-hidden="true"></span><span class="opMenu">超级管理员后台</span></a></li>
				</ul>
			</div>
			<div class="list-group">
				<button type="button"
					class="list-group-item btn-menu btn_text main-page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span class="opMenu">主页</span>
				</button>
				<button type="button"
					class="list-group-item btn-menu btn_text create-admin">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
					<span class="opMenu">创建角色</span>
				</button>
				<button type="button" style="display:none;"
					class="list-group-item btn-menu btn_icon create-admin">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text create-user">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
					<span class="opMenu">创建用户</span>
				</button>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon create-user">
					<span class="glyphicon glyphicon-magnet" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
					<span class="opMenu">管理角色</span>
				</button>
				<button id="manage-admin" type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-admin">
					<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<span class="opMenu">管理用户</span>
				</button>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon manage-user">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
				</button>
				<button type="button"
					class="list-group-item  btn-menu btn_text data-export">
					<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
					<span class="opMenu">数据备份</span>
				</button>
				<button type="button" style="display:none;"
					class="list-group-item  btn-menu btn_icon data-export">
					<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
				</button>
			</div>
		</div>
	
	<div id="main">
		<div id="content"></div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			$("#content").load("<%=path%>/servlet/AFirstPageServlet");
			var cnt = -1;//表示页面的编号
			$(".create-admin").click(function(){
				cnt = 0;
				$("#content").load("jsp_admin/create_admin.jsp");
			});
			
			$(".create-user").click(function(){
				cnt=1;
				$("#content").load("<%=request.getContextPath()%>/servlet/CreateUserServlet");
			});
			
			$(".manage-admin").click(function(){
				cnt=2;
				$("#content").load("<%=path%>/servlet/ManageAdminServlet");
			});
			
			$(".manage-user").click(function(){
				cnt=3;
				$("#content").load("<%=basePath%>/servlet/ManageUserServlet?random" + Math.random());
			});
			
			$(".data-export").click(function(){
				cnt=4;
				$("#content").load("<%=path%>/servlet/BackupDataServlet");
			});
			
			/*点击个人信息*/
		$("#a_person_info").click(function(){
			$("#content").load("<%=path%>/person_info.jsp");
		});
		
		
		var turn = true;
		var judgeCJNT = false;
		var openMenus = new Array();//存放展开的子menu的div
		$("#j_nav_toggle").click(function(){
			if(turn){
				openMenus = [];//clear

				$(".opMenu").hide("500");
				$("#j-left-menu").css("width","58px");
				$("#content").css("marginLeft","68px");
				$("#upHAInfor").css("marginLeft","58px");
				
				turn = false;
			}else{
				for(var i=0;i<openMenus.length;i++){
					openMenus[i].show();//显示出来
				}
				$("#j-left-menu").css("width","230px");
				$("#content").css("marginLeft","240px");
				$("#upHAInfor").css("marginLeft","230px");

				$(".opMenu").show("500");
				turn = true;
			}
		});
		
		
		$("#j-left-menu").mouseenter(function(){
			if(!turn){
				$("#j_nav_toggle").click();
				judgeCJNT = true;
			}
		});
		$("#j-left-menu").mouseleave(function(){
			if(judgeCJNT){
				$("#j_nav_toggle").click();
				judgeCJNT = false;
	            }
		});
		
	});
	
	/*主页面加载*/
		$(".main-page").click(function(){
			$("#content").load("<%=path%>/servlet/AFirstPageServlet");
		});
		/*退出*/
		$("#fin_exit").click(function(){
			$.ajax({
				url:"servlet/LoginServlet",
				type:"POST",
				data:{type:"3"},
				async:false,
				success:function(re){
					if(re=="yes"){
						window.location.reload();
					}
				},
				error:function(){
					alert("服务器错误")
				}
			});
		});
	</script>
</body>
</html>
