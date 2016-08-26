<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>监控终端管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    <script  src="<%=request.getContextPath()%>/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    
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
 		<div class="searchTop">监控终端管理</div>
		<div class = "searchItem">
		设备编号:
			<input type="text" class="searchInput">
		</div>
		<div class = "searchItem">
		添加人:
			<input type="text" class="searchInput">
		</div>
		<div class = "searchItem">
		添加时间:
			<input type="text" class="searchInput">
		</div>
		<div class = "searchItem">
		持有方:
			<select  class="searchInput">
				<option>工作人员</option>
			</select>
		</div>
		
		<div class = "searchSub">
			<input type="button" class="searchButton" value="查询">
		</div>
 	</div>
 	
 <div class = "list">
   <div class = "editButton">
     	<a class='linkEdit' onclick='add()' href='javascript:void(0);'>添加</a>
     </div>
  
     <%-- <a  href="${pageContext.request.contextPath}/testdemomanagement/${user.id}/update">修改</a> --%>
     <div class = "editButton">
     	<a class='linkEdit' onclick='edit()' href='javascript:void(0);'>修改</a>
     </div>

       <div class = "editButton">
     	<a class='linkEdit' onclick='del()' href='javascript:void(0);'>删除</a>
     </div>

     <div class = "editButton1">
     	<a class='linkEdit' onclick='reset()' href='javascript:void(0);'>导入文件</a>
     </div>
       <div class = "editButton1">
     	<a class='linkEdit' onclick='reset()' href='javascript:void(0);'>导出文件</a>
     </div>
       <div class = "editButton1">
     	<a class='linkEdit' onclick='reset()' href='javascript:void(0);'>下载模板</a>
     </div>
     
 	<table class="table">
	    <thead>
	        <tr>
	            <th>
	            	<label>
	            		选择<input class="all-select" type="checkbox">
					</label>
				</th>
	            <th>设备编号</th>
	            <th>设备持有人</th>
	            <th>设备所属单位</th>
	            <th>添加人</th>
	            <th>添加时间</th>
	        </tr>
	    </thead>
	    <tbody>
	        <%-- <c:forEach items="${userList}" var="user">
	            <tr>
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
	                <td>
	                	<label>
	            			<input class="all-select" type="checkbox"  value="${user.locked}">启用
						</label>
	                </td>
	            </tr>
	        </c:forEach> --%>
	    </tbody>
	</table>
 </div>

	<div class="yemian1" id="newpage1" ></div>
</div>

<script type="text/javascript">
	function show(){
		var data = {};
		data =  eval("(" + $("#userList").attr('dd') + ")");
	}
	show();

	var ht1 = "";
	function add(){
		if (ht1 == "") {
			var Url = "testdemomanagement/create";
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
		}else{
			$("#newpage1").html(ht1);
			$(".yemian1").show();
		} 
	};
		

</script>
</body>
</html>