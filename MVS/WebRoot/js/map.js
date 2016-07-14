// JavaScript Document
//显示所有站点

	//var hhj_satation=data;
	//markers(hhj_satation);
var satations=hhj_satation;
var path =new Array();
for(var i=0;i<25;i++){
	path[i]=new Array();
	}
for(var i=0;i<hhj_satation.length;i++){
	console.log(hhj_satation[i]);
	console.log(hhj_satation[i].route+hhj_satation[i].number);
    	path[hhj_satation[i].route].push(hhj_satation[i].lng);
	}
function showroute(i){
	Dragroute(path[i]);
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
var hhj_ctn=document.getElementById('addsatation-info').innerHTML;
function addsatation(){
	var hhj_flag=0;
	console.log("add  "+hhj_flag);
	document.getElementById('addsatation-info').innerHTML=hhj_ctn;
	var clickEventListener = map.on('click', function(e) {
		console.log("start"+hhj_flag);
        //document.getElementById("infos").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();
		document.getElementById('lnglat').value=e.lnglat.getLng() + ',' + e.lnglat.getLat();
		console.log(e);
		if(hhj_flag!=1){
			console.log("setamarker"+hhj_flag);
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
			//alert(lis[0].value);
			console.log(s);
			console.log(ctn);
			info(e.lnglat,ctn);
			/*var placeSearch = new AMap.PlaceSearch({location:e.lnglat});  //构造地点查询类
		//详情查询
			placeSearch.getDetails("B000A83U0P", function(status, e) {
				if (status === 'complete' && e.info === 'OK') {
					 var poiArr = e.poiList.pois;
					 var location = poiArr[0].location;
					 console.log(poiArr[0]);
					///s alert("OK");
				}
			});*/
			var sbm=ctn.getElementsByTagName('button');		
			var newsatation=hhj_satation[0];
			sbm[0].onclick=function(){ 
				hhj_flag=1;
				newsatation.name=document.getElementById('satation-name').value;
				newsatation.address=document.getElementById('satation-address').value;
				newsatation.lng=e.lnglat;
				newsatation.route=$("#satation-route").val();
				newsatation.number=$("#satation-number").val();
				newsatation.people=$("#satation-people").val();
				console.log("elnglat"+e.lnglat);
				console.log("hhj_satation"+hhj_satation);
				console.log("newsatation"+newsatation+newsatation.number);
				//satations.push(newsatation);
				hhj_satation[hhj_satation.length]=newsatation;
				markers(newsatation);
				console.log("hhj_satation"+hhj_satation.length);
				marker.hide();
				info2.close();
				};
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