<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>轨迹管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    
 <style type="text/css">
	a{text-decoration:none;background:none;}
	a:hover{background:#CCCCCC; }
</style>
</head>
<body>

<!-- id="trackMain" 自定义 -->
<div class = "main" id="trackMain" >
 	<div class = "search-container">
 	<form class="FindData-form" id = "FindData-form">
 		<div class=" search-top">轨迹管理</div>
			<div class = "searchItem">汽车编号:<input type="text" class="search-input" name="carCode"><!-- 这里识别用的是name 自定义 --></div>
			<div class = "searchItem">地址:<input type="text" class="search-input" name="address"></div>
		<div class = "searchSub-item">
			<input type="button" class="search-btn btn-base" value="查询">
		</div>
		</form>
 	</div>
 	
 <div class = "item-list">
 <div class = "item-btn">
 <shiro:hasPermission name="user:create">
   <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/create">添加</a> --%>
   <div class = "btn-container">
     	<a class='linkEdit btn-base add-demo'  href='javascript:void(0);'>添加</a>
     </div>
  
</shiro:hasPermission>
 <shiro:hasPermission name="user:update">
     <%-- <a  href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/update">修改</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base edit-operate'  href='javascript:void(0);'>修改</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:delete">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/delete">删除</a> --%>
       <div class = "btn-container">
     	<a class='linkEdit btn-base delete-operate' href='javascript:void(0);'>删除</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:update">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base' onclick='reset()' href='javascript:void(0);'>初始化密码</a>
     </div>
 </shiro:hasPermission>
 
  <shiro:hasPermission name="user:show">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base show-operate' href='javascript:void(0);'>查看</a>
     </div>
 </shiro:hasPermission>
 
   <shiro:hasPermission name="user:excel">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base  export-Excel' href='javascript:void(0);'>导出</a>
     </div>
 </shiro:hasPermission>

   <shiro:hasPermission name="user:excel">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<a class='linkEdit btn-base  imPort-Excel' href='javascript:void(0);'>导入</a>
     </div>
 </shiro:hasPermission>

 </div>
	<table cellspacing="0" cellpadding="0" class="demo-table" id="track-table"><!-- 这里识别用的是id， 自定义 -->
			<thead>
				<tr>
					<th class="th-short"><label><input class="all-select" type="checkbox" ></label></th>
					<th class="th-short">编号</th>
							<th>汽车编号</th>
							<th>地址</th>
							<th>经度</th>
							<th>维度</th>
							<th>日期</th>
							<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody ></tbody>
		</table>
	<div class='item-result-operate'>
		<div class='item-result-pages' id='item-result-pages'></div>
	</div>
 </div>
	<div class="yemian1" id="newpage1" ></div>
	<div class="export-main"></div>
</div>

<script type="text/javascript">
(function($, exports) {
	var TrackModule = new exports.Modle("trackMain","trackmanagement","edittrack","track-table","track");
	//debugger;
	//构建获取的每一行
	TrackModule.buildHtmlBody = function(date){
		var htm = "";
    	for ( var i = 0; i < date.length; i++) {
    		htm += ("<tr data='' id='"+date[i].id+"' class='' ><td><input type='checkbox' class='checkboxId'></td><td class='show-entity'>"+(i+1)+"</td>"
	          + "<td>"+(date[i].carCode || "")+"</td>"
	          + "<td>"+(date[i].address || "")+"</td>"
	          + "<td>"+(date[i].lat || "")+"</td>"
	          + "<td>"+(date[i].lng || "")+"</td>"
	          + "<td>"+(date[i].tdate || "")+"</td>"
	          + "<td>"+(date[i].type || "")+"</td>"
    		+"<td><input type='button' class='delete'></td></tr>");
		}
    	return htm;
	};
	TrackModule.initExcel({url:"trackmanagement",findFrom:"FindData-form",mailClassName:"export-main",data:[
																			{title:"汽车编号",value:"carCode"},
																			{title:"地址",value:"address"},
																			{title:"经度",value:"lat"},
																			{title:"维度",value:"lng"},
																			{title:"日期",value:"tdate"},
																			{title:"类型",value:"type"},
																			{title:"demoName",value:"demoName"},
		                                                                    ]});
	TrackModule.init();

	window.Modules.setModle("track",TrackModule);

})(jQuery, window);
</script>
</body>
</html>