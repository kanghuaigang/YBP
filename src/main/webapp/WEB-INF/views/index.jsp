<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>总体概况</title>
	<link type="text/css" href="${ctx}/static/select2/select2.css" rel="stylesheet" />
	<style type="text/css">
	.select2-search{
		position: absolute;
		top:0px;
		left:-1000px;
	}
</style>

<c:if test="${not empty message}">
	<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>
<form id="usersForm" name="usersForm" method="post" class="form-inline" style="margin-bottom:5px;">
	<input type="text" id="userName" name="userName" placeholder="用户名称" title="用户名称"/>
	<input type="text" name="loginName" placeholder="登录名称" title="登录名称"/>
	<input class="btn btn-primary" onclick="pageNav(0)" type="button" value="搜索"/>
	<input class="btn btn-primary" type="reset" value="重置"/>
	<a class="btn btn-primary" 
		data-toggle="modal" 
		data-target="#addUserDialog"
		data-backdrop="static">新增用户</a>
</form>
	
	<div id="ascTableList" ></div>
	<div id="ascTablePagebar"></div>

<div id="addUserDialog" class="modal hide fade" role="dialog" aria-labelledby="addUserLabel">
	 <div class="modal-header"  style="padding-top:0px;padding-bottom: 0px;">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h5 id="addUserLabel">用户管理</h5>
	</div>
	<div class="modal-body" style="padding-top:20px;padding-bottom: 0">
	<form id="addUserForm" name="addUserForm" action="${ctx}/index/register" method="post" class="form-horizontal">
		<fieldset>
			<div class="control-group">
				<label for="loginName" class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="userName" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="plainPassword" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword" class="input-large required" equalTo="#plainPassword"/>
				</div>
			</div>
			<div class="control-group">
				<label for="tel" class="control-label">手机号码:</label>
				<div class="controls">
					<input type="text" id="tel" name="tel" class="input-large required isMobile"/>
				</div>
			</div>
			<div class="control-group">
				<label for="state" class="control-label">状态:</label>
				<div class="controls">
					<select id="state" class="form-control" name="state">
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</div>
			</div>
		</fieldset>
	</form>	
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
		<a href="#" id="add_user_btn" class="btn btn-primary">提交</a>
	</div>
</div>

<script type="text/html" id='user_list_template'>
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>登录名</th>
				<th>注册时间</th>
			</tr>
		</thead>
		<tbody>
		<$for(var i=0;i<content.length;i++){$>
			<tr >
				<td><$=i+1$></td>
				<td><$=content[i]['loginName']$></td>
				<td><$=content[i]['userName']$></td>
				<td><$=content[i]['registerDate']$></td>
			</tr>
		<$}$>
		</tbody>
	</table>
</script>
<tags:pagination/>
<script>

$('#state').select2({width:'225px'});
	$("#addUserDialog").on("hidden", function() {
	    $(this).removeData("modal");
	});
	function pageNav(start){
		$.post("${ctx}/index/list",{start:start},function(data){
			var bt=baidu.template;
			var html=bt('user_list_template',data);
			document.getElementById('ascTableList').innerHTML=html;
			html=bt('pagebar_template',data);
			document.getElementById('ascTablePagebar').innerHTML=html;
		});
	}
	pageNav(0);
</script>