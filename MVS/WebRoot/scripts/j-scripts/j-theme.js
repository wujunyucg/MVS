$(function(){
	var turn = true;
	var openMenus = new Array();//存放展开的子menu的div
	$("#j_nav_toggle").click(function(){
		if(turn){
			openMenus = [];//clear
			$(".j-child-menu").each(function(){
				if($(this).css("display")=="block"){
					openMenus.push($(this));//若展开的，则加入
				}
			});
			
			$(".j-child-menu").hide();
			$("#j-left-menu").css("width","60px");
			$("#content").css("marginLeft","70px");
			$(".btn_text").hide();
			$(".btn_icon").show();
			turn = false;
		}else{
			for(var i=0;i<openMenus.length;i++){
				openMenus[i].show();//显示出来
			}
			$("#j-left-menu").css("width","15%");
			$("#content").css("marginLeft","16%");
			$(".btn_text").show();
			$(".btn_icon").hide();
			turn = true;
		}
	});
	
	$(".btn_text").click(function(){
		
		$(this).next("div").slideToggle("1000");
	});
});