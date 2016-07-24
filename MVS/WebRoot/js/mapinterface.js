/**
 * 
 */
//设置地图中心，把地图拉到中心
//参数：loction:[],
//var loc=[104.11,30.25];moveTocenter(loc);
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
//showAllstaffinfo(hhj_satations)
function showAllstaffinfo(data){
	//var loc=new AMap.LngLat(satationLoc[0],satationLoc[1]);
	for(var i=0;i<data.length;i++){
			staffmarker(data[i]);
	}	
}
//显示所有站点信息
//showAllsatationinfo(hhj_satations)
function showAllsatationinfo(satations){
	for(var i=0;i<satations.length;i++){
		satationsmarker(satations[i]);
	}
}
//显示站点周围未员工设置站点的员工
/*var ll={
  		name:"新园(白马寺北顺街)",
  		address:"白马寺北顺街99号",
  		lng:[104.069072,30.684504],
  		number:4,
  		route:1,
  		people:15};
getStaffnum(hhj_satations,ll);*/
function getStaffnum(staffs,satation){
	satationsmarker(satation);
	var loc=new AMap.LngLat(satation.lng[0],satation.lng[1]);
	var circle=new AMap.Circle({
		map:map,
		center:satation.lng,
		radius:1000,
		fillColor:'#ccc',
		strokeColor:'#ccc',
		zIndex:35
	});
	for(var i=0;i<staffs.length;i++){
		if(staffs[i].route<0&&(loc.distance(staffs[i].lng)<1000)){
			staffmarker(staffs[i]);
		}
}
}
//显示没设置的员工
function staffIdle(staffs){
	for(var i=0;i<staffs.length;i++){
		if(staffs[i].route<0){
			staffmarker(staffs[i]);
		}
	}
}
//显示未设置线路的站点
function sataionIdle(satations){
	for(var i=0;i<staffs.length;i++){
		if(satations[i].route<0){
			staffmarker(staffs[i]);
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