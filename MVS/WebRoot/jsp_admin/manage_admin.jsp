<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'create_admin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="scripts/jquery.js"></script>
<script src="scripts/bootstrap.min.js"></script>
</head>

<body style = "text-align: center;">
	<h1>管理角色</h1>
	<c:if test="${admin_map != null }">
		<div style="text-align: center;margin-right: auto;margin-left: auto;">
			<table class="table table-hover table-bordered"
				style="width:98%;margin-right: auto;margin-left: auto;color:#000">
				<thead>
					<tr>
						<th>#</th>
						<th>序号</th>
						<th>角色名称</th>
						<th>角色权限</th>
						<th>查看详情</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${admin_map}" begin="1" var="admin"
							varStatus="status">
							<tr>
								<td><input type="checkbox" /></td>
								<td class="row_index">${status.index}</td>
								<td>${admin.key}</td>
								<td>${admin.value}</td>
								<td><a class="admin_detail" href="javascript:;">查看详情</a></td>
								<td><a class="admin_modify" href="javascript:;">修改</a></td>
								<td><a class="admin_delete" href="javascript:;">删除</a></td>
							</tr>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</c:if>
	<!-- 异步分页 -->
	<nav>
	<ul class="pagination" style="magin:0 auto !important; ">
		<li><a id="page_pre" href="javascript:;" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
		</a></li>
		<c:forEach begin="1" end="${admin_total}" varStatus="status">
			<c:choose>
				<c:when test="${adminStartPage == status.index}">
					<li class="active admin-page"><a href="javascript:;">${status.index}</a></li>
				</c:when>
				<c:otherwise>
					<li class="admin-page"><a href="javascript:;">${status.index}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li><a id="page_next" href="javascript:;" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
	</nav>
	<!-- 详情的模态框 -->
	<div class="modal fade" id="model_detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">角色权限详情</h4>
				</div>
				<div class="modal-body">

					<div id="show_name" class="alert alert-success" role="alert">powers</div>
					<div class="panel panel-default">
						<div id="show_power" class="panel-body">...</div>
					</div>
					<div class="panel panel-default">
						<div id="show_user" class="panel-body">共有几个用户为此角色</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 修改的模态框 -->
	<div class="modal fade" id="model_modify">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">角色修改</h4>
				</div>
				<div class="modal-body">

					<div id="result" class="alert alert-success" role="alert"
						style="display:none;">很好，您已成功修改了一个角色</div>

					<!-- 第一个页面获取角色名称 -->
					<div id="page1" class="mypage first">
						<div id="judgeAdminName" class="alert alert-danger" role="alert"
							style="display:none;">此角色名已存在</div>
						<div class="input-group input-group-lg">
							<span class="input-group-addon" id="sizing-addon1">请输入角色名称：</span>
							<input id="admintext" type="text" class="form-control"
								placeholder="name" aria-describedby="sizing-addon1">
						</div>
						<button type="button" class="btn btn-default" id="btn_next1">下一步</button>
					</div>

					<!-- 第二个页面选择权限 -->
					<div id="page2" class="mypage " style="display: none;">
						<button id="btn_select_all" type="button" class="btn btn-default">全选</button>

						<p class="bg-success text-primary">人员管理</p>
						<button type="button" class="btn btn-default btn-sm">增加人员</button>
						<button type="button" class="btn btn-default btn-sm">删除人员</button>
						<button type="button" class="btn btn-default btn-sm">修改人员</button>
						<button type="button" class="btn btn-default btn-sm">查询人员</button>

						<p class="bg-info text-primary">线路管理</p>
						<button type="button" class="btn btn-default btn-sm">增加线路</button>
						<button type="button" class="btn btn-default btn-sm">删除线路</button>
						<button type="button" class="btn btn-default btn-sm">修改线路</button>
						<button type="button" class="btn btn-default btn-sm">查询线路</button>

						<p class="bg-success text-primary">排班管理</p>
						<button type="button" class="btn btn-default btn-sm">增加班次</button>
						<button type="button" class="btn btn-default btn-sm">删除班次</button>
						<button type="button" class="btn btn-default btn-sm">修改班次</button>
						<button type="button" class="btn btn-default btn-sm">查询班次</button>
						<button type="button" class="btn btn-default btn-sm">班次数据导出</button>

						<p class="bg-info text-primary">站点管理</p>
						<button type="button" class="btn btn-default btn-sm">增加站点</button>
						<button type="button" class="btn btn-default btn-sm">删除站点</button>
						<button type="button" class="btn btn-default btn-sm">修改站点</button>
						<button type="button" class="btn btn-default btn-sm">查询站点</button>
						<button type="button" class="btn btn-default btn-sm">站点数据导出</button>

						<p class="bg-success text-primary">车辆管理</p>
						<button type="button" class="btn btn-default btn-sm">新车入厂</button>
						<button type="button" class="btn btn-default btn-sm">删除车辆</button>
						<button type="button" class="btn btn-default btn-sm">更新车辆信息</button>
						<button type="button" class="btn btn-default btn-sm">查询车辆信息</button>
						<button type="button" class="btn btn-default btn-sm">车辆数据导出</button>

						<p class="text-primary">
							<button type="button" class="btn btn-default" id="btn_pre2">上一步</button>
							<button type="button" class="btn btn-default" id="btn_next2">下一步</button>
						</p>
					</div>

					<!-- 第三个页面显示此角色的信息和权限 -->
					<div id="page3" class="mypage " style="display: none;">
						<div class="alert alert-info" role="alert">下面是您修改后的角色信息，若有错误，请返回上一步修改！否则请点击完成</div>

						<div id="show_name_m" class="alert alert-success" role="alert"></div>
						<div id="show_power_m" class="alert alert-success" role="alert"></div>
						<button id="btn_pre3" type="button" class="btn btn-default">上一步</button>
						<button id="btn_finish" type="button" class="btn btn-default">完成</button>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 删除的提示模态框 -->
	<div class="modal fade" id="modal_delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">删除角色</h3>
				</div>
				<div class="modal-body">
					<h4 id="delete_info" style="color:red;">
						严重警告：<br />您将删除此角色，并且角色下面的所有用户都将不存在，任继续吗？
					</h4>
				</div>
				<div class="modal-footer">
					<button id="btn_delete" type="button" class="btn btn-danger">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<script type="text/javascript">
		$(function() {
			//查看详情点击事件
			$(".admin_detail").click(function() {
				//取得此行的角色名称
				var adminname = $(this).parent().prev().prev().text();
				$(this).attr("data-toggle", "modal");
				$(this).attr("data-target", "#model_detail");
				//异步获取内容
				$.ajax({
					url : "servlet/ManageAdminServlet2",
					type : "POST",
					data : {
						type : "1",
						adminName : adminname
					},
					success : function(re) {
						if ("no" != re) {
							$("#show_name").text(adminname);
							$("#show_power").text(re);
						} else {
						}
					}
				});
			});

			//下面是对角色的修改
			var lastModify = "-1";//看是否是与上一次相同的修改
			var initialName;//原始角色名称
			var powerIds = "";
			$(".admin_modify").click(
					function() {

						var nowModify = $(this).parent().prev().prev().prev()
								.prev().text();
						if (nowModify != lastModify) {
							lastModify = nowModify;
							$("#page2").hide();
							$("#page3").hide();
							$("#page1").show();
						}
						//取得此行的角色名称
						var adminname = $(this).parent().prev().prev().prev()
								.text();
						initialName = adminname;
						$("#admintext").val(adminname);

						$(this).attr("data-toggle", "modal");
						$(this).attr("data-target", "#model_modify");

			});

			//控制页面的切换
			$("#btn_next1").click(function() {
				$("#page1").hide("1000");

				//需要异步获取到此角色原有的权限
				$.ajax({
					url : "servlet/ManageAdminServlet2",
					type : "POST",
					data : {
						type : "2",
						adminName : initialName
					},
					success : function(re) {
						if ("no" != re) {
							//返回的是"1,2,3..."
							var ids = re.split(",");
							for (var i = 0; i < ids.length; i++) {
								$(".btn-sm").each(function(index) {
									if (index == ids[i]) {
										$(this).addClass("btn-success");
									}
								});
							}
						} else {

						}
					}
				});
				$("#page2").show();
			});

			$("#btn_next2").click(function() {
				$("#page2").hide();
				$("#page3").show();

				//加载数据到第三页
				$("#show_name_m").text("角色名称：" + $("#admintext").val());

				var powers = "<h2>拥有的权限：</h2><br/>";
				var cnt = 0;
				$(".btn-sm").each(function(index) {
					if ($(this).hasClass("btn-success")) {
						powerIds += index + ",";
						powers += ($(this).text() + "  &nbsp;&nbsp");
						cnt++;

						if (cnt % 4 == 0) {
							powers += "<br/>";
						}
					}
				});
				if (0 == cnt) {
					$("#show_power_m").text("此角色难道没有权限？抱歉，您不能完成此次角色修改");
					$("#btn_finish").attr("disabled", true);
				} else {
					$("#btn_finish").attr("disabled", false);
					$("#show_power_m").html(powers);
					//去掉最后的一个逗号
					powerIds = powerIds.substr(0, powerIds.length - 1);
				}
			});

			$("#btn_pre2").click(function() {
				$("#page2").hide();
				$("#page1").show();
			});

			$("#btn_pre3").click(function() {
				$("#page3").hide();
				$("#page2").show();

				powerIds = "";//必须清空
			});

			//当文本框文字改变监听
			$("#admintext").keyup(function() {
				if ($(this).val() == "") {
					$("#judgeAdminName").hide();
					$("#btn_next1").attr("disabled", true);
				} else {
					//异步检测名字是否重复
					adminname = $(this).val();
					$.ajax({
						url : "servlet/CreateAdminServlet",
						type : "POST",
						data : {
							type : "1",
							adminName : adminname
						},
						success : function(re) {
							//修改时可以用原名字
							if (re == "yes" || re == initialName) {
								$("#judgeAdminName").hide();
								$("#btn_next1").attr("disabled", false);
							} else {
								$("#judgeAdminName").show();
								$("#btn_next1").attr("disabled", true);
							}
						}
					});
				}
			});

			//若选中此权限按钮，按钮变为绿色
			$(".btn-sm").click(function() {
				$(this).toggleClass("btn-success");
			});

			//全选的操作
			var t = true;
			$("#btn_select_all").click(function() {
				if (t) {
					$(".btn-sm").each(function() {
						$(this).addClass("btn-success");
					})
					$("#btn_select_all").text("取消全选");
					t = false;
				} else {
					$(".btn-sm").each(function() {
						$(this).removeClass("btn-success");
					})
					$("#btn_select_all").text("全选");
					t = true;
				}
			});

			//点击完成后更新角色数据到数据库
			$("#btn_finish").click(function() {
				adminname = $("#admintext").val();
				$.ajax({
					url : "servlet/ManageAdminServlet2",
					type : "POST",
					data : {
						type : "3",
						adminName : adminname,
						initName : initialName,
						powerId : powerIds
					},
					success : function(re) {
						$("#result").show();
						if ("no" != re) {
							$("#page3").hide("1000");
							var ss = re.split(",");
							$(".row_index").each(function(index) {
								if (lastModify == index) {
									$(this).next().text(ss[0]);
									$(this).next().next().text(ss[1]);
								}
							});
						} else {
							$("#result").removeClass("alert-success");
							$("#result").addClass("alert-danger");
							$("#result").text("抱歉，未知原因修改失败");
						}
					}
				});
			});

			//下面是对角色的删除
			$(".admin_delete").click(
					function() {
						//取得此行的角色名称
						var adminname = $(this).parent().prev().prev().prev()
								.prev().text();
						$(this).attr("data-toggle", "modal");
						$(this).attr("data-target", "#modal_delete");
						var thisDelete = this;

						$("#btn_delete").click(
								function() {
									$(this).attr("disabled", true);
									$.ajax({
										url : "servlet/ManageAdminServlet2",
										type : "POST",
										data : {
											type : "4",
											adminName : adminname,
										},
										success : function(re) {
											//$("#btn_delete").attr("disabled", false);
											if ("no" != re) {
												$("#delete_info").text(
														"删除成功，共删除了此角色下的" + re
																+ "个用户");
												//在页面上移出表格的这一行
												$(thisDelete).parent().parent()
														.remove();
											} else {
												$("#delete_info").text(
														"未知原因删除失败");
											}
										},
										error : function() {
											$(this).attr("disabled", false);
										}
								});
						});
			});
			//分页控制
			$(".admin-page a").click(function(){
				var pageNow = $(this).text();
				$("#content").load("<%=path%>/servlet/ManageAdminServlet?adminStartPage="
										+ pageNow);
			});
			$("#page_pre").click(function(){
				var pageNow = parseInt(${adminStartPage});
				var pagePre = pageNow;
				if(pageNow>1){
					pagePre = pageNow-1;
				}else{
					return ;
				}
				$("#content").load("<%=path%>/servlet/ManageAdminServlet?adminStartPage="
										+ pagePre);
			});
			
			$("#page_next").click(function(){
				var pageNow = parseInt(${adminStartPage});
				var pageNext = pageNow;
				if(pageNow<parseInt(${admin_total})){
					pageNext = pageNow+1;
				}else{
					return ;
				}
				$("#content").load("<%=path%>/servlet/ManageAdminServlet?adminStartPage="
										+ pageNext);});
		});
	</script>
</body>
</html>
