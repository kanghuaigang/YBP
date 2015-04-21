<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.asc.state.DeptEnum"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<c:set var="dept" value="<%=DeptEnum.values()%>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link type="text/css" href="${ctx}/static/select2/select2.css" rel="stylesheet" />
<style type="text/css">
	.select2-search{
		position: absolute;
		top:0px;
		left:-1000px;
	}
</style>
<script>
	$(document).ready(function() {
		//为inputForm注册validate函数
		$("#inputForm").validate();
		$('#dept').select2({
			width:'225'
		});
		$('#state').select2({
			width:'225'
		});
	});
</script>
<form id="inputForm" name="context2" action="${ctx}/admin/user/update" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${user.id}"/>
	<fieldset>
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<input type="text" value="${user.loginName}" class="input-large" disabled="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<input type="text" id="name" name="userName" value="${user.userName}" class="input-large required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="plainPassword" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="plainPassword" name="plainPassword" class="input-large" placeholder="...Leave it blank if no change"/>
			</div>
		</div>
		<div class="control-group">
			<label for="confirmPassword" class="control-label">确认密码:</label>
			<div class="controls">
				<input type="password" id="confirmPassword" name="confirmPassword" class="input-large" equalTo="#plainPassword" />
			</div>
		</div>
		<div class="control-group">
			<label for="tel" class="control-label">手机号码:</label>
			<div class="controls">
				<input type="text" id="tel" name="tel" value="${user.tel}" class="input-large required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="dept" class="control-label">部门:</label>
			<div class="controls">
				<form:select id="dept" path="user.ascDept.id"
				 items="${dept}" 
				 itemValue="code" 
				 itemLabel="name" 
				 cssClass="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label for="state" class="control-label">状态:</label>
			<div class="controls">
				<form:select path="user.state" id="state" cssClass="form-control">
					<form:option value="1">启用</form:option>
					<form:option value="0">禁用</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册日期:</label>
			<div class="controls">
				<span class="help-inline" style="padding:5px 0px"><fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /></span>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript" src="${ctx}/static/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/select2/select2_locale_zh-CN.js"></script>
