<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    <style>
    	.main{
    		background-color: white;
    		width: 100%;
    		height: 100%;
    		color: black;
    	}
    	.inputName{
    		padding: 10px;
    	}
    	.space{
    		padding: 27px;
    	}
    	.leftLine{
    		width:50%;
    		position: absolute;
    	}
    	.submitButton_space{
    		position: absolute;
    		left: 30%;
    	}
    	.rightLine{
    		width:50%;
    		left:50%;
    		position: absolute;
    	}
    	.userInfo_label{
    		left: 30px;
		    position: absolute;
		    text-align: center;
		    width: 80px;
    	}
    	.userInfo_label1{
    		left: 100px;
		    position: relative;
		    text-align: center;
		    width: 80px;
    	}
    	.userInfo_input{
    		position: relative;
    		left: 100px;
    	}
    	.userInfo_select{
    		position: relative;
    		left: 100px;
    		width: 160px;
    	}
    	.submitButton{
		background-color: #66ccff;
	    color: white;
	    height: 36px;
	    line-height: 36px;
	    text-align: center;
	    width: 86px;
	    display: inline-block;
		}
		.submitButton1{
		background-color: #66ccff;
	    color: white;
	    height: 36px;
	    left: 30%;
   		line-height: 36px;
   		position: absolute;
	    text-align: center;
	    width: 86px;
	    display: inline-block;
		}
		.editBottom{
			left: 20%;
		    position: absolute;
		    top: 90%;
		}
        ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:200px;overflow-y:scroll;overflow-x:auto;}
    </style>

</head>
<body>
<input  type="hidden" id="departmentList" dd='${departmentList}'>
<input  type="hidden" id="roleList" dd='${roleList}'>
<input  type="hidden" id="user" dd='${user}'>
<input  type="hidden" id="op" dd='${op}'>
	<div class="main">
		<div class="leftLine">
			<div class="inputName">
				<label class="userInfo_label">用户姓名：</label>
				<input type="text" class="userInfo_input" id="username">
			</div>
			
			<div class="inputName">
				<label class="userInfo_label">职务:</label>
				<input type="text" class="userInfo_input" id="job">
			</div>
			
			<div class="inputName">
				<label class="userInfo_label">电话号码：</label>
				<input type="text" class="userInfo_input" id="phoneNumber">
			</div>
			
			<div class="inputName">
				<label class="userInfo_label">状态：</label>
				<input type="checkbox" class="userInfo_input st" id="available" > 
				<label class="userInfo_label1">启用</label>
				<input type="checkbox" class="userInfo_input st"  id="unavailable"> 
				<label class="userInfo_label1">禁用</label>
			</div>
			<div class = "submitButton1">
				<a class='linkEdit' onclick='submit()' href='javascript:void(0);'>确定</a>
     		</div>
		</div>
		
		<div class="rightLine">
			<div class="inputName">
				<label class="userInfo_label">部门：</label>
				<select id="department" class="userInfo_select department"  ></select>
			</div>
			
			<div class="inputName">
				<label class="userInfo_label">角色选择：</label>
				<select id="role" class="userInfo_select role"  ></select>
			</div>
			
			<div class="inputName"> 
				<label class="userInfo_label">初始密码：</label>
				<div class="userInfo_input" id="password" >123456</div>
			</div> 
			<div class="space" > 
			</div> 
			<div class = "submitButton1">
     			<a class='linkEdit' onclick='cancel()' href='javascript:void(0);'>取消</a>
     		</div>
		</div>
		
			 
	</div>
	
    <script  type="text/javascript">
    	var userID="";
    
    	function init(){//初始化
    		document.getElementById("available").checked=true;//初始化状态
    		 
    		//初始化部门下拉框
    		var data = {};
    		data =  eval("(" + $("#departmentList").attr('dd') + ")");
    		var html = "";
    		for ( var int = 0; int < data.length; int++) {
 	            html += ("<option value='"+data[int].id+"' >"+ data[int].name+"</option>");
    		}
    		$(".department").html(html);	
    		
    		//初始化角色下拉框
    		data =  eval("(" + $("#roleList").attr('dd') + ")");
    		html = "";
    		for ( var int = 0; int < data.length; int++) {
 	            html += ("<option value='"+data[int].id+"' >"+ data[int].name+"</option>");
    		}
    		$(".role").html(html);	
    	
    		
    		//初始化修改用户信息
    		if($("#user").attr('dd')!="{}"){
    			var user = {};
    			user = eval("(" + $("#user").attr('dd')+ ")");
    			userID = user.id;
    			$("#username").val(user.loginName);
    			$("#job").val(user.job);
    			$("#phoneNumber").val(user.tel);
    			if(user.available==0){
    				document.getElementById("unavailable").checked = true;
    				document.getElementById("available").checked = false;
    			}
    		 	$("#department").val(user.department.id);
    			$("#role").val(user.role.id);
    			
    		}
    	}
    	init();
    	
   		function cancel(){
   			$(".yemian1").hide();
   		}
   		
   		function submit(){
   			if($("#username").val()==""||$("#username").val()==null){
   				alert("请填写用户名");
   			}else{
   				var username = $("#username").val();
   				var job = $("#job").val();
   				var phoneNumber = $("#phoneNumber").val();
   				var available = "";
   				var department = $("#department").val();
   				var role = $("#role").val();
   				if(document.getElementById("available").checked){
   					available = 1;
   				}else{
   					available = 0;
   				}
   				var Url="";
   				if($("#op").attr("dd")=="新增"){
   					Url = "testdemomanagement/addUser?username=" + username + "&password=123456" +
   	   				"&job="+job +"&phoneNumber="+phoneNumber +"&available="+available +"&department="+department +"&role="+role ;
   					$.ajax({
   						type : "GET",
   						url : Url,
   						dataType : "html",
   						success : $.proxy(function(obj){
   							alert("添加成功");
   							$(".yemian1").hide();
   							htUser = obj;
   							$("#newpage").html(htUser);
   						},this),
   						error : function(msg) {
   							alert("网络错误");
   						}
   					});
   				}else if($("#op").attr("dd")=="修改"){
   					Url = "testdemomanagement/editUser?username=" + username + "&password=123456" +"&id="+userID+
   	   				"&job="+job +"&phoneNumber="+phoneNumber +"&available="+available +"&department="+department +"&role="+role ;
   					$.ajax({
   						type : "GET",
   						url : Url,
   						dataType : "html",
   						success : $.proxy(function(obj){
   							alert("修改成功");
   							$(".yemian1").hide();
   							htUser = obj;
   							$("#newpage").html(htUser);
   						},this),
   						error : function(msg) {
   							alert("网络错误");
   						}
   					});
   				}
   				
   			}
   		}
   		
   		
   		
   		$("#available").bind("click", function(e){
   			if(document.getElementById("available").checked && document.getElementById("unavailable").checked){
   				document.getElementById("unavailable").checked = false;
   			}
   			if(!document.getElementById("available").checked && !document.getElementById("unavailable").checked){
   				document.getElementById("unavailable").checked = true;
   			}
   		});
   		$("#unavailable").bind("click", function(e){
   			if(document.getElementById("available").checked && document.getElementById("unavailable").checked){
   				document.getElementById("available").checked = false;
   			}
   			if(!document.getElementById("available").checked && !document.getElementById("unavailable").checked){
   				document.getElementById("available").checked = true;
   			}
   		});
    </script>

</body>
</html>