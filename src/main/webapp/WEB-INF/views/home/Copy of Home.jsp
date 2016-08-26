<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>警卫任务系统后台管理系统</title>
<link rel="icon" href="static/images/favicon.ico">
<link href="./static/jquery-validation/1.10.0/validate.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="static/styles/home.css" />
<link href="static/popuwnd/css/PopuWnd.css" rel="stylesheet" type="text/css">
<link href="static/pagebutton/css/PageButton.css" rel="stylesheet" type="text/css">
<link href="static/styles/addHome.css" rel="stylesheet" type="text/css">
<!-- 日记图表 -->

<script  src="static/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script type="text/javascript" src="static/map/js/RMap5.js"></script>
<script type="text/javascript" src="./static/jquery-json/jquery.json-2.4.min.js"></script>
<script src="static/pagebutton/js/PageButton.js" type="text/javascript"></script>
<script src="static/popuwnd/js/PopuWnd.js" type="text/javascript"></script>
<script src="static/jquery-validation/checkOnKepDown.js" type="text/javascript"></script>
<script type="text/javascript" src="static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="static/jquery-validation/checkAfter.js"></script>

<script type="text/javascript" src="static/jqueryMy/jqueryMy.js"></script>
<script type="text/javascript" src="static/My-export/excelFrom.js"></script>
<!-- DIV移动  -->
<script type="text/javascript" src="static/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>

</head>
<body>
	<div class='header'>
		<div class='header-system-title font-18'></div>
		<div class="header-welcome-content font-12-0">
			欢迎您:<shiro:principal/>:<font class='header-username font-14-1'></font>&nbsp;&nbsp;<a class="font-14-1 changepw" href="#">【修改密码】</a>&nbsp;&nbsp;<a href="logout" class="font-14-1">【退出登录】</a>
		</div>
		<div class="header-notice-content">
			<a class="pad-lost"></a>&nbsp;<a class="order-notice"></a>&nbsp;<a class="order-message"></a>
		</div>
	</div>
	<div class='content'>
		<div class='left'>
			<div class='navs'>
				<div class='navs-content'>
					<div class='navs-content-container'>
						<div class='operate-nav'>
							<div class='operate-nav-title'>
								<div class='operate-nav-title-contaner'>后台操作</div>
							</div>
							<div class='operate-nav-main font-14'>
								
							</div>
						</div>
						<!-- 
						<div class='search-nav menu-search-nav search-nav-hidden'>
							<div class='search-nav-title'>
								<div class='search-nav-title-container'>菜单查询</div>
							</div>
							<div class='search-nav-main font-14'>
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>分类</div>
										<div class='search-nav-item-input'>
											<select  class="food-mainCategories">
												<option></option>
												<option>招牌</option>
												<option>热菜</option>
												<option>冷菜</option>
												<option>海鲜</option>
												<option>时蔬</option>
												<option>甜品</option>
												<option>水果</option>
												<option>酒饮</option>
												<option>其它</option>
											</select>
										</div>
									</div>
								</div>
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>编号</div>
										<div class='search-nav-item-input'>
											<input class="food-number">
										</div>
									</div>
								</div>
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>关键字</div>
										<div class='search-nav-item-input'>
											<input class="food-name">
										</div>
									</div>
								</div>
								<div class='search-nav-item search-nav-item-operate'>
									<div class='search-nav-item-container'>
										<input type="button" class="find-foods" value="查&nbsp;&nbsp;询">
									</div>
								</div>
							</div>
						</div>
						
						<div class='search-nav order-search-nav search-nav-hidden'>
							<div class='search-nav-title'>
								<div class='search-nav-title-container'>订单查询</div>
							</div>
							<div class='search-nav-main font-14'>
								
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>状态</div>
										<div class='search-nav-item-input'>
											<select class="order-state-nav-select">
												<option value=''>全部</option>
												<option value='1'>预定</option>
												<option value='2'>新订单</option>
												<option value='3'>未结账</option>
												<option value='4'>已结账</option>
											</select>
										</div>
									</div>
								</div>
								
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>座位号</div>
										<div class='search-nav-item-input'>
											<select class="order-board-nav-select">
												<option value=''>全部</option>
											</select>
										</div>
									</div>
								</div>
								<div class='search-nav-item'  style="height:56px;!importemt;">
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>起始时间
											<input class="order-orderDate-start-nav"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
										</div>
									</div>
								</div>
								<div class='search-nav-item'  style="height:56px;!importemt;">
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>结束时间
											<input class="order-orderDate-end-nav"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
										</div>
									</div>
								</div>
								<div class='search-nav-item search-nav-item-operate'>
									<div class='search-nav-item-container'>
										<input type="button" class="find-order" value="查&nbsp;&nbsp;询">
									</div>
								</div>
							</div>
						</div>
						
						<div class='search-nav user-search-nav search-nav-hidden'>
							<div class='search-nav-title'>
								<div class='search-nav-title-container'>用户</div>
							</div>
							<div class='search-nav-main font-14'>
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>角色</div>
										<div class='search-nav-item-input'>
											<select  class="user-role">
												<option></option>
											</select>
										</div>
									</div>
								</div>
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>编号</div>
										<div class='search-nav-item-input'>
											<input class="user-number">
										</div>
									</div>
								</div>
								<div class='search-nav-item'>
									<div class='search-nav-item-container'>
										<div class='search-nav-item-tab'>用户名</div>
										<div class='search-nav-item-input'>
											<input class="user-name">
										</div>
									</div>
								</div>
								<div class='search-nav-item search-nav-item-operate'>
									<div class='search-nav-item-container'>
										<input type="button" class="find-user" value="查&nbsp;&nbsp;询">
									</div>
								</div>
							</div>
						</div>
						 -->
					</div>
				</div>
			</div>
		</div>
		<div class='right'>
		<div class="main-content"> </div>
			<div class='main-content-y'>
				<div class='main-content-x'>
					<div class='main-content-container'>
						<div class='item-content item-content-template item-content-hidden'>
							<div class='item-content-header'>
								<div class='item-content-header-tab'></div>
								<div class='item-content-header-operate'></div>
							</div>
							<div class='item-content-append-container-y'>
								<div class='item-content-append-container-x'>
									<div class='item-content-append-container'>
										<div class='item-content-loading'>正在加载...</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
						<div class='main-content-map hiddentMap' id="map">
						<div class="main-content-top"></div>
						<div class="main-content-bottom"></div>
						<div class="main-content-left"></div>
						<div class="main-content-right"></div>
						<div class="main-content-top-left-corner"></div>
						<div class="main-content-top-right-corner"></div>
						<div class="main-content-bottom-left-corner"></div>
						<div class="main-content-bottom-right-corner"></div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class='footer'>技术支持：公安部交通管理科学研究所&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<script type="text/javascript" src="static/app/home.js"></script>
</body>
</html>