<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <div class='item-content-header'>
	<div class='item-content-header-tab'>
		名字 <input class="sch-name " type="text" >
		名称2 <input class="sch-name2 " type="text" >
	</div>
	<div class='item-content-header-operate'>
		&nbsp;&nbsp;<input class="sch-p-demo" type="button" value=" 查询 ">
	</div>
</div> --%>
<style>
.dd{
 background: black none repeat scroll 0 0;
}
</style>
<div class='item-content-header'>
	<div class='item-content-header-main-x'>
		<div class='item-content-header-main'>
			<form class="FindData-form">
			<div class="header-bg-center"></div>
			<div class="header-bg-left header-bg-side"></div>
			<div class="header-bg-right header-bg-side"></div>
			<div class='item-content-header-tab'>
			名字 <input class="sch-name " name="name" type="text" >
			名称2 <input class="sch-name2 " name="name2" type="text" >
			<select class="test-select">
			<option class="dd">1</option>
			<option  class="tt">2</option>
			<option  class="ee">3</option>
			</select>
			</div>
		<div class='item-content-header-operate'>
			&nbsp;&nbsp;<input class="sch-p btn-find btn-base-style" type="button" value=" 查询 ">
		</div>
		</form>
		</div>
	</div>
</div>
<div class="item-but">
	<div class='item-result-operate-container'>
		<div class='item-result-operate-left'>
			<label class="Nolabel "><input class="modify-operate btn-base-style" type="button" value=" 修改 "></label>
			<label><input class="delete-operate btn-base-style" type="button" value=" 删除 "></label>
			<label><input class="add-demo btn-base-style" type="button" value=" 添加 "></label>
			<label><input class="export-Excel btn-base-style" type="button" value=" 导出 "></label>
			<label><input class="imPort-Excel btn-base-style" type="button" value=" 导入 "></label>
		</div>
		<div class='item-result-operate-right'>
		</div>
	</div>
</div>
<div class="export-main">
</div>

<%-- <div class="item-but">
	<div class='item-result-operate-container'>
		<div class='item-result-operate-left'>
			<label class="Nolabel "><input class="modify-operate" type="button" value=" 修改 "></label>
			<label><input class="delete-operate" type="button" value=" 删除 "></label>
			<label><input class="add-demo" type="button" value=" 添加 "></label>
		</div>
		<div class='item-result-operate-right'>
			
		</div>

	</div>
</div> --%>
<div class='item-content-append-container-y'>
	<div class='item-content-append-container-x'>
		<div class='item-content-append-container'>
			
			<div class='item-result '>
				<div class='item-result-show'>
				<table cellspacing="0" cellpadding="0" class="demo-table">
						<!-- 	<thead>
							<tr>
								<th class="th-short">编号</th>
								<th class="th-short"><label><input class="all-select" type="checkbox" ></label></th>
									<th>名字</th>
									<th>名称2</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>-->
						</tbody>
					</table>
				</div>
				<div class='item-result-operate'>
					<div class='item-result-pages' id='item-result-pages'></div>
				</div>
				
			</div>
			
		</div>
	</div>
</div>

<script type="text/javascript" src="./static/app/modules/demomanagement.js"></script>