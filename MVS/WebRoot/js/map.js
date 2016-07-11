// JavaScript Document
//显示所有站点
markers(hhj_satation);
var path =new Array();
for(var i=0;i<25;i++){
	path[i]=new Array();
	}
for(var i=0;i<hhj_satation.length;i++){
	console.log(hhj_satation[i]);
	console.log(hhj_satation[i].route+hhj_satation[i].number);
    	path[hhj_satation[i].route].push(hhj_satation[i].lng);
	}
	//Dragroute(path[2]);

/*for(var i=1;i<3;i++){ 		
	Dragroute(path[i]);
}*/
function showroute(i){
	//route2.destroy();
	Dragroute(path[i]);
	//);
	}

function hideroute(){
	route2.destroy();
}
var routes_clk_show=document.getElementById('routes').getElementsByTagName('li');
for(var i=0;i<routes_clk_show.length;i++){
		routes_clk_show[i].index=i;
		console.log(i);
		routes_clk_show[i].onclick=function(){
			console.log(this.index+1);
			showroute((this.index+1));
			alert("OK"+this.index+1);
		}
}