/**
 * 
 */
//设置地图中心，把地图拉到中心
//参数：loction:[],
/*var loc=[104.11,30.25];moveTocenter(loc);
function moveTocenter(loction){
	map.setCenter(loction);
}

//展示所有员工数据

var hhj_satations=[
              	{name:"金鱼街",
              	 address:"金鱼街10号",
              	 lng:[104.040329,30.681873],
              	 number:1,
              	 route:3,
              	 people:10
              	},
              	{
              		name:"天茵苑",
              		address:"通锦路1号",
              		lng:[104.056036,30.680028],
              		number:2,
              		route:2,
              		people:16
              		},
              	 {
              		name:"五丁苑",
              		address:"西北桥边街1号",
              		lng:[104.062388,30.683423],
              		number:3,
              		route:1,
              		people:6},
              	 {
              		name:"新园(白马寺北顺街)",
              		address:"白马寺北顺街99号",
              		lng:[104.069072,30.684504],
              		number:4,
              		route:1,
              		people:15},];
var locs=[104.069072,30.684504];
showAllstaffinfo(hhj_satations,locs);*/

function showAllstaffinfo(data,satationLoc){
	var loc=new AMap.LngLat(satationLoc[0],satationLoc[1]);
	for(var i=0;i<data.length;i++){
		console.log(loc.distance(data[i].lng));
		if(loc.distance(data[i].lng,satationLoc)<2000){
			staffmarker(data[i]);
			//markers(data[i]);
		}
	}
}

function satationSearch(){
	$('#satation-search input').bind('input oninput',function(){
		var index=$('#satation-search input').val();
		if(index>=0&&index<paths.length){ 
			if(flag!=1){rr.destroy();flag=2;}
			map.clearMap();
			Dragroutetest(paths[index].lng);
			flag=2;
		}
	});
}