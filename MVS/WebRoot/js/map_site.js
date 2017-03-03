/**
 * 
 */
var dis=true;
  
  function disp(){
  alert(1);
  	if(dis){
  		map.clearMap();
  		dis=false;
  	}
  	else{
  		for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
					satationsmarker(sitelist[i]);
				}
			}
		for(var i=0;i<stafflist.length;i++){
					staffmarker(stafflist[i]);
		}
		dis=true;
  	}
  }
  
  
	var sitelist,stafflist;
 
            var hhj_ctn;
$(function(){
alert(2);
hhj_ctn=document.getElementById('addsatation-info').innerHTML;
			 console.log(hhj_ctn);
		var site='${json_site_list}';
		var list = eval('(' + site + ')');
		 sitelist = list.sitelist;
			map.setCity('成都');
		for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
					satationsmarker(sitelist[i]);
				}
			}
			
		var staff='${json_staff_list}';
		 list = eval('(' + staff + ')');
		 stafflist = list.stafflist;
		 
		 for(var i=0;i<stafflist.length;i++){
		 	//console.log(stafflist[i]);
					staffmarker(stafflist[i]);
					
			}
	$('#listselect').change(function () {
		var id = $(this).val();
		if(id==-1)
			map.clearMap();
		else
		$.ajax({ 
		type:"post",
		url: "servlet/ManageSiteServlet",
		data:{
				type:6,
				lineid:id
		}, 
		error: function(request) {
           // document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			map.clearMap();
			var list = eval('(' + request + ')');
			var lsitelist = list.lsitelist;
		
	    	for(var i=0;i<lsitelist.length;i++){		
					satationsmarker(lsitelist[i]);
			}
      }});
	});
	
	
	
	});
	
	
		function showall(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
					satationsmarker(sitelist[i]);
				}
			}
		}
		function showsite(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId==null||sitelist[i].lineId==""){
					satationsmarker(sitelist[i]);
				}
			}
		}
		function showallstaff(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<stafflist.length;i++){
					staffmarker(stafflist[i]);
			}
		}
		function showstaff(){
			//map.setCity('成都');
			map.clearMap();
			for(var i=0;i<stafflist.length;i++){
				if(stafflist[i].siteId<0)
					staffmarker(stafflist[i]);
			}
		}
		
		var up=true;
		$("#updown").click(function() {
			if(up){
				$("#updownimg").attr("src","images/down.png");
				up=false;
			}
			else{
				$("#updownimg").attr("src","images/up.png");
				up=true;
			}
		});
		
		var n=0;
var json="{\"slist\":[";
var s="-1",arr=new Array();
	var num;
		function autosite(){
		$("#ww-modal-body").html("请选择是全部生成(清除以往数据)或为未匹配站点员工生成站点");
		$("#ww-modal-but2").css("display","inline");
		$("#ww-modal-but").css("display","inline");
		$("#ww-modal-close").css("display","inline");
		$("#ww-modal-but").html("全部生成");
		$("#ww-modal-but2").html("部分生成");
		$("#ww-modal-close").html("取消");
		$("#ww-modal-but").attr("onclick","kmeans();");
		$("#ww-modal-but2").attr("onclick","kmeans2();");
		$("#ww-load_modal").modal('show');
		}
		
		
		function kmeans(){
		$("#ww-modal-body").html("正在处理中......");
		$("#ww-modal-but2").css("display","none");
		$("#ww-modal-but").css("display","none");
		$("#ww-modal-close").css("display","none");
		$.ajax({ 
		type:"post",
		url: "servlet/ManageSiteServlet",
		data:{
				type:0,
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
		
			var list = eval('(' + request + ')');
			var sitelist2 = list.bsitelist;
			json="{\"slist\":[";
			arr=new Array();
			n=0;
	    	nextkmeans(sitelist2);
      }});
     
	}
	
	function kmeans2(){
		$("#ww-modal-body").html("正在处理中......");
		$("#ww-modal-but2").css("display","none");
		$("#ww-modal-but").css("display","none");
		$("#ww-modal-close").css("display","none");
		
		$.ajax({ 
		type:"post",
		url: "servlet/ManageSiteServlet",
		data:{
				type:7,
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
		
			var list = eval('(' + request + ')');
			var sitelist2 = list.bsitelist;
			json="{\"slist\":[";
			arr=new Array();
			n=0;
	    	nextkmeans(sitelist2);
      }});
     
	}
	
	
	
	
	function nextkmeans(sitelist2){
		 num=sitelist2.length;
		 
		for(var i= 0; i<sitelist2.length ;i++){
		console.log(num);
		 	satationSuit2(sitelist2[i].longitude,sitelist2[i].latitude,sitelist2[i].bufftag);
	}
}

function satationSuit2(lng,lat,sta){
	//var name=place.name;
	
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:10,
		//panel:'panel'
	});
	console.log([lng,lat]);
	satation_search.searchNearBy("街|路|道",[lng,lat],300,function(status,result){
	
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
		 console.log(n);
		if(num==n){
			json=json+']}';
		
			
		$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageSiteServlet", 
		contenttype :"application/x-www-form-urlencoded;charset=utf-8",
		data:{
				type:1,
				jsonlist:json
		}, 
		error: function(request) {
            document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
		success: function(request){
			$("#ww-modal-body").html("生成站点完毕");
			$("#ww-modal-close").css("display","inline");
			$("#ww-modal-close").html("确定");
			var list = eval('(' + request + ')');
		 	sitelist = list.nsitelist;
		 	stafflist=list.stafflist;
			var tab='<thead class="fixedThead"><tr><th>#</th><th>站点名称</th><th>站点地址</th><th>站点人数</th><th>站点所属线路</th><th>站点所属线路次序</th></tr></thead><tbody class="scrollTbody">';
			for(var i=0;i<sitelist.length;i++){
				if(sitelist[i].lineId>=0){
				satationsmarker(sitelist[i]);
				tab=tab+'<tr id="'+i+'"><td>'+(i+1)+'</td><td>'+sitelist[i].name+'</td><td>'+sitelist[i].address+'</td><td>'+sitelist[i].peoNum+'</td><td>'+sitelist[i].lineName+'</td><td>'+sitelist[i].order+'</td></tr>';
				}
			}
			tab=tab+'</tbody>';
			$("#site_table").html(tab);
			$("#site_table td").click(function() {
 			$("tr").each(function() {
 				$(this).css('background-color','white');
 			})
 				map.clearMap();
             var  tr=$(this).parent().attr("id");
               satationsmarker(sitelist[tr]);
               moveTocenter([sitelist[tr].longitude,sitelist[tr].latitude]);
               $("#"+tr).css('background-color','red');
        
           
            });
	    
      }
      });
		}
			
	});
}
$("#site_table td").click(function() {
 			$("tr").each(function() {
 				$(this).css('background-color','white');
 			})
 				map.clearMap();
             var  tr=$(this).parent().attr("id");
               satationsmarker(sitelist[tr]);
               map.setCenter([sitelist[tr].longitude,sitelist[tr].latitude]);
               $("#"+tr).css('background-color','#CCFFFF');
        
           
            });
  function showinput(){
  	$("#name").css("display","inline");
  }
  function disnone(){
  $("#name").css("display","none");
  }
  
  function checksite(){
  		//alert($("#tipinput").val());
  		$.ajax({ 
		type:"post",
		url: "<%=basePath%>servlet/ManageSiteServlet", 
		contenttype :"application/x-www-form-urlencoded;charset=utf-8",
		data:{
				type:5,
				sitename:$("#tipinput").val()
		}, 
		error: function(request) {
           // document.getElementById("p2"). innerHTML = '修改失败，请重新修改';
         },
         success:function(request){
        // alert(request);
         map.clearMap();
         	var list = eval('(' + request + ')');
		 	var csitelist = list.csitelist;
		 	for(var i=0;i<csitelist.length;i++){
					satationsmarker(csitelist[i]);
			}
         }
         });
  }