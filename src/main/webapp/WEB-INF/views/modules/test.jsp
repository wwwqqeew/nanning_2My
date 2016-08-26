<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>警卫任务系统后台管理系统</title>

<script  src="static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script>
    navigator.geolocation.getCurrentPosition( // 该函数有如下三个参数
        function(pos){ // 如果成果则执行该回调函数
            alert("<li>"+
                '  经度：' + pos.coords.latitude +
                '  纬度：' + pos.coords.longitude +
                '  高度：' + pos.coords.altitude +
                '  精确度(经纬)：' + pos.coords.accuracy +
                '  精确度(高度)：' + pos.coords.altitudeAccuracy +
                '  速度：' + pos.coords.speed
                +"<li>"
            );
        }, function(err){ // 如果失败则执行该回调函数
            alert("不支持");
        }, { // 附带参数
            enableHighAccuracy: false, // 提高精度(耗费资源)
            timeout: 3000, // 超过timeout则调用失败的回调函数
            maximumAge: 1000 // 获取到的地理信息的有效期，超过有效期则重新获取一次位置信息
        }
    );
</script>
</head>
<body>

</body>
</html>