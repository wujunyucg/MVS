var map = new AMap.Map('container', {
			resizeEnable: true,
			zoom:11,
			center: [116.397428, 39.90923],
			isHotspot: true,
			keyboardEnable:true
		});
		map.setCity('成都市');
		map.setZoom(18);
		map.plugin(["AMap.MapType"],function(){
			//地图类型切换
			var type= new AMap.MapType({
			defaultType:0 //使用2D地图
			});
			map.addControl(type);
		});
		
		map.plugin(["AMap.ToolBar"],function(){
			//加载工具条
			var tool = new AMap.ToolBar({autoPosition:true});
			map.addControl(tool);    
		});// JavaScript Document
		function Mousetools(){
			map.plugin(["AMap.MouseTool"],function(){ 
				var mousetool = new AMap.MouseTool(map); 
				 //使用鼠标工具，在地图上画标记点
				 ruler2.turnOff();
				//var marker=document.getElementById("#marker_kinds").getElementsByTagName("img");
				var marker=document.getElementById('marker_kinds').getElementsByTagName('img');
				if(marker[0].onclick=(function(e){
						marker_img_init();			
						marker[0].src="images/dian_on.png";
						mousetool.marker({
						draggable:true,
						clickable:true
					});})){}
					 
				 //折线
				 if(marker[2].onclick=(function(e){
					 marker_img_init();			 
					 marker[2].src="images/zx_on.png";
				 	 mousetool.polyline({
					 });})){}
					 
				 //多边形
				 if(marker[3].onclick=(function(e){			
					 marker_img_init();
					 marker[3].src="images/dbx_on.png";
					 mousetool.polygon();})){}
				 //矩形
				 
				// mousetool.rectangle();
				 //圆形
				 if(marker[1].onclick=(function(e){				 
					 marker_img_init();
					 marker[1].src="images/area_on.png";
				 	 mousetool.circle();})){}
				 //测距
				 if($('#disdance').click(function(e){
					 //mousetool.rule();
				 	mousetool.close(false);
				 	startRuler2();
				 	fun_imgs();
					 $('#disdance').attr("src","images/disdance_on.png");
				 })){}
				 //面积
				 if($('#area').click(function(e){
					  ruler2.turnOff();	
				 	mousetool.measureArea();		
				 	fun_imgs();
				 	$('#area').attr("src","images/area_on.png");})){}
				
				 
				 //mousetool.close();
			});
		}
		//启用自定义样式测距
		var ruler1,ruler2;
		map.plugin(["AMap.RangingTool"], function() {
       // ruler1 = new AMap.RangingTool(map);
      //  AMap.event.addListener(ruler1, "end", function(e) {
         //   ruler1.turnOff();
       // });
        var sMarker = {
            icon: new AMap.Icon({
                size: new AMap.Size(19, 31),//图标大小
                image: "images/on_mark_b1.png"
            })
        };
        var eMarker = {
            icon: new AMap.Icon({
                size: new AMap.Size(19, 31),//图标大小
                image: "images/mark_b2.png"
            }),
            offset: new AMap.Pixel(-9, -31)
        };
        var lOptions = {
            strokeStyle: "solid",
            strokeColor: "#FF33FF",
            strokeOpacity: 1,
            strokeWeight: 2
        };
        var rulerOptions = {startMarkerOptions: sMarker, endMarkerOptions: eMarker,startLabelText:"起始点", 			lineOptions: lOptions};
        ruler2 = new AMap.RangingTool(map, rulerOptions);
    });
    	function startRuler2() {
        	//ruler1.turnOff();
        	ruler2.turnOn();
    	}
		//鼠标标记//
		
		/*点标记
		 参数：data：positon:[经纬度],
		 			name:name
		
		var d={position:[116.303843, 39.983412],
				name:"marker"};
		point_marker(d)
		*/
		function point_marker(data){
			//var d=([116.303843, 39.983412]);
			var pmarker=new AMap.Marker({
				map:map,
				position:data.position,
				title:data.name,
				//content:document.getElementById('hello')
				}
			);
			
			//
			AMap.event.addListener(pmarker,'click',function (data){
				pmarker.setLabel({content:data.name,
								  offset:(-2,-3)});
				pmarker.setTitle(data.name);
			});
			AMap.event.addListener(pmarker,'dblclick',function(e){
				//alert("hello");

			});
		}
		//可编辑线段
		/*参数为：线路数组
		var arr=new Array();//经纬度坐标数组 
			arr.push(new AMap.LngLat("104.085283","30.672659")); 
			arr.push(new AMap.LngLat("104.086485","30.644602")); 
			arr.push(new AMap.LngLat("104.047861","30.647703")); 
			arr.push(new AMap.LngLat("104.110689","30.653315")); 
		editLine(arr);
		var arr2=new Array();//经纬度坐标数组 
			arr2.push(new AMap.LngLat("104.148626","30.674873")); 
			arr2.push(new AMap.LngLat("104.164762","30.663504")); 
			arr2.push(new AMap.LngLat("104.185362","30.658927")); 
			arr2.push(new AMap.LngLat("104.180898","30.632639")); 
		editLine(arr2);		
		*/
		function editLine(arr){ 
					 
			//定义折线对象
			polyline=new AMap.Polyline({
				path:arr,     //设置折线的节点数组
				strokeColor:"#F00",
				strokeOpacity:0.4,
				strokeWeight:3,
				strokeStyle:"dashed",
				strokeDasharray:[10,5]
			}); 
			 
			polyline.setMap(map);//地图上添加折线 
			 
			//构造折线编辑对象，并开启折线的编辑状态
			map.plugin(["AMap.PolyEditor"],function(){
				polylineEditor = new AMap.PolyEditor(map,polyline); 
				polylineEditor.open(); 
			});                                  
		}
		
		/*折线标记，不可拖拽*/
		var path = [];
   		//path.push([116.303843, 39.983412]);
		path[0]=([116.303843, 39.983412]);
		path[1]=([116.321354, 39.896436]);
    	path[2]=([116.407012, 39.992093]);
		path[3]=([116.303843, 39.983412]);
		//Polyline_marker(path);
		function Polyline_marker(paths){
			var polyline=new AMap.Polyline({
				map:map,
				path:paths
			});
			//polyline.setPath(data);
			AMap.event.addListener(polyline,'mouseover',function(paths){
				
			});
			AMap.event.addListener(polyline,'mouseover',function(paths){
				
			});
		}
		//多边形参数为多边形的每个顶点,有填充颜色
		//Polygon_marker(path);//测试数据
		function Polygon_marker(paths){
			var Polygon=new AMap.Polygon({
				map:map,
				path:paths
			});
			//polyline.setPath(data);
			AMap.event.addListener(Polygon,'mouseover',function(paths){
				//alert(Polygon.getArea( ));//获取当前区域面积
			});
			AMap.event.addListener(Polygon,'mouseover',function(paths){
				
			});
		}
		
		//圆形标记，参数为圆心，半径
		/*测试数据
		var data=map.getCenter();
		Circle_marker(data,1000);*/
		function Circle_marker(data,radio){
				var circle=new AMap.Circle({
					map:map,
					center:data,
					radius:radio					
				});
		}
		
		//拖曳导航路径
		function Dragroute(path){
			map.plugin("AMap.DragRoute",function(){
				route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE); 
				//构造拖拽导航类，传入参数分别为：地图对象，初始路径，驾车策略
				route.search(); //查询导航路径并开启拖拽导航
				//当路径完成时的事件
				AMap.event.addListener(route,'complete',function(e){
					//alert("OK");
					//alert(e.data.routes[0].steps[0].action);
				    var r=e.data.routes[0].steps;
					for(i=0;i<r.length;i++){
					     //各路段描述
						console.log(e.data.routes[0].steps[i].instruction );
						//目标路程
						console.log(e.data.routes[0].distance  );
						//预计时间
						console.log(e.data.routes[0].time  );
						
					}
					//参考这个格式
				});
			});
		}
		//测试
		//Dragroute(path);
		
		//麻点图，参数为查询的名称
		//Md_marker('医院');
		function Md_marker(name){
			map.plugin('AMap.PlaceSearchLayer', function (){
				var searchLayer = new AMap.PlaceSearchLayer({
					keywords : name //搜索关键字为“超市”的poi
					});
				//将海量麻点叠加在地图上
				searchLayer.setMap(map);
				//AMap.event.addListener(searchLayer, 'complete', 'complete', onComplete);
				AMap.event.addListener(searchLayer, 'click',function (e){
					alert(e.content[0].name);	
					info(e.lnglat,document.getElementById('info'));	
				});
				AMap.event.addListener(searchLayer, 'mousemove',function (e){
					//alert(e.lnglat);
					//info(e.lnglat,document.getElementById('info'));	
				});
			});
			
		}
		
		/*信息框
			参数为pos:位置，content:htmldom*/
			//var pos=([116.303843,39.983412]);
			//info(pos,document.getElementById('info'));
		
		function info(position,content){
			var info=new AMap.InfoWindow();	
			info.setContent(SetContent(position));
			
			//alert(pos);
				info.open(map,position);
				if(info.getIsOpen()){
					//alert('OK');
				}
				else {
					info.open();	
				}
			
			return info;
		}
		//测距
		function RanginTool(){
			map.plugin(["AMap.RangingTool"],function(){ 
				 var ruler = new AMap.RangingTool(map); 
				 ruler.turnOn( ); 
			});
		}

		
		/*多聚点图
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
		markers(path);
		*/
		function markers(data){
			
			var markers = []; //province见Demo引用的JS文件
			for (var i = 0; i < data.length; i += 1) {
					//alert(data[i].center);
					
				marker = new AMap.Marker({
				  position: data[i].pos,
				  title: data[i].name,
				  map: map
				});
			 	AMap.event.addListener(marker, 'click',function (e){
					//alert(data[i].name);	
					info(e.lnglat,document.getElementById('info'));	
				});
				markers.push(marker);
				}
		}
		//搜索栏
		map.plugin(["AMap.Autocomplete"],function(){
		var auto = new AMap.Autocomplete({
        input: "tipinput"
		});
		AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
			});
		
		function select(e) {
			if (e.poi && e.poi.location) {
				map.setZoom(15);
				map.setCenter(e.poi.location);
			}
		}
		
	//地图热点
	var placeSearch = new AMap.PlaceSearch();  //构造地点查询类
    var infoWindow2=new AMap.AdvancedInfoWindow({});
    map.on('hotspotclick', function(result) {
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
	function SetContent(pos){
		var s = [];
		var poi={
				name:"站点3",
				address:"金牛区",
				num:03,
				line:3,
				count:12
			};
        s.push('<div class="info-title">'+poi.name+'</div><div class="info-content">'+"地址：" + poi.address);
        s.push("编号：" + poi.num);
        s.push("路线：" + poi.line);
		s.push("人数："+poi.count);
        s.push('<div>');
        return s.join("<br>");	
	}
	//获取经纬度
		//周边检索
	function search_(str){
		var searchOptions = {
			map: map,
			panel: 'panel',
			keywords: str,
			pageSize: 100,
			orderBy: '_id:ASC'
		};
		//加载CloudDataSearch服务插件
		AMap.service(["AMap.CloudDataSearch"], function() {
			search = new AMap.CloudDataSearch('56a46b0f305a2a328839130f', searchOptions); //构造云数据检索类
			search.searchByDistrict('成都');
		});
		
	}
	
	//地图绑定鼠标右击事件——弹出右键菜单
	
	function initright(){
		var contextMenu = new AMap.ContextMenu();  //创建右键菜单
		//右键放大
		contextMenu.addItem("放大一级", function() {
			map.zoomIn();
		}, 0);
		//右键缩小
		contextMenu.addItem("缩小一级", function() {
			map.zoomOut();
		}, 1);
		//右键显示全国范围
		contextMenu.addItem("缩放至全国范围", function(e) {
			map.setZoomAndCenter(4, [108.946609, 34.262324]);
		}, 5);
		contextMenu.addItem("缩放至全市范围", function(e) {
			//alert(e.lnglat);
			map.setZoomAndCenter(11, map.getCenter());
		}, 3);
		contextMenu.addItem("缩放至全省范围", function(e) {
			map.setZoomAndCenter(7,map.getCenter());
		}, 4);
		
		//右键添加Marker标记
		contextMenu.addItem("设为站点", function(e) {
			var marker = new AMap.Marker({
				map: map,
				position: contextMenuPositon, //基点位置
				draggable: true,
			});
			marker.setTitle("站点3");
			var inf=new AMap.InfoWindow();	
			AMap.event.addListener(marker, 'click',function (e){
					console.log("click");
					inf=info(e.lnglat,document.getElementById('info'));
					marker.draggable=false;
				});
			AMap.event.addListener(marker, 'dblclick',function (e){
					console.log("dblclick");
					inf=info(e.lnglat,document.getElementById('info'));
					marker.setDraggable(false);
				});
			AMap.event.addListener(marker, 'mouseover',function (e){
					console.log("mouseover");
					//info(e.lnglat,null);
					inf=info(e.lnglat,document.getElementById('info'));
					//inf.close();
				});
			AMap.event.addListener(marker, 'mouseout',function (e){
					console.log("mouseout");
					//info(e.lnglat,null);
					inf.close();
				});	
			AMap.event.addListener(marker, 'rightclick',function (e){
					console.log("rightclick");
					marker.setDraggable(true);
					inf.close;
					var contextMenu = new AMap.ContextMenu();  //创建右键菜单
			//		contextMenu.addItem()
				});
		}, 2);
	
		//地图绑定鼠标右击事件——弹出右键菜单
		map.on('rightclick', function(e) {
			contextMenu.open(map, e.lnglat);
			contextMenuPositon = e.lnglat;
		});
	}
	var amapAdcode = {};
    amapAdcode._district = new AMap.DistrictSearch({//高德行政区划查询插件实例
        subdistrict: 1   //返回下一级行政区
    });
    amapAdcode._overlay = [];//行政区划覆盖物
    amapAdcode.createSelectList = function(selectId, list) {//生成下拉列表
        var selectList = document.getElementById(selectId);
        selectList.innerHTML = '';
        selectList.add(new Option('--请选择--'));
        for (var i = 0, l = list.length, option; i < l; i++) {
            option = new Option(list[i].name);
            option.setAttribute("value", list[i].adcode)
            selectList.add(option);
        }
    }
    amapAdcode.search = function(adcodeLevel, keyword, selectId) {//查询行政区划列表并生成相应的下拉列表
        var me = this;
        if (adcodeLevel == 'district'||adcodeLevel == 'city') {//第三级时查询边界点
            this._district.setExtensions('all');
        } else {
            this._district.setExtensions('base');
        }
        this._district.setLevel(adcodeLevel); //行政区级别
        this._district.search(keyword, function(status, result) {//注意，api返回的格式不统一，在下面用三个条件分别处理
            var districtData = result.districtList[0];
            if (districtData.districtList) {
                me.createSelectList(selectId, districtData.districtList);
            } else if (districtData.districts) {
                me.createSelectList(selectId, districtData.districts);
            } else {
                document.getElementById(selectId).innerHTML = '';
            }
            map.setCenter(districtData.center);
            me.clearMap();
            me.addPolygon(districtData.boundaries);
        });
    }
    amapAdcode.clearMap = function(selectId) {//清空地图上的覆盖物
        map.remove(this._overlay);
        this._overlay = [];
    }
    amapAdcode.addPolygon = function(boundaries) {//往地图上添加覆盖物
        if (boundaries) {
            for (var i = 0, l = boundaries.length; i < l; i++) {
                //生成行政区划polygon
                var polygon = new AMap.Polygon({
                    map: map,
                    path: boundaries[i],
					fillOpacity: 0.2,
                    fillColor: '#CCF3FF',
                    strokeColor: '#CC66CC'
                });
                this._overlay.push(polygon);
            }
            map.setFitView();//地图自适应
        }
    }
    amapAdcode.clear = function(selectId) {//清空下拉列表
        var selectList = document.getElementById(selectId);
        selectList.innerHTML = '';
    }
    amapAdcode.createProvince = function() {//创建省列表
        this.search('country', '中国', 'province');
    }
    amapAdcode.createCity = function(provinceAdcode) {//创建市列表
        this.search('province', provinceAdcode, 'city');
        this.clear('district');
        this.clear('biz_area');
    }
    amapAdcode.createDistrict = function(cityAdcode) {//创建区县列表
        this.search('city', cityAdcode, 'district');
        this.clear('biz_area');
    }
    amapAdcode.createBiz = function(districtAdcode) {//创建商圈列表
        this.search('district', districtAdcode, 'biz_area');
    }
	amapAdcode.createProvince();
	   var clickEventListener = map.on('click', function(e) {
       // document.getElementById("infos").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();
		document.getElementById('lnglat').value=e.lnglat.getLng() + ',' + e.lnglat.getLat();
		
    });
	//路线查询
	$('#route').click(function(){
		fun_imgs();
		$('#route').attr("src","images/route_on.png");
		right();	
	});
	function route2(lngLat1,lngLat2,type){	//传入路径经纬度,type表示类型0驾车，1步行，2公交
		var driving = new AMap.Driving({
			map: map,
			panel: "panel"
		}); 
		// 根据起终点经纬度规划驾车导航路线
		driving.search(lngLat1,lngLat2);
	}
	function dragroute(path){		//传入路径经纬度
		map.plugin("AMap.DragRoute", function() {
			route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE,true); //构造拖拽导航类
			route.search(); //查询导航路径并开启拖拽导航
		/*	document.getElementById('route_condition').onclick=function(){
				var str=route.getWays() ;
				route2(path[0],str[0],0);
				for(var i=0;i<str.length-1;i++){
					route2(str[i],str[i+1],0);	
				}
				route2(str[str.length-1],path[path.length-1],0);
				var driving = new AMap.Driving({
				data:reslut,
				map: map,
				panel: "panel"
			}); 
			// 根据起终点经纬度规划驾车导航路线
			//driving.search(str[0],route[0]);
			//route2(path[0],str[0],0);
				for(var i=0;i<str.length-1;i++){
					route2(str[i],str[i+1],0);	
				}
				route2(str[str.length-1],path[path.length-1],0);
				}*/
				var str=route.getWays();
				var paths=[];
				paths[0]=path[0];
				for(var i=0;i<str.length;i++){
					paths[i+1]=str[i];
				}
				paths[paths.length]=path[length-1];
				//alert(str);
				//routeinfo(str);
				var contextMenu = new AMap.ContextMenu();  //创建右键菜单
				contextMenu.addItem("查看路径", function(e) {
					document.getElementById('panel').style.display="inline";
					routeinfo(paths);
				});
				map.on('rightclick', function(e) {
					contextMenu.open(map, e.lnglat);
					contextMenuPositon = e.lnglat;
				});
			});
	}
	function routeinfo(path){
		for(var i=0;i<=path.length-1;i++){
					route2(path[i],path[i+1],0);	
				}
		
	}
	
	function right(){
		//设置右键菜单
		var path = [];
		var contextMenu = new AMap.ContextMenu();  //创建右键菜单
		
		//右键添加Marker标记
		var marker1= new AMap.Marker({
				map: map,
				//position: contextMenuPositon, //基点位置
				draggable: true,
				visible:false
			});
		var marker2= new AMap.Marker({
				map: map,
			//	position: contextMenuPositon, //基点位置
				draggable: true,
				visible:false
			});
		var marker3= new AMap.Marker({
				map: map,
			//	position: contextMenuPositon, //基点位置
				draggable: true,
				visible:false
			});
		var marker4= new AMap.Marker({
				map: map,
			//	position: contextMenuPositon, //基点位置
				draggable: true,
				visible:false
			});
		contextMenu.addItem("起点", function(e) {
			/*var marker = new AMap.Marker({
				map: map,
				position: contextMenuPositon, //基点位置
				draggable: true
			});*/
			marker1.setPosition(contextMenuPositon);
			marker1.show();
			//path[0]=(marker1.getPosition());
			if(path.length>1){
				while(path.length>1)
					var ee=path.pop();
			}
		}, 0);
		contextMenu.addItem("经点", function(e) {
			/*(var marker = new AMap.Marker({
				map: map,
				draggable: true,
				position: contextMenuPositon //基点位置
			});*/
			marker2.setPosition(contextMenuPositon);
			marker2.show();
			//path[0]=(marker1.getPosition());
			//path[path.length]=(contextMenuPositon);
		}, 1);
		contextMenu.addItem("终点", function(e) {
			/*var marker = new AMap.Marker({
				map: map,
				draggable: true,
				position: contextMenuPositon //基点位置
			});*/
			marker3.setPosition(contextMenuPositon);
			marker3.show();
			//path[path.length]=(marker.getPosition());
		}, 2);
		//右键放大
		
		contextMenu.addItem("放大一级", function() {
			map.zoomIn();
		}, 4);
		//右键缩小
		contextMenu.addItem("缩小一级", function() {
			map.zoomOut();
		}, 5);
		contextMenu.addItem("导航", function() {
			//map.zoomOut();
			var str="marker";
			//for(var i=0;i<)
			path[0]=(marker1.getPosition());
			path[1]=(marker2.getPosition());
			path[2]=(marker3.getPosition());
			//path[3]=(marker4.getPosition());
			dragroute(path);
			marker1.hide();
			marker2.hide();
			marker3.hide();
		}, 3);
		//地图绑定鼠标右击事件——弹出右键菜单
		map.on('rightclick', function(e) {
			contextMenu.open(map, e.lnglat);
			contextMenuPositon = e.lnglat;
		});
	}