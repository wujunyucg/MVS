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

<title>Manage Arrange</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap-datetimepicker.min.css">
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
</head>
<body>
	<h2>排班数据维护</h2>
	<div style="">
	<div style="float:left;"class="input-group date form_date col-md-2" data-date="年月"
		data-date-format="yyyy-mm">
		<input id="arr_date"class="form-control" size="16" type="text" value="${arr_date}" readonly>
		<span class="input-group-addon"><span
			class="glyphicon glyphicon-calendar"></span></span>
	</div>
	<button id="arr_show_by_month"type="button" class="btn btn-success">按月查看
	</button>
	<button id="arr_show_all"type="button" class="btn btn-success">全部班次
	</button>
	<button type="button" class="btn btn-success">导出</button>
	<button type="button" class="btn btn-success">导入</button>
	<button type="button" class="btn btn-default">删除</button>
	</div>
	<c:if test="${arr_data != null }">
		<div>
			<table class="table table-hover table-bordered"
				style="width:98%;color:#000">
				<thead>
					<tr>
						<th>#</th>
						<th>班次名称</th>
						<th>日期</th>
						<th>发车时间</th>
						<th>线路名称</th>
						<th>车牌号</th>
						<th>司机</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${arr_data}" begin="0" var="arr"
							varStatus="status">
							<tr>
								<td class="arr_id"style="display:none;">${arr.getArrangeId()}</td>
								<td><input type="checkbox" />${status.index}</td>
								<td class="can_change">${arr.getArrName()}</td>
								<td class="can_change">${arr.getDate()}</td>
								<td class="can_change">${arr.getTime()}</td>
								<td class="can_change">${arr.getLineName()}</td>
								<td class="can_change">${arr.getLicensePlate()}</td>
								<td class="can_change">${arr.getDriver()}</td>
								<td><a class="arr_modify" href="javascript:;">修改</a></td>
								<td><a class="arr_delete" href="javascript:;">删除</a></td>
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
		<c:forEach begin="1" end="${arr_total}" varStatus="status">
			<c:choose>
				<c:when test="${arrStartPage == status.index}">
					<li class="active arr-page"><a href="javascript:;">${status.index}</a></li>
				</c:when>
				<c:otherwise>
					<li class="arr-page"><a href="javascript:;">${status.index}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li><a id="page_next" href="javascript:;" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
	</nav>
	<div id="display_month"></div>
	<!-- 提示信息模态框 -->
	<div id="msg_modal"class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        	<b id="modal_content">正在加载，请稍后...</b>
      </div>
    </div>
  </div>
	</div>
	
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
					<h3 class="modal-title">删除班次</h3>
				</div>
				<div class="modal-body">
					<h4 id="delete_info" style="color:red;">
						您将删除此班次，仍继续吗？
					</h4>
				</div>
				<div class="modal-footer">
					<button id="arr_btn_delete" type="button" class="btn btn-danger">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript">
		//分页控制
			$(".arr-page a").click(function(){
				var date = $("#arr_date").val();
				$("#load_modal").modal('show');//显示加载框
				var pageNow = $(this).text();
				$("#content").load("<%=path%>/servlet/ManageArrangeServlet?arrStartPage="
										+ pageNow+"&type="+${arr_type}+"&date="+date);
			});
			$("#page_pre").click(function(){
				var date = $("#arr_date").val();
				var pageNow = parseInt(${arrStartPage});
				var pagePre = pageNow;
				if(pageNow>1){
					$("#load_modal").modal('show');//显示加载框
					pagePre = pageNow-1;
				}else{
					return ;
				}
				$("#content").load("<%=path%>/servlet/ManageArrangeServlet?arrStartPage="
										+ pagePre+"&type="+${arr_type}+"&date="+date);
			});
			
			$("#page_next").click(function(){
				var date = $("#arr_date").val();
				var pageNow = parseInt(${arrStartPage});
				var pageNext = pageNow;
				if(pageNow<parseInt(${arr_total})){
					$("#load_modal").modal('show');//显示加载框
					pageNext = pageNow+1;
				}else{
					return ;
				}
				$("#content").load("<%=path%>/servlet/ManageArrangeServlet?arrStartPage="+
				 pageNext+"&type="+${arr_type}+"&date="+date);
				});
		//日期只选择年月
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm',
			autoclose : true,
			todayBtn : true,
			startView : 'year',
			minView : 'year',
			maxView : 'decade'
		});
		
		//按月查看提交
		$("#arr_show_by_month").click(function(){
			$("#load_modal").modal('show');//显示加载框
			var date = $("#arr_date").val();
			$("#content").load("<%=path%>/servlet/ManageArrangeServlet?arrStartPage=1"+
			"&date="+date+"&type=2");
		});
		//查看所有
		$("#arr_show_all").click(function(){
			$("#load_modal").modal('show');//显示加载框
			$("#content").load("<%=path%>/servlet/ManageArrangeServlet?arrStartPage=1"+
			"&type=1");
		});
		
		//可编辑表格进行修改
		$(".arr_modify").click(function(){
			var take = $(this);
			if(take.text()=="修改"){
				var td = $(this).parent();
				td.siblings(".can_change").each(function(){
					var t = $(this);
					var text = t.text();
				 	// 创建替换的input 对象  
                	var $input = $("<input type='text'>").css("background-color",  
                        t.css("background-color")).width(t.width());
					t.html("");
					$input.val(text);
					t.append($input);
				});
				take.text("保存");
			}else{
				var td = $(this).parent();
				td.siblings(".can_change").each(function(){
					var p = $(this);
					var t = p.children(":input");
					var text = t.val();
					if(""==text){
						
						$("#modal_content").text("有空格，不行");
						alert($("#modal_content").text())
						$("#msg_modal").modal('show');
						return false;
					}
					p.html(text);
				});
				//异步提交修改
				
				take.text("修改");
				//$("#modal_content").text("修改成功");
				//$("#msg_modal").modal('show');
			}
		});
		
		//异步删除行
		var del;
		$(".arr_delete").click(function(){
			//找到待删除行的id表格td
			del = $(this).parent().parent().children().first();
			$("#modal_delete").modal('show');
		});
		var delSuccess = true;
		$("#arr_btn_delete").click(function(){
			if(delSuccess){
			$("#modal_delete").modal('hide');
			$("#modal_content").text("正在删除请稍后......");
			$("#msg_modal").modal('show');
			var arr_id = del.text();
			$.ajax({
				url:"servlet/ModifyArrServlet",
				type:"POST",
				data:{type:"1",arrId:arr_id},
				success:function(re){
					$("#msg_modal").modal('hide');
					$("#modal_delete").modal('show');
					if(re=="yes"){
						$("#delete_info").text("删除成功，点击确定刷新");
						delSuccess = false;
					}else{
						$("#delete_info").text("删除失败，请重试");
					}
				},
				error:function(){
					$("#delete_info").text("服务器内部错误，请重试");
				}
			});
			}else{
				$(".modal-backdrop").hide();
				//刷新操作
				//$("#delete_info").text("删除成功，点击确定刷新");
				var date = $("#arr_date").val();
				$("#load_modal").modal('show');//显示加载框
				$("#content").load("<%=path%>/servlet/ManageArrangeServlet?arrStartPage=${arrStartPage}"+
				"&type="+${arr_type}+"&date="+date);
			}
		});
		
		$("#load_modal").modal('hide');//隐藏加载框
	</script>
</body>
</html>