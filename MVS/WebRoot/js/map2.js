/** 地图控件部分 **/
		var map = new AMap.Map('container', {
			resizeEnable: true,
			zoom:18,
			isHotspot: true,
			keyboardEnable:true,
			resizeEnable: true
		});
		 map.plugin('AMap.Geolocation', function() {
		        geolocation = new AMap.Geolocation({
		            enableHighAccuracy: true,//是否使用高精度定位，默认:true
		            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
		            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
		            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
		            buttonPosition:'RB'
		        });
		        map.addControl(geolocation);
		        geolocation.getCurrentPosition();
		        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
		        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
		    });
		    //解析定位结果
		    function onComplete(data) {
		        //alert("定位成功");
		    }
		    //解析定位错误信息
		    function onError(data) {
		        //alert("error");
		    }
		//map.setCity('成都市');
		//map.setZoom(18);
		map.plugin(["AMap.MapType"],function(){
			//地图类型切换
			var type= new AMap.MapType({
			defaultType:0 //使用2D地图
			});
			map.addControl(type);
		});
		//var hhj_satation=new Array();
		
		map.plugin(["AMap.ToolBar"],function(){
			//加载工具条
			var tool = new AMap.ToolBar({autoPosition:true});
			map.addControl(tool);    
		});
		var route2;
		//拖曳导航路径
		function Dragroute(path,satations,isadd){
			console.log("enter route-----");
			map.plugin("AMap.DragRoute",function(){
				route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE,{
						midMarkerOptions:{
							visible :false,	
						},
						startMarkerOptions:{
							visible :false,	
						},
						endMarkerOptions:{
							visible :false,	
						},
						polyOptions :{
							strokeColor:'#cc9',
							strokeOpacity:0.5
						}
					}); 
				//构造拖拽导航类，传入参数分别为：地图对象，初始路径，驾车策略
				route.search(); //查询导航路径并开启拖拽导航
				//当路径完成时的事件
				console.log("enter serach-----");
				AMap.event.addListener(route,'complete',function(e){
					//alert("OK");
					//alert(e.data.routes[0].steps[0].action);
					console.log("enter complete-----");
				    var r=e.data.routes[0].steps;
					for(i=0;i<r.length;i++){
					     //各路段描述
						console.log(e.data.routes[0].steps[i].instruction );
						//目标路程
						console.log(e.data.routes[0].distance);
						//预计时间
						console.log(e.data.routes[0].time);
						console.log("enter complete-----");
					}
					AMap.event.addListener(route,'addway',function(e){
						alert("dd");
					});	
					//参考这个格式
					//var trs=$('#result table tr');
					//trs[0].val("线路"+satations[0].route);
					if(isadd!=1){
					$('#result').css("display","inline");
					$('#addroute').css("display","none");
					var trs=document.getElementById("routenumber");
					trs.innerHTML="线路"+satations[0].route;
					var tds=document.getElementById("time-distance").getElementsByTagName("td");
					tds[0].innerHTML=e.data.routes[0].time/60+"分钟";
					tds[1].innerHTML=e.data.routes[0].distance/1000+"千米";
					var s_e=document.getElementById("start-end");
					s_e.innerHTML=satations[0].name+"-----"+satations[satations.length-1].name;
					//alert();
					}
					else{
						$('#result').css("display","none");
						$('#addroute').css("display","inline");
						$("#addroute-start input").val(e.data.origin);
						//$("#addroute-start input").val(e.data.orgin);
						$("#addroute-end input").val(e.data.destination);
						var tt=document.getElementById("addroute-time");
						tt.innerHTML=e.data.routes[0].time/60+"分钟";
						var dd=document.getElementById("addroute-distance");
						dd.innerHTML=e.data.routes[0].distance/1000+"千米";
					}
					route2=route;
					//route2.destroy();
				});
			});
		}	
		/*信息框
			参数为pos:位置，content:htmldom*/
			//var pos=([116.303843,39.983412]);
			//info(pos,document.getElementById('info'));
		var info2=new AMap.InfoWindow({offset:{x:0,y:-15}});	
		function info(position,content){
			//var info2=new AMap.InfoWindow({offset:{x:0,y:-15}});	
			info2.setContent(content);
			//alert(pos);
			info2.open(map,position);
			if(info2.getIsOpen()){
				//alert('OK');
			}
			else {
				info2.open();	
			}
		}
		//测距
		function RanginTool(){
			map.plugin(["AMap.RangingTool"],function(){ 
				 var ruler = new AMap.RangingTool(map); 
				 ruler.turnOn( ); 
			});
		}

		
		/*//多聚点图
		//聚点图，传入data数据,经纬度
		// 测试数据
		var path = new Array();
   		//path.push([116.303843, 39.983412]);
		path[0]={pos:[116.303843, 39.983412],
				 name:"1"};
		path[1]={pos:[116.321354, 39.896436],
				 name:"1"};
    	path[2]={pos:[116.407012, 39.992093],
				 name:"1"};
		path[3]={pos:[116.368904, 39.923423],
				 name:"1"};
		//markers(path);	*/
		function markers(data){
			
			var markers = []; //province见Demo引用的JS文件
			if(!data.length){
				markers[0]=setmarker(data,markers);
				markers.push(marker);
			}
			for (var i = 0; i < data.length; i += 1) {
				//console.log("testmar"+data[i]+"  "+i+data[i].lng);
				markers[i]=setmarker(data[i],markers);
				markers.push(marker);
			}
		}
		/***缩小**/
		function SatationContent(data) {  //信息窗体内容
			var s = [];
			s.push('<div class="info-title">'+data.name+'</div><div class="info-content">'+"地址：" + data.address);
			s.push("路线："+data.route);
			s.push("编号："+data.number);
			s.push("人数："+data.people);
			s.push('<div>');
			return s.join("<br>");
   		 }
		 function setmarker(data,markers){
			
				marker = new AMap.Marker({
				  position: data.lng,
				  title: data.name,	  
				  raiseOnDrag:true,
				  map: map
				});
				setListener(marker,data,markers);
			 	
				return marker;
				//地图绑定鼠标右击事件——弹出右键菜单
				
		}
		function setListener(marker,data,markers){
				var conten=SatationContent(data);
				AMap.event.addListener(marker, 'click',function (e){
					//alert(data[i].name);	
					//console.log(data);
					data=marker.getPosition();
					info(marker.getPosition(),conten);	
					
				});	 
				var ss=data;
				AMap.event.addListener(marker, 'rightclick',function(e){
					//info();
					info2.close( ) ;
					var contextMenu = new AMap.ContextMenu();  //创建右键菜单
		//右键放大
					contextMenu.addItem("移动", function() {
						marker.setDraggable(true);
					}, 0);
					contextMenu.addItem("删除", function() {
						marker.hide();
					}, 1);
					console.log("OK-----右键点击"+ss);
					contextMenu.addItem("修改", function(){EditSatation(ss);}, 2);
					contextMenu.addItem("查看路线", function() {
						var path = [];
						for(var i=0;i<markers.length;i++){
							console.log(markers[i]);
								path.push(markers[i].getPosition());
							} 		
						Dragroute(path);
					}, 3);
					contextMenu.open(map, marker.getPosition());
					contextMenuPositon = marker.getPosition();
				
				});	
		}
		
	//地图热点
	var placeSearch = new AMap.PlaceSearch();  //构造地点查询类
    var infoWindow2=new AMap.AdvancedInfoWindow({closeWhenClickMap:true});
    // map.on('hotspotclick', function(result) {
    map.on('', function(result) {
        placeSearch.getDetails(result.id, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                placeSearch_CallBack(result);
            }
        });
    });
	//回调函数
    function placeSearch_CallBack(data) { //infoWindow.open(map, result.lnglat);
        var poiArr = data.poiList.pois;
        var location = poiArr[0].location;
        infoWindow2.setContent(createContent(poiArr[0]));
        infoWindow2.open(map,location);
    }
    function createContent(poi) {  //信息窗体内容
        var s = [];
        s.push('<div class="info-title">'+poi.name+'</div><div class="info-content">'+"地址：" + poi.address);
        s.push("电话：" + poi.tel);
        s.push("类型：" + poi.type);
		s.push("监控摄像头：无");
        s.push('<div>');
        return s.join("<br>");
    }	
	//地图绑定鼠标右击事件——弹出右键菜单
	function initright(){
		var contextMenu = new AMap.ContextMenu();  //创建右键菜单
		//右键放大
		contextMenu.addItem("隐藏路径", function() {
			//map.zoomIn();
			route2.destroy();
		}, 0);
		//右键缩小	
		//右键添加Marker标记
		contextMenu.addItem("添加标记", function(e) {
			var marker = new AMap.Marker({
				map: map,
				position: contextMenuPositon //基点位置
			});
		}, 2);
	
		//地图绑定鼠标右击事件——弹出右键菜单
		map.on('rightclick', function(e) {
			contextMenu.open(map, e.lnglat);
			contextMenuPositon = e.lnglat;
		});
	}
	initright();
	function route2(lngLat1,lngLat2,type){	//传入路径经纬度,type表示类型0驾车，1步行，2公交
		var driving = new AMap.Driving({
			map: map,
			panel: "panel"
		}); 
		// 根据起终点经纬度规划驾车导航路线
		driving.search(lngLat1,lngLat2);
	}
