<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<link rel="stylesheet" language='text/css' href="styles/layout.css"/>-->

<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<link rel="stylesheet" href="styles/maplayout.css"/>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    
    <script src="http://webapi.amap.com/js/marker.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=12f941dddbe64260f57468811bb77c77&plugin=AMap.DistrictSearch,AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.Driving,AMap.MapType"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>

<body>
    <div id='container'>
    
    </div>
    <div id="info"><h1><h1></div>
     <div id="myPageTop" style="position: absolute; top:175px; right:100px;" >
    
    <div id="panel"></div>
    </div>
    <div id="result"></div>
    <div>
        <ul  id="routes" style="position: absolute;">
            <li><button >线路1</button></li>
            <li><button >线路2</button></li>
            <li><button >线路3</button></li>
        </ul>
    </div>
    <div style="position: absolute;">
        <!--站点管理-->
        <ul id="satation">
            <li>站点管理</li>
            <ul >
                <li><button onclick="addsatation()">添加站点</button></li>
                <li>删除站点</li>
                <li><button onclick="map.clearMap();markers(hhj_satation)">显示所有</button></li>
                <li>查询站点</li>
            </ul>
        </ul>
        <ul id="manage_route">
            <li>路线管理</li>
            <ul >
                <li>添加路线</li>
                <li>删除路线</li>
                <li>详细信息</li>
                <li>查询路线</li>
            </ul>
            <li><input id="lnglat" value=''/></li>
        </ul>
      </div>
      <div id="addsatation-info" style="position: absolute;margin-top:480px;display:none;">	 
         <ul id="info-satation">
              <li>&nbsp;&nbsp;名称&nbsp;<input type="text" value="" id="satation-name"/></li>
              <li>&nbsp;&nbsp;地址&nbsp;<input type="text" value="" id="satation-address"/></li>
              <li>&nbsp&nbsp经纬度<input type="text" id="satation-lng"/></li>
              <li>&nbsp&nbsp乘坐人数<input type="text" id="satation-people"/></li>
              <li>所属路线
              	<select size="1" style="margin-bottom:10px;" id="satation-route">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                 </select></li>
              <li>编号：
                  <select size="1" style="margin-bottom:10px;" id="satation-number">
                     <option value="1">1</option>
                     <option value="2">2</option>
                    <option value="3">3</option>
                  </select>
              </li>
              <li><button type="submit" id="sbm">确认</button></li>
              <li><button type="reset" id="set">修改</button></li>
         </ul>                 
      </div>
    <script type="text/javascript" src="js/satation.js"></script>
    <script type="text/javascript" src="js/map2.js"></script>
    <script type="text/javascript" src="js/map.js"></script>
    
</body>
</html>
