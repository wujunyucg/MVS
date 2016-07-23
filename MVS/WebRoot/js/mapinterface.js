/**
 * 
 */
//设置地图中心，把地图拉到中心
//参数：loction:[],
var loc=[104.11,30.25];moveTocenter(loc);
function moveTocenter(loction){
	map.setCenter(loction);
}