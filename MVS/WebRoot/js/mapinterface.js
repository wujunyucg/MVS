/**
 * 
 */

function moveTocenter(loction){
	map.setCenter(loction);
}


//展示所有员工数据


var hhj_satations=[{
	siteId:0,
	peoNum:0,
	lineId:"",
	order:0,
	delay:0,
	latitude:30.681873,
	longitude:104.040329,
	address:"金鱼街10号",
	name:"金鱼街"	
},{
	siteId:0,
	peoNum:0,
	lineId:"",
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
	lineId:"",
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
	lineId:"dsfsdhff",
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
			//console.log("移动成功");
			flag=0;
			satationSuit(marker.getPosition().getLng(),marker.getPosition().getLat(),data);
		//	console.log("匹配成功");
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
	//console.log("enter route-----");
	var path=[];
//	showAllsatationinfo(paths);
	var sitemarkers=[];
	for(var i=0;i<paths.length;i++){
		path.push([paths[i].longitude,paths[i].latitude]);
		if(i>0&&i<paths.length-1){
			//sitemarkers[i-1]=siteOnroute(paths[i],id,i-1);
		}
		//console.log(path[0]+paths[i].longitude+paths[i]. latitude);
	}  
	path.push([fac_lon,fac_lan]);
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
		route2[id]=route;
		//当路径完成时的事件
		AMap.event.addListener(route,'complete',function(e){
			AMap.event.addListener(route,'addway',function(e){
			});	
			//参考这个格式
			//var trs=$('#result table tr');
			//trs[0].val("线路"+satations[0].route);
			$('#result').css("display","inline");
			$('#addroute').css("display","none");
			var trs=document.getElementById("routenumber");
			trs.innerHTML=name;
			var tds=document.getElementById("time-distance").getElementsByTagName("td");
			tds[0].innerHTML=e.data.routes[0].time/60+"分钟";
			tds[1].innerHTML=e.data.routes[0].distance/1000+"千米";
			var s_e=document.getElementById("start-end");
			s_e.innerHTML=paths[0].name+"-----"+paths[paths.length-1].name;
			//route2.destroy();
		});
	});
}
var rr;
function DelRoute(id){
	$('#result').css("display","none");
	route2[id].destroy();
	//for(var i=0;i<markersOnRoute[id].length;i++){
	//	markersOnRoute[id][i].hide();
	//}
}
//站点街道匹配
//satationSuit(104.097315,30.680841);
function satationSuit(lng,lat,data){
	//var name=place.name;
	//document.getElementById('return_satationinfo').innerHTML='';
//	console.log(data);
	var satation_search=new AMap.PlaceSearch({
		keywords :name, //搜索关键字为“超市”的poi
		city:'成都',
		citylimit:true,
		pageSize:10,
		//panel:'panel'
	});
	//console.log("ddddddddddd");
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
	//console.log(rr+';;;;;'+data);
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
//place_search('金鱼街');
//place_search('五丁苑');
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
					siteId:"",
					peoNum:0,
					lineName:"null",
					order:"",
					delay:"",
					latitude:result.poiList.pois[0].location.lat,
					longitude:result.poiList.pois[0].location.lng,
					address:name,
					name:""	
			};
			satationsmarker(satation);
			map.setCenter([result.poiList.pois[0].location.lng,result.poiList.pois[0].location.lat]);
		//}
	});
}
//p_s('金鱼街',hhj_ctn);
function p_s(name,ctn){
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
					siteId:"",
					peoNum:0,
					lineName:"null",
					order:"",
					delay:0,
					latitude:result.poiList.pois[0].location.lat,
					longitude:result.poiList.pois[0].location.lng,
					address:name,
					name:"",
					bufftag:0
			};
			var marker=satationsmarker(satation);
			map.setCenter([result.poiList.pois[0].location.lng,result.poiList.pois[0].location.lat]);
			EditSatation2(satation,marker,ctn);
	});
}
var siteable=0;
function EditSatation2(data,marker,ctn){
	map.on('click',function(e){
		marker.hide();
	});
	document.getElementById('addsatation-info').innerHTML="";
	document.getElementById('addsatation-info').innerHTML=ctn;
	var s=[data.longitude,data.latitude];
	var ctn=document.getElementById('info-satation');
	//console.log(document.getElementById('satation-lng'));
	$('#satation-lng').val(s);
	$('#satation-name').val(data.name);
	$('#satation-address').val(data.address);
	$('#satation-route').val(data.lineName);
	$('#satation-number').val(data.order);
	$('#satation-people').val(data.peoNum);
	info(s,ctn);
	//var sbm=document.getElementById('sbm');
	var sbm=ctn.getElementsByTagName('button');	
	//console.log(sbm);
	//console.log(data);
	sbm[0].onclick=function(){
		//document.getElementById('result_satationinfo').innerHTML=data.name+","+data.address+","+data.longitude+""+data.latitude+","+data.lineId+","+","+data.siteId+","+data.peoNum+","+data.delay;
		
		data.order=$('#satation-number').val();
		data.name=document.getElementById('satation-name').value;
		data.address=document.getElementById('satation-address').value;
		data.peoNum=parseInt($('#satation-people').val());
		///console.log(data);
		//alert("增加成功");
		satationsmarker(data);
		var json=JSON.stringify(data);
		Addsitedata(json.toString());
		marker.hide();
		map.on('click',function(e){});
		siteable=0;
		info2.close();
	};
	sbm[1].onclick=function(){
		info2.close();
		marker.hide();
	};

}

var enableclick=1;
function addclicksite(ctn){
	var mapclick;	
	enableclick=1;
	$('#exitclick').bind('click',function(){
		AMap.event.removeListener(mapclick); 
		enableclick=0;
	});
	enableclick=1;
	mapclick=map.on('click',function(e){
		var aa=$('.dropdown-menu a');
		if(enableclick==1){
		var marker=new AMap.Marker({
			map:map,
			draggable:true,
			icon:"icons/satation2.svg",
		});
		marker.hide();
		if(siteable==0){
		marker.show();
		siteable=1;
		var ll=e;
		marker.setPosition(e.lnglat);
			var data={
					siteId:"",
					peoNum:0,
					lineName:"null",
					order:"",
					delay:0,
					latitude:e.lnglat.lat,
					longitude:e.lnglat.lng,
					address:"",
					name:"",
					bufftag:0
			};
			var satation_search=new AMap.PlaceSearch({
				keywords :name, //搜索关键字为“超市”的poi
				city:'成都', 
				pageSize:10,
				//panel:'panel'
			});
			

			satation_search.searchNearBy("", e.lnglat,600,function(status,result){
				//console.log(marker.getPosition());
			
					data.longitude=marker.getPosition().lng;
					data.latitude=marker.getPosition().lat;
					data.address=result.poiList.pois[0].name;
					var conten=SatationContent(data);
					info(marker.getPosition(),conten);
					EditSatation2(data,marker,ctn);	
				
			});
			
			
			
			AMap.event.addListener(marker, 'click',function(e){
				var conten=SatationContent(data);
				info(marker.getPosition(),conten);
			});
			siteable=1;
		}
		else{
			info2.close();
			marker.hide();
			siteable=0;
		}
		}
	});
	
}


//Routeshowsizt(hhj_satations,0);
var index2=0;
var ii=[];
function Routeshowsizt(sites,isAll){
	var car_fac=new AMap.Marker({
		 position:[fac_lon,fac_lan],
		  raiseOnDrag:true,
		  map: map,
		  icon: "icons/car_factory.svg",
		  zIndex:300
	});
	var path=[];
	var markers=[];
	var wei=[];
	ii=[];
	index2=0;
	$('#surecreLsite').bind('click', function(){
		modlineId = -1;
		
		var path_t=[];
		for(var i=0;i<path.length;i++){
			if(wei[i]==1)
				path_t.push(path[i]);
		}
		console.log(path_t);
		if(path_t==null||path_t==""||path_t==[]||path_t.length < 0){
			$("#surecreLsite").attr("data-target","#linesiteNull");
		}else{
			$("#surecreLsite").attr("data-target","#h_creline");
			$("#hcre_page1").show();
			$("#hcre_page2").hide();
			h_creLine(path_t);
		}
	});
	
	//var index=0;
	var poly=new  AMap.Polyline({map:map});
	var k=0;
	for(var i=0;i<sites.length;i++){
		if(sites[i].allsite.lineId==null||sites[i].allsite.lineId==""||isAll){
			
			siteOnroutes(sites[i].allsite,path,markers,k,poly,wei);
			var ss=sites[i].allsite.lineName;
			//console.log(ss);
			if(ss.indexOf("_Z")<0)
				k++;
		}
	}
	
}



var index2=0;
var ii=[];

function siteOnroutes(data,path,markers,index,poly,wei){
	var marker=new AMap.Marker({
		 position:[data.longitude,data.latitude],
		  title: data.name,	  
		  raiseOnDrag:true,
		  map: map,
		  icon:"icons/satationOnRoute.svg",
		  zIndex:100
	});
	if(data.lineId==null||data.lineId==""){
		 marker.setIcon('icons/satations.svg');
	}
	var ss=data.lineName;
	if(ss.indexOf("_Z")>=0){
		marker.setIcon('icons/znzd.svg');
	}
	var ss=data.lineName;
	if(ss.indexOf("_Z")<0){
	AMap.event.addListener(marker, 'click',function(e){
		var conten=SatationContent(data);
		info(marker.getPosition(),conten);
	});
	var temp_icon;
	AMap.event.addListener(marker, 'rightclick',function(e){
		var contextMenu=new AMap.ContextMenu();
		contextMenu.addItem("设为经点", function() {
			if(marker.getIcon() != 'icons/newrtsizt.svg'){
				path.push(data);
				ii[index]=index2;
				wei[ii[index]]=1;
				showPolyline(path,poly,wei);
				temp_icon = marker.getIcon();
				marker.setIcon('icons/newrtsizt.svg');
				markers.push(marker);
				
				index2++;
			}
		},0);
		
//		contextMenu.addItem("查看路线", function(){
//			var path2=[];
//			for(var i=0;i<path.length;i++){
//				if(wei[i]==1)
//					path2.push(path[i]);
//			}
//			showroute(path2,1,"线路1");
//			poly.hide();
//		}, 3); 
		contextMenu.addItem("取消设置", function(){
			if(marker.getIcon() == 'icons/newrtsizt.svg'){
			  	marker.setIcon(temp_icon);
				wei[ii[index]]=0;
				showPolyline(path,poly,wei);
			}
		}, 2);
		contextMenu.open(map, marker.getPosition());
		contextMenuPositon = marker.getPosition();
	});	
	}
}  
function siteOnroutes2(data,path,markers,index,poly,wei,onroute,lName){
	//console.log(path);
	var marker=new AMap.Marker({
		 position:[data.longitude,data.latitude],
		  title: data.name,	  
		  raiseOnDrag:true,
		  map: map,
		  icon:"",
		  zIndex:100
	});
	if(onroute != 1){
		console.log(data.lineId);
		if(data.lineId==null||data.lineId==""){
			 marker.setIcon('icons/satations.svg');
		}
		else if(data.lineName.indexOf("_Z")>=0){
			marker.setIcon('icons/znzd.svg');
		}
		else{
			marker.setIcon('icons/satationOnRoute.svg');
		}
	}
	if(onroute == 1){
		marker.setIcon('icons/newrtsizt.svg');
	}
	AMap.event.addListener(marker, 'click',function(e){
		var conten=SatationContent(data);
		info(marker.getPosition(),conten);
	});
	var temp_staIcon = 'icons/nowLS.svg';
	if((data.lineName.indexOf("_Z")<0)||onroute){
		AMap.event.addListener(marker, 'rightclick',function(e){
			//info();
			var contextMenu=new AMap.ContextMenu();
			contextMenu.addItem("设为经点", function() { 
				if(marker.getIcon() != 'icons/newrtsizt.svg'){
					path.push(data);
					ii[index]=index2;
					wei[ii[index]]=1;
					showPolyline(path,poly,wei);
					temp_staIcon = marker.getIcon();
					marker.setIcon('icons/newrtsizt.svg');
					markers.push(marker);
					
					index2++;
				}
			},0);
			
//			contextMenu.addItem("查看路线", function(){
//				var path2=[];
//				for(var i=0;i<path.length;i++){
//					if(wei[i]==1)
//						path2.push(path[i]);
//				}
//				showroute(path2,1,"线路1");
//				poly.hide();
//			}, 3); 
			contextMenu.addItem("取消设置", function(){
				if(marker.getIcon() == 'icons/newrtsizt.svg'){
				  	marker.setIcon(temp_staIcon);
					wei[ii[index]]=0;
					console.log(wei);
					showPolyline(path,poly,wei);
				}
			}, 2);
			contextMenu.open(map, marker.getPosition());
			contextMenuPositon = marker.getPosition();
		});	
	}
}  
var rsitesmk=[];
var terminal=[];

function showPolyline(data,poly,wei){
	var path2=[];
	poly.hide();
	console.log(data);
	console.log(wei);
	for(var i=0;i<data.length;i++){
		if(wei[i]==1)
			path2.push([data[i].longitude,data[i].latitude]);
	}
	console.log(path2);
	path2.push(terminal);
	poly.setPath(path2);
	poly.show();
}

//右键不能点击
function setroutesitesmk(data,isAll){
	if(isAll == 2){
		var car_fac=new AMap.Marker({
			 position:[fac_lon,fac_lan],
			  raiseOnDrag:true,
			  map: map,
			  icon: "icons/car_factory.svg",
			  zIndex:300
		});
		rsitesmk.push(car_fac);
	}
	else if((data.lineId==""||data.lineId==null)||isAll){
		rsitesmk.push(satationsmarker2(data));
	}
	
	
}
//隐藏
function hideroutesitesmk(){
	for(var i=0;i<rsitesmk.length;i++){
		rsitesmk[i].hide();
	}
}
//显示
function showroutesitesmk(){
	for(var i=0;i<rsitesmk.length;i++){
		rsitesmk[i].show();
	}
}
//EditRoutes(hhj_satations,pp);
function EditRoutes(sites,route,lname){
	var car_fac=new AMap.Marker({
		 position:[fac_lon,fac_lan],
		  raiseOnDrag:true,
		  map: map,
		  icon: "icons/car_factory.svg",
		  zIndex:300
	});
	var path=[];
	var markers=[];
	var wei=[];
	ii=[];
	index2=0;

	/*路线*/
	$('#suremodLsite').bind('click', function(){
		var path22=[];
		for(var i=0;i<path.length;i++){
			if(wei[i])
				path22.push(path[i]);
		}
		if(path22==null||path22==""||path22==[]||path22.length < 0){
			
			$("#linesiteNull").modal("show");
		}else{
			$("#h_creline1").modal("show");
			$("#hcre_page11").show();
			$("#hcre_page21").hide();
			h_creLine11(path22);
		}
	});
	/*路线*/
	var poly=new  AMap.Polyline({map:map});
	
	var k=0;
	for(var i=0;i<sites.length;i++){
		if(sites[i].allsite.lineName.indexOf(lname) < 0){
			siteOnroutes2(sites[i].allsite,path,markers,k,poly,wei,0,lname);
			var ss=sites[i].allsite.lineName;
			k++;
		}
	}
	
	for(var i=0;i<route.length-1;i++){
		path.push(route[i]);
		siteOnroutes2(route[i],path,markers,k,poly,wei,1,lname);
		wei[i]=1;
		ii[k]=i;
		k++;
		index2++;
	}
	showPolyline(path,poly,wei);
}
