<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
		if (null == session.getAttribute("user")) {
		response.sendRedirect(path+"/jsp_admin/sadmin.jsp");
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'backup_data.jsp' starting page</title>

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
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-5">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">备份数据库</h3>
					</div>
					<div class="panel-body">
						<c:choose>
							<c:when test="${lastExTime!=null}">
						<b id="ex_date">您上次备份时间是：${lastExTime}</b>
							</c:when>
							<c:otherwise>
								<b id="ex_date" class="never">您从未进行备份</b>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<button id="ex_btn" type="button"
					class="col-md-12 btn btn-primary btn-lg">备份数据</button>
				<div style="margin-top:10px;display:none;" id="ex_process"
					class="progress col-md-12">
					<div class="progress-bar ex_value" role="progressbar"
						aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						style="width: 0%;">0%</div>
				</div>
				<div style="display:none;margin-top:10px;"
					class="alert alert-success ex_msg col-md-12" role="alert">您已成功备份</div>
			</div>
			<div class="col-md-5">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">恢复数据库</h3>
					</div>
					<div class="panel-body">
						<c:choose>
							<c:when test="${lastIxTime!=null}">
						<b>您上次恢复数据时间是：</b><b id="ix_date">${lastIxTime}</b>
							</c:when>
							<c:otherwise>
								<b id="ix_date" class="never">您从未进行恢复</b>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<c:choose>
					<c:when test="${lastExTime!=null}">
						<button id="ix_btn" type="button"
					class="col-md-12 btn btn-primary btn-lg">恢复数据</button>
					</c:when>
					<c:otherwise>
				<button id="ix_btn" type="button"disabled="true"
					class="col-md-12 btn btn-primary btn-lg">恢复数据</button>
					</c:otherwise>
				</c:choose>
				<div style="margin-top:10px;display:none;" id="ix_process"
					class="progress col-md-12">
					<div class="progress-bar ix_value" role="progressbar"
						aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						style="width: 0%;">0%</div>
				</div>
				<div style="display:none;margin-top:10px;"
					class="alert alert-success ix_msg col-md-12" role="alert">您已成功恢复数据</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		//获取时间
		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var seperator2 = ":";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = date.getFullYear() + seperator1 + month
					+ seperator1 + strDate + " " + date.getHours() + seperator2
					+ date.getMinutes() + seperator2 + date.getSeconds();
			return currentdate;
		}
		var change = true;
		$("#ex_btn").click(function() {
			if (change) {
				$("#ex_process").slideDown("1000");
				$.ajax({
					url : "servlet/BackupDataServlet",
					type : "POST",
					data : {
						type : "1"
					},
					success : function(re) {
						myre = re;
						if (re == "yes") {
							var pro = 0;
							var id = setInterval(function() {
								pro += 20;
								$(".ex_value").text(pro + "%");
								$(".ex_value").css("width", pro + "%");
								if (pro >= 100) {
									clearInterval(id);
									$(".ex_msg").text("您已成功备份");
									$(".ex_msg").slideDown("2000");
									$("#ex_process").slideUp("1000");
									//更新备份时间
									$("#ex_date").text("您上次备份时间是："+getNowFormatDate());
									change = false;
									$("#ix_btn").attr("disabled",false);
								}
							}, 100);
						}
					},
					error : function() {
					}
				});
			} else {
				$(".ex_msg").text("您刚才才备份过");
				$(".ex_msg").slideDown("1000");
			}
		});
		var ichange = true;
		$("#ix_btn").click(function() {
			if (ichange) {
				$("#ix_process").slideDown("1000");
				$.ajax({
					url : "servlet/BackupDataServlet",
					type : "POST",
					data : {
						type : "2"
					},
					success : function(re) {
						myre = re;
						if (re == "yes") {
							var pro = 0;
							var id = setInterval(function() {
								pro += 20;
								$(".ix_value").text(pro + "%");
								$(".ix_value").css("width", pro + "%");
								if (pro >= 100) {
									clearInterval(id);
									$(".ix_msg").text("您已成功恢复数据");
									$(".ix_msg").slideDown("2000");
									$("#ix_process").slideUp("1000");
									//更新备份时间
									$("#ix_date").text(getNowFormatDate());
									ichange = false;
								}
							}, 100);
						}
					},
					error : function() {
					}
				});
			} else {
				$(".ix_msg").text("您刚才才恢复了");
				$(".ix_msg").slideDown("1000");
			}
		});
	</script>
</body>
</html>
