<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
		response.sendRedirect(path+"/jsp_admin/sadmin.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'site_get_address' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<link rel="stylesheet" type="text/css" href="css/map/layout.css">
<link rel="stylesheet" type="text/css" href="css/j-css/map-theme.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css">
  </head>
  
  <body>
   <div id="load_modal" class="modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<b>正在加载，请稍后...</b>
				</div>
				<div class="modal-footer">
            <button type="button" id="w-modal-close"  class="btn btn-default" 
               data-dismiss="modal" style="display:none" onclick='javascript:window.location.href="servlet/MapSiteServlet";'>确定
            </button>
         </div>
			</div>
		</div>
	</div>
    <script src='scripts/jquery.js'></script>
<script src='scripts/bootstrap.min.js'></script>
	<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/js/marker.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
	<script type="text/javascript" src="js/satation.js"></script>
	<script type="text/javascript" src="js/map2.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<script type="text/javascript" src="js/mapinterface.js"></script>
	<script type="text/javascript" src="js/testroute.js"></script>
	<script>
	var num=0;
	$(window).load(function(){
$("#load_modal").modal('show');
		 <c:forEach items="${buff_site_list}" var="site" varStatus="status" >
		 		 	num=${status.index};
		 	satationSuit(${site.getLongitude()},${site.getLatitude()},${site.getBufftag()});
	
	</c:forEach>
			
		});
var n=0;
var json="{\"slist\":[";
var s="-1",arr=new Array();
function satationSuit(lng,lat,sta){
	//var name=place.name;
	
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:10,
		//panel:'panel'
	});
	console.log([lng,lat]);
	satation_search.searchNearBy("街",[lng,lat],300,function(status,result){
	
		var dd=result.poiList.pois[0].name+","+result.poiList.pois[0].location+'';//+result.poiList.pois[0].address;
		console.log(result.poiList.pois[0].name);
		console.log(sta);
		if(result.poiList.pois[0]==null){
			arr[0]="未匹配到合适街道";
			arr[1]=lng;
			arr[2]=lat;
		}
		else 
			arr=dd.split(",");
		
		if(n==0){
		 		json=json+"{\"address\":\""+arr[0]+"\",\"long\":"+arr[1]+",\"lati\":"+arr[2]+",\"index\":"+sta+"}";
		 	}
		 	else
		 	{
		 	
		 		json=json+",{\"address\":\""+arr[0]+"\",\"long\":"+arr[1]+",\"lati\":"+arr[2]+",\"index\":"+sta+"}";
		 	}
		 n++;
		if(num+1==n){
			json=json+']}';
			$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageSiteServlet", 
		data:{
				type:1,
				jsonlist:json
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			$(".modal-body").html("生成站点完毕");
			$("#w-modal-close").css("display","inline");
			
	    
      }});
		}
			
	});
	
	
}
	</script>
  </body>
</html>
