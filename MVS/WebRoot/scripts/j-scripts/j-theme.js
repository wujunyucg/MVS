$(function(){
	var turn = true;
	var judgeCJNT = false;
	var openMenus = new Array();//存放展开的子menu的div
	$("#j_nav_toggle").click(function(){
		if(turn){
			openMenus = [];//clear
			$(".j-child-menu").each(function(){
				if($(this).css("display")=="block"){
					openMenus.push($(this));//若展开的，则加入
				}
			});
			
			//$(".j-child-menu").hide();
			//$("#j-left-menu").hide();
			$(".opMenu").hide("500");
			$("#j-left-menu").css("width","58px");
			$("#content").css("marginLeft","68px");
			$("#j-left-menu").css("width","58px");
			$("#upHAInfor").css("marginLeft","58px");
			console.log($("#accordion"));
			if($(".accordionLS") != undefined){
				$(".accordionLS").css("left","58px");
			}
			
			//$(".btn_text").hide();
			//$(".btn_icon").show();
			turn = false;
		}else{
			for(var i=0;i<openMenus.length;i++){
				openMenus[i].show();//显示出来
			}
			//$("#j-left-menu").show();
			$("#j-left-menu").css("width","230px");
			$("#content").css("marginLeft","240px");
			$("#j-left-menu").css("width","230px");
			$("#upHAInfor").css("marginLeft","230px");
			if($(".accordionLS") != undefined){
				$(".accordionLS").css("left","230px");
			}
			
			$(".opMenu").show("500");
			//$(".btn_text").show();
			//$(".btn_icon").hide();
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
				ar.removeClass().addClass("glyphicon glyphicon-menu-down opMenu");
			}
		});
		t.next("div").slideToggle("1000");
		//右边的箭头
		var ar  = t.children("span").last();
		if(ar.hasClass("glyphicon-menu-down")){
			ar.removeClass().addClass("glyphicon glyphicon-menu-up opMenu");
		}else{
			ar.removeClass().addClass("glyphicon glyphicon-menu-down opMenu");
		}
	});
	
	$("#j-left-menu").mouseenter(function(){
		if(!turn){
			var s = event.fromElement || event.relatedTarget; 
           // if (!this.contains(s)){   
				$("#j_nav_toggle").click();
				judgeCJNT = true;
           // }
		}
	});
	$("#j-left-menu").mouseleave(function(){
		if(judgeCJNT){
			var s = event.fromElement || event.relatedTarget; 
        //    if (!this.contains(s)){   
				$("#j_nav_toggle").click();
				judgeCJNT = false;
            }
		//}
	});
});