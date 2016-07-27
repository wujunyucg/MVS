// JavaScript Document
//显示所有站点

	//var hhj_satation=data;
	//markers(hhj_satation);
//Dragroute(hhj_satation);
document.getElementById("addroute-number").innerHTML=document.getElementById("satation-route").innerHTML;
//搜索提醒
function autosearch(){
	var auto = new AMap.Autocomplete({
	    input: "tipinput"
	});
}

var obj={
		 "name":"null",
		 "address":"null",
		 "lng":[0,0],
		 "number":1,
		 "route":1,
		 "people":0};
var satations=hhj_satation;
var hhj_ctn=document.getElementById('addsatation-info').innerHTML;
var satation_routes=document.getElementById("satation-route").innerHTML;
//站点查询
function satation_search(){}
//获取站点数据
function getSatationinfo(){}
//添加站点
function AddSation(){}
//删除站点
function DeleteSatation(){}
//修改站点
function EditSatation(data,marker){
	document.getElementById('addsatation-info').innerHTML="";
	document.getElementById('addsatation-info').innerHTML=hhj_ctn;
	var s=[data.longitude,data.latitude];
	var ctn=document.getElementById('info-satation');
	console.log(document.getElementById('satation-lng'));
	$('#satation-lng').val(s);
	$('#satation-name').val(data.name);
	$('#satation-address').val(data.address);
	var route=$('#satation-route option');
	console.log(data.lineId);
	route[data.lineId].selected='selected';
	console.log(data.order);
	//var num=$('#satation-number');
	//console.log(data.number);
	//num[data.order].selected='selected';
	$('#satation-number').val(data.order);
	$('#satation-people').val(data.peoNum);
	info(s,ctn);
	//var sbm=document.getElementById('sbm');
	var sbm=ctn.getElementsByTagName('button');	
	//console.log(sbm);
	console.log(data);
	sbm[0].onclick=function(){
		
	
		//document.getElementById('result_satationinfo').innerHTML=data.name+","+data.address+","+data.longitude+""+data.latitude+","+data.lineId+","+","+data.siteId+","+data.peoNum+","+data.delay;
		data.lineId=parseInt(route.val());
		data.order=parseInt(num.val());
		data.name=document.getElementById('satation-name').value;
		data.address=document.getElementById('satation-address').value;
		data.peoNum=parseInt($('#satation-people').val());
		console.log(data);
		alert("修改成功");
		satationsmarker(data);
		var json=JSON.stringify(data);
		Editdata(json.toString());
		marker.hide();
		info2.close();
	};
	sbm[1].onclick=function(){
		info2.close();
	};
	console.log("4");
}
 

map.on('click', function(e) {
	console.log(e);
	//console.log(testps[0].lng+testps[1].lng);
	$("#satation-search input").attr("value","输入关键字进行查询");});
	$("#satation-search input").bind("click",function(){
	$("#satation-search input").attr("value","");
	
});

function addsatation(){
	var hhj_flag=0;
	console.log("add  "+hhj_flag);
	document.getElementById('addsatation-info').innerHTML=hhj_ctn;
	var clickEventListener = map.on('click', function(e) {
		console.log("start"+hhj_flag);
		$("#satation-search input").attr("value","输入关键字进行查询");
        //document.getElementById("infos").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();
		document.getElementById('lnglat').value=e.lnglat.getLng() + ',' + e.lnglat.getLat();
		console.log(e);
		if(hhj_flag!=1){
			
			//console.log("setamarker"+hhj_flag);
			var marker= new AMap.Marker({
					map: map,
					position: e.lnglat, //基点位置
					draggable:true,
					raiseOnDrag:true,
				});
			var ctn=document.getElementById('info-satation');
			//var lis=ctn.getElementsByTagName('input');
			//=ctn;
			var s=e.lnglat;
			//lis[1].value=s; 
			console.log(document.getElementById('satation-lng'));
			document.getElementById('satation-lng').value=s;
			var num=$('#satation-number option');
			//console.log(data.number);
			num[hhj_satation.length].selected='selected';
			console.log(s);
			console.log(ctn);
			info(e.lnglat,ctn);
			var sbm=ctn.getElementsByTagName('button');		
			var newsatation={"name":"null",
					 "address":"null",
					 "lng":[0,0],
					 "number":1,
					 "route":1,
					 "people":0};
			
			sbm[0].onclick=function(){ 
				hhj_flag=1;
				newsatation.name=document.getElementById('satation-name').value;
				newsatation.address=document.getElementById('satation-address').value;
				newsatation.lng=e.lnglat;
				newsatation.route=$("#satation-route").val();
				newsatation.number=hhj_satation.length+1;
				newsatation.people=$("#satation-people").val();
				console.log("elnglat"+e.lnglat);
				console.log("hhj_satation"+hhj_satation);
				console.log("newsatation"+newsatation+newsatation.number);
				//satations.push(newsatation);
				hhj_satation[hhj_satation.length]=newsatation;
				map.clearMap();
				markers(hhj_satation);
				console.log("hhj_satation"+hhj_satation.length);
				marker.hide();
				info2.close();
				};
				map.on('click', function(e){});
			}
		});	
}
function Showallsatation(){
	map.clearMap();
	markers(hhj_satation);
	$('#satation-search input').unbind('input oninput');
	$('#satation-search input').bind('input oninput',function(){
		console.log("search---------");
		var wei=$('#satation-search input').val();
		if(findlng(wei)>=0){
			map.setCenter(hhj_satation[findlng(wei)].lng);
			var conten=SatationContent(hhj_satation[findlng(wei)]);
			info(hhj_satation[findlng(wei)].lng,conten);
		}
		
	});
}
function sataion_infoctn(){
	  var s=document.getElementById('addsatation-info').innerHTML='<div>'+
	  			'<ul id="info-satation" style="list-style-type:none;">'+
				  '<li>&nbsp;&nbsp;&nbsp;&nbsp;地址&nbsp;<input type="text" value=""/></li>'+
				  '<li>&nbsp;经纬度&nbsp;<input type="text"  id="sataion-lng" value=""/></li>'+
				 ' <li>所属路线'+
					'<select size="1" style="margin-bottom:10px;">'+
						'<option>1</option>'+
						'<option>2</option>'+
						'<option>3</option>'+
					' </select></li>'+
				 ' <li>编号：'+
					  '<select size="1" style="margin-bottom:10px;">'+
						' <option>1</option>'+
						' <option>2</option>'+
						'<option>3</option>'+
					  '</select>'+
				  '</li>'+
	  			 ' <li><button type="submit" id="sbm">确认</button></li>'+
				 ' <li><button type="reset" id="set">修改</button></li>'+
			 '</ul> </div>';
		return s;
	}

function findlng(value){
	for(var i=0;i<hhj_satation.length;i++){
		if((hhj_satation[i].name==value)||(hhj_satation[i].number==value)){
			return i;
		}
	}
	return -1;
}
//线路管理

function addroute(){
	console.log("enter addroute"); 
	//var marker=new AMap.Marker();
	var markers=[];
	var index=1;
	var contextMenu = new AMap.ContextMenu();  //创建右键菜单
	//右键放大
				contextMenu.addItem("起点", function() {
					markers[0]= new AMap.Marker({
						map: map,
						position: contextMenuPositon, //基点位置
						draggable:true,
						raiseOnDrag:true,
					});
				 info2.close();
				 
				}, 0);
				contextMenu.addItem("途径", function(e) {
					//marker.hide();
					markers[index]=new AMap.Marker({
						map: map,
						position: contextMenuPositon, //基点位置
						draggable:true,
						raiseOnDrag:true,
					});
					index++;
					info2.close();
				}, 1);
				contextMenu.addItem("终点", function() {
					markers[index]=new AMap.Marker({
						map: map,
						position: contextMenuPositon, //基点位置
						draggable:true,
						raiseOnDrag:true,
					});
					index++;
					info2.close();
				}, 2);
				//console.log("OK-----右键点击"+ss);
				contextMenu.addItem("查看路线", function() {
					var path = [];
					var satation=[];
					
					for(var i=0;i<markers.length;i++){
					//	console.log(markers[i]);
							path.push(markers[i].getPosition());
							satation[i]={
									name:"",
									address:"",
									lng:[104.056036,30.680028],
									number:0,
									route:0,
									people:0};
							satation[i].lng=markers[i].getPosition();
						} 
					
					for(var i=0;i<index;i++){
						
					}
					Dragroute(path,satation,1);
					//$('#result').css("display","inline"); 
					
					//alert();
					
				}, 3);
				//alert("sss");
				map.on('rightclick',function(e){
				//	alert("sss");
					contextMenu.open(map,e.lnglat);
					contextMenuPositon = e.lnglat;
				});
}



function showAllRoute(all_satations){
	map.clearMap();
	var flag=1;
	console.log("start show routes");
	//$('#satation-search input').val("输入线路编号进行查询");
	$('#satation-search input').unbind('input oninput');
	markers(all_satations);
	
	var routes=new Array();
	var route_satations=new Array();
	//var path =new Array();
	for(var i=0;i<25;i++){
		routes[i]=new Array();
		route_satations[i]=new Array();
		//routes[i][0]=obj;
		}
	for(var i=0;i<hhj_satation.length;i++){
		console.log(hhj_satation[i]);
		//console.log(hhj_satation[i].route+hhj_satation[i].number);
		routes[hhj_satation[i].route].push(hhj_satation[i].lng);
		route_satations[hhj_satation[i].route].push(hhj_satation[i]);
		}
	//showroute(1);
	function showroute(i){
		console.log(routes[i]);
		//route2.destroy();
		Dragroute(routes[i],route_satations[i],0);
		
		//driving(routes[i]);
		//showroutesinfo(routes[i]);
	}

	function hideroute(){
		route2.destroy();
	}
	
	//$('#satation-route').show();
	$('#satation-search input').bind('input oninput',function(){
		console.log("ok show route");
		console.log($(this).val()); 
		if($(this).val()<20&&$(this).val()>0){
			if(flag!=1)
				hideroute();
			console.log($(this).val());
			var index=$(this).val()
			showroute(index);
			flag=2; 
		}
	});
}
$('#manager-satation-btn').bind('click',function (){
	//alert("OK");
	$('#result').css('display','none');
	$('#addroute').css('display','none');
	//$('#satation-search input').val("输入站点编号或名称进行查询");
	//$('#satation-search input').unbind('input oninput');
	map.clearMap();
});

