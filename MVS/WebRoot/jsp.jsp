<div id='container' style="margin-left:255px;margin-top:50px;width:86.5%;height: 95%"></div>
			
			--------
			<div id="info">
				<h1>
					<h1>
			</div>
			<div id="myPageTop"
				style="position: absolute; top:175px; right:100px;">
				<div id="panel"></div>
			</div>
			<div id="addroute" style="position:absolute;margin-left:55px;margin-top:105px;background-color:#fff;width:180px;display:none">
				<ul>
					<li>路线<select size="1" id="addroute-number"></select></li>
					<li id="addroute-start">起点<input type="text" value=""/></li>
					<li id="addroute-end">终点<input type="text" value=""/></li>
					<li >用时<span id="addroute-time">111</span></li>
					<li >距离<span id="addroute-distance">111</span></li>
					<li id="addroute-sbm"><button class="button-left">确认</button></li>
					<li id="addroute-quit"><button class="button-right">取消</button></li>
				</ul>
			</div>
			<div id="result" style="position:absolute;margin-left:55px;margin-top:105px;background-color:#fff;width:180px;display:none">		
					<span id="routenumber",align="center" style="width:90%;margin:0px 10px;text-align:center">线路</span></br>	
					<span id="start-end" style="width:90%;margin:0px 10px;">起点---终点</span>
					<table style="width:90%;margin:0px 10px;">
					<tr >
						<td>时间</td>
						<td>距离</td>
					</tr>
					<tr id="time-distance">
						<td>155</td>
						<td>155</td>
					</tr>
				</table>
			</div>
			<div>
        <ul  id="routes" style="position: absolute;display:none;">
            <li><button >线路1</button></li>
            <li><button >线路2</button></li>
            <li><button >线路3</button></li>
        </ul>
    </div>
	<div style="position: absolute;margin-left:355px;margin-top:50px;">
        <!--站点管理-->
        <ul id="satation" style="display:none;">
            <li>站点管理</li>
            <ul >
                <li><button onclick="addsatation()">添加站点</button></li>
                <li>删除站点</li>
                <li><button onclick="map.clearMap();markers(hhj_satation)">显示所有</button></li>
                <li>查询站点</li>
            </ul>
        </ul>
        <ul id="manage_route" style="display:none">
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
      <div id="satation-search" style="position:absolute;margin-left:65px;margin-top:80px;width:180px" autoComplete='off'>
      	<input type="text" id="tipinput" value="输入关键字进行查询" />
      	
      </div>
      
      <div id="addsatation-info" style="position: absolute;margin-top:480px;display:none;">	 
         <ul id="info-satation" style="list-style-type:none;">
              <li>&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;&nbsp;名称&nbsp;<input type="text" value="" id="satation-name"/></li>
              <li>&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp;&nbsp;地址&nbsp;<input type="text" value="" id="satation-address"/></li>
              <li>&nbsp;&nbsp;&nbsp;经纬度 &nbsp;<input type="text" id="satation-lng"/></li>
              <li>乘坐人数&nbsp;<input type="text" id="satation-people"/></li>
              <li>所属路线
              	<select size="1" style="margin-bottom:10px;" id="satation-route">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                 </select></li>
              <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp编号
                  <select size="1" style="margin-bottom:10px;" id="satation-number">
                     <option value="1">1</option>
                     <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                     <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                     <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                     <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                     <option value="15">15</option>
                    <option value="16">16</option>
                  </select>
              </li>
              <li style="float:left;margin-left:30%;width:20%"><button type="submit" id="sbm">确认</button></li>
              <li style="float:right;margin-right:30%;width:20%""><button type="reset" id="set">修改</button></li>
         </ul>                 
      </div>
      <div id="route-info">
      
      </div>
      <div id='panel'></div>
      </div>
      
     <div id="collasped" class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"  style="position:fixed;bottom:0px;right:0px;width: 86.5%"> 
  <div class="panel panel-default">
    <a id="updown" class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree" >
    <div class="panel-heading" role="tab" id="headingThree" style="background-color:#000000;">
        <img id="updownimg" src="images/up.png">
    </div>
     </a>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body" style="height: 200px">
     	<table class="table">
		<thead class="fixedThead">
			<tr><th>#</th><th>站点名称</th><th>站点名称</th>
			<th>站点地址</th><th>站点人数</th><th>站点所属线路</th>
			</tr>
		</thead>
		<tbody class="scrollTbody">
			
		</tbody>
	</table>
      </div>
    </div>
  </div>
</div>