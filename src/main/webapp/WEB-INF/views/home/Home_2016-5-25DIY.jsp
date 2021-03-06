<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>  
<html>  
<head>  
<link type="text/css" rel="stylesheet" href="static/styles/baseHome.css?date=2016-3-29 16:14:05" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">  
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
	<style type="text/css">
			#site-topbar {
			  position: relative;
			  z-index: 30;
			  height: 60px;
			  font-family: "微软雅黑 Bold","微软雅黑";
   			 font-size: 18px;
   			 font-style: normal;
   			 font-weight: 700;
			  color: #fff;
			  background: #336699;
			}
			#container {
			  padding-top: 10px;
 			  margin-left: 10px;
			  width: 526px;
			}
			
			.containerLogin{
			  position: absolute;
  			  right: 2%;
			}
			#header-nav {
  				height: 36px;
 				
			  color: #fff;
 				 background: #009DDA;
 				 width: 100%;
			}
			 .nav-list {
  				float: left;
			    height: 36px;
			     width: 12%;
			    list-style-type: none;
			   text-align: center;
			    position: relative;
			    z-index: 10;
			    display: block;
			}
			.nav-item {
			  float: left;
			   width: 100%;
			   height: 100%;
			   display: block;
			    font-family: "微软雅黑 Bold","微软雅黑";
   			 font-size: 16px;
   			 font-style: normal;
   			 font-weight: 700;
			}
			.nav-item2 {
			  float: left;
			   width: 100%;
			   height: 100%;
			   display: block;
			    font-family: "微软雅黑 Bold","微软雅黑";
   			 font-size: 16px;
   			 font-style: normal;
   			 font-weight: 700;
			}
			.link {
			  display: block;
			  padding-top: 5px;
			  height:30px; 
			  color:#fff;
			  
			}
			.link1 {
			  display: block;
			  padding-top: 5px;
			  height:30px; 
			   color:black;
			}
			a{text-decoration:none;background:none;}
			a:hover{background:#CCCCCC; }
			*{list-style:none;margin:0;padding:0;}
			 .children-list {
				 float: left;
    			width: 100%;
   				 list-style-type: none;
  				  margin: 0;
   				  border: 1px solid #ccc;
			}
			.nav-item1 {
			   display: none;
   				 float: left;
   			 text-align: center;
   			 width: 100%;
   			   background: #fff;
   			    list-style-type: none;
  				  margin: 0;
   				  border-left: 1px solid #ccc;
   				  border-right: 1px solid #ccc;
   				  border-bottom: 1px solid #ccc;
			}
			
			.yemian{
			    background-color: white;
			    height: 86%;
			    line-height: 2;
			    position: absolute;
			    width: 98%;
			    z-index: 1;
			    margin:10px;
			}

		</style>
		<link href="<%=request.getContextPath()%>/static/jquery-validation/1.10.0/validate.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="static/styles/home.css" />
<link href="<%=request.getContextPath()%>/static/pagebutton/css/PageButton.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/static/popuwnd/css/PopuWnd.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/static/styles/addHome.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/static/diy_select/css/diy_select.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/static/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script  src="<%=request.getContextPath()%>/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/static/pagebutton/js/PageButton.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/static/popuwnd/js/PopuWnd.js" type="text/javascript"></script>
		<script  src="<%=request.getContextPath()%>/static/map/js/RMap5.js" type="text/javascript"></script>
		<script  src="<%=request.getContextPath()%>/static/jquery-json/jquery.json-2.4.min.js" type="text/javascript"></script>
		<script  src="<%=request.getContextPath()%>/static/jqueryMy/jqueryMy.js"  type="text/javascript" ></script>
		<script  src="<%=request.getContextPath()%>/static/diy_select/js/diy_select.js"  type="text/javascript" ></script>
		<script  src="<%=request.getContextPath()%>/static/diy_select/js/selectArea.js"  type="text/javascript" ></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/My-export/excelFrom.js"></script>
		<script  src="<%=request.getContextPath()%>/static/bootstrap/3.3.5/js/bootstrap.min.js"  type="text/javascript" ></script>
		
		<!-- DIV移动  -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
		<script  src="<%=request.getContextPath()%>/static/app/baseHome.js?d=3"  type="text/javascript" ></script>
		<title>南宁铁路抢险救援信息平台</title>
</head>  
<body>
		<input  type="hidden" id="it" dd='${menus}'>
		<div id="site-topbar">
			<div id="container">南宁铁路抢险救援信息平台</div>
			<div class="containerLogin">欢迎[<shiro:principal/>]，<a href="${pageContext.request.contextPath}/logout">退出</a></div>
		</div>
		
		<div id="header-main" >
			<div id="header-nav" class = "htm">
				
			</div>
		</div>
		
		<div class="yemian" id="newpage"></div>
		
<script type="text/javascript">
	function showHome(){
		var data = {};
		data =  eval("(" + $("#it").attr('dd') + ")");
		var nid = "";
		var htm = "<ul class='nav-list '>";
		var htm2 = "";
		var pareId = "";
		for ( var i = 0; i < data.length; i++) {
			if( i>0 && (data[i].type == "menu" || data[i].type == "nomenu")){
				htm += "</ul>"; 
				htm += "<ul class='nav-list' >";
			}
			
			if (data[i].type == "menu") {
				if(data[i].group != null){
					nid = data[i].group;
				}
				htm += "<li class='nav-item' tag="+nid+"><a class='link' href='javascript:void(0);'>"+data[i].name+"</a></li>";
				pareId = data[i].id;
			}else if(data[i].type == "nomenu"){
				htm += "<li class='nav-item2' ><a class='link' onclick='show1()' href='javascript:void(0);'>"+data[i].name+"</a></li>";
			}else if(data[i].type == "menu_ch"){
				htm += "<li class='nav-item1 "+nid+"' tag="+nid+"><a class='link1'  onclick=\"getHemls('"+data[i].url+"');\"  >"+data[i].name+"</a></li>"; 
			}
		}
		htm += "</ul>"; 
		$(".htm").html(htm);
	}
	showHome();
	
	$(".nav-item, .nav-item1").hover(function() {
		$("."+$(this).attr("tag")).show();
	}, function() {
		$("."+$(this).attr("tag")).hide();
	});
	
	
	
	
	 
		window.Htmls = {};//装载所有对象
		
		Htmls.htm = [];
		/**
		 * 返回module 实体
		 * key 唯一ID
		 */
		 Htmls.getModle = function(key){		
			/* for ( var i = 0; i < Htmls.htm.length; i++) {
				if (key == Htmls.htm[i].name) {
					return Htmls.htm[i].html;
				}
			} */
			return Htmls[key];
		};
		
		/**
		 * 全局实体变量保存，方便到时候的调用
		 * key 唯一ID，一半为实体名
		 * module 实体
		 */
		 Htmls.setModle = function(key,getHtml){
			 Htmls[key] = getHtml;
			/* var isFind = false;
			for ( var i = 0; i < Htmls.htm.length; i++) {
				if (key == Htmls.htm[i].name) {
					Htmls.htm[i].html = getHtml;
					isFind = true;
					break;
				}
			}
			//队列里没有，从新加一个
			if (!isFind) {
				getHtml.name = key;
				getHtml.html = getHtml;
				Htmls.htm.push(getHtml);
			} */
		};
	 

		function getHemls(url){
			var  md =  Htmls.getModle(url);
			//alert(md == null);
				if (md == null) {
					$.ajax({
						type : "GET",
						url : url,
						dataType : "html",
						success : $.proxy(function(obj){
							Htmls.setModle(url,obj);
							$("#newpage").html(obj);
						},this),
						error : function(msg) {
							alert("网络错误");
						}
					});
				}else{
					$("#newpage").html(md);
				} 
		}

	 
</script>
</body>
</html> 