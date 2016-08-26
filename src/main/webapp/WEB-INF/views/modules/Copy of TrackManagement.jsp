<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>轨迹管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    
 <style type="text/css">
	a{text-decoration:none;background:none;}
	a:hover{background:#CCCCCC; }
	.newTr{
	 background-color: #f4f4f4 !important;
	 background-image: linear-gradient(to bottom, #f8f8f8, #eeeeee) !important;
	 background-repeat: repeat-x !important;
	 }
	 .table-bordered-diy{
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-collapse: separate;
    border-color: #dddddd #dddddd #dddddd -moz-use-text-color;
    border-image: none;
    border-radius: 3px;
    border-style: solid solid solid none;
    border-width: 1px 1px 1px 0;
}
</style>
</head>
<body>

<!-- id="trackMain" 自定义 -->
<div class = "main" id="trackMain" >
 	<div class = "search-container">
 	<!-- <form class="FindData-form" id = "FindData-form">
 		<div class=" search-top">轨迹管理</div>
			<div class = "searchItem">汽车编号:<input type="text" class="search-input" name="carCode">这里识别用的是name 自定义</div>
			<div class = "searchItem">地址:<input type="text" class="search-input" name="address"></div>
		<div class = "searchSub-item">
			<input type="button" class="search-btn btn-base" value="查询">
		</div>
		</form> -->
		<form  class="FindData-form" id = "FindData-form" >
			<div style='width: 160px;margin-left: auto;margin-right: auto;'>
				<div class="form-group">
			    <label for="exampleInputEmail1">汽车编号</label>
			    <input type="text" class="form-control" id="exampleInputEmail1" name='Email' placeholder="汽车编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="text" class="form-control" id="exampleInputPassword1" name='Password' placeholder="Password">
			  </div>
			  <div class="form-group" style='text-align: center; position: relative;top: 10px;'>
			  
			   <button type="submit" style=" width: 100px;" class="btn btn-default search-btn">查询</button>
			  </div>
			</div>
		</form>
 	</div>
 	
 <div class = "item-list">
 <div class = "item-btn" style='line-height: 50px;height: 50px;'>
 <shiro:hasPermission name="user:create">
   <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/create">添加</a> --%>
   <div class = "btn-container">
     	<!-- <a class='linkEdit btn-base add-demo'  href='javascript:void(0);'>添加</a> -->
     	<button type="button" class="btn btn-info  add-demo">添加</button>
     </div>
  
</shiro:hasPermission>
 <shiro:hasPermission name="user:update">
     <%-- <a  href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/update">修改</a> --%>
     <div class = "btn-container">
     	<!-- <a class='linkEdit btn-base edit-operate'  href='javascript:void(0);'>修改</a> -->
     	<button type="button" class="btn btn-info edit-operate">修改</button>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:delete">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/delete">删除</a> --%>
       <div class = "btn-container">
     	<!-- <a class='linkEdit btn-base delete-operate' href='javascript:void(0);'>删除</a> -->
     	<button type="button" class="btn btn-info delete-operate">删除</button>
     </div>
 </shiro:hasPermission>
 
  <shiro:hasPermission name="user:show">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<!-- <a class='linkEdit btn-base show-operate' href='javascript:void(0);'>查看</a> -->
     	<button type="button" class="btn btn-info show-operate">查看</button>
     </div>
 </shiro:hasPermission>
 
   <shiro:hasPermission name="user:excel">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<!-- <a class='linkEdit btn-base  export-Excel' href='javascript:void(0);'>导出</a> -->
     	<button type="button" class="btn btn-info export-Excel">导出</button>
     </div>
 </shiro:hasPermission>

   <shiro:hasPermission name="user:excel">
    <%--  <a href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/changePassword">改密</a> --%>
     <div class = "btn-container">
     	<!-- <a class='linkEdit btn-base  imPort-Excel' href='javascript:void(0);'>导入</a> -->
     	<button type="button" class="btn btn-info imPort-Excel">导入</button>
     </div>
 </shiro:hasPermission>

 </div>
	<table cellspacing="0" cellpadding="0" class="demo-table table-bordered-diy" id="track-table"><!-- 这里识别用的是id， 自定义 -->
			<thead>
				<tr class='newTr'>
					<th class="th-short newTr"><label><input class="all-select" type="checkbox" ></label></th>
					<th class="th-short newTr">编号</th>
							<th class=" newTr">汽车编号</th>
							<th class=" newTr">地址</th>
							<th class=" newTr">经度</th>
							<th class=" newTr">维度</th>
							<th class=" newTr">日期</th>
							<th class=" newTr">类型</th>
					<th class=" newTr">操作</th>
				</tr>
			</thead>
			<tbody ></tbody>
		</table>
	<div class='item-result-operate'>
		<div class='item-result-pages newTr' id='item-result-pages'></div>
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