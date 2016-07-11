<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>Frame</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body class='contrast-blue fixed-navigation'>
	<!-- 通过这个引入导航栏和资源文件 -->
	<%@ include file="navabar.jsp" %>
	<div id='wrapper'>
		<div id='main-nav-bg'></div>
		<nav class='main-nav-fixed' id='main-nav'>
			<div class='navigation'>

				<ul class='nav nav-stacked'>
					<li class=''><a href='index.html'> <i
							class='icon-dashboard'></i> <span>主页</span>
					</a></li>
					<li class=''><a class='dropdown-collapse' href='#'> <i
							class='icon-edit'></i> <span>Forms</span> <i
							class='icon-angle-down angle-down'></i>
					</a>
						<ul class='nav nav-stacked'>
							<li class=''><a href='form_styles.html'> <i
									class='icon-caret-right'></i> <span>Form styles and
										features</span>
							</a></li>
							<li class=''><a href='form_components.html'> <i
									class='icon-caret-right'></i> <span>Form components</span>
							</a></li>
							<li class=''><a href='validations.html'> <i
									class='icon-caret-right'></i> <span>Validations</span>
							</a></li>
							<li class=''><a href='wizard.html'> <i
									class='icon-caret-right'></i> <span>Wizard</span>
							</a></li>
						</ul></li>

					<li class=''><a id="test_load" href='javascript:;'> <i
							class='icon-star'></i> <span>异步加载</span>
					</a></li>
					<li class='active'><a href='index2.jsp'> <i
							class='icon-table'></i> <span>跳转</span>
					</a></li>
				</ul>
			</div>
		</nav>
		<section id='content'>
			<!-- 自定义内容 -->
			<div id="mycontent"
				style="width: 100%;height:100%;margin:5px;background-color: #ccc;">content</div>
		</section>
	</div>

	<script type="text/javascript">
		$("#test_load").click(function() {
			$("#mycontent").load("index.jsp");
		});
	</script>
</body>
</html>

