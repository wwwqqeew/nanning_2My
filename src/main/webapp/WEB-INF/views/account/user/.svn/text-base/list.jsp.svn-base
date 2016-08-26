<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    
 <style type="text/css">
		.yemian1{
		    color: #898989;
		    line-height: 2;
		    position: absolute;
		    width: 50%;
		    height:60%;
		     left: 25%;
		     display:none;
		}
	.main{
		width:100%;
	}
	.list{
		left: 240px;
	    position: absolute;
	    right: 0;
	}
	.search{
		border: 1px solid #aaa;
		position: absolute;
    	width: 227px;
    	top:0;
    	bottom:10px;
    	
	}
	.searchTop{
		background-color: #66ccff;
	    color: white;
	    font-size: 16px;
	    height: 38px;
	    line-height: 38px;
	    text-align: center;
	}
	
	.searchItem {
		color: black;
	    font-size: 16px;
	    padding-left: 20px;
	    padding-top: 10px;
	}
	.searchInput{
		width:185px;
		height:25px;
	}
	.searchSub{
		text-align: center;
		padding-top: 50px;
	}
	.searchButton{
		width:95px;
		height:25px;
	}
	.editButton{
		background-color: #66ccff;
	    color: white;
	    height: 36px;
	    line-height: 36px;
	    text-align: center;
	    width: 86px;
	    display: inline-block;
	}
	.editButton1{
		background-color: #66ccff;
	    color: white;
	    height: 36px;
	    line-height: 36px;
	    text-align: center;
	    width: 122px;
	    display: inline-block;
	}
	.linkEdit {
		display: block;
		color:#fff;
	  
	}
	a{text-decoration:none;background:none;}
	a:hover{background:#CCCCCC; }
</style>
</head>
<body>
<input  type="hidden" id="userList" dd='${userList}'>
<div class = "main">
 	<div class = "search">
 		<div class="searchTop">用户管理</div>
		<div class = "searchItem">
		用户名:
			<input type="text" class="searchInput">
		</div>
		<div class = "searchItem">
		部门:
			<input type="text" class="searchInput">
		</div>
		
		<div class = "searchSub">
			<input type="button" class="searchButton" value="查询">
		</div>
 	</div>
 	
 <div class = "list">
 <shiro:hasPermission name="user:create">
   <div class = "editButton">
     	<a class='linkEdit' onclick='add()' href='javascript:void(0);'>添加</a>
     </div>
  
</shiro:hasPermission>
 <shiro:hasPermission name="user:update">
     <div class = "editButton">
     	<a class='linkEdit' onclick='edit()' href='javascript:void(0);'>修改</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:delete">
       <div class = "editButton">
     	<a class='linkEdit' onclick='del()' href='javascript:void(0);'>删除</a>
     </div>
 </shiro:hasPermission>

 <shiro:hasPermission name="user:update">
     <div class = "editButton1">
     	<a class='linkEdit' onclick='reset()' href='javascript:void(0);'>初始化密码</a>
     </div>
 </shiro:hasPermission>
 	<table class="table" id="table">
	    <thead>
	        <tr>
	            <th>选择
	            	<!-- <label>
	            		选择<input class="all-select" type="checkbox">
					</label> -->
				</th>
	            <th>姓名</th>
	            <th>部门</th>
	            <th>职务</th>
	            <th>角色名称</th>
	            <th>电话</th>
	            <th>状态</th>
	        </tr>
	    </thead>
	    <tbody class = "htmUser"> 
	            <%-- <tr>
	                <td>
	               		<label>
							<input class="all-select" type="checkbox">
						</label>
	                </td>
	                <td>${user.realName}</td>
	                <td>${user.department}</td>
	                <td>${user.job}</td>
	                <td>${user.role.description}</td>
	                <td>${user.tel}</td>
	                <td>${user.state}</td> 
	            </tr> --%>
	    </tbody>
	</table>
 </div>

	<div class="yemian1" id="newpage1" ></div>
</div>

<script type="text/javascript">
	function init(){
		var data = {};
		data =  eval("(" + $("#userList").attr('dd') + ")");
		var htmUser = "";
		for ( var int = 0; int < data.length; int++) {
			htmUser += "<tr>";
			htmUser += "<td ><label><input class='all-select' type='checkbox' id="+data[int].id+"></label></td>";
			htmUser += " <td>"+ data[int].loginName +"</td>";
			htmUser += " <td>"+ data[int].department.name +"</td>";
			if(data[int].job==null){
				htmUser += " <td></td>";
			}else{
				htmUser += " <td>"+ data[int].job +"</td>";
			}
			htmUser += " <td>"+ data[int].role.name +"</td>";
			if(data[int].tel==null){
				htmUser += " <td></td>";
			}else{
				htmUser += " <td>"+ data[int].tel +"</td>";
			}
			if(data[int].available==1){
				htmUser += " <td>启用 </td>";
			}else{
				htmUser += " <td>禁用</td>";
			}
			
			htmUser += "</tr>";
		}
		
		$(".htmUser").html(htmUser);
	}
	init();

	var ht1 = "";
	function add(){
		var Url = "testdemomanagement/createUser";
		$.ajax({
			type : "GET",
			url : Url,
			dataType : "html",
			success : $.proxy(function(obj){
				ht1 = obj;
				$("#newpage1").html(ht1);
				$(".yemian1").show();
			},this),
			error : function(msg) {
				alert("网络错误");
			}
		});
	};
	
	function edit(){
		idList = [];
		$(".all-select").each(function() {
			if ($(this).is(':checked')) {
				var userID=$(this).attr("id");
				var Url = "testdemomanagement/editUserReq?id="+userID;
				$.ajax({
					type : "GET",
					url : Url,
					dataType : "html",
					success : $.proxy(function(obj){
						ht1 = obj;
						$("#newpage1").html(ht1);
						$(".yemian1").show();
					},this),
					error : function(msg) {
						alert("网络错误");
					}
				});
				return false;
			}
		});
	};
		
	function del(){
		var idList = [];
		var mark = 0;
		$(".all-select").each(function() {
			if ($(this).is(':checked')) {
				if($(this).attr("id")==1){
					mark = 1;
				}else{
					idList.push($(this).attr("id"));
				}
				
			}
		});
		if(mark == 1){
			alert("不允许删除超级管理员账号");
		}else if(idList==""){
			alert("请选择要删除的用户");
		}else if(confirm("确定删除?")){
			var Url = "testdemomanagement/deleteUser?id="+idList;
			$.ajax({
				type : "GET",
				url : Url,
				dataType : "html",
				success : $.proxy(function(obj){
					htUser = obj;
					$("#newpage").html(htUser);
				},this),
				error : function(msg) {
					alert("网络错误");
				}
			});
		}
		
		
		
	}
</script>
</body>
</html>