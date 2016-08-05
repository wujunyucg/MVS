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
			$("#j-left-menu").hide();
			$("#j-left-menu").css("width","60px");
			$("#content").css("marginLeft","10px");
			$(".btn_text").hide();
			$(".btn_icon").show();
			turn = false;
		}else{
			for(var i=0;i<openMenus.length;i++){
				openMenus[i].show();//显示出来
			}
			$("#j-left-menu").show();
			$("#j-left-menu").css("width","15%");
			$("#content").css("marginLeft","16%");
			$(".btn_text").show();
			$(".btn_icon").hide();
			turn = true;
		}
	});
	
	$(".btn_text").click(function(){
		var t = $(this);
		/*先收起其他的列表*/
		$(".btn_text").each(function(){
			var tt = $(this);
			if(tt.text()!=t.text()){
				tt.next("div").slideUp("500");
				var ar  = tt.children("span").last();
				ar.removeClass().addClass("glyphicon glyphicon-menu-down");
			}
		});
		t.next("div").slideToggle("1000");
		//右边的箭头
		var ar  = t.children("span").last();
		if(ar.hasClass("glyphicon-menu-down")){
			ar.removeClass().addClass("glyphicon glyphicon-menu-up");
		}else{
			ar.removeClass().addClass("glyphicon glyphicon-menu-down");
		}
	});
	
});