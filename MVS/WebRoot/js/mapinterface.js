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

var hhj_satations=[{
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
/*显示路线 */
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
//showroute(pp);
var route2=[];

function siteOnroute(data,id,i){
	var marker=new AMap.Marker({
		  position:[data.longitude,data.latitude],
		  title: data.name,	  
		  raiseOnDrag:true,
		  map: map,
		  icon:"icons/satation2.svg",
		  zIndex:100
	});
	routesiztLic(marker,data);
	return marker;
}
function routesiztLic(marker,data){
	var flag=0;
	map.on('click',function(){
		info2.close();
	});
    var conten=SatationContent(data);
	AMap.event.addListener(marker, 'click',function (e){
		//data=marker.getPosition(); 
		marker.setDraggable(false);
		if(flag==1){
			console.log("移动成功");
			flag=0;
			satationSuit(marker.getPosition().getLng(),marker.getPosition().getLat(),data);
			console.log("匹配成功");
			marker.hide();
		}
		else{
		var conten=SatationContent(data);
		info(marker.getPosition(),conten);	
		}
	});
	
}
var ss=new Array();
for(var i=0;i<30;i++){
	ss[i]=new Array();
}
var index=0;
function showroute(paths,id,name){
	console.log("enter route-----");
	var path=[];
//	showAllsatationinfo(paths);
	var sitemarkers=[];
	for(var i=0;i<paths.length;i++){
		path.push([paths[i].longitude,paths[i].latitude]);
		if(i>0&&i<paths.length-1){
			sitemarkers[i-1]=siteOnroute(paths[i],id,i-1);
		}
		console.log(path[0]+paths[i].longitude+paths[i]. latitude);
	}  
	//ss[0]=sitemarkers;
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
					//strokeColor:'#cc9',
					//strokeOpacity:0.5
				}
			}); 
		//构造拖拽导航类，传入参数分别为：地图对象，初始路径，驾车策略
		route.search(); //查询导航路径并开启拖拽导航
		//当路径完成时的事件
	//	console.log("enter serach-----");
		console.log(id);
		AMap.event.addListener(route,'complete',function(e){
			//alert("OK");
			//alert(e.data.routes[0].steps[0].action);
			console.log("-------------------------enter complete-----");
			AMap.event.addListener(route,'addway',function(e){
				alert("dd");
			});	
			//参考这个格式
			//var trs=$('#result table tr');
			//trs[0].val("线路"+satations[0].route);
			$('#result').css("display","inline");
			$('#addroute').css("display","none");
			var trs=document.getElementById("routenumber");
			console.log(paths[0].lineId);
			trs.innerHTML=name;
			var tds=document.getElementById("time-distance").getElementsByTagName("td");
			tds[0].innerHTML=e.data.routes[0].time/60+"分钟";
			tds[1].innerHTML=e.data.routes[0].distance/1000+"千米";
			var s_e=document.getElementById("start-end");
			s_e.innerHTML=paths[0].name+"-----"+paths[paths.length-1].name;
			route2[id]=route;
			//route2.destroy();
		});
	});
}
var rr;
function DelRoute(id){
	$('#result').css("display","none");
	route2[id].destroy();
	for(var i=0;i<markersOnRoute[id].length;i++){
		markersOnRoute[id][i].hide();
	}
}
//站点街道匹配
//satationSuit(104.097315,30.680841);
function satationSuit(lng,lat,data){
	//var name=place.name;
	document.getElementById('return_satationinfo').innerHTML='';
	console.log(data);
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:10,
		//panel:'panel'
	});
	console.log("ddddddddddd");
	satation_search.searchNearBy("街", [lng,lat],200,function(status,result){
		//data.longitude=lng;
	//	console.log(data);
		data.longitude=lng;
		data.latitude=lat;
		data.address=result.poiList.pois[0].name;
		satationsmarker(data);
		var json=JSON.stringify(data);
	//	console.log(json);
	//	console.log(json.toString());
		Editdata(json.toString());
	});
}
 
function read(data){
	rr=data;
	console.log(rr+';;;;;'+data);
	return rr;
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
place_search('金鱼街');
function place_search(name){
	//var name=place.name;
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:1,
		//panel:'panel'
	});
	satation_search.search(name,function(status,result){
		//for(var i=0;i<result.poiList.pois.length;i++){
			map.setCenter([result.poiList.pois[0].location.lng,result.poiList.pois[0].location.lat]);
			var satation={
					siteId:1,
					peoNum:0,
					lineId:1,
					order:1,
					delay:1,
					latitude:result.poiList.pois[0].location.lat,
					longitude:result.poiList.pois[0].location.lng,
					address:name,
					name:name	
			};
			satationsmarker(satation);
			map.setCenter([result.poiList.pois[0].location.lng,result.poiList.pois[0].location.lat]);
		//}
	});
}
