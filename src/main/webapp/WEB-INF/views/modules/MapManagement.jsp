<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<title> Map Test </title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<style>
	html,body{
		width:100%;
		height:100%;
		margin:0;
		padding:0;
	}
	</style>
</head>

<body>
<div id="left" style="float:left;height:70%;width:5%;background-color:#00FFFF;"></div>
<div id="mapdiv" style="float:left;height:95%;width:95%;"></div>
<script language="JavaScript">




$(document).ready(function(){ 
	var list = [{Cx:116,Cy:40},{Cx:116.05,Cy:40}];
	var xy = {};
	var y=39.666821;
	var x=118.127602;
	
	 //创建一个RMap对象
	window.map = new RMap(document.getElementById("mapdiv"), 116, 40, 8, $('#mapdiv').width(), $('#mapdiv').height());
	  
	var rmap = window.map;
	
	//显示地图
	rmap.show();
	$.ajax({
		type : "POST",
		url : "mapmanagement/test",
		dataType : "json",
		async:false,
		contentType : 'application/json;charset=UTF-8',
		success : $.proxy(function(obj) {
			if (obj.success) {
				var data = obj.data;
				for ( var int = 0; int < data.latLng.length; int++) {
					
					xy.Cx = data.latLng[int].lat/1000000;
					xy.Cy = data.latLng[int].lng/1000000;
					list.push(xy);
				}
				//创建一个图形
			
			} 
		}, this),
		error : function(msg) {
			alert(msg.success);
		}
	});

	//新建一个标注
	var pointMarker = new RPointMarker( x, y, "./image/icon_0.png", 2, 2, 30, 30);
	
	//将标注添加入 rmap对象
	rmap.addMarker(pointMarker);
	
	alert(list[0].Cx);
	debugger;
	//创建一个图形
    var shape =  new RLine( list, 3, "#FF5958", 1 );
	//将图形添加到地图
	rmap.addGraphics(shape)
	//刷新地图
	rmap.refresh();

	
	
});

</script>
</body>
</html>
