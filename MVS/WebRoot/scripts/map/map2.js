/** 地图控件部分 **/		
		fun_imgs();
		Mousetools();
		$("#marker").click(function(e) {		
						fun_imgs();
						$('#marker').attr("src","images/mark_on.png");
						$('#marker_kinds').css("display","inline");
						Mousetools();
       			 });
	/***缩小**/
			
	/**  导航栏处理事件 **/
		function nav(type){
			$("#left>ul").hide();
			$(this).show();
			switch(type){
				case 1:$("#events").show();break;	
				case 2:$("#resource").show();break;
				case 3:$("#spl").show();$("#container").hide();$("#Repository").show();break;
				case 4:$("#ways").show();break;
				case 5:$("#shiping").show();break;
				case 6:$("#commnication").show();break;
				case 7:$("#set").show();break;	
			}	
		}
		
		$("#events").show();
		function events(type){
			$("#resource ul").hide();
			switch(type){
				case 1:$(".communication-team").show();break;k,m
				case 2:$(".expert").show();break;
				case 3:alert("3");break;
				case 4:alert("4");break;
				case 5:$(".equipment").show();break;
			}		
		}
		
		function fun_imgs(type){	
			$('#marker').attr("src","images/mark.png");
			$('#disdance').attr("src","images/disdance.png");
			$('#area').attr("src","images/area.png");
			$('#dangerous').attr("src","images/dangerous.png");
			$('#route').attr("src","images/route.png");
			$('#route_recode').attr("src","images/route_recode.png");
			$('#call').attr("src","images/route_recode.png");
			
			$('#jiuyuanwz').attr("src","images/jiuyuanwz.png");		
			$('#marker_kinds').css("display","none");	
			$('#danger_jb').css("display","none");
			$('#goods_kinds').css("display","none");
		}
		function marker_img_init(){
			var imgs=document.getElementById('marker_kinds').getElementsByTagName('img');
			imgs[0].src="images/dian.png";
			imgs[1].src="images/area.png";
			imgs[2].src="images/zx.png";
			imgs[3].src="images/dbx.png";			
		}// JavaScript Document
		
		
	//获取经纬度

	initright();
	
	//危险品分布
	$('#dangerous').click(function(){
		fun_imgs();
		$('#dangerous').attr("src","images/dangerous_on.png");
		$('#danger_jb').css("display","inline");
		var dangers=document.getElementById('danger_jb').getElementsByTagName('option');
		dangers[0].onclick=Md_marker("易燃");
		dangers[1].onclick=Md_marker("易爆");
		dangers[2].onclick=Md_marker("化工厂");
	});
	
	function exd(){
		map.zoomIn();
	}
	$('#jiuyuanwz').click(function(){
		fun_imgs();
		$('#jiuyuanwz').attr("src","images/jiuyuanwz_on.png");
		$('#goods_kinds').css("display","inline");
		var dangers=document.getElementById('goods_kinds').getElementsByTagName('option');
		dangers[0].onclick=Md_marker("消防栓");
		dangers[1].onclick=Md_marker("医院");
		dangers[2].onclick=Md_marker("消防队");
		dangers[3].onclick=Md_marker("派出所");
		
	});