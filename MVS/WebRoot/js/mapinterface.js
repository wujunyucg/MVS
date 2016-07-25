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
	var loc=new AMap.LngLat(satation.longitude,satation.latitude);
	var circle=new AMap.Circle({
		map:map,
		center:[satation.longitude,satation.latitude],
		radius:1000,
		fillColor:'#ccc',
		strokeColor:'#ccc',
		zIndex:35
	});
	for(var i=0;i<staffs.length;i++){
		if(staffs[i].lineId<0&&(loc.distance([satation.longitude,satation.latitude])<1000)){
			staffmarker(staffs[i]);
		}
	}
}
//显示没设置的员工
function staffIdle(staffs){
	for(var i=0;i<staffs.length;i++){
		if(staffs[i].lineId<0){
			staffmarker(staffs[i]);
		}
	}
}
//显示未设置线路的站点
function sataionIdle(satations){
	for(var i=0;i<satations.length;i++){
		if(satations[i].lineId<0){
			satationsmarker(satations[i]);
		}
	}
}
//设置一个站点
function setAsatation(name){
	place_search(name);
	
}
/*显示路线
var pp=[{
	siteId:0,
	peoNum:0,
	lineId:0,
	order:0,
	delay:0,
	latitude:30.681873,
	longitude:104.040329,
	address:"金鱼街10号",
	name:"金鱼街"	
},{
	siteId:0,
	peoNum:0,
	lineId:0,
	order:0,
	delay:0,
	latitude:30.684504,
	longitude:104.069072,
	address:"白马寺北顺街99号",
	name:"新园(白马寺北顺街)"	
},
{
	siteId:0,
	peoNum:0,
	lineId:0,
	order:0,
	delay:0,
	latitude:30.680028,
	longitude:104.056036,
	name:"天茵苑",
  	address:"通锦路1号",
},
{
	siteId:0,
	peoNum:0,
	lineId:0,
	order:0,
	delay:0,
	latitude:30.683423,
	longitude:104.062388,
  	name:"五丁苑",
	address:"西北桥边街1号",
},
];

//显示所有线路
showroute(pp); */
function showroute(paths){
	console.log("enter route-----");
	var path=[];
//	showAllsatationinfo(paths);
	for(var i=0;i<paths.length;i++){
		path.push([paths[i].longitude,paths[i].latitude]);
		if(i>0&&i<paths.length-1){
			satationsmarker(paths[i]);
		}
		console.log(path[0]+paths[i].longitude+paths[i].latitude);
	}
	map.plugin("AMap.DragRoute",function(){
		route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE,{
				midMarkerOptions:{
					visible :false,	
				},
				startMarkerOptions:{
					//visible :false,	
				},
				endMarkerOptions:{
					//visible :false,	
				},
				polyOptions :{
					strokeColor:'#cc9',
					strokeOpacity:0.5
				}
			}); 
		//构造拖拽导航类，传入参数分别为：地图对象，初始路径，驾车策略
		route.search(); //查询导航路径并开启拖拽导航
		//当路径完成时的事件
	//	console.log("enter serach-----");
		AMap.event.addListener(route,'complete',function(e){
			//alert("OK");
			//alert(e.data.routes[0].steps[0].action);
			console.log("-------------------------enter complete-----");
		    var r=e.data.routes[0].steps;
		    
			for(i=0;i<r.length;i++){
			     //各路段描述
				//console.log(e.data.routes[0].steps[i].instruction );
				//目标路程
				//console.log(e.data.routes[0].distance);
				//预计时间
				//console.log(e.data.routes[0].time);
			//console.log("enter complete-----");
			}
			AMap.event.addListener(route,'addway',function(e){
				alert("dd");
			});	
			//参考这个格式
			//var trs=$('#result table tr');
			//trs[0].val("线路"+satations[0].route);
			$('#result').css("display","inline");
			$('#addroute').css("display","none");
			var trs=document.getElementById("routenumber");
			trs.innerHTML="线路"+paths[0].lineId;
			var tds=document.getElementById("time-distance").getElementsByTagName("td");
			tds[0].innerHTML=e.data.routes[0].time/60+"分钟";
			tds[1].innerHTML=e.data.routes[0].distance/1000+"千米";
			var s_e=document.getElementById("start-end");
			s_e.innerHTML=paths[0].name+"-----"+paths[paths.length-1].name;
			route2=route;
			//route2.destroy();
		});
	});
}

//站点街道匹配
satationSuit(104.097315,30.680841);
function satationSuit(lng,lat){
	//var name=place.name;
	document.getElementById('return_satationinfo').innerHTML="-1";
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:10,
		//panel:'panel'
	});
	console.log("ddddddddddd");
	
	satation_search.searchNearBy("街",[lng,lat],200,function(status,result){
		//console.log(status);
		//console.log(result);
	//	for(var i=0;i<result.poiList.pois.length;i++){
			//console.log(result.poiList.pois[i].name+"	"+result.poiList.pois[i].location.lat+"	"+result.poiList.pois[i].location.lng);	
	//	}
		console.log("jjjjjjjjjjjjjjjjjj");
		document.getElementById('return_satationinfo').innerHTML=result.poiList.pois[0].name+","+result.poiList.pois[0].location+'';//+result.poiList.pois[0].address;
		var dd=document.getElementById('return_satationinfo').innerHTML;
		console.log(dd);
	});
	
	/*satation_search.searchNearBy("路",[lng,lat],200,function(status,result){
			//console.log(status);
			//console.log(result);
			for(var i=0;i<result.poiList.pois.length;i++){
				//console.log(result.poiList.pois[i].name+"	"+result.poiList.pois[i].location.lat+"	"+result.poiList.pois[i].location.lng);
			}
			console.log("zzzzzzzzzzzzzzzzzzz");
			
	});*/
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